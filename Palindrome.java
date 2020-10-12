package com.maxim;
import java.util.Scanner;

public class Palindrome
{
  
    public static boolean isPalindrome1(String s, int first, int last) {

		if(last <= first)
	    	return true;

		// if the characters are equal, check the next ones (closer to each other)
		else if(s.charAt(first) == s.charAt(last)) {
			return isPalindrome1(s, first + 1, last-1);
	    }
		else
	    	return false;
    }

    public static void main(String [] args){
		System.out.println("Please enter the string you would like to check: ");
		Scanner input = new Scanner(System.in);
		String s = input.next();

		int first = 0;
		int last = s.length() - 1;

		if (isPalindrome1(s,first,last))
			System.out.println(s + " is a palindrome");
		else
			System.out.println(s + " is not a palindrome");

	}
}
    
