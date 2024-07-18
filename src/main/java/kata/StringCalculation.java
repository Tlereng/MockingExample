package kata;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class StringCalculation {
    String numbers;
    String delimiter = "";

    public int add(String numbers) {
        this.numbers = numbers;
        if (numbers.startsWith("//"))
            setDelimiter(numbers);
        checkNegativeNumbers();
        return sum();
    }

    private void checkNegativeNumbers() {
        int[] negativeNumbers = new int[0];
        if (numbers.contains("-"))
            negativeNumbers = getNegativeNumbers();
        if (negativeNumbers.length != 0)
            throw new IllegalArgumentException("Negatives not allowed: "
                    + Arrays.toString(negativeNumbers));
    }

    private int[] getNegativeNumbers() {
        return getIntStream().filter(n -> n < 0).toArray();
    }

    private int sum() {
        if (numbers.equals(""))
            return 0;
        return getIntStream().filter(num -> num < 1000).sum();
    }

    private IntStream getIntStream() {
        return stream(numbers.split("[,\\n]" + delimiter))
                .mapToInt(Integer::parseInt);
    }

    private void setDelimiter(String s) {
        numbers = s.substring(2);
        String[] strings = numbers.split("\\n", 2);
        delimiter = strings[0];
        checkDelimiter();
        numbers = strings[1];
    }

    private void checkDelimiter() {
        if (delimiter.startsWith("["))
            delimiter = getReformattedDelimiter();
        else
            delimiter = "|[" + delimiter + "]";
    }

    private String  getReformattedDelimiter() {
        return delimiter
                .replace("[", "|(")
                .replace("]", ")")
                .replace("|", "\\|")
                .replace("\\|(", "|(")
                .replace("*", "\\*");
    }
}