#include "LinkedList.h"
#include <iostream>

using namespace std;

int main() {
    LinkedList l1;
    LinkedList l2;
    LinkedList l3;

    for (int i = 1; i <= 10; i++) {
        l1.insert(i);
    }

    for (int i = 11; i <= 20; i++) {
        l2.insert(i);
    }

    cout << "Display list 1: ";
    l1.display();

    cout << "Display list 2: ";
    l2.display();

    l3.merge(l1, l2);
    
    cout << "Merged list: ";
    l3.display();

    int comp = l3.search(4);
    cout << "Comparisons: " << comp << endl;

    return 0;
}
