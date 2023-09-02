package com.example.course_work_2.service.iml;

import com.example.course_work_2.exception.QuestionNotFountException;
import com.example.course_work_2.model.Question;
import com.example.course_work_2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();
    @Override
    public Question add(String question, String answer) {
        return add (new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if(!questions.remove(question)){
            throw new QuestionNotFountException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        int number = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(number);
    }
}
