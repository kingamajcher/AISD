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
        //cout << "Array sorted successfully!" << endl;
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
  //print(arr);
}

void sort(vector<int> &arr, int l, int r) {
  if (l < r) {

    int m = l + (r - l) / 2;

    sort(arr, l, m);
    sort(arr, m + 1, r);

    merge(arr, l, m, r);
  }
}

void mergeSort(vector<int>& arr, int &comp, int &swp) {
    vector<int> initialArray = arr;

    sort(arr, 0, arr.size() - 1);
    check(arr);

    //cout << "comparisons: " << comparisons << endl;
    comp = comparisons;
    //cout << "swaps: " << swaps << endl;
    swp = swaps;

    comparisons = 0;
    swaps = 0;
}

int main() {
    ifstream inFile("n50k1data.txt");
    ofstream outFile("MSresults-n50k1.txt");
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
        for (int k = 1; k <= 1; k++) {
            getline(inFile, line);
            istringstream iss(line);
        
            vector<int> data;

            string token;
            while (iss >> token) {
                data.push_back(stoi(token));
            }
            
            int comparisons = 0;
            int swaps = 0;
            mergeSort(data, comparisons, swaps);
            
            outFile << n << " " << comparisons << " " << swaps << endl;
        }
    }
    
    inFile.close();
    outFile.close();

    return 0;
}
