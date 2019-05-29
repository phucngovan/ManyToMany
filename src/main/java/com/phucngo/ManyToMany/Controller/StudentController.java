package com.phucngo.ManyToMany.Controller;

import com.phucngo.ManyToMany.Model.Student;
import com.phucngo.ManyToMany.Repository.StudentRepository;
import com.phucngo.ManyToMany.Service.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }


    @PatchMapping("/students/{id}")
    public Student updateStudent(@PathVariable Integer id, @Valid @RequestBody Student studentRequest) {
        return studentRepository.findById(id).map(
                student -> {
                    student.setId(studentRequest.getId());
                    student.setName(studentRequest.getName());
                    student.setPhone(studentRequest.getPhone());
//                    student.setEnrollments(studentRequest.getEnrollments());
                    return studentRepository.save(student);
                }
        ).orElse(null);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
        return studentRepository.findById(id).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElse(null);
    }

}
