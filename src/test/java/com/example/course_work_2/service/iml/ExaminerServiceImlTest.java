package com.example.course_work_2.service.iml;

import com.example.course_work_2.exception.IncorrectQuestionAmountException;
import com.example.course_work_2.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.course_work_2.service.iml.QuestionTextConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImlTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceIml examinerService;

    @Test
    public void shouldThrowIncorrectQuestionAmountException() {
        when(questionService.getAll()).thenReturn(MOCK_QUESTIONS);

        assertThatExceptionOfType(IncorrectQuestionAmountException.class)
                .isThrownBy(() -> examinerService.getQuestion(-1));
        assertThatExceptionOfType(IncorrectQuestionAmountException.class)
                .isThrownBy(() -> examinerService.getQuestion(MOCK_QUESTIONS.size() + 1));
    }

    @Test
    public void shouldReturnQuestion() {
        when(questionService.getAll()).thenReturn(MOCK_QUESTIONS);
        when(questionService.getRandomQuestion()).thenReturn(
                QUESTION_1,
                QUESTION_2,
                QUESTION_3,
                QUESTION_4
        );

        int questionAmount = MOCK_QUESTIONS.size() - 2;

        assertThat(examinerService.getQuestion(questionAmount))
                .hasSize(questionAmount);
    }

}
