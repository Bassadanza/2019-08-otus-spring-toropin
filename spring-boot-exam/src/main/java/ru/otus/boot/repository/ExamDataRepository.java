package ru.otus.boot.repository;

import ru.otus.boot.model.ExamData;

import java.util.List;

public interface ExamDataRepository {
    List<ExamData> getRusExamData();

    List<ExamData> getEngExamData();
}
