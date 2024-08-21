package com.studentgrades.studentgrades.service;

import com.studentgrades.studentgrades.dto.TopicDto;
import com.studentgrades.studentgrades.model.Course;
import com.studentgrades.studentgrades.model.Topic;
import com.studentgrades.studentgrades.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    /**
     * Save new topic
     * @param topic a Topic object that represent the new topic to be saved
     */
    public void createTopic(TopicDto topic, Course course) throws Exception {
        try
        {
            if(topicRepository.findByName(topic.getName()).isPresent())
            {
                //Throw new exception
                throw new Exception("the topic your are trying to create (" + topic.getName() + ") already exists.");
            }
            else
            {
                //Create object to be saved
                Topic newTopic = new Topic();
                newTopic.setName(topic.getName());
                newTopic.setDescription(topic.getDescription());
                newTopic.setCourse(course);
                topicRepository.save(newTopic);
            }

        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while saving topic: " + e.getMessage());
        }
    }

    /**
     * Get topics by course
     * @param course a given Course object to search list of topics
     * @return a list of Topics of a given Course
     * @throws Exception
     */
    public List<Topic> getTopicByCourseId(Course course) throws Exception {
        try
        {
            //Get and return data
            return topicRepository.findByCourse(course);
        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while retrieving topics by course id: " + e.getMessage());
        }
    }

    /**
     * Update topic
     * @param topic that represent the topic to be updated
     * @throws Exception
     */
    public void updateTopic(TopicDto topic, Course course) throws Exception {
        try
        {
            //Check topic
            Topic existingTopic = topicRepository.findById(topic.getId())
                    .orElseThrow(() -> new RuntimeException("Topic not found with id: " + topic.getId()));

            //Update topic
            existingTopic.setName(topic.getName());
            existingTopic.setDescription(topic.getDescription());
            existingTopic.setCourse(course);

            //Save changes
            topicRepository.save(existingTopic);

        }catch (Exception e)
        {
            //Throw exception on error
            throw new Exception("while updating topics byid: " + e.getMessage());
        }
    }
}
