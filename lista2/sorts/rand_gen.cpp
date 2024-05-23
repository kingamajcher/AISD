#include <iostream>
#include <vector>
#include <random>

using namespace std;

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

    vector<int> randomKeys = generateRandomKeys(n);
    for (int key : randomKeys) {
        cout << key << " ";
    }
    cout << endl;

    return 0;
}
