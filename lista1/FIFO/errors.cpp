#include <iostream>
#include "FIFO.h"

using namespace std;

int main() {
    int size = 5;
    FIFO Queue(size);
    
    cout << "Queue of size " << size << endl;
    cout << "Testing for queue overflow. There should be 2 overflows." << endl;


    for (int i = 1; i <= size+2; i++) {
        Queue.enqueue(i);
    }

    cout << "Testing for empty queue. After deleting everything there should be 2 commands empty queue." << endl;

    for (int i = 1; i <= size+2; i++) {
        cout << Queue.dequeue() << endl;
    }

    return 0;
}