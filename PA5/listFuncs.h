// Name: Matheos Asfaw
// Loginid: **********
// CSCI 455 PA5
// Fall 2016


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.


#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  
using namespace std;

struct Node {
  string key;
  int value;

  Node *next;

  Node(const string &theKey, int theValue);

  Node(const string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.



/**
 * Removes a node from a linked list.
 * @param list linked list
 * @param target : key value to use when removing a node.
 * @return true iff the target was in the list and was removed.
 */
    bool listRemove (ListType & list, string target);
/**
 * Adds a node to the begining of the list with key  == target
 * @param list :reference to the list
 * @param target : new string to be added
 * @param value : value associated with the key.
 * PRE: Assumes the list is a well formed list.
 */
    void listAdd(ListType & list, string target, int value);
/**
 * iterates through a list to see if the target is presnet
 * @param list: list to iterate through
 * @param target : target to look for
 * @return true iff the target is found in the list.
 */
    bool contains (const ListType  &list, string target);
/**
 * prints all the elements is the linked list.
 * @param list
 */
    void printList (const ListType  &list ) ;
/**
 * returns the pointer to the node that has the target as a key
 * @param list : linked list to search in
 * @param target : target string to look for
 * @return pointer to the node that has the string as key.
 */
    Node * listLookup(const ListType &list, string target);
/**
 * Returns the size of the linked list
 * @param list : list to measure the size of
 * @return int value of the size of the list.
 */
    int listSize (const ListType &list);










// keep the following line at the end of the file
#endif
