package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fest.assertions.api.Assertions.assertThat;
import static tw.core.GameStatus.CONTINUE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tw.core.GameStatus.SUCCESS;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
  private Game game;
  private AnswerGenerator answerGenerator;

  @Before
  public void setup() throws OutOfRangeAnswerException {
    answerGenerator = mock(AnswerGenerator.class);
    when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
    game = new Game(answerGenerator);
  }

  @Test
  public void should_return_guess_result() {

    String result = game.guess(Answer.createAnswer("1 5 6 7")).getResult();

    String regEx = "^\\d{1}A\\d{1}B$";

    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(result);
    boolean isFormatCorrect = matcher.matches();

    assertThat(isFormatCorrect).isEqualTo(true);
    assertThat(result).isEqualTo("1A0B");
  }

  @Test
  public void should_return_status() {

    String status;

    game.guess(Answer.createAnswer("1 0 8 2"));
    status = game.checkStatus();
    assertThat(status).isEqualTo(CONTINUE);

    game.guess(Answer.createAnswer("1 2 3 4"));
    status = game.checkStatus();
    assertThat(status).isEqualTo(SUCCESS);
  }
}
