#include "LIFO.h"
#include <iostream>

using namespace std;

LIFO::LIFO(int size) {
    stackSize = size;
    top = -1;
    stack = new int[stackSize];
}

LIFO::~LIFO() {
    delete[] stack;
}

bool LIFO::isEmpty() {
    if (top == -1) {
        return true;
    }
    else {
        return false;
    }
}

bool LIFO::isFull() {
    if (top == stackSize - 1) {
        return true;
    }
    else {
        return false;
    }
}

void LIFO::push(int value) {
    if (isFull()) {
        cout << "Stack Overflow" << endl;
        return;
    }
    else {
        top++;
        stack[top] = value;
        cout << "Pushed " << value << endl;
    }
}

int LIFO::pop() {
    if (isEmpty()) {
        cout << "Stack Underflow" << endl;
        return -1;
    }
    else {
        top--;
        cout << "Popped ";
        return stack[top+1];
    }
}