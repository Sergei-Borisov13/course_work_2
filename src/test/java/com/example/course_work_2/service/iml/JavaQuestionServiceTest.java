package com.example.course_work_2.service.iml;

import com.example.course_work_2.exception.QuestionNotFountException;
import com.example.course_work_2.model.Question;
import com.example.course_work_2.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.course_work_2.service.iml.QuestionTextConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    public void beforeEach() {
        questionService.add(QUESTION_1);
        questionService.add(QUESTION_2);
        questionService.add(QUESTION_3);
    }

    @Test
    public void shouldAddQuestion() {
        int beforeCount = questionService.getAll().size();

        assertThat(questionService.add(QUESTION_4))
                .isEqualTo(QUESTION_4)
                .isIn(questionService.getAll());

        assertThat(questionService.getAll()).hasSize(beforeCount + 1);
    }

    @Test
    public void shouldRemoveQuestion() {
        int beforeCount = questionService.getAll().size();

        assertThat(questionService.remove(QUESTION_1))
                .isEqualTo(QUESTION_1)
                .isNotIn(questionService.getAll());

        assertThat(questionService.getAll()).hasSize(beforeCount - 1);
    }

    @Test
    public void shouldThrowQuestionNotFountException() {
        assertThatExceptionOfType(QuestionNotFountException.class)
                .isThrownBy(() -> questionService.remove(new Question("INCORRECT", "INCORRECT")));
    }
    @Test
    public void shouldReturnAllQuestions() {
        assertThat(questionService.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        QUESTION_1,
                        QUESTION_2,
                        QUESTION_3
                );
    }

    @Test
    public void shouldReturnRandomQuestion() {
        assertThat(questionService.getRandomQuestion())
                .isNotNull()
                .isIn(questionService.getAll());
    }



}