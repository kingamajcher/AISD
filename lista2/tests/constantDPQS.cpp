#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>
#include <fstream>
#include <random>

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
    if (!sorted) {
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


void dualPivotQuickSort(vector<int> &arr) {
    vector<int> initialArray = arr;

    sort(arr, 0, arr.size() - 1);
    check(arr);
}

vector<int> generateRandomKeys(int n) {
    vector<int> keys;
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> dis(0, 2 * n - 1);

    for (int i = 0; i < n; ++i) {
        keys.push_back(dis(gen));
    }

    return keys;
}

void constantExperiment() {
    comparisons = 0;
    double k = 0;
    for (int i = 10; i <= 1000; i = i + 10) {
        comparisons = 0;
        vector<int> arr = generateRandomKeys(i);
        dualPivotQuickSort(arr);
        k += comparisons/(i*log(i));
    }
    cout << "constant =  " << k/100 << endl;
}

int main() {
    constantExperiment();

    return 0;
}
