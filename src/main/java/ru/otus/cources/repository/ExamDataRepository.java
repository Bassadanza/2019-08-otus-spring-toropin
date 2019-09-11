package ru.otus.cources.repository;

import ru.otus.cources.model.ExamData;

import java.util.List;

public interface ExamDataRepository {
    List<ExamData> getRusExamData();

    List<ExamData> getEngExamData();
}
