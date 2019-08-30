package ru.otus.cources;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.cources.model.ExamData;
import ru.otus.cources.services.CommandLineService;
import ru.otus.cources.repository.ExamDataRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class StudentExamApplication {

    private static final String DELIMITER = " ";
    private static final String RESULT_MESSAGE = "%s, there was %s right answers from %s";
    private static final String YOUR_FIRST_NAME = "Your first name:";
    private static final String YOUR_LAST_NAME = "Your last name:";
    private static final String ANONYMOUS = "Anonymous";
    private static final String CONTEXT_XML = "/context.xml";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_XML);
        ExamDataRepository examDataRepository = context.getBean(ExamDataRepository.class);
        CommandLineService commandLineService = context.getBean(CommandLineService.class);
        List<ExamData> examData = examDataRepository.getExamData();
        String firstName = commandLineService.askQuestion(YOUR_FIRST_NAME);
        String lastName = commandLineService.askQuestion(YOUR_LAST_NAME);
        AtomicInteger score = new AtomicInteger(0);
        examData.forEach(e -> {
                    String answer = commandLineService.askQuestion(e.getQuestion());
                    if (answer.equalsIgnoreCase(e.getAnswer())) {
                        score.getAndIncrement();
                    }
                }
        );
        String studentName = firstName + DELIMITER + lastName;

        if (isBlank(studentName)) {
            studentName = ANONYMOUS;
        }
        System.out.println(format(RESULT_MESSAGE, studentName, score, examData.size()));
    }
}
