package com.paymoon.leetcode.Array;

public class RemoveDuplicatesfromSortedArray {
public static void main(String[] args) {
	String[] strings = {"1","2","3","3","3","4","4","5"};
	String value = "5";
	String j = remove(strings, value);
	System.out.println("result is: " + j);
	
}
public static String remove(String[] strings, String value) {
	if (strings.length == 0) {
		return "0";
	}
	   int j = 0;
       for(int i = 1; i < strings.length; i++) {
           if(strings[j] != strings[i]) {
               strings[++j] = strings[i];
           }
       }
   //	System.out.println(strings.length);
	for (int j2 = 0; j2 < strings.length; j2++) {
		System.out.println(strings[j2]);
	}
	return String.valueOf(j+1);
}
}
