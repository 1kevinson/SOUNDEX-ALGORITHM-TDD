package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SoundexTest {

    @Test
    void should_return_sole_letter_of_one_letter_word() {
        Soundex soundex = new Soundex();

        final var encoded = soundex.encode("A");

    }
}
