package nl.gerimedica.demo.controllers;

import lombok.RequiredArgsConstructor;

import nl.gerimedica.demo.dtos.ExerciseDto;
import nl.gerimedica.demo.dtos.ResponseDto;
import nl.gerimedica.demo.exceptions.ExceptionCode500;
import nl.gerimedica.demo.services.ExerciseService;
import nl.gerimedica.demo.utils.FileProcessor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseDto> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (FileProcessor.fileHasCSVFormat(file)) {
            try {

                exerciseService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(message));
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> fetchAll() {

        try {
            List<ExerciseDto> exerciseDtoList = exerciseService.getAllExercises();

            if (exerciseDtoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(exerciseDtoList, HttpStatus.OK);
        } catch (Exception e) {
            throw new ExceptionCode500();
        }

    }

    @GetMapping("/{code}")
    public ResponseEntity<ExerciseDto> fetchOneByCodeAll(@PathVariable String code) {
        return new ResponseEntity<>(exerciseService.getOneByCode(code), HttpStatus.OK);
    }


    @DeleteMapping
    public void deleteAll() {
       exerciseService.deleteAllData();
    }

}