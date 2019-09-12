package ru.otus.cources.services;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.cources.model.ExamData;
import ru.otus.cources.repository.ExamDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.util.Locale.ENGLISH;

@Service
public class LocaleService {

    private static final String RU_LOCALE = "ru";
    private static final String EN_LOCALE = "en";
    private static final Locale RUSSIAN = new Locale("ru", "RU");
    private Locale LOCALE = ENGLISH;
    private List<ExamData> examData = new ArrayList<>();
    private final ExamDataRepository examDataRepository;
    private final MessageSource prepareExamMessageSource;

    public LocaleService(MessageSource prepareExamMessageSource,
                         ExamDataRepository examDataRepository) {
        this.prepareExamMessageSource = prepareExamMessageSource;
        this.examDataRepository = examDataRepository;
    }

    public List<ExamData> getExamData() {
        return examData;
    }

    public String getCommonMessage(String code) {
        return prepareExamMessageSource.getMessage(code, null, LOCALE);
    }

    public void changeLocale(String localeCode) {
        switch (localeCode) {
            case RU_LOCALE:
                LOCALE = RUSSIAN;
                examData = examDataRepository.getRusExamData();
                break;
            case EN_LOCALE:
                LOCALE = ENGLISH;
                examData = examDataRepository.getEngExamData();
                break;
            default:
                System.out.println("Invalid input. English locale selected");
                examData = examDataRepository.getEngExamData();
                break;
        }
    }
}
