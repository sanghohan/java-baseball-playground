package study;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterStringCalculatorTest {
    @Test
    void calculatorForString() {
        String inputString = "2 + 3 * 4 / 2";
        String[] values = inputString.split(" ");

        double result = 0;
        for (int i = 0; i < values.length; i++) {

            if(i == (values.length - 1))
                break;

            if (CalculatorMap.of(values[i]) != null) {
                if (i > 2)
                    values[i - 1] = String.valueOf((int) result);

                result = CalculatorMap.of(values[i]).operation.operation(Integer.parseInt(values[i - 1]),
                        Integer.parseInt(values[i + 1]));
            }
        }

        assertThat(result).isEqualTo(10);

    }

    enum CalculatorMap {

        PLUS("+", new CharacterStringCalculatorTest.plus()),
        MINUS("-", new CharacterStringCalculatorTest.minus()),
        MULTIPLICATION("*", new CharacterStringCalculatorTest.multiplication()),
        DIVISION("/", new CharacterStringCalculatorTest.division());

        String operator;
        Operation operation;

        CalculatorMap(String operator, Operation operation) {

            this.operator = operator;
            this.operation = operation;
        }

        private static Map<String, CalculatorMap> map = new HashMap<>();

        static {
            for (CalculatorMap calculatorMap : CalculatorMap.values()) {
                map.put(calculatorMap.operator, calculatorMap);
            }
        }

        public static CalculatorMap of(String operator) {

            if (map.get(operator) == null) {
                return null;
            }

            return map.get(operator);
        }

    }

    interface Operation {
        double operation(int a, int b);
    }


    public static class plus implements Operation {

        @Override
        public double operation(int a, int b) {
            return a + b;
        }
    }

    public static class minus implements Operation {

        @Override
        public double operation(int a, int b) {
            return a - b;
        }
    }

    public static class multiplication implements Operation {

        @Override
        public double operation(int a, int b) {
            return a * b;
        }
    }

    public static class division implements Operation {

        @Override
        public double operation(int a, int b) {
            return a / b;
        }
    }

}
