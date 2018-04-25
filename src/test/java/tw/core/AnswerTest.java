package tw.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

  @Test
  public  void should_return_created_Answer() {
      String inputStr = "1 2 3 4";
      List<String> inputList = Answer.createAnswer(inputStr).getNumList();
      List<String> resultList= Arrays.asList("1","2","3","4");
      assertThat(inputList).isEqualTo(resultList);
  }

  @Rule
  public ExpectedException expectedEx = ExpectedException.none();

  @Test
  public void whether_throw_exception () throws OutOfRangeAnswerException {
      Answer inputAnswer = new Answer();
      List<String> inputAnswerList = Arrays.asList("11","5","6","5");
      inputAnswer.setNumList(inputAnswerList);

      expectedEx.expect(OutOfRangeAnswerException.class);
      expectedEx.expectMessage("Answer format is incorrect");
      inputAnswer.validate();
  }

  @Test
  public void should_return_Record() {
        Answer inputAnswer = new Answer();
        List<String> inputAnswerList = Arrays.asList("1","5","6","7");
        inputAnswer.setNumList(inputAnswerList);

        Answer answer = new Answer();
        List<String> correctAnswer = Arrays.asList("1","2","3","4");
        answer.setNumList(correctAnswer);

        int numOfA = answer.check(inputAnswer).getValue()[0];
        int numOfB = answer.check(inputAnswer).getValue()[1];

        assertThat(numOfA).isEqualTo(1);
        assertThat(numOfB).isEqualTo(0);
  }

  @Test
   public void should_return_index_of_num() {
      Answer answer = new Answer();
      List<String> correctAnswer = Arrays.asList("1","2","3","4");
      answer.setNumList(correctAnswer);

      int indexOfNum = answer.getIndexOfNum("3");

      assertThat(indexOfNum).isEqualTo(2);
   }

}