#include "LinkedList.h"
#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

LinkedList::LinkedList() : head(nullptr), size(0) {}

LinkedList::~LinkedList() {
    while (size > 0) {
        clear();
    }
}

void LinkedList::insert(int i) {
    Node* newNode = new Node(i);
    if (head == nullptr) {
        head = newNode;
        head->next = head;
    } else {
        newNode->next = head->next;
        head->next = newNode;
        head = newNode;
    }
    size++;
}

int LinkedList::search(int key) {
    if (head == nullptr)
        return -1;
    
    Node* current = head;
    int comparisons = 0;
    
    do {
        comparisons++;
        if (current->data == key) {
            return comparisons;
        }
        current = current->next;
    } while (current != head);
    
    return comparisons;
}

void LinkedList::display() {
    if (head == nullptr) {
        cout << "List is empty" << endl;
        return;
    }
    
    Node* current = head->next;
    do {
        cout << current->data << " ";
        current = current->next;
    } while (current != head->next);
    cout << endl;
}

void LinkedList::merge(LinkedList& l1, LinkedList& l2) {
    if (l1.head == nullptr && l2.head == nullptr) {
        return;
    }

    Node* l1_current = l1.head->next;
    Node* l2_current = l2.head->next;

    do {
        insert(l1_current->data);
        l1_current = l1_current->next;
    } while (l1_current != l1.head->next);

    do {
        insert(l2_current->data);
        l2_current = l2_current->next;
    } while (l2_current != l2.head->next);
}

void LinkedList::clear() {
    if (head == nullptr)
        return;
    
    Node* current = head;
    Node* nextNode = nullptr;
    
    do {
        nextNode = current->next;
        delete current;
        current = nextNode;
    } while (current != head);
    
    head = nullptr;
    size = 0;
}