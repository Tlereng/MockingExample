package kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringCalculationTest {
    StringCalculation calculator = new StringCalculation();

    @ParameterizedTest
    @CsvSource({
            "'', 0",
            "'1', 1",
            "'1,2', 3",
            "'1,2,2', 5"})
    void addReturnsSumOfInputNumbers(String numbers, int expected) {
        assertThat(calculator.add(numbers)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,3\n2', 6",
            "'//;\n1;2', 3",
            "'//¨\n1,2\n3¨4', 10",
            "'//[***]\n1,2\n3***4', 10",
            "'//[±s±]\n1,2\n3±s±4', 10",
            "'//[±s&±][¶¶|]\n1±s&±2\n3¶¶|4', 10",
            "'//[±s&±][¶¶|][«ø»]\n1±s&±7«ø»1001¶¶|4', 12"})
    void addAcceptsSpecificDelimiterAndReturnsSumOfInputNumbers(String numbers, int expected) {
        assertThat(calculator.add(numbers)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'-1,2', '[-1]'",
            "'//¨\n-3¨-4', '[-3, -4]'"})
    void inputStringWithNegativeNumberResultInException(String numbers, String expected) {
        assertThatThrownBy(() -> calculator.add(numbers)).hasMessage("Negatives not allowed: " + expected)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,3,1000', 4",
            "'//;\n1100;2', 2",
            "'//¨\n1,2\n3¨999', 1005"})
    void allNumbersOverOneThousandIsExcluded(String numbers, int expected) {
        assertThat(calculator.add(numbers)).isEqualTo(expected);
    }
}