package ru.otus.boot.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.boot.model.ExamData;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class ExamService implements CommandLineRunner {

    private static final String DELIMITER = " ";
    private static final String CHOOSE_YOUR_LANGUAGE = "Choose your language (en/ru)";
    private static final String RESULT_MESSAGE = "resultMessage";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ANONYMOUS = "anonymous";

    private final IOService ioService;
    private final LocaleService localeService;

    public ExamService(IOService ioService,
                       LocaleService localeService) {
        this.ioService = ioService;
        this.localeService = localeService;
    }

    @Override
    public void run(String... args) {
        proceedExam();
    }

    public void proceedExam() {
        final String localeCode = ioService.askQuestion(CHOOSE_YOUR_LANGUAGE);
        localeService.changeLocale(localeCode);
        List<ExamData> examData = localeService.getExamData();
        String firstName = askCommonQuestion(FIRST_NAME);
        String lastName = askCommonQuestion(LAST_NAME);
        AtomicInteger score = new AtomicInteger(0);
        examData.forEach(e -> {
                    String rightAnswer = e.getAnswer();
                    String userAnswer = ioService.askQuestion(e.getQuestion());
                    if (userAnswer.equalsIgnoreCase(rightAnswer)) {
                        score.getAndIncrement();
                    }
                }
        );
        String studentName = firstName + DELIMITER + lastName;

        if (isBlank(studentName)) {
            studentName = localeService.getCommonMessage(ANONYMOUS);
        }
        ioService.printMessage(
                format(localeService.getCommonMessage(RESULT_MESSAGE), studentName, score, examData.size())
        );
    }

    private String askCommonQuestion(String question) {
        return ioService.askQuestion(localeService.getCommonMessage(question));
    }
}
