package com.company;
import static java.lang.Integer.valueOf;

public class Main {

    public static void main(String[] args){
    StringCalculator calc = new StringCalculator();
    System.out.println(calc.Add("1,2"));
    }
}

class StringCalculator{

    public int Add(String numbers){
        if (numbers.equals("")) return 0;
        String[] arr = numbers.split(",");
        int sum = 0;
        for (int i = 0; i < arr.length;i++) sum += valueOf(arr[i]);
        return sum;
    }

}

