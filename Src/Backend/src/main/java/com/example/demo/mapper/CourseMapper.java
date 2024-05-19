package com.example.demo.mapper;

import com.example.demo.domain.Course;
import com.example.demo.dto.CourseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CourseMapper {
    CourseDTO courseToCourseDTO(Course course);
    Course courseDTOToCourse(CourseDTO courseDTO);
    void updateCourseFromDTO(CourseDTO courseDTO, @MappingTarget Course course);
}