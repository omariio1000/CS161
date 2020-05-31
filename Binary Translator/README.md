 

Problem Solving

 

We will be learning how to use the String class, Character class, int primitive type, char primitive type, and File class.

 

The Goal

The goal of this program is to create a program that will be able to translate numbers from binary to decimal, or vice versa. You must also be able to input numbers either from a file, or from the command line.

 

Implementation

Create a Java project that asks if your input is coming from a file, or if it is coming from the command line. Then read in all of your number, either by typing your input, or reading it in from the corresponding file. It should be only a positive binary or decimal integer. Numbers will be between zero and 65,535. (You should figure out why those particular values…)

There are two common ways to read files. One way (which you will find online) is using a class called BufferedReader. The other way uses a class called Scanner. Either is appropriate, though Scanner uses fewer lines of code:

 

Scanner myscanner = new Scanner(System.in);

String input = myscanner.nextLine();

 

After you have your input, ask the user if they want to translate from decimal to binary, or from binary to decimal. We are assuming they are using positive integers for all of these problems, just to avoid the difficulty of negative integers.

 

Output the appropriate translation. Be sure to delete leading zeros, if you have them!

 

Examples:

 

Input:

10001001

(Choose binary to decimal)

Output:

137

 

Input:

42

(Choose decimal to binary)

Output:

101010


Scores:

Console input: 10 pts

File input: 10 pts

Decimal to Binary conversion: 10pts

Binary to Decimal conversion: 10pts

 

A simple search of “binary to decimal algorithm” will lead you to a plethora of examples of how to switch from one to the other, as will “decimal to binary algorithm” for the other direction. YOU MAY NOT USE BUILT IN FUNCTIONS THAT AUTOMATICALLY DO THE CONVERSION. You must implement the algorithm yourself.
