#include <iostream>
#include "LIFO.h"

using namespace std;

int main() {
    int size = 50;
    LIFO Stack(size);


    for (int i = 1; i <= size; i++) {
        Stack.push(i);
    }

    for (int i = 1; i <= size; i++) {
        cout << Stack.pop() << endl;
    }

    return 0;
}