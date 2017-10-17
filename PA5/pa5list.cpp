// Name: Matheos Asfaw
// loginid: ***********
// CS 455 PA5

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"
#include "Table.h"



int main() {

    Node* list = new Node("hello", 1);
    printList(list);
    cout << " "<< endl;
    cout << contains(list, "hello")<< endl;
    cout << contains(list, "hello")<< endl;

    Table * t = new Table(10);

    t->insert("hello",1);
    cout << t->insert("hello",1) << endl;
    int * p = t->lookup("hello");
    *p = *p + 10;

    cout << *(t->lookup("hello"))<< endl;
/*
    bool rem = listRemove(list, "hello");


    cout << " after the first  remove" << endl;
    printList(list);
    cout << contains(list, "hello")<< endl;



    listAdd( list, "hello", 10);
    printList(list);    cout << " "<< endl;


    listAdd( list, "world", 10);
    listAdd( list, "beast", 12);
    listAdd( list, "man", 10);
    listAdd( list, "python", 42);
    listAdd( list, "hello", 5);
    printList(list);    cout << " "<< endl;


    if (contains(list, "hello")){
        cout<<"true"<< endl;
    }


    listRemove(list, "man");
    listRemove(list, "python");
    listRemove(list, "hello");


    printList(list);    cout << " "<< endl;
    cout << contains(list, "hello")<< endl;






     Node * loc = listLookup(list,"beast");

    if (loc != NULL ){
        cout << loc->key << " "<< loc->value <<endl;
    }
*/
    return 0;
}
