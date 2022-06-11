package nl.gerimedica.demo.mappers;

import nl.gerimedica.demo.dtos.ExerciseDto;
import nl.gerimedica.demo.persistence.entities.Exercise;

import nl.gerimedica.demo.utils.Converters;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE, componentModel = "spring")
public interface ExerciseMapper {

    @Mappings({
        @Mapping(source = "exercise", target = "fromDate", qualifiedByName = "getFromDate"),
        @Mapping(source = "exercise", target = "toDate", qualifiedByName = "getToDate"),
    })
    ExerciseDto entityToDto(Exercise exercise);

    Exercise dtoToEntity(ExerciseDto exerciseDto);

    @Named("getFromDate")
    default String getFromDate(Exercise exercise) {
        return Converters.convertDateToString(exercise.getFromDate());
    }

    @Named("getToDate")
    default String getToDate(Exercise exercise) {
        return Converters.convertDateToString(exercise.getToDate());
    }

}