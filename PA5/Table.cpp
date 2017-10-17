// Name: Matheos Asfaw
// Loginid: ***********
// CSCI 455 PA5
// Fall 2016

// Table.cpp  Table class implementation


/*
 * Modified 11/22/11 by CMB
 *   changed name of constructor formal parameter to match .h file
 */

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {

    tableElements = 0;
    table = new ListType[HASH_SIZE];
    hashSize = HASH_SIZE;

    initTable(table, hashSize);
}


Table::Table(unsigned int hSize) {
    tableElements = 0;
    table = new ListType[hSize];
    hashSize = hSize;
    initTable(table, hashSize);


}


int * Table::lookup(const string &key) {

   int hash = hashCode(key);
    Node * loc = listLookup(table[hash], key);

    if (loc != NULL ){
        return &(loc ->value);
    }

  return NULL ;
}

bool Table::remove(const string &key) {

    int hash = hashCode(key);
    if (listRemove(table[hash], key)){
        tableElements--;
        return true;
    }
    return false;
}

bool Table::insert(const string &key, int value) {
    int hash = hashCode(key);

    if (contains (table[hash], key)){
        return false;
    }
    else {
        listAdd(table[hash], key, value);
        tableElements++;
        return true;
    }

}

int Table::numEntries() const {
  return tableElements;
}


void Table::printAll() const {
    cout<< "printing"<< endl;
    for ( int i = 0; i < hashSize; i ++){
       printList(table[i]);
    }
}

void Table::hashStats(ostream &out) const {

    out<< "number of buckets: "<< hashSize<< endl;
    out<< "number of entries: "<< tableElements<< endl;
    out<< "number of non-empty buckets: "<< countEmptybuckets(table,hashSize)<< endl;
    out<< "longest chain : "<< longestChain(table,hashSize)<< endl;



}



// add definitions for your private methods here

void Table::initTable(ListType * t, int size){
    for (int i = 0 ; i < size; i ++ ){
        t[i] = NULL;
    }

}

int Table::countEmptybuckets(ListType *t, int size) const {
    int count = 0;
    for (int i = 0 ; i < size; i ++ ){
        if (t[i] != NULL){
            count++;
        }
    }
    return count;
}

int Table::longestChain (ListType * t, int size) const {
    int longest = 0;

    for (int i = 0 ; i < size; i ++ ) {
        int size = listSize(t[i]);
        if (size > longest) {
            longest = size;
        }

    }
    return longest;
}


