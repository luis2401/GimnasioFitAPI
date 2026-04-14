package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Instructor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepositorio extends JpaRepository<Instructor, Integer> {

    Optional<Instructor> findByNombreInstructorIgnoreCase(String nombreInstructor);

    @Transactional
    void deleteByNombreInstructorIgnoreCase(String nombreInstructor);
}
