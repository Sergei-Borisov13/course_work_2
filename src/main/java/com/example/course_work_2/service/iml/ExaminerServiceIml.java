package com.example.course_work_2.service.iml;

import com.example.course_work_2.exception.IncorrectQuestionAmountException;
import com.example.course_work_2.model.Question;
import com.example.course_work_2.service.ExaminerService;
import com.example.course_work_2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceIml implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceIml(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {
        if (amount <= 0 || amount > questionService.getAll().size()) {
            throw new IncorrectQuestionAmountException();
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }

        return questions;
    }
}
