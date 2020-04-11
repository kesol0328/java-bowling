package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

public class AnswersTest {
    private Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Contents1");
    private Answer answer2 = new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q1, "Contents2");
    private Answer answer3 = new Answer(13L, UserTest.SANJIGI, QuestionTest.Q1, "Contents3");

    @Test
    void checkDeletable_success() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        answers.checkDeletable(UserTest.JAVAJIGI);
    }

    @Test
    void checkDeletable_fail() {
        Answers answers = new Answers(Arrays.asList(answer1, answer3));
        Assertions.assertThatThrownBy(
                () -> answers.checkDeletable(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
