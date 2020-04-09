package PasswordGenerator;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String text = null;

        System.out.println("Provide the text and the generator will produce a password from it:");

        try {
            text = input.nextLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        PasswordGenerator passwordGenerator = new PasswordGenerator(text);
        passwordGenerator.deletePunctuationMarks();
        passwordGenerator.changeLetters(1, 5);
        passwordGenerator.changeOToZero();
        passwordGenerator.changeAToAtSign();
        passwordGenerator.replaceEvenLettersToLowerCase();
        String password = passwordGenerator.getPassword();
        System.out.println(password);
    }
}
