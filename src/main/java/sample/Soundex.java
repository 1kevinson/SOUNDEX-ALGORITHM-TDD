package sample;

import java.util.HashMap;

public class Soundex {

    public static final int MAX_CODE_LENGTH = 4;

    public String encode(String word) {
        return zeroPad(head(word).concat(encodedDigits(tail(word))));
    }

    private String tail(String word) {
        return word.substring(1);
    }

    private String head(String word) {
        return word.substring(0, 1);
    }

    private String encodedDigits(String word) {
        var encoding = "";

        for (Character character: word.toCharArray()) {
            if (isComplete(encoding)) break;

            if (isTwoAdjacentLettersHaveTheSame(encoding, character)) {
                encoding += encodedDigit(character);
            }
        }
        return encoding;
    }

    private boolean isTwoAdjacentLettersHaveTheSame(String encoding, Character character) {
        return !encodedDigit(character).equals(lastDigit(encoding));
    }

    private String lastDigit(String encoding) {
        if (encoding.isEmpty()) return "";
        return encoding.substring(encoding.length() - 1);
    }

    private Character upperFront(char letter) {
        return Character.toUpperCase(letter);
    }

    private boolean isComplete(String encoding) {
        return encoding.length() == MAX_CODE_LENGTH - 1;
    }

    public String encodedDigit(Character letter) {
        var encodingsData = new HashMap<Character,String>();

        // Encoding with 1
        encodingsData.put('B',"1");
        encodingsData.put('F',"1");
        encodingsData.put('P',"1");
        encodingsData.put('V',"1");

        // Encoding with 2
        encodingsData.put('C',"2");
        encodingsData.put('G',"2");
        encodingsData.put('J',"2");
        encodingsData.put('K',"2");
        encodingsData.put('Q',"2");
        encodingsData.put('S',"2");
        encodingsData.put('X',"2");
        encodingsData.put('Z',"2");

        // Encoding with 3
        encodingsData.put('D',"3");
        encodingsData.put('T',"3");

        // Encoding with 4
        encodingsData.put('L',"4");

        // Encoding with 5
        encodingsData.put('M',"5");
        encodingsData.put('N',"5");

        // Encoding with 6
        encodingsData.put('R',"6");

        return encodingsData.getOrDefault(upperFront(letter),"");
    }

    public String zeroPad(String word) {
        final var numberOfZeroNeeded = MAX_CODE_LENGTH - word.length();
        return word.concat("0".repeat(numberOfZeroNeeded));
    }
}
