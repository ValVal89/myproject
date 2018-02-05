package com.example.myproject.course;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course, String>
{
    List<Course> findByName(String name);
    List<Course> findByDescription(String description);

    List<Course> findByTopicId(String topicId);
}
