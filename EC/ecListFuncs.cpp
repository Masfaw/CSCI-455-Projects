/*  Name: Matheos Asfaw
 *  loginid: **********
 *  CS 455 Fall 2016, Extra Credit assignment
 *
 *  See ecListFuncs.h for specification of each function.
 */

#include <iostream>

#include <cassert>

#include "ecListFuncs.h"

using namespace std;


bool isInOrder(ListType list) {
    Node * p = list;
    int num;
    if (p!=NULL){
        num = p->data;
    }

    while (p != NULL){
       if (p->data < num){
           return false;
       }
        num = p->data;
        p= p->next;
    }


  return true;
}



void insertInOrder(ListType & list, Node *itemP) {
  assert(isInOrder(list));     // checks the preconditions
  assert(itemP->next == NULL);
  // add the rest of the code after this line

    Node * p = list;
    Node * t = list;
    if (p != NULL){
        if (p->data > itemP->data){
            itemP->next = p;
            list = itemP;
            return;
        }
        else {
            p = p->next;
        }

    }
    else {

    }
    if (p != NULL){
        while (p != NULL && p->data < itemP->data){
            p = p ->next;
            t=t->next;
        }

        itemP->next = p;
        t->next = itemP;

    }
    else {
        itemP ->next = NULL;
        list = itemP;
    }

}



void insertionSort(ListType &list) {
    Node * sorted = list;
    Node * p = list->next;
    sorted->next = NULL;

    while (p != NULL){
        Node * cur = p;
        p = p->next;

        if (cur->data < sorted->data){
            cur->next = sorted;
            sorted = cur;
        }else{
            Node * t = sorted;
            while (t->next != NULL && cur->data > t->next->data){
                t= t->next;
            }

            cur->next = t -> next;
            t->next = cur;
        }

    }

    list = sorted;
}




void splitEvenOdd(ListType &list, ListType &a, ListType &b){
    int count = 1;
    while (list != NULL ){
        if (count % 2 != 0 ){

            if (a == NULL){
                a= list;
                list = list->next;
                a->next = NULL;
            }else {
                Node * p = a;
                while (p ->next != NULL){
                    p = p->next;
                }

                p->next = list;
                list = list->next;
                p->next->next = NULL;
            }


        }
        else if (count % 2 == 0 ){
            if (b == NULL){
                b = list;
                list = list->next;
                b->next= NULL;
            }else {

                Node * p = b;
                while (p ->next != NULL){
                    p = p->next;
                }

                p->next = list;
                list = list->next;
                p->next->next = NULL;
            }
        }
        count++;

    }


}
