package com.studentgrades.studentgrades.controller;

import com.studentgrades.studentgrades.dto.*;
import com.studentgrades.studentgrades.model.Course;
import com.studentgrades.studentgrades.service.CurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller to manage requests for interacting with courses
 */
@RestController
@RequestMapping("api/course/")
public class CourseController {

    @Autowired
    GenericResponseDto genericResponseDto;

    @Autowired
    CurseService curseService;

    /**
     * Create new course
     * @param course that represent the new course to be created
     * @return a generic http response that contain the created element
     */
    @PostMapping("save")
    public ResponseEntity<HttpResponseSingleDto<Course>> saveCourse(@RequestBody CourseDto course) throws Exception {

        // Create object response
        HttpResponseSingleDto<Course> response = new HttpResponseSingleDto<>();

        try {

            //Save element
            Course data = curseService.createCurse(course);

            //Set object response
            response.setData(data);
            response.setTotal(1);
            response.setMssg("New course created successfully");
            response.setSuccess(true);

            //Return on success
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e)
        {
            //Return on error
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return a generic http response that contain the list of all courses
     */
    @GetMapping("get-all")
    public ResponseEntity<HttpResponseDto<Course>> getAllCourses() throws Exception {

        // Create object response
        HttpResponseDto<Course> response = new HttpResponseDto<>();

        try {

            //Save element
            List<Course> data = curseService.getAll();

            if(!data.isEmpty())
            {
                //Set object response
                response.setData(data);
                response.setTotal(data.size());
                response.setMssg("List of courses retrieved successfully");
                response.setSuccess(true);
                //Return on success
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                //Set object response
                response.setData(null);
                response.setTotal(0);
                response.setMssg("No data were found");
                response.setSuccess(true);
                //Return on success
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        }catch (Exception e)
        {
            //Return on error
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all courses filtering by a specific name
     * @param param a ParameterCurseNameDto object that contain the name of the given course to be filtered
     * @return a list of courses that match with the given name
     * @throws Exception
     */
    @PostMapping("get-courses-by-name")
    public ResponseEntity<HttpResponseDto<Course>> getAllCoursesByName(@RequestBody ParameterCurseNameDto param) throws Exception {

        // Create object response
        HttpResponseDto<Course> response = new HttpResponseDto<>();
        try {

            //Save element
            List<Course> data = curseService.getByNameContaining(param.getCurseName());

            //Set object response
            response.setData(data);
            response.setTotal(data.size());
            String mssg = !data.isEmpty() ? "List of courses retrieved successfully" : "No data were found";
            response.setMssg(mssg);
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e)
        {
            //Return on error
            //Set object response
            response.setData(null);
            response.setTotal(0);
            response.setMssg("An error has occurred: " +  e.getMessage());
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update existing course
     * @param courseToUpdate a CourseDto that contain the columns of the course to be edited (excluding id)
     * @return a generic http response
     * @throws Exception
     */
    @PutMapping("update-course")
    public ResponseEntity<HttpResponseDto<Course>> updateCourse(@RequestBody Course courseToUpdate) throws Exception {

        // Create object response
        HttpResponseDto<Course> response = new HttpResponseDto<>();
        try {

            //Save element
            curseService.updateCourse(courseToUpdate);

            //Set object response
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

        }catch (Exception e)
        {
            //Return on error
            //Set object response
            response.setData(null);
            response.setTotal(0);
            response.setMssg("An error has occurred: " +  e.getMessage());
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
