package com.company;

import static java.lang.Integer.valueOf;

public class Main {

    public static void main(String[] args){
    StringCalculator calc = new StringCalculator();
    String str = "//[**]\n4**6\n7**12";
    int result = calc.Add(str);
    System.out.println("Sum is: " +result+ "\n(-1 is the code of the exeption of negative nums)" );
    }
}

class StringCalculator extends Spliter{

    public int Add(String numbers){
        if (numbers.equals(""))
            return 0;
        String delimeter = Delimeter(numbers);
        numbers = withoutBeg(numbers);
        numbers = numbers.replace("\n", delimeter);
        String[] arr = SplitByDel(numbers, delimeter);
        String[] negative = null;
        int sum = 0;
        for (int i = 0; i < arr.length;i++){
            if (valueOf(arr[i])>=0){
                if (valueOf(arr[i])<=1000)
                    sum += valueOf(arr[i]);}
            else {negative = append(negative,arr,i,i+1);}}
        if (negative!=null){
            System.out.println("negatives not allowed");
            for (int i = 0; i < negative.length;i++)
                System.out.println(String.format("%s", negative[i]));
            return -1;
        }
        return sum;
    }

    private String Delimeter(String numbers){
        if (anoutherDelimeter(numbers)) {
            return numbers.split("")[2];
        }
        else if (multipDelimeter(numbers)){
            int ind1 = 0, ind2 = 0;
            String[] numS = numbers.split("");
            for (int i = 0; i< numS.length; i++){
                if (numS[i].equals("[")) ind1 =i+1;
                if (numS[i].equals("]")&&numS[i+1].equals("\n")) ind2 = i;
            }
            String del = "";
            for (int i = ind1; i<ind2; i++){
                del = del+ numS[i];
            }
            return del;
        }
        return ",";
    }

    private boolean anoutherDelimeter(String numbers){
        String[] nums = numbers.split("");
        if (nums.length<4)
            return false;
        if (nums[0].equals("/") && nums[1].equals("/") && nums[3].equals("\n"))
            return true;
        return false;
    }
    private boolean multipDelimeter(String numbers) {
       String[] numS = numbers.split("");
       if (numS.length<5)
           return false;
       if (!(numS[0].equals("/")&&numS[1].equals("/")&&numS[2].equals("[")))
           return false;
       for (int i = 5; i< numS.length; i++){
           if (numS[i].equals("]")&&(numS[i+1].equals("\n")))
               return true;
       }
       return false;
    }

    private String withoutBeg(String numbers){
        String[] numS = numbers.split("");
        String res = "";
        if (anoutherDelimeter(numbers)){
            for (int i = 4; i < numS.length;i++)
                res = res  + numS[i];
        }
        else if (multipDelimeter(numbers)){
            for (int i = 5+Delimeter(numbers).length(); i < numS.length;i++)
                res = res  + numS[i];
        }
        else res = numbers;
        return res;
    }

}

class Spliter{

    public String[] SplitByDel(String str, String del){
        String[] arr = str.split("");
        String[] result = null;
        int ind1 = 0, ind2 = 0;
        if (del.length()==1){
        for (int i = 0; i< arr.length;i++){
            if (arr[i].equals(del)){
                ind2 = i;
                result = append(result,arr,ind1,ind2);
                ind1 = i+1;
            }
        }
        if (ind1< arr.length)
            result =  append(result,arr,ind1,arr.length);
        return result;}
        else{
            int index = str.lastIndexOf(del);
            while(index!=-1){
                result = append(result,arr,index+del.length(), arr.length);
                arr = removeLast(arr, arr.length - index );
                str="";
                for (int i = 0; i< arr.length;i++) str = str+arr[i];
                index = str.lastIndexOf(del);
            }
            result = append(result, arr, 0, arr.length);
            return result;
        }
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

    public String[] removeLast(String[] arr, int n){
        String[] newArr = new String[arr.length - n];
        for (int i = 0; i< newArr.length; i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }
}
