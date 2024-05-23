#include <iostream>
#include <vector>
#include <sstream>
#include <cmath>

using namespace std;

int comparisons = 0;
int swaps = 0;
int n = 0;

//compares whether a > b
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

int partition(vector<int>& arr, int start, int end)
{
    int pivot = arr[(start+end)/2];
    int i = start;
    int j = end;

    while (true) {
        while (compare(pivot, arr[i])) i++;
        while (compare(arr[j], pivot)) j--;
        if (i >= j) return j;
        swap(&arr[i], &arr[j]);
        i++;
        j--;
    }
}

void sort(vector<int>& arr, int start, int end) {
    if (start < end) {
        print(arr);
        int p = partition(arr, start, end);
        sort(arr, start, p);
        sort(arr, p + 1, end);
    }
}

void quickSort(vector<int>& arr) {
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
    quickSort(data);

    return 0;
}
