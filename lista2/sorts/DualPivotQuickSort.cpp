#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>

using namespace std;

int comparisons = 0;
int swaps = 0;
int n = 0;

//comparing whether a > b
bool compare(int a, int b) {
    comparisons++;
    return a > b;
}

void swap(int* a, int* b) {
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

void partition(vector<int> &arr, int low, int high, int &leftPivot, int &rightPivot) {
    if (arr[low] > arr[high])
        swap(&arr[low], &arr[high]);

    int j = low + 1;
    int g = high - 1, k = low + 1, p = arr[low], q = arr[high];

    while (k <= g) {
        if (compare(p, arr[k])) {
            swap(&arr[k], &arr[j]);
            j++;
        } else if (!(compare(q, arr[k]))) {
            while (compare(arr[g], q) && k < g)
                g--;
            swap(&arr[k], &arr[g]);
            g--;

            if (compare(p, arr[k])) {
                swap(&arr[k], &arr[j]);
                j++;
            }
        }
        k++;
    }
    j--;
    g++;

    if (low != j){
        swap(&arr[low], &arr[j]);
    }

    if (high != g){
        swap(&arr[high], &arr[g]);
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


void dualPivotQuickSort(vector<int>& arr) {
    vector<int> initialArray = arr;

    if (n < 40) {
        cout << "INITIAL ARRAY: " << endl;
        print(initialArray);
        cout << endl;
    }
    sort(arr, 0, arr.size() - 1);
    check(arr);
    if (n < 40) {
        cout << endl << "INITIAL ARRAY: " << endl;
        print(initialArray);
        cout << "SORTED ARRAY: " << endl;
        print(arr);
    }
    cout << "comparisons: " << comparisons << endl;
    cout << "swaps: " << swaps << endl;
}

int main() {
    string input;
    getline(cin, input);
    stringstream ss(input);
    vector<int> data;
    int temp;
    while (ss >> temp) {
        data.push_back(temp);
    }
    n = data.size();
    dualPivotQuickSort(data);

    return 0;
}
