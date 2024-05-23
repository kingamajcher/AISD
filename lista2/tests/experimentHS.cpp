#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>

using namespace std;

int comparisons = 0;
int swaps = 0;
int n = 0;

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

void swapIS(int* a, int* b) {
    *a = *b;
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

void print(vector<int>& arr) {
    if (n < 40) {
        for (int value : arr) {
            if (value < 10) {
                cout << " " << value << " ";
            }
            else {
                cout << value << " ";
            }
        }
    cout << endl;
    }
}

int partition(vector<int>& arr, int start, int end)
{
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

void insertionSort(vector<int>& arr, int start, int end) {
    for (int i = start + 1; i <= end; ++i) {
        int key = arr[i];
        int j = i - 1;

        while (j >= start && compare(arr[j], key)) {
            swapIS(&arr[j + 1], &arr[j]);
            j = j - 1;
        }
        swapIS(&arr[j + 1], &key);
        swaps--;
    }
}

void sort(vector<int>& arr, int start, int end, int k) {
    print(arr);
    if (start >= end) {
        return;
    }
    int p = partition(arr, start, end);
    print(arr);
    if (end - start <= k) {
        insertionSort(arr, start, p);
        insertionSort(arr, p + 1, end);
    }
    else {
        sort(arr, start, p, k);
        sort(arr, p + 1, end, k);
    }
}

void hybridSort(vector<int>& arr, int k) {
    vector<int> initialArray = arr;

    if (n < 40) {
        cout << "INITIAL ARRAY: " << endl;
        print(initialArray);
        cout << endl;
    }
    sort(arr, 0, arr.size() - 1, k); // Corrected the end index
    check(arr);
    if (n < 40) {
        cout << endl << "INITIAL ARRAY: " << endl;
        print(initialArray);
        cout << "SORTED ARRAY: " << endl;
        print(arr);
    }
    cout << "comparisons: " << comparisons << endl;
    cout << "swaps: " << swaps << endl;
    //cout << swaps+comparisons << endl;
}

int main() {
    string input;
    getline(cin, input);
    stringstream ss(input);
    vector<int> initialData;
    vector<int> data;
    int temp;
    int leastComparisons = 10000000;
    int kSwaps = 0;
    int kComparisons = 0;
    int leastSwaps = 10000000;
    
    while (ss >> temp) {
        data.push_back(temp);
    }
    initialData = data;
    n = data.size();
    //int k = 3;
    for (int k = 0; k < n/2; k++) {
        data = initialData;
        hybridSort(data, k);
        if (comparisons < leastComparisons) {
            leastComparisons = comparisons;
            kComparisons = k;
        }
        if (swaps < leastSwaps) {
            leastSwaps = swaps;
            kSwaps = k;
        }
        comparisons = 0;
        swaps = 0;
    }
    //hybridSort(data, k);
    cout << "best k for swaps: " << kSwaps << endl;
    cout << "best k for comps: " << kComparisons << endl;
    cout << "average best k:   " << (kSwaps+kComparisons)/2 << endl;

    return 0;
}