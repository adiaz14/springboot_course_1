package com.studentgrades.studentgrades.controller;

import com.studentgrades.studentgrades.dto.HttpResponseDto;
import com.studentgrades.studentgrades.dto.ParameterCurseIdDto;
import com.studentgrades.studentgrades.dto.TopicDto;
import com.studentgrades.studentgrades.model.Course;
import com.studentgrades.studentgrades.model.Topic;
import com.studentgrades.studentgrades.service.CurseService;
import com.studentgrades.studentgrades.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller to manage request of topics
 */
@RestController
@RequestMapping("api/topic/")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    CurseService curseService;

    /**
     * Save new topic
     * @param newTopic a TopicDto that contain the topic information to be saved
     * @return a generic http response that contain the result of the saving process
     */
    @GetMapping("save")
    public ResponseEntity<HttpResponseDto<Topic>> save(@RequestBody TopicDto newTopic){

        //Create object response
        HttpResponseDto<Topic> response = new HttpResponseDto<Topic>();

        try
        {
            //Check course
            Optional<Course> course = curseService.getById(newTopic.getFkCourse());

            //Save data
            topicService.createTopic(newTopic, course.get());

            //Return
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

        }catch (Exception e)
        {
            //Set on error
            response.setSuccess(false);
            response.setData(null);
            response.setTotal(0);
            response.setMssg("An error has occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Get all topics of the given course
     * @param courseToBeSearched a ParameterCurseIdDto that contain the id of the course to be searched
     * @return a generic http response that contain the result of the searching process
     */
    @PostMapping("get-by-course")
    public ResponseEntity<HttpResponseDto<Topic>> GetByCourse(@RequestBody ParameterCurseIdDto courseToBeSearched){

        //Create object response
        HttpResponseDto<Topic> response = new HttpResponseDto<Topic>();

        try
        {
            //Check course
            Optional<Course> course = curseService.getById(courseToBeSearched.getCourseId());

            //Save data
            List<Topic> data = topicService.getTopicByCourseId(course.get());

            if(!data.isEmpty())
            {
                //Set object response
                response.setData(data);
                response.setTotal(data.size());
                response.setMssg("List of topics retrieved successfully");
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
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }catch (Exception e)
        {
            //Set on error
            response.setSuccess(false);
            response.setData(null);
            response.setTotal(0);
            response.setMssg("An error has occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Update topic
     * @param topicToUpdate a Topic to be udpated
     */
    @PostMapping("update")
    public ResponseEntity<HttpResponseDto<Topic>> update(@RequestBody TopicDto topicToUpdate){

        //Create object response
        HttpResponseDto<Topic> response = new HttpResponseDto<Topic>();

        try
        {
            //Check course
            Optional<Course> course = curseService.getById(topicToUpdate.getFkCourse());

            //Save data
            topicService.updateTopic(topicToUpdate, course.get());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

        }catch (Exception e)
        {
            //Set on error
            response.setSuccess(false);
            response.setData(null);
            response.setTotal(0);
            response.setMssg("An error has occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
