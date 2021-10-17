package com.company;
import java.lang.constant.Constable;

import static java.lang.Integer.valueOf;

public class Main {

    public static void main(String[] args){
    StringCalculator calc = new StringCalculator();
    int result = calc.Add("//;\n-1;2\n-3;4\n-5;96\n7;8");
    System.out.println("Sum is: " +result+ "\n(-1 is the code of the exeption of negative nums)" );
    }
}

class StringCalculator extends Spliter{

    public int Add(String numbers){
        if (numbers.equals(""))
            return 0;
        String delimeter = ",";
        if (anoutherDelimeter(numbers)) {
            delimeter = numbers.split("")[2];
            numbers = withoutBeg(numbers);
        }
        numbers = numbers.replace("\n", delimeter);
        String[] arr = SplitByDel(numbers,delimeter);
        String[] negative = null;
        int sum = 0, ind_negative = 0;
        for (int i = 0; i < arr.length;i++){
            if (valueOf(arr[i])>=0)
            sum += valueOf(arr[i]);
            else {negative = append(negative,arr,i,i+1);}}
        if (negative!=null){
            System.out.println("negatives not allowed");
            for (int i = 0; i < negative.length;i++)
                System.out.println(String.format("%s", negative[i]));
            return -1;
        }
        return sum;
    }

    private boolean anoutherDelimeter(String numbers){
        String[] nums = numbers.split("");
        if (nums.length<4)
            return false;
        if (nums[0].equals("/") && nums[1].equals("/") && nums[3].equals("\n"))
            return true;
        return false;
    }

    private String withoutBeg(String numbers){
        String[] numS = numbers.split("");
        String res = "";
        for (int i = 4; i < numS.length;i++)
            res = res  + numS[i];
        return res;
    }

}

class Spliter{

    public String[] SplitByDel(String str, String del){
        String[] arr = str.split("");
        String[] result = null;
        int ind1 = 0, ind2 = 0;
        for (int i = 0; i< arr.length;i++){
            if (arr[i].equals(del)){
                ind2 = i;
                result = append(result,arr,ind1,ind2);
                ind1 = i+1;
            }
        }
        if (ind1< arr.length)
            return append(result,arr,ind1,arr.length);
        return result;
    }

    public String[] append(String[] newarr, String[] arr, int ind1, int ind2){
        int newLen = 1;
        if (newarr!=null)
            newLen = newarr.length+1;
        String[] NewArr = new String[newLen];
        String el = "";
        for (int i = ind1; i<ind2; i++){
            el = el + arr[i];
        }
        for (int i = 0; i<newLen-1;i++){
            NewArr[i] = newarr[i];
        }
        NewArr[newLen-1] = el;
        return NewArr;
    }
}
