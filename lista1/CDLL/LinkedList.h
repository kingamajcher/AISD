#ifndef LINKEDLIST_H
#define LINKEDLIST_H
#include "Node.h"

// Circular doubly linked list
struct LinkedList {
    Node* head;
    int size;
    
    LinkedList();
    ~LinkedList();

    void insert(int i);
    int search(int key);

    void display();
    
    void merge(LinkedList& l1, LinkedList& l2);
    
private:
    
    void clear();
};

#endif
