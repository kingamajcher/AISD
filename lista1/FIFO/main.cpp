#include <iostream>
#include "FIFO.h"

using namespace std;

int main() {
    int size = 50;
    FIFO Queue(size);


    for (int i = 1; i <= size; i++) {
        Queue.enqueue(i);
    }

    for (int i = 1; i <= size; i++) {
        cout << Queue.dequeue() << endl;
    }

    return 0;
}