package com.stgconsulting.math;

public class Fibonacci {

    /**
     * Given the requirement:
     * <br>
     *  "For this challenge, we are going to write a class that display the fibonacci sequence up to a certain number.
     *  If I want the fibonacci for the 9 order of the sequence, I should see 21."
     * <br>
     * the order -> sequence goes as follows.
     * <ul>
     *     <li>1 -> 0</li>
     *     <li>2 -> 1</li>
     *     <li>3 -> 1</li>
     *     <li>4 -> 2</li>
     *     <li>5 -> 3</li>
     *     <li>6 -> 5</li>
     *     <li>7 -> 8</li>
     *     <li>8 -> 13</li>
     *     <li>9 -> 21</li>
     *     <li> ... </li>
     * </ul>
     * @param order An integer indicating how far to calculate the Fibonnaci sequence
     * @return For order <=1, 0, otherwise the Fibonacci number for the order given the requirement for the calculation.
     */
    public static int calculateFibonacci(int order) {
        int result = 0;
        int next = 1;

        //Return 0 for order 1 (or less to handle invalid input)
        for(int i=2; i<=order; i++) {
            result = result + next;
            next = result - next;
        }
        return result;
    }
}
