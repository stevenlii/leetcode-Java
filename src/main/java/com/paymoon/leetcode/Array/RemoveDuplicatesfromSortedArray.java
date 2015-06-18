package com.paymoon.leetcode.Array;

public class RemoveDuplicatesfromSortedArray {
public static void main(String[] args) {
	String[] strings = {"1","2","3","5","4","5"};
	String value = "5";
	String j = remove(strings, value);
//	System.out.println(j);
	
}
public static String remove(String[] strings, String value) {
	int i = 0;
	int j = 0;
	for ( i = 0; i < strings.length; i++) {
		
		if (strings[i].equals(value)) {
			continue;
		}
		strings[j] = strings[i];
		j++;
	}
//	System.out.println(strings.length);
	for (int j2 = 0; j2 < strings.length; j2++) {
		System.out.println(strings[j2]);
	}
	return String.valueOf(j);
}
}
