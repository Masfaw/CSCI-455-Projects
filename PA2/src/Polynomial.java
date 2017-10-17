// Name: Matheos Asfaw
// USC loginid: ******
// CS 455 PA2
// Fall 2016


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {


    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
        terms = new ArrayList<Term>();
        assert isValidPolynomial();
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
        terms = new ArrayList<Term>();
        if (term.getCoeff() != 0 ){
            terms.add(term);
        }
        assert isValidPolynomial();
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {

        Polynomial sum = new Polynomial();
        int indexForThis = 0;
        int indexForB = 0 ;

        while (indexForThis < this.terms.size() && indexForB < b.terms.size()){

                // this adds the coef if the exp are the same and adds it to the sum poly
            if (this.terms.get(indexForThis).getExpon() == b.terms.get(indexForB).getExpon()){
                double thisCo = this.terms.get(indexForThis).getCoeff();
                double bCo = b.terms.get(indexForB).getCoeff();
                double sumCo = thisCo + bCo;
                if (sumCo != 0 ){
                    sum.terms.add(new Term(sumCo,b.terms.get(indexForB).getExpon()));
                }
                indexForB++;
                indexForThis++;
            }
            // this portion is the same as a merge sort.
            else if (this.terms.get(indexForThis).getExpon() > b.terms.get(indexForB).getExpon()){
                sum.terms.add(this.terms.get(indexForThis));
                indexForThis++;
               }
            else {
                sum.terms.add(b.terms.get(indexForB));
                indexForB++;
            }
        }
        // Adding the remaining terms from this polynomial
        while (indexForThis < this.terms.size()){
            sum.terms.add(this.terms.get(indexForThis));
            indexForThis++;
        }

        // Adding the remaining terms from the b Polynomial
        while (indexForB < b.terms.size()){
            sum.terms.add(b.terms.get(indexForB));
            indexForB++;
        }
        assert isValidPolynomial();
        assert b.isValidPolynomial();
        assert sum.isValidPolynomial();

	return sum;
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
        double result = 0;
        for (int i =0; i<terms.size();i++ ){
            result += terms.get(i).getCoeff() * (Math.pow(x, terms.get(i).getExpon()));
        }

        assert isValidPolynomial();
	return result;
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {

        StringBuilder poly = new StringBuilder();
        if (terms.size() == 0 ){
            poly.append("0.0");
        }

        for (int i = 0; i < terms.size();i++){
            if (i != 0 ){ //&& !(terms.get(i).getExpon() == 0)
                poly.append(" + ");
            }

            if (terms.get(i).getExpon() == 0 ){ //||
                poly.append(terms.get(i).getCoeff());
            }
            else if (terms.get(i).getExpon() == 1){
                poly.append(terms.get(i).getCoeff() + "x");
            }
            else {
                if (terms.get(i).getCoeff() == 1){
                    poly.append("x^" + terms.get(i).getExpon());
                }
                else if (terms.get(i).getCoeff() == -1){
                    poly.append("-x^" + terms.get(i).getExpon());
                }
                else {
                    poly.append(terms.get(i).getCoeff() + "x^" + terms.get(i).getExpon());
                }
            }
        }
        assert isValidPolynomial();

        return poly.toString();
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true iff the poly data is in a valid state.
    */
    private boolean isValidPolynomial() {
        boolean isValid = true;

        if (terms.size()!= 0 ){
            for (int i =0 ; i < terms.size() -1 ;i ++){
                if (terms.get(i).getExpon() < terms.get(i+1).getExpon()){
                    isValid = false;
                }
            }
        }


	return isValid;
    }


    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)

    /*
        * Invariants :
        * 1.terms variable  must have the terms of the Polynomial in decreasing order by exponent.
        * 2.the constructor with signature public Polynomial(Term term) must be called with a valid term
        * .ie term must have an exponent greater or equal to 0.
        * 3.add function must return a Polynomial that adheres to invariant 1.
        * 4.Each element in the list will represent the (Ci,i) pair for a non zero term in the polynomial.
        * 5. There shall be no two terms with the same exponent.
        * 6. All zero polynomials are expressed as empty lists
        */
    private ArrayList<Term> terms ;


    // Private instance variable




}
