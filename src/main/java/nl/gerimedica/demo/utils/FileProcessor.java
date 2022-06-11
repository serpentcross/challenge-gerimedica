package nl.gerimedica.demo.utils;

import nl.gerimedica.demo.dtos.ExerciseDto;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public final static String TYPE = "text/csv";

    public static boolean fileHasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<ExerciseDto> csvToTutorials(InputStream is) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            CSVParser csvParser = new CSVParser(
                fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())
            ) {

            List<ExerciseDto> exerciseDtoList = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                exerciseDtoList.add(
                    ExerciseDto.builder()
                        .source(checkIfEmpty(csvRecord.get("source")))
                        .code(checkIfEmpty(csvRecord.get("code")))
                        .codeListCode(checkIfEmpty(csvRecord.get("codeListCode")))
                        .displayValue(checkIfEmpty(csvRecord.get("displayValue")))
                        .longDescription(checkIfEmpty(csvRecord.get("longDescription")))
                        .fromDate(checkIfEmpty(csvRecord.get("fromDate")))
                        .toDate(checkIfEmpty(csvRecord.get("toDate")))
                        .sortingPriority(checkIntegerField(csvRecord.get("sortingPriority")))
                    .build()
                );
            }

            return exerciseDtoList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static String checkIfEmpty(String field) {
        return field.isEmpty() ? null : field;
    }

    public static Integer checkIntegerField(String field) {
        if (field != null && !field.isEmpty()) {
            return Integer.parseInt(field);
        } else {
            return null;
        }
    }

}