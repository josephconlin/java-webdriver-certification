package com.stgconsulting.math;

public class NumberToWords {

    //Array of names for orders of magnitude
    private static final String[] magnitudeNames = {
            "",
            " thousand",
            " million",
            " billion",
            " trillion",
            " quadrillion",
            " quintillion"
    };

    //Array of names for tens
    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    //Array of number names from one to nineteen, zero handled separately since it usually becomes part of tens names
    private static final String[] numberNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    //Numbers < 1000 use different logic than numbers >= 1000. Calculate words from right to left (ones to tens to hundreds)
    private static String convertNumberLessThanOneThousandToWords(int number) {
        String current;

        //Check for the last two digits of the number being less than 20
        if (number % 100 < 20){
            //Use last two digits of number as the index into numberNames
            current = numberNames[number % 100];
            //Remove last two digits from number before further processing
            number /= 100;
        }
        //Last two digits of the number represent 20 or more
        else {
            //Use last digit of number as the index into numberNames
            current = numberNames[number % 10];
            //Remove last digit from number before further processing
            number /= 10;

            //Use new last digit of number as the index into tensNames and put that in front of current words
            current = tensNames[number % 10] + current;
            //Remove new last digit of number before further processing
            number /= 10;
        }

        //If new last digit of number is 0, return the current words
        if (number == 0) return current;
        //Otherwise, use new last digit of number as the index into numberNames and add " hundred" and then current words
        return numberNames[number] + " hundred" + current;
    }

    //Calculate words from right to left (hundreds to thousands to millions, etc)
    public static String convertNumberToWords(int number) {

        //Handle number being 0
        if (number == 0) { return "zero"; }

        String prefix = "";

        //Check for negative number, if found, set prefix of "negative" and change number to positive so indexing into
        //arrays will work
        if (number < 0) {
            number = -number;
            prefix = "negative";
        }

        String current = "";
        //place indicates which order of magnitude is currently being converted to words
        int place = 0;

        while(number > 0) {
            //n is the rightmost 3 digits of the number
            int n = number % 1000;

            //Check if rightmost 3 digits of number equal 0, if true, nothing needs to be done for them
            if (n != 0){
                //Digits are not zero, get the "less than one thousand" words for them
                String s = convertNumberLessThanOneThousandToWords(n);
                //Use place as the index into magnitudeNames and put that in front of the "less than one thousand" words
                current = s + magnitudeNames[place] + current;
            }

            //Prepare to work on the next order of magnitude
            place++;
            //Remove the rightmost three digits so next iteration of the loop can work on the next three digit group
            number /= 1000;
        }

        return (prefix + current).trim();
    }
}
