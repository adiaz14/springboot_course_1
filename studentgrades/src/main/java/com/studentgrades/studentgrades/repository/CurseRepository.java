package com.studentgrades.studentgrades.repository;

import com.studentgrades.studentgrades.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c WHERE c.name = :nameParam")
    Optional<Course> findByName(@Param("nameParam") String name);

    @Query("SELECT c FROM Course c WHERE c.name LIKE %:nameParam%")
    List<Course> findByNameContaining(@Param("nameParam") String name);
}
