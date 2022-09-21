package sample;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SoundexTest {

    Soundex soundex;

    @BeforeEach
    void setUp() {
        soundex = new Soundex();
    }

    @Test
    @Order(1)
    void should_return_sole_letter_of_one_letter_word() {
        assertThat(soundex.encode("A")).isEqualTo("A000");
    }

    @Test
    @Order(2)
    void should_pads_with_zero_to_ensure_three_digits() {
        assertThat(soundex.encode("I")).isEqualTo("I000");
    }

    @Test
    @Order(3)
    void should_replace_consonants_with_appropriate_digits() {
        assertThat(soundex.encode("AX")).isEqualTo("A200");
    }

    @Test
    @Order(4)
    void should_ignore_non_alphabetic() {
        assertThat(soundex.encode("A#")).isEqualTo("A000");
    }

    @Test
    @Order(5)
    void should_replace_multiple_consonants_with_digits() {
        assertThat(soundex.encode("ACDL")).isEqualTo("A234");
    }

    @Test
    @Order(6)
    void should_limit_length_to_four_characters() {
        assertThat(soundex.encode("DCDLB")).hasSize(4);
    }

    @Test
    @Order(7)
    void should_ignore_vowels() {
        assertThat(soundex.encode("BAEIOUHYCDL")).isEqualTo("B234");
    }

    @Test
    @Order(8)
    void should_combine_duplicate_encodings() {
        assertThat(soundex.encodedDigit('B')).isEqualTo(soundex.encodedDigit('F'));
        assertThat(soundex.encodedDigit('C')).isEqualTo(soundex.encodedDigit('G'));
        assertThat(soundex.encodedDigit('D')).isEqualTo(soundex.encodedDigit('T'));

        assertThat(soundex.encode("ABFCGDT")).isEqualTo("A123");
    }

    @Test
    @Order(9)
    void should_ignore_double_letters_and_voyels() {
        assertThat(soundex.encode("BAAeEiIoOuUHHyYcdl")).isEqualTo("B234");
    }
}
