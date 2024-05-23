#include "LinkedList.h"
#include <iostream>
#include <random>

using namespace std;

int main() {
    LinkedList l1;

    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<int> dist1(0, 100000);
    uniform_int_distribution<int> dist2(0, 9999);

    int T[10000];

    int comparisons1 = 0;
    int comparisons2 = 0;

    for (int i = 0; i < 10000; i++) {
        T[i] = dist1(gen);
        l1.insert(T[i]);
    }

    l1.search(123);

    for (int i = 0; i < 1000; i++) {
        int random_index = dist2(gen);
        comparisons1 += l1.search(T[random_index]);
        comparisons2 += l1.search(dist1(gen));
    }
    
    double average_comp1 = comparisons1 / 1000;
    double average_comp2 = comparisons2 / 1000;

    cout << "Average cost of a 1000 searches of numbers that are in array T: " << average_comp1 << endl;

    cout << "Average cost of a 1000 searches of numbers that are in range I: " << average_comp2 << endl;

    return 0;
}
