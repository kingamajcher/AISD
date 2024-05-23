#include <iostream>
#include <vector>

using namespace std;

vector<int> generateSortedAscending(int n) {
    vector<int> sortedAsc(n);
    for (int i = 0; i < n; ++i) {
        sortedAsc[i] = i + 1;
    }
    return sortedAsc;
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

    vector<int> sortedAscending = generateSortedAscending(n);
    for (int key : sortedAscending) {
        cout << key << " ";
    }
    cout << endl;

    return 0;
}
