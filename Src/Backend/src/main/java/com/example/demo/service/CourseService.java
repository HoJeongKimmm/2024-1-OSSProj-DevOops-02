package com.example.demo.service;

import com.example.demo.domain.Course;
import com.example.demo.dto.CourseDTO;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.courseToCourseDTO(course);
    }

    public CourseDTO createCourse(CourseDTO courseDto) {
        Course course = courseMapper.courseDTOToCourse(courseDto);
        course = courseRepository.save(course);
        return courseMapper.courseToCourseDTO(course);
    }

    public CourseDTO updateCourse(CourseDTO courseDto) {
        Course existingCourse = courseRepository.findById(courseDto.getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseMapper.updateCourseFromDTO(courseDto, existingCourse);
        existingCourse = courseRepository.save(existingCourse);
        return courseMapper.courseToCourseDTO(existingCourse);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    public List<CourseDTO> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getCoursesByStartDateBetween(Date startDate, Date endDate) {
        return courseRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }

    public List<CourseDTO> getCoursesByEndDateBetween(Date startDate, Date endDate) {
        return courseRepository.findByEndDateBetween(startDate, endDate).stream()
                .map(courseMapper::courseToCourseDTO)
                .collect(Collectors.toList());
    }
}