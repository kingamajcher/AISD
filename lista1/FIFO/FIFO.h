#ifndef FIFO_H
#define FIFO_H

class FIFO {
private:
    int *queue;
    int front;
    int rear;
    int queueSize;

public:
    FIFO(int size);
    ~FIFO();
    bool isEmpty();
    bool isFull();
    void enqueue(int value);
    int dequeue();
};

#endif