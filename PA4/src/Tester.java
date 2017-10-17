import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Matheos Asfaw  on 11/11/2016.
 */
public class Tester {

    public static void main (String args[]){

       /* System.out.println("Testing the Prefix Class");
        ArrayList<String> w1 = new ArrayList<>();
        ArrayList<String> w2 = new ArrayList<>();

        w1.add("Donald");
        w1.add("Trump");
        w1.add("Hilary");
        w1.add("Clinton");

        w2.add("Trump");
       // w2.add("Hilary");
        w2.add("Donald");
       // w2.add("Clinton");


        Prefix a = new Prefix(w1);
        Prefix b = new Prefix(w1);
        Prefix c = new Prefix(w2);

        System.out.println("a quals b == " + a.equals(b));
        System.out.println("a quals c == " + a.equals(c));
        System.out.println("b quals c == " + b.equals(c));
        System.out.println("b quals b == " + b.equals(b));
        System.out.println();
        System.out.println("The hashcode values");
        System.out.println("a = "+ a.hashCode());
        System.out.println("b = "+ b.hashCode());
        System.out.println("c = "+ c.hashCode());

        System.out.println();
        System.out.println("cheking the to string");
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(c.toString());

       */

        ArrayList<String> text = new ArrayList<>();
        ArrayList<String> ret = new ArrayList<>();
        String line = "I like the big blue dog better than the big elephant with the big blue hat on his tusk.";
       // String line = "the the the the the\n";
       // String line = "the end\n";
        Scanner linereader = new Scanner(line);

        while (linereader.hasNext()){
            text.add(linereader.next());
        }
        RandomTextGenerator generator = new RandomTextGenerator(text, 2,false);
       // System.out.println(generator.toString());
        String rettext = generator.generateText(15);
        linereader = new Scanner(rettext);
        while (linereader.hasNext()){
            ret.add(linereader.next());
        }
       // System.out.println("length number of words in ret " + ret.size() );
        //System.out.println(rettext);







    }
}
