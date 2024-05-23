#include "FIFO.h"
#include <iostream>

using namespace std;

FIFO::FIFO(int size) {
    queueSize = size;
    front = -1;
    rear = -1;
    queue = new int[queueSize];
}

FIFO::~FIFO() {
    delete[] queue;
}

bool FIFO::isEmpty() {
    return (front == -1 && rear == -1);
}

bool FIFO::isFull() {
    return (rear == queueSize - 1);
}

void FIFO::enqueue(int value) {
    if (isFull()) {
        cout << "Queue is full" << endl;
        return;
    }
    else {
        if (front == -1) {
            front = 0;
        }
        rear++;
        queue[rear] = value;
        cout << "Inserted " << value << endl;
    }
}

int FIFO::dequeue() {
    if (isEmpty()) {
        cout << "Queue is empty" << endl;
        return -1;
    }
    else {
        int dequeuedValue = queue[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        else {
            front++;
        }
        cout << "Deleted ";
        return dequeuedValue;
    }
}
