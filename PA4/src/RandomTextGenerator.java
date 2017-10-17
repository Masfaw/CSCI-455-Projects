// Name: Matheos Asfaw
// USC loginid: ************
// CS 455 PA4
// Fall 2016



import java.util.*;

/**
 * Used to getenerate random text from an input source file and prefixLength parameter
 */
public class RandomTextGenerator {

    private Random rand ;
    private Prefix prefix;
    private int prefixLength;
    private Map<Prefix,ArrayList<String>> wordPool;
    private ArrayList<String> source;
    private boolean debug;


    /*************************************************************
     * Invariants
     *  1. The Hashmap has all the possible prefixes and once created (when constructor is called )can not be altered.
     *  2. The Prefix length has to be smaller than the source length by at least 1.
     *  3. prefix variable is immutable. each time shiftIn is called a new prefix is created.
     *  4. prefixLength can not be smaller than 1.
     *  5. the source has to have a minimum of one word. source.size() > 0.
     *  6. rand is set to 1 when debug flag is set to true. Other wise its random.
     */

    /**
     * constructs the RandomTextGenereator class.
     * @param sourceText input file to base the text generation of of.
     * @param preLen prefixlength from the command line.
     * @param debug  boolean to control weather to run the program in debug mode or not.
     * PRE: preLen has to be greater than 0,
     * PRE: sourceText.size() > preLen. By at least 1.
     */
    public RandomTextGenerator(ArrayList<String> sourceText, int preLen, boolean debug){
        // ititialize all private Variables
        prefixLength = preLen;
        source = new ArrayList<>();
        wordPool = new HashMap<>();
        if (debug){
            rand = new Random(1);
        }
        else{
            rand = new Random();
        }

        this.debug = debug;
        // copy over the source Text
        for (String x : sourceText){
            source.add(x);
        }

        initPrefex();
        makeWordPool();

    }

    /**
     * Generates numWords random words based on the Map that was created using the input text.
     * Takes the current prefix finds its successors chooses a word and adds them to the output text.
     * @param numWords number of words to be generated.
     * @return a string that is the new text. This is max 80 char long per line.
     */

    public String generateText(int numWords){

        ArrayList<String> suc = new ArrayList<>();
        String word;
        final String space = " ";
        final int  MAX_CHAR = 80;
        int linelength;
        StringBuilder line = new StringBuilder(), genText = new StringBuilder();

       for (int i = 0; i < numWords;i++){
           suc.clear();
           suc = new ArrayList<>(wordPool.get(prefix));
           if (debug){ // added for debug purposes runs only when debug flag is true.
               System.out.println("DEBUG: prefix: " + prefix.toString());
           }
           while (suc.size()== 0){
               if(debug){ // added for debug purposes runs only when debug flag is true.
                   System.out.println("DEBUG: Successors: <END OF FILE>" );
               }
               initPrefex();
               suc = new ArrayList<>(wordPool.get(prefix));
           }
           word = suc.get(rand.nextInt(suc.size()));
           if (debug){ // added for debug purposes runs only when debug flag is true.
               System.out.println("DEBUG: Successors: " + suc.toString());
               System.out.println("DEBUG: word generated : " + word);
           }
           linelength = line.length() + space.length()+ word.length();
           if (linelength <= MAX_CHAR ){
               line.append(word + space);
           }
           else {
               genText.append(line.toString().trim() + "\n");
               line.delete(0,line.length());
               line.append(word + space);
           }
            prefix = prefix.shiftIn(word);
       }
        genText.append(line.toString().trim());
        return genText.toString();
    }



    /**
     * initilizes the prefix from a random point in the text using the prefix length.
     * This is run when the object is constructed and whenever the generateText gets to the end of the file.
     */
    private void initPrefex(){


        ArrayList<String> pre = new ArrayList<>();
        // getting the index for the first word in the prefix.
        // - prefixLength makes it so that we will get all the words we need and have one more
        int index = rand.nextInt(source.size() - prefixLength);
        int size = index + prefixLength;
        while (index < size){
           pre.add(source.get(index));
           index++;
        }

        prefix = new Prefix(pre);
        if(debug){
            System.out.println("DEBUG : choose a new initial prefix: " + prefix.toString());
        }

    }

    /**
     * Creates a Hash Map where all the possible prefixes are mapped with there successors.
     * inorder to generate text just do a lookup with a prefix and will get all the successors.
     */
    private void makeWordPool(){

        //Make the first prefix of prefixLength words
        ArrayList<String> preWords = new ArrayList<>();
        ArrayList<String> suc = new ArrayList<>();

        for (int i = 0; i < prefixLength;i++){
            preWords.add(source.get(i));
        }

        Prefix pre = new Prefix(preWords);



        // check if prefix already exists in the Map
        if (!wordPool.containsKey(pre)){
            suc.add(source.get(prefixLength));
            wordPool.put(pre,new ArrayList<String>(suc));
        }
        else {
            suc = wordPool.get(pre);
            suc.add(source.get(prefixLength));
            wordPool.put(pre,new ArrayList<String>(suc));
        }
       // System.out.println(toString());
        for(int i = prefixLength; i < source.size() ;i++){
            suc.clear();

           // System.out.println(source.get(i));
            pre = pre.shiftIn( source.get(i));



              if (!(wordPool.containsKey(pre))){
                  if (i+1 < source.size()){
                      suc.add(source.get(i+1));
                  }
                wordPool.put(pre,new ArrayList<String>(suc));
            }
            else {
                suc = wordPool.get(pre);
                 if (i+1 < source.size()){
                      suc.add(source.get(i+1));
                  }
                wordPool.put(pre,new ArrayList<String>(suc));
            }

        }

    }

    /**
     * Outputs all the prefixes, successor stored in the hash map.
     * @return string containig the prefix and suc.
     */
    @Override
    public String toString(){
        Set<Map.Entry<Prefix,ArrayList<String>>> setView = wordPool.entrySet();
        Iterator<Map.Entry<Prefix,ArrayList<String>>> iter = setView.iterator();
        StringBuilder output = new StringBuilder();

        while(iter.hasNext()){
            Map.Entry<Prefix,ArrayList<String>> curr = iter.next();
            output.append("Prefix: " + curr.getKey().toString() + "\n");
            output.append("successors: " );

            for(int i = 0; i <curr.getValue().size(); i++){
                output.append(curr.getValue().get(i) );
                if (i != curr.getValue().size() -1){
                    output.append(" ");
                }
            }
            output.append("\n");
        }

        return output.toString();
    }
}
