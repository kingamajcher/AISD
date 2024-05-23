#ifndef LIFO_H
#define LIFO_H

class LIFO {
private:
    int *stack;
    int top;
    int stackSize;

public:
    LIFO(int size);
    ~LIFO();
    bool isEmpty();
    bool isFull();
    void push(int value);
    int pop();
};

#endif