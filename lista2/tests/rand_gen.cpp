#include <iostream>
#include <vector>
#include <random>
#include <fstream>

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
    ofstream outFile("n50000k100data.txt");

        if (!outFile.is_open()) {
        cout << "Error opening file!" << endl;
        return 1;
    }
    for (int n = 1000; n <= 50000; n = n + 1000) {
        for (int k = 1; k <= 100; k++) {
            vector<int> randomKeys = generateRandomKeys(n);
            for (int key : randomKeys) {
                outFile << key << " ";
            }
            outFile << endl;
        }
    }

    return 0;
}
