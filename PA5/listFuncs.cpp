// Name: Matheos Asfaw
// Loginid: *********
// CSCI 455 PA5
// Fall 2016


#include <iostream>

#include <cassert>

#include "listFuncs.h"


using namespace std;

Node::Node(const string &theKey, int theValue) {
  key = theKey;
  value = theValue;
  next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
  key = theKey;
  value = theValue;
  next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

/**
 * Removes a node from a linked list.
 * @param list linked list
 * @param target : key value to use when removing a node.
 * @return true iff the target was in the list and was removed.
 */
bool listRemove (ListType & list, string target){
    Node *p ;
    Node * q;
    p = list;
    q = list;

    if (p == NULL){
        return false;
    }
    p = p->next;

    if (p == NULL){
        delete q;
        delete p;
        list = NULL;

        return true;
    }
    if (q->key == target){
        delete q;
        list = p;
        return true;
    }

    while (p != NULL){

        if (p->key == target){
            q->next = p->next;
            delete p;
            return true;
        }
        p=p->next;
        q=q->next;
    }


    return false;
}
/**
 * Adds a node to the begining of the list with key  == target
 * @param list :reference to the list
 * @param target : new string to be added
 * @param value : value associated with the key.
 * PRE: Assumes the list is a well formed list.
 */
void listAdd(ListType & list, string target, int value){
    Node * loc = listLookup(list, target);

    if (  loc != NULL){

        loc ->value = loc->value + value;
    }
    else {
        list = new Node(target, value, list);
    }
}



/**
 * iterates through a list to see if the target is presnet
 * @param list: list to iterate through
 * @param target : target to look for
 * @return true iff the target is found in the list.
 */
bool contains (const ListType  &list, string target){
    Node *p ;
    p = list;

    if (p == NULL){
        return false;
    }

    while (p != NULL){
        if (p->key == target){

            return true;
        }
        p=p->next;
    }

    return false;
}

/**
 * prints all the elements is the linked list.
 * @param list
 */
void printList (const ListType  &list ) {
    Node *p ;
    p = list;

    if (p == NULL){
        return;
    }

    while (p != NULL){
        cout << p->key<< " "<< p->value<< ",";
        p=p->next;
    }
    cout<<endl;
}


/**
 * returns the pointer to the node that has the target as a key
 * @param list : linked list to search in
 * @param target : target string to look for
 * @return pointer to the node that has the string as key.
 */
Node * listLookup(const ListType &list, string target){
    Node * loc = NULL;
    Node * p;

    p = list;

    if (p == NULL){
        return loc;
    }

    while (p != NULL){
        if (p->key == target){
            loc =p ;
            return loc;
        }
        p=p->next;
    }


    return loc;
}

/**
 * Returns the size of the linked list
 * @param list : list to measure the size of
 * @return int value of the size of the list.
 */

int listSize (const ListType &list){
    Node * p = list;
    int size = 0;
    while (p!= NULL ){
        size++;
        p=p->next;
    }

    return size;

}
