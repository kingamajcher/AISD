#include <iostream>
#include <vector>
#include <sstream>
#include <fstream>

using namespace std;

int comparisons = 0;
int swaps = 0;

bool compare(int a, int b) {
    comparisons++;
    return a > b;
}

void swapIS(int* a, int* b) {
    *a = *b;
    swaps++;
}

void check(vector<int> arr) {
    int n = arr.size();
    int sorted = false;
    for (int i = 0; i < n; i++) {
        if (arr[i] > arr[i+1]) {
            sorted = false;
        }
    }
    sorted = true;
    if (sorted) {
        cout << "Array sorted successfully!" << endl;
    }
    else {
        cout << "Array sorting failed :(" << endl;
    }
}


void insertionSort(vector<int> &arr, int &comp, int &swp) {
    int n = arr.size();
    vector<int> initialArray = arr;

    for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && compare(arr[j], key)) {
            swapIS(&arr[j+1], &arr[j]);
            j = j - 1;
        }
        arr[j+1] = key;
    }
    check(arr);

    cout << "comparisons: " << comparisons << endl;
    comp = comparisons;
    cout << "swaps: " << swaps << endl;
    swp = swaps;

    comparisons = 0;
    swaps = 0;
}

int main() {
    ifstream inFile("n50k100data.txt");
    ofstream outFile("ISresults-n50k100.txt");
    string line;
    
    if (!inFile.is_open()) {
        cout << "Error opening input file!" << endl;
        return 1;
    }
    if (!outFile.is_open()) {
        cout << "Error opening output file!" << endl;
        return 1;
    }
    
    for (int n = 10; n <= 50; n += 10) {
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
            insertionSort(data, comparisons, swaps);
            
            outFile << n << " " << comparisons << " " << swaps << endl;
        }
    }
    
    inFile.close();
    outFile.close();

    return 0;
}
