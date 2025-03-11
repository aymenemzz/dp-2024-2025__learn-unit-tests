package fr.anthonyquere.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public static List<String> startFizzBuzz(int count) {

        List<String> result = new ArrayList<String>();
        ArrayList<Integer> countList = new ArrayList<>();
        if (count == 0) {
            return result;
        }

        for (int number = 1; number < count+1; number++) {
            countList.add(number);
        }

        result = countList.stream().map(FizzBuzz::fillFizzBuzz).toList();
        return result;
    }
    private static String fillFizzBuzz(Integer number) {

        String Fizz = "Fizz";
        String Buzz = "Buzz";

        if (number % 5 == 0 && number % 3 == 0)
        {
            return Fizz+Buzz;
        }
        else {
            return Fizz;
        }
    }
}
