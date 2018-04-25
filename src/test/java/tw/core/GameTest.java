package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fest.assertions.api.Assertions.assertThat;
import static tw.core.GameStatus.CONTINUE;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {
  private Game game;
  private  Answer inputAnswer = new Answer();

  @Before
  public void setup() throws OutOfRangeAnswerException {
    RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
    AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
    game = new Game(answerGenerator);
  }

  @Test
  public void should_return_guess_result() {

    //此处可以mock数据
    List<String> inputAnswerList = Arrays.asList("1","5","6","7");
    inputAnswer.setNumList(inputAnswerList);

    String result = game.guess(inputAnswer).getResult();

    String regEx = "^\\d{1}A\\d{1}B$";

    Pattern pattern = Pattern.compile(regEx);
    Matcher matcher = pattern.matcher(result);
    boolean isFormatCorrect = matcher.matches();

    assertThat(isFormatCorrect).isEqualTo(true);
  }

  @Test
  public void should_return_status() {
    Answer inputAnswer1 = new Answer();
    List<String> inputAnswerList1 = Arrays.asList("1","5","6","7");
    inputAnswer1.setNumList(inputAnswerList1);

    Answer inputAnswer2 = new Answer();
    List<String> inputAnswerList2 = Arrays.asList("1","2","3","4");
    inputAnswer2.setNumList(inputAnswerList2);

    game.guess(inputAnswer1);
    game.guess(inputAnswer2);

    String status = game.checkStatus();

    assertThat(status).isEqualTo(CONTINUE);
  }
}
