package ru.otus.boot.repository.impl;

import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import ru.otus.boot.model.ExamData;
import ru.otus.boot.repository.ExamDataRepository;
import ru.otus.boot.utils.CsvUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExamDataCsvRepository implements ExamDataRepository {

    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private final Resource englishResource;
    private final Resource russianResource;
    private List<ExamData> rusExamData = new ArrayList<>();
    private List<ExamData> engExamData = new ArrayList<>();

    public ExamDataCsvRepository(@Qualifier("en") Resource englishResource,
                                 @Qualifier("ru") Resource russianResource) {
        this.englishResource = englishResource;
        this.russianResource = russianResource;
    }

    @PostConstruct
    public void initialize() throws IOException {
        initializeDataList(rusExamData, russianResource);
        initializeDataList(engExamData, englishResource);
    }

    public List<ExamData> getRusExamData() {
        return this.rusExamData;
    }

    public List<ExamData> getEngExamData() {
        return this.engExamData;
    }

    private void initializeDataList(List<ExamData> dataList, Resource resource) throws IOException {
        CSVParser csvParser = CsvUtil.getCsvParser(resource);
        csvParser.forEach(e -> {
            String question = e.get(QUESTION).trim();
            String answer = e.get(ANSWER).trim();
            dataList.add(new ExamData(question, answer));
        });
    }
}
