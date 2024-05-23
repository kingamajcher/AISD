#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>
#include <fstream>

using namespace std;

int comparisons = 0;
int swaps = 0;
int n = 0;

//compares whether a > b
bool compare(int a, int b) {
    comparisons++;
    return a > b;
}

void swapQS(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
    swaps++;
}

void check(vector<int> arr) {
    bool sorted = true;
    for (int i = 0; i < n - 1; i++) {
        if (arr[i] > arr[i + 1]) {
            sorted = false;
            break;
        }
    }
    if (sorted) {
        cout << "Array sorted successfully!" << endl;
    }
    else {
        cout << "Array sorting failed :(" << endl;
    }
}

int partition(vector<int>& arr, int start, int end) {
    int pivot = arr[(start+end)/2];
    int i = start;
    int j = end;

    while (true) {
        while (compare(pivot, arr[i])) i++;
        while (compare(arr[j], pivot)) j--;
        if (i >= j) return j;
        swapQS(&arr[i], &arr[j]);
        i++;
        j--;
    }
}

void sort(vector<int>& arr, int start, int end) {
    if (start < end) {
        int p = partition(arr, start, end);
        sort(arr, start, p);
        sort(arr, p + 1, end);
    }
}

void quickSort(vector<int> &arr, int &comp, int &swp) {
    vector<int> initialArray = arr;

    sort(arr, 0, arr.size() - 1);
    check(arr);

    cout << "comparisons: " << comparisons << endl;
    comp = comparisons;
    cout << "swaps: " << swaps << endl;
    swp = swaps;

    comparisons = 0;
    swaps = 0;
}

int main() {
    ifstream inFile("n50000k100data.txt");
    ofstream outFile("QSresults-n50000k100.txt");
    string line;
    
    if (!inFile.is_open()) {
        cout << "Error opening input file!" << endl;
        return 1;
    }
    if (!outFile.is_open()) {
        cout << "Error opening output file!" << endl;
        return 1;
    }
    
    for (int n = 1000; n <= 50000; n += 1000) {
        for (int k = 1; k <= 100; k++) {
            getline(inFile, line);
            istringstream iss(line);
        
            vector<int> data;

            string token;
            while (iss >> token) {
                data.push_back(stoi(token));
            }
            
            int comparisons = 0;
            int swaps = 0;
            quickSort(data, comparisons, swaps);
            
            outFile << n << " " << comparisons << " " << swaps << endl;
        }
    }
    
    inFile.close();
    outFile.close();

    return 0;
}
