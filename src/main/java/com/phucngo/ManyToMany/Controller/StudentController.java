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

@CrossOrigin(origins = "")
@RestController
@RequestMapping("/api")
public class StudentController {
    public static Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentService studentService;
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllStudent(){
        List<Student> listStudent= studentService.findAll();
        if(listStudent.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(listStudent, HttpStatus.OK);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student findStudent(@PathVariable("id") int id) {
        Student student= studentService.findById(id);
        if(student == null) {
            ResponseEntity.notFound().build();
        }
        return student;
    }

    @RequestMapping(value = "/student/", method = RequestMethod.POST)
    public Student saveStudent(@Valid @RequestBody Student student) {
        return studentService.create(student);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
                                                   @Valid @RequestBody Student studentForm) {
        Student student = studentService.findById(studentId);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }
        student.setId(studentForm.getId());
        student.setName(studentForm.getName());
        student.setPhone(studentForm.getPhone());
        student.setCourses(studentForm.getCourses());
        Student updatedStudent = studentService.create(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") Integer id) {
        Student student = studentService.findById(id);
        if(student == null) {
            return ResponseEntity.notFound().build();
        }

        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
