package com.studentgrades.studentgrades.service;

import com.studentgrades.studentgrades.dto.CourseDto;
import com.studentgrades.studentgrades.model.Course;
import com.studentgrades.studentgrades.repository.CurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Course class to manage courses services
 */
@Service
public class CurseService {

    @Autowired
    CurseRepository curseRepository;

    /**
     * Save new course
     * @param course a course object to be saved
     * @return the new course created
     * @throws Exception
     */
    public Course createCurse(CourseDto course) throws Exception {
        try
        {
            if(curseRepository.findByName(course.getName()).isPresent())
            {
                //Throw new exception
                throw new Exception("the course your are trying to create (" + course.getName() + ") already exists.");
            }else
            {
                //Set new course
                Course newCourse = new Course();
                newCourse.setName(course.getName());
                newCourse.setMode(course.getMode());
                newCourse.setEndDate(course.getEndDate());

                //Save new course
                return curseRepository.save(newCourse);
            }

        }catch (Exception e)
        {
            //Throw exception
            throw new Exception("while creating new curse: " + e.getMessage());
        }
    }

    /**
     * Get all courses
     * @return a list of Courses
     */
    public List<Course> getAll() throws Exception {
        try
        {
            //Get and return all courses
            return curseRepository.findAll();
        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while retrieving all courses: " + e.getMessage());
        }
    }

    /**
     * Get a course by id
     * @param id an Integer that represent the id of the course to be searched
     * @return a single course
     * @throws Exception
     */
    public Optional<Course> getById(Integer id) throws Exception {
        try
        {
            //Get and return all courses
            return Optional.ofNullable(curseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found with id: " + id)));

        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while retrieving a course by id: " + e.getMessage());
        }
    }

    /**
     * Get list of Courses by name containing
     * @param name a string that represent the name of the course to be searched
     * @return a list of Courses
     */
    public List<Course> getByNameContaining(String name) throws Exception {
        try
        {
            //Get and return
            return curseRepository.findByNameContaining(name);
        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while retrieving list of courses by name containing: " + e.getMessage());
        }
    }

    /**
     * Update existing course
     * @param course that represent the course to be updated
     * @throws Exception
     */
    public void updateCourse(Course course) throws Exception {
        try
        {
            //Get and return
            Course existingCourse = curseRepository.findById(course.getIdCurse())
                                                   .orElseThrow(() -> new RuntimeException("Course not found with id: " + course.getIdCurse()));

            //Update course
            existingCourse.setName(course.getName());
            existingCourse.setMode(course.getMode());
            existingCourse.setEndDate(course.getEndDate());

            //Save changes
            curseRepository.save(existingCourse);

        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while updating course: " + e.getMessage());
        }
    }
}
