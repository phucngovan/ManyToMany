package com.phucngo.ManyToMany.Controller;

import com.phucngo.ManyToMany.Model.Course;
import com.phucngo.ManyToMany.Model.Student;
import com.phucngo.ManyToMany.Repository.CourseRepository;
import com.phucngo.ManyToMany.Service.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "")
@RestController
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable Integer courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }


    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Integer courseId, @Valid @RequestBody Course courseRequest) {
        return courseRepository.findById(courseId).map(
                course -> {
                    course.setName(courseRequest.getName());
//                    course.setStudents(courseRequest.getStudents());
                    course.setEnrollments(courseRequest.getEnrollments());
                    return courseRepository.save(course);
                }
        ).orElse(null);
    }

    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElse(null);
    }
}
