import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by Matheos Asfaw  on 9/22/16.
 */
public class PolynomialTester {
        static Polynomial poly = new Polynomial();
    public static void main (String [] args ){

        Polynomial p0 = new Polynomial(new Term(7,0)); Polynomial p0Five = new Polynomial(new Term(5,0));
        Polynomial p1 = new Polynomial(new Term(2,1));
        Polynomial p2 = new Polynomial(new Term(2,2));
        Polynomial p3 = new Polynomial(new Term(3,3));
        Polynomial p3Neg = new Polynomial(new Term(-3,3));
        Polynomial p4 = new Polynomial(new Term(4,40));
        Polynomial p5 = new Polynomial(new Term(1,5));
        Polynomial p6 = new Polynomial(new Term(-6,6));
        Polynomial p7 = new Polynomial(new Term(1,7));
        Polynomial p8 = new Polynomial(new Term(8,8));
        Polynomial p9 = new Polynomial(new Term(9,9));
        Polynomial p10 = new Polynomial(new Term(1,10));
/*
        Polynomial sum = p3.add(p1).add(p0);
        Polynomial sum2 = p3Neg.add(p0Five).add(p5);
        Polynomial sum3 = sum2.add(sum);
        System.out.println("sum = " + sum.toFormattedString());
        System.out.println("sum = " + sum2.toFormattedString());
        System.out.println("sum3 = " + sum3.toFormattedString());
        System.out.println("Eval 2 ==" + sum.eval(2));

        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial(new Term(0, 3));
        Polynomial p3 = p2.add(new Polynomial(new Term(0, 7)));
        Polynomial p4 = new Polynomial(new Term(3, 2)).add(new Polynomial(new Term(-3, 2)));
        Polynomial p5 = new Polynomial(new Term(3, 2)).add(new Polynomial(new Term(-5, 2)));
        Polynomial p6 = p5.add(new Polynomial(new Term(2, 2)));


        System.out.println("p1 = " + p1.toFormattedString());
        System.out.println("p2 = " + p2.toFormattedString());
        System.out.println("p3 = " + p3.toFormattedString());
        System.out.println("p4 = " + p4.toFormattedString());
        System.out.println("p5 = " + p5.toFormattedString());
        System.out.println("p6 = " + p6.toFormattedString());
       // System.out.println("p7 = " + p7.toFormattedString());
*/

        System.out.println("p0 = " + p0.toFormattedString());
        Polynomial sum = p3.add(p7).add(p0).add(p6);
        System.out.println("sum = " + sum.toFormattedString());
      //  System.out.println("eval x = 2 is " + sum.eval(2));
        changePoly(sum);
        System.out.println("sum = " + sum.toFormattedString());
        System.out.println("poly after  " + poly.toFormattedString());
       // System.out.println("Sum p3 + p7  = " + sum.toFormattedString());

    //    sum = sum.add(p4);
       // System.out.println("Sum+ p7  = " + sum.toFormattedString());

    //     sum = sum.add(p3);
     //   System.out.println("sum = " + sum.toFormattedString());
     //   sum = sum.add(p7);
      //  System.out.println("sum = " + sum.toFormattedString());
      //  sum = sum.add(p4).add(p10).add(p1).add(p1);

      //  System.out.println(" final sum = " + sum.toFormattedString());
      //System.out.println("eval x = 2 is " + sum.eval(2));

    }


    static void changePoly(Polynomial p){
        System.out.println("P before " + p.toFormattedString());
        System.out.println("Poly  before " + poly.toFormattedString());
       poly = p;
        System.out.println("P after " + p.toFormattedString());
        System.out.println("Poly after " + poly.toFormattedString());

    }
}
