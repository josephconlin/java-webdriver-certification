package certification.challenge4;

import com.stgconsulting.math.Fibonacci;
import com.stgconsulting.math.NumberToWords;
import org.testng.annotations.Test;

/*
 * Challenge 4 - Operators and Functions:
 * For this challenge, we are going to write a class that display the fibonacci sequence up to a certain number.  If I
 * want the fibonacci for the 9 order of the sequence, I should see 21.  Keep your function to calculate the fibonacci
 * sequence separate from the test file.
 *
 * However, to add additional challenge to this challenge, instead of displaying the number 21, I want the string
 * representation of twenty one.  This will require you to use string concatenation to print out the string.
 */
public class Challenge4OperatorsAndFunctions {

    @Test
    public void calculateFibonacciOrderAndDisplayResultAsWords() {
        int order = 9;
        System.out.println("Showing the Fibonacci numbers from order 1 to "+order);
        for(int i=1; i <= order; i++) {
            int fibonacci = Fibonacci.calculateFibonacci(i);
            System.out.println(fibonacci+" - "+NumberToWords.convertNumberToWords(fibonacci));
        }
    }
}
