package PasswordGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PasswordGenerator {
    private ArrayList<Character> password;
    private final char[] shiftedKeysOfNumbers = { ')', '!', '@', '#', '$', '%', '^', '&', '*', '(' };
    private final char[] ASCII = makeASCII();

    PasswordGenerator(String text) throws NullPointerException {
        if (text != null) {
            String[] textPassed = text.split(" ");
            password = new ArrayList<>();

            for (String word : textPassed) {
                password.add(Character.toUpperCase(word.charAt(0)));
            }

            for (int i = textPassed.length - 1; i >= 0; i--) {
                int wordLength = textPassed[i].length();
                char lastLetter = textPassed[i].charAt(wordLength - 1);

                if(wordLength == 1 && !isPunctuationMark(lastLetter)) {
                    password.add(Character.toUpperCase(lastLetter));
                }
                else {
                    while (isPunctuationMark(lastLetter) && wordLength > 0) {
                        wordLength--;
                        lastLetter = textPassed[i].charAt(wordLength - 1);
                    }
                    password.add(Character.toUpperCase(lastLetter));
                }
            }
        }
        else {
            throw new NullPointerException();
        }
    }

    void deletePunctuationMarks() {
        password.removeIf(this::isPunctuationMark);
    }

    void changeLetters(int startPoint, int range) {
        if (startPoint == 0) {
            return;
        }
        else if (startPoint <= 0) {
            startPoint = 25 - (Math.abs(range) % 25);
        }
        else if (startPoint > 25) {
            startPoint %= 25;
        }
        if (range == 0) {
            return;
        }
        else if (range <= 0) {
            range = 9 - (Math.abs(range) % 9);
        }
        else if (range > 9) {
            range %= 9;
        }
        char[] rangeLetters = new char[range];
        for (int i = 0; i < rangeLetters.length; i++) {
            rangeLetters[i] = ASCII[startPoint + i];
        }

        for (char nextChar : rangeLetters) {
            password.replaceAll(ch -> ch == nextChar ?
                    isLetterEven(nextChar) ? changeLetterToNumber(nextChar) : changeLetterToShiftedNumber(nextChar)
                    : ch);
        }
    }

    private boolean isPunctuationMark(Character character) {
        int characterCode = (int) character;
        int firstPunctuationMarkIndex = 32;
        int lastPunctuationMarkIndex = 47;
        return characterCode >= firstPunctuationMarkIndex && characterCode <= lastPunctuationMarkIndex;
    }

    private boolean isLetterEven(char letter) {
        return (int) letter % 2 == 0;
    }

    private boolean isLetterEvenInPassword(int index) {
        return index % 2 == 0;
    }

    private char changeLetterToShiftedNumber(char ch) {
        int indexInShiftedTable = ((int) ch) - 64;
        return shiftedKeysOfNumbers[indexInShiftedTable];
    }

    private char changeLetterToNumber(char ch) {
        int indexInASCIITable = ((int) ch) - 16;
        return (char) (indexInASCIITable);
    }

    void changeAToAtSign() {
        Collections.replaceAll(password, 'A', '@');
    }

    void changeOToZero() {
        Collections.replaceAll(password, 'O', '0');
    }

    private char[] makeASCII() {
        char[] ASCIITable = new char[25];
        for (int i = 0; i < ASCIITable.length; i++) {
            ASCIITable[i] = (char) (i + 65);
        }
        return ASCIITable;
    }

    void replaceEvenLettersToLowerCase() {
        for (int i = 0; i < password.size(); i++) {
            if (isLetterEvenInPassword(i)) {
                Character ch = Character.toLowerCase(password.get(i));
                password.set(i, ch);
            }
        }
    }

    public String getPassword() {
        StringBuffer sb = new StringBuffer();
        for (Character ch : password) {
            sb.append(ch);
        }
        String returnPassword = new String(sb);
        return returnPassword;
    }
}
