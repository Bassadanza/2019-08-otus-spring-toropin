package ru.otus.cources.repository;

import org.apache.commons.csv.CSVParser;
import org.springframework.core.io.Resource;
import ru.otus.cources.model.ExamData;
import ru.otus.cources.utils.CsvUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExamDataRepository {

    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    private List<ExamData> examData = new ArrayList<>();
    private Resource resource;

    public ExamDataRepository(Resource resource) {
        this.resource = resource;
    }

    @PostConstruct
    public void initialize() throws IOException {
        CSVParser csvParser = CsvUtil.getCsvParser(resource);
        csvParser.forEach(e -> {
            String question = e.get(QUESTION).trim();
            String answer = e.get(ANSWER).trim();
            examData.add(new ExamData(question, answer));
        });
    }

    public List<ExamData> getExamData() {
        return this.examData;
    }
}
