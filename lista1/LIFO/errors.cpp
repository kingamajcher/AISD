#include <iostream>
#include "LIFO.h"

using namespace std;

int main() {
    int size = 5;
    LIFO Stack(size);
    
    cout << "Stack of size " << size << endl;
    cout << "Testing for stack overflow. There should be 2 overflows." << endl;


    for (int i = 1; i <= size+2; i++) {
        Stack.push(i);
    }

    cout << "Testing for empty stack. After popping everything there should be 2 commands empty stack." << endl;

    for (int i = 1; i <= size+2; i++) {
        cout << Stack.pop() << endl;
    }

    return 0;
}