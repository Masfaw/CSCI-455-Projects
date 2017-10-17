// Name: Matheos Asfaw
// USC loginid: **********
// CS 455 PA4
// Fall 2016


import java.util.ArrayList;
import java.util.Objects;

/**
 * Prefix class
 *
 * Stores the sequence of words that will be used to choose the next word to generate.
 * Will implement the hash code function and the equals to be used in a hash map.
 *
 * This is an immutable class.
 */
public class Prefix {

    private ArrayList<String> prefixes;

    /*************************************************************
     * Invariants
     *
     * 1. shiftin creates a new Prefix object does not alter the current one.
     * 2. Prefix class implements the hashcode and equals to comply with Hashmap contract.
     * */

    /**
     * Constructs the Prefix class.
     * copies over the contents of the prefixes and the successors for that prefix
     * @param pre an arraylist of words that is prefixLength long.
     *            these words together are the prefix.
     *
     */
    public Prefix(ArrayList<String> pre){

        prefixes = new ArrayList<>();

        for (String x : pre){
            prefixes.add(x);
        }

    }

    /**
     * Creates a new prefix object that uses all the words except
     * the first in the current prefix and an new word that is passed in
     * @param word string that will be added to the end of the prefix
     * @return a new prefix object following the above description
     */
    public Prefix shiftIn(String word){

        ArrayList<String> pre= new ArrayList<>();

        for(int i = 1; i < prefixes.size();i++){
            pre.add(prefixes.get(i));
        }
        pre.add(word);

        return new Prefix(pre);
    }

    /**
     * returns the length of the prefix.
     * should be the same as the input parameter from the command line.
     * @return int size of the prefix.
     */
    public int getPrefixLength(){
        return prefixes.size();
    }


    /**
     * Overides equals to comply with hashmap contract.
     * @param obj object to be compared with this one.
     * @return returns true if they are the same object or have the exact same contents.
     */
    @Override
    public boolean equals(Object obj){
        if (obj ==null){
            return false;
        }
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Prefix)){
            return false;
        }
        Prefix other = (Prefix)obj;

        if (this.getPrefixLength() == other.getPrefixLength()){
            for (int i = 0; i < this.getPrefixLength();i++){
                if (!(this.prefixes.get(i).equals(other.prefixes.get(i)))) {
                    return false;
                }
            }
            return true;
        }

        return false;

    }

    /**
     * Overides hashCode to comply with hashmap contract
     * @return the hash index
     */
    @Override
    public int hashCode(){
        int result =1;
        final  int prime = 31;

        for (int i =0; i < prefixes.size();i++){
            result += Math.abs(result * prime) + prefixes.get(i).hashCode();
        }

        return Math.abs(result);

    }

    /**
     * Overrides toString. to output the contents of the class
     * @return string containing the prefixes.
     */
    @Override
    public String toString(){
        StringBuilder pre = new StringBuilder();

        for (int i = 0 ; i < prefixes.size(); i++){
            pre.append(prefixes.get(i));
            if(i != prefixes.size() -1){
                pre.append(" ");
            }
        }

        return pre.toString();
    }



}
