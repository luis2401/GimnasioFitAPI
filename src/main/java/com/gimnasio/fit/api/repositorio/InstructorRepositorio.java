package com.gimnasio.fit.api.repositorio;

import com.gimnasio.fit.api.modelo.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepositorio extends JpaRepository<Instructor, Integer> {

    Instructor findBynombreInstructor(String nombreInstructor);

}
