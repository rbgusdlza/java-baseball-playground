package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 요구사항
 * 1. 빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.
 * 2. 숫자 하나를 문자열로 입력할 경우 해당 숫자를 반환한다.
 * 3. 숫자 두개를 쉼표(,) 구분자로 입력할 경우 두 숫자의 합을 반환한다.
 * 4. 구분자를 쉼표(,) 이외에 콜론(:)을 사용할 수 있다.
 * 5. "//"와 "\n" 문자 사이에 커스텀 구분자를 지정할 수 있다.
 * 6. 음수를 전달하는 경우 RuntimeException 예외 처리를 한다.
 */
public class StringCalculator {

    public int add(String text) {
        if (text == null || text.isEmpty()) return 0;
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
        if (m.find()) {
            String customDelimiter = m.group(1);
            String[] tokens = m.group(2).split(customDelimiter);
            return addNumbersSplitBy(tokens);
        }
        String[] tokens = text.split(",|:");
        return addNumbersSplitBy(tokens);
    }

    private int addNumbersSplitBy(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            int number = Integer.parseInt(token);
            if (number < 0) {
                throw new RuntimeException();
            }
            sum += number;
        }
        return sum;
    }
}
