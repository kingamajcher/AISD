#include <iostream>
#include <vector>

using namespace std;

vector<int> generateSortedDescending(int n) {
    vector<int> sortedDesc(n);
    for (int i = 0; i < n; ++i) {
        sortedDesc[i] = n - i;
    }
    return sortedDesc;
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        cerr << "Usage: " << argv[0] << " <array_size>" << endl;
        return 1;
    }

    int n = stoi(argv[1]);
    if (n <= 0) {
        cerr << "Array size must be a positive integer." << endl;
        return 1;
    }

    vector<int> sortedDescending = generateSortedDescending(n);
    for (int key : sortedDescending) {
        cout << key << " ";
    }
    cout << endl;

    return 0;
}
