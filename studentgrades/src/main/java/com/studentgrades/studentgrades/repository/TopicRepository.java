package com.studentgrades.studentgrades.repository;

import com.studentgrades.studentgrades.model.Course;
import com.studentgrades.studentgrades.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    Optional<Topic> findByName(String name);
    List<Topic> findByCourse(Course course);
}
