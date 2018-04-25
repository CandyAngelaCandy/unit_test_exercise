package tw.core;

import org.junit.Test;
import tw.validator.InputValidator;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    @Test
    public void should_return_whether_validate() {
        InputValidator inputValidator = new InputValidator();

        assertThat(inputValidator.validate("3 4 5 6")).isEqualTo(true);
        assertThat(inputValidator.validate("3 4 5 6 7")).isEqualTo(false);
        assertThat(inputValidator.validate("3 4 5 10")).isEqualTo(false);
        
        assertThat(inputValidator.validate("3 4 5 3")).isEqualTo(false);
    }

}
