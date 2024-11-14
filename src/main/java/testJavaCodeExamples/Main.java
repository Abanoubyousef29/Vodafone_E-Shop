package testJavaCodeExamples;

import java.lang.reflect.Array;
import java.util.Scanner;

// this class for testing any java method
public class Main {
    public static void main(String[] args) {

       // Array[] = 25 , 26 , 1 , 2 , 3  ,99 , 70 , 71 , 72 ,73 , 4
        System.out.println("enter the array lenght");
        Scanner scan = new Scanner(System.in);
        int arrayLenght = Integer.parseInt(scan.nextLine());

















    }



















    public static void replaceWord(){

        System.out.println("enter the full statement");
        Scanner scan = new Scanner(System.in);
        String randomText = scan.nextLine();

        System.out.println("enter the old word");
        String oldWord = scan.nextLine();

        System.out.println("enter the new word");
        String newWord = scan.nextLine();

        String[] words = randomText.split(" ");
        String[] modifiedString = new String[words.length];

        for (int i = 0 ; i < words.length ; i++ ){
            if (words[i].equals(oldWord))
                modifiedString[i] = newWord ;

            else {
                // Otherwise, keep the original word
                modifiedString[i] = words[i];
            }
        }

        for (int i = 0 ; i < modifiedString.length ; i++ )
            System.out.print(modifiedString[i]+" ");
    }


    private static void reverse() {

        System.out.println("enter your name");
        Scanner scan = new Scanner(System.in);
        String randomText = scan.nextLine();

        // reverse a text
        char[] charArray = randomText.toCharArray();
        char replace;
        for (int i = 0; i < charArray.length / 2; i++) {
            replace = charArray[i];
            charArray[i] = charArray[charArray.length - 1 - i];
            charArray[charArray.length - 1 - i] = replace;
        }

        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i]);
        }
    }

    private static void replace() {

        System.out.println("enter your name");
        Scanner scan = new Scanner(System.in);
        String randomText = scan.nextLine();

        String vowel = "AaEeIiOoUu";
        // replace letters

        for (int i = 0; i < randomText.length(); i++) {
            if (randomText.charAt(i) == ' ')
                System.out.print('S');
            else if (vowel.indexOf(randomText.charAt(i)) != -1)
                System.out.print('V');
            else
                System.out.print(randomText.charAt(i));
        }
    }
}
