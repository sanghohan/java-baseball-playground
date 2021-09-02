package study;

import org.assertj.core.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void split(){
        String actual = "1,2";
        String[] split = actual.split(",");
        assertThat(split).contains("1", "2");
        assertThat(split).containsExactly("1", "2");
    }

    @Test
    void substring() {
        String actual = "(1,2)";
        String result = actual.substring(1, 4);
        assertThat(result).isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt 테스트")
    void charAt() {
        String actual = "abc";
        String position = String.valueOf(actual.charAt(1));
        assertThat(position).isEqualTo("b");
        assertThatThrownBy(() -> {
            String wrongPosition = String.valueOf(actual.charAt(4));
        }).isInstanceOf(IndexOutOfBoundsException.class).hasMessageContaining("String index out of range: 4");
    }

    @ParameterizedTest
    @ValueSource(strings = { "1", "2", "3" })
    void contains(String input) {
        Set numbers = new HashSet(3);
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");

        assertTrue(numbers.contains(input));
    }

    @ParameterizedTest
    @CsvSource(value = { "1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':' )
    void containsTrueOrFalse(String input, boolean expected) {
        Set numbers = new HashSet(3);
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");

        assertEquals(expected, numbers.contains(input));
    }
}
