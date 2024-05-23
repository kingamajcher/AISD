#include <iostream>
#include <vector>
#include <sstream>

using namespace std;

int comparisons = 0;
int swaps = 0;

bool compare(int a, int b) {
    comparisons++;
    return a > b;
}

void swap(int* a, int* b) {
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

void print(vector<int> &arr) {
    if (arr.size() < 40) {
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


void insertionSort(vector<int> &arr) {
    int n = arr.size();
    vector<int> initialArray = arr;

    if (n < 40){
        cout << "INITIAL ARRAY: " << endl;
        print(initialArray);
        cout << endl;
    }

    for (int i = 1; i < n; ++i) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && compare(arr[j], key)) {
            swap(&arr[j+1], &arr[j]);
            j = j - 1;
        }
        arr[j+1] = key;
        print(arr);
    }
    check(arr);
    if (n < 40){
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

    insertionSort(data);

    return 0;
}
