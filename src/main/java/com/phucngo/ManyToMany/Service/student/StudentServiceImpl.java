package com.phucngo.ManyToMany.Service.student;

import com.phucngo.ManyToMany.Model.Student;
import com.phucngo.ManyToMany.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student create(Student student) {
        return studentRepository.save(student) ;
    }

    @Override
    public Student delete(int id) {
        Student student = findById(id);
        if (student !=null) {
            studentRepository.delete(student);
        }
        return student ;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }
}
