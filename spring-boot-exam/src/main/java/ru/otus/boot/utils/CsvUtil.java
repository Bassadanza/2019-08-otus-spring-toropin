package ru.otus.boot.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class CsvUtil {

    private static final String[] HEADERS = {"question", "answer"};

    public static CSVParser getCsvParser(Resource resource) throws IOException {
        InputStream is = resource.getInputStream();
        Reader reader = new InputStreamReader(is);
        return CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .parse(reader);
    }
}
