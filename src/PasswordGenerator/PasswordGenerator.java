package PasswordGenerator;

import java.util.ArrayList;
import java.util.List;

class PasswordGenerator {
    private ArrayList<String> password;

    PasswordGenerator(String text) throws NullPointerException {
        if (text != null) {
            this.password = new ArrayList<>(List.of(text.split(" ")));
            deletePunctuationMarks();
            upperCaseFirstLetters();
        }
        else {
            throw new NullPointerException();
        }
    }

    private void deletePunctuationMarks() {
        ArrayList<String> textProcessed = new ArrayList<>(this.password);
        for (String word : this.password) {
            if (word.matches("[.!?\\-]")) {
                textProcessed.remove(word);
            }
        }
        this.password = textProcessed;
    }

    private void upperCaseFirstLetters() {
        ArrayList<String> textProcessed = new ArrayList<>();
        for (String word : this.password) {
            word = word.toUpperCase();
            char firstLetter = word.charAt(0);
            word = String.valueOf(firstLetter);
            textProcessed.add(word);
        }
        this.password = textProcessed;
    }

    public String getPassword() {
        StringBuffer sb = new StringBuffer();
        for (String word : this.password) {
            sb.append(word);
        }
        String returnPassword = new String(sb);
        return returnPassword;
    }
}
