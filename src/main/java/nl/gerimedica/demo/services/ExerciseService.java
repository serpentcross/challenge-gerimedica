package nl.gerimedica.demo.services;

import lombok.RequiredArgsConstructor;

import nl.gerimedica.demo.dtos.ExerciseDto;
import nl.gerimedica.demo.mappers.ExerciseMapper;
import nl.gerimedica.demo.persistence.entities.Exercise;
import nl.gerimedica.demo.persistence.repositories.ExerciseRepository;
import nl.gerimedica.demo.utils.FileProcessor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static nl.gerimedica.demo.utils.Converters.convertDateToString;
import static nl.gerimedica.demo.utils.Converters.convertStringToLocalDateTime;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper exerciseMapper;

    public void save(MultipartFile file) throws ParseException {

        try {

            for (ExerciseDto exerciseDto : FileProcessor.csvToTutorials(file.getInputStream())) {
                exerciseRepository.save(

                    Exercise.builder()
                        .source(exerciseDto.getSource())
                        .codeListCode(exerciseDto.getCodeListCode())
                        .code(exerciseDto.getCode())
                        .displayValue(exerciseDto.getDisplayValue())
                        .longDescription(exerciseDto.getLongDescription())
                        .fromDate(convertStringToLocalDateTime(exerciseDto.getFromDate()))
                        .toDate(convertStringToLocalDateTime(exerciseDto.getToDate()))
                        .sortingPriority(exerciseDto.getSortingPriority())
                    .build()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to upload csv data: " + e.getMessage());
        }
    }

    public List<ExerciseDto> getAllExercises() {
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        for (Exercise exercise : exerciseRepository.findAll()) {
            exerciseDtoList.add(
                ExerciseDto.builder()
                    .source(exercise.getSource())
                    .codeListCode(exercise.getCodeListCode())
                    .code(exercise.getCode())
                    .displayValue(exercise.getDisplayValue())
                    .longDescription(exercise.getLongDescription())
                    .fromDate(convertDateToString(exercise.getFromDate()))
                    .toDate(convertDateToString(exercise.getToDate()))
                    .sortingPriority(exercise.getSortingPriority())
                .build()
            );
        }
        return exerciseDtoList;
    }

    public ExerciseDto getOneByCode(String code) {
        ExerciseDto exerciseDto = null;
        Optional<Exercise> exerciseOptional = exerciseRepository.findByCode(code);
        if (exerciseOptional.isPresent()) {
            exerciseDto = exerciseMapper.entityToDto(exerciseOptional.get());
        }
        return exerciseDto;
    }

    public void deleteAllData() {
        exerciseRepository.deleteAll();
    }

}