// Name: Matheos Asfaw
// Loginid: ************
// CSCI 455 PA5
// Fall 2016

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

void readConsoleData(Table * grades);
void printComands();
void remove(Table * grades);
void lookup(Table * grades);
void change(Table *grades );
void insert(Table * grades);

int main(int argc, char * argv[]) {

    // gets the hash table size from the command line
    int hashSize = Table::HASH_SIZE;


    Table * grades;  // Table is dynamically allocated below, so we can call
                   // different constructors depending on input from the user.

    if (argc > 1) {
        hashSize = atoi(argv[1]);  // atoi converts c-string to int

        if (hashSize < 1) {
          cout << "Command line argument (hashSize) must be a positive number"
           << endl;
          return 1;
        }

        grades = new Table(hashSize);

    }
    else {   // no command line args given -- use default table size
        grades = new Table();
    }


    grades->hashStats(cout);

    // add more code here
    // Reminder: use -> when calling Table methods, since grades is type Table*
    readConsoleData(grades);

  return 0;
}

/**
 * Reads commands from the console to perform operations.
 * @param grades : hash table that was created in the main.
 */

void readConsoleData(Table * grades){


    const string INSERT = "insert";    const string CHANGE = "change";    const string LOOKUP = "lookup";
    const string REMOVE = "remove";    const string PRINT = "print";      const string SIZE = "size";
    const string STATS = "stats";      const string HELP = "help";        const string QUIT = "quit";
    string comnd;

    cout<< "cmd> ";
    while (cin >> comnd){
        if (comnd == INSERT){
          insert(grades);
        }else if (comnd == CHANGE){
            change(grades);
        } else if (comnd == LOOKUP){
            lookup(grades);
        }else if (comnd == REMOVE){
            remove(grades);
        }else if (comnd == PRINT){
            grades->printAll();
        }else if (comnd == SIZE){
            cout << grades->numEntries()<<endl;
        }else if (comnd == HELP){
            printComands();
        }else if (comnd == QUIT){
            exit(1);
        }else if (comnd == STATS){
            grades->hashStats(cout);
        }else {
            cout << "please enter a valid command. refer to the following list "<< endl;
            printComands();
        }
        cout<< "cmd> ";
    }
}

/**
 * prints out the commands and what they do
 */
void printComands(){
    cout << "List of comands are: "<< endl;
    cout << "insert name score : inserts the pair name and score if they were not already present in the table"<< endl;
    cout << "change name newscre : changes the score for a name that was already there in the table" << endl;
    cout << "remove name: removes the name and score assciacted with it from the table."<< endl;
    cout << "print : prints all the values of the table" << endl;
    cout << "size : prints the number of entries in the table" << endl;
    cout << "stats : prints out the statistics about the table" << endl;
    cout << "help : prints out the commands that are acceptable"<< endl;
    cout<< "quit : exits the program" << endl;
}

/**
 *  Reads a name and removes it it exisists in the table.
 * @param grades : table
 */
void remove(Table * grades){
    string name;
    cin>>name;
    if (grades->remove(name)){
        cout << "entry removed"<< endl;
    }else{
        cout<< "entry not found"<< endl;
    }
}
/**
 * Reads name from the console and prints out the name and value associated with the name
 * @param grades: table
 */

void lookup(Table * grades ){
    string name;
    cin>> name;
    int * value = grades->lookup(name);
    if (value != NULL){
        cout << name << " "<< *value<<endl;
    }else {
        cout << "Student was not int the table"<< endl;
    }
}
/**
 * Reads the name and score and changes the value to the new
 * @param grades : table
 */

void change(Table *grades){
    string name;
    int score;
    cin>> name; cin >>score;
    int* value = grades->lookup(name);
    if (value != NULL ){
        *value = score;
    }else {
        cout<< "Name you entered was not present."<< endl;
    }
}
/**
 * Reads the name and number from the consol and inserts the pair into the table
 * @param grades : table
 */
void insert(Table * grades){
    string name; int score;

    cin>> name;      cin>> score;
    if (! grades->insert(name,score)){
        cout<< " Name was already present in the table no changes made."<< endl;
    }
}
