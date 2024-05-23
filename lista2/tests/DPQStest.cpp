#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>
#include <fstream>

using namespace std;

int comparisons = 0;
int swaps = 0;
int n = 0;

//comparing whether a > b
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

void partition(vector<int> &arr, int low, int high, int &leftPivot, int &rightPivot) {
    if (arr[low] > arr[high])
        swapQS(&arr[low], &arr[high]);

    int j = low + 1;
    int g = high - 1, k = low + 1, p = arr[low], q = arr[high];

    while (k <= g) {
        if (compare(p, arr[k])) {
            swapQS(&arr[k], &arr[j]);
            j++;
        } else if (!(compare(q, arr[k]))) {
            while (compare(arr[g], q) && k < g)
                g--;
            swapQS(&arr[k], &arr[g]);
            g--;

            if (compare(p, arr[k])) {
                swapQS(&arr[k], &arr[j]);
                j++;
            }
        }
        k++;
    }
    j--;
    g++;

    if (low != j){
        swapQS(&arr[low], &arr[j]);
    }

    if (high != g){
        swapQS(&arr[high], &arr[g]);
    }
    
    leftPivot = j;
    rightPivot = g;
}


void sort(vector<int> &arr, int low, int high) {
    if (low < high) {
        int leftPivot, rightPivot;
        partition(arr, low, high, leftPivot, rightPivot);

        sort(arr, low, leftPivot - 1);
        sort(arr, leftPivot + 1, rightPivot - 1);
        sort(arr, rightPivot + 1, high);
    }
}


void dualPivotQuickSort(vector<int> &arr, int &comp, int &swp) {
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
    ofstream outFile("DPQSresults-n50000k100.txt");
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
            dualPivotQuickSort(data, comparisons, swaps);
            
            outFile << n << " " << comparisons << " " << swaps << endl;
        }
    }
    
    inFile.close();
    outFile.close();

    return 0;
}
