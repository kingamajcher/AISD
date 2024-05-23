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

void merge(vector<int> &arr, int p, int q, int r) {
  
  int n1 = q - p + 1;
  int n2 = r - q;

  int L[n1], M[n2];

  for (int i = 0; i < n1; i++)
    L[i] = arr[p + i];
  for (int j = 0; j < n2; j++)
    M[j] = arr[q + 1 + j];

  int i, j, k;
  i = 0;
  j = 0;
  k = p;

  while (i < n1 && j < n2) {
    if (!compare(L[i], M[j])) {
        arr[k] = L[i];
        i++;
    } else {
        swap(&arr[k], &M[j]);
        j++;
    }
    k++;
  }

  while (i < n1) {
    arr[k] = L[i];
    i++;
    k++;
  }

  while (j < n2) {
    arr[k] = M[j];
    j++;
    k++;
  }
  print(arr);
}

void sort(vector<int> &arr, int l, int r) {
  if (l < r) {

    int m = l + (r - l) / 2;

    sort(arr, l, m);
    sort(arr, m + 1, r);

    merge(arr, l, m, r);
  }
}

void mergeSort(vector<int>& arr) {
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
    mergeSort(data);

    return 0;
}
