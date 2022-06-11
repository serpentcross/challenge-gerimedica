package nl.gerimedica.demo.persistence.repositories;

import nl.gerimedica.demo.persistence.entities.Exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    Optional<Exercise> findByCode(String code);
}