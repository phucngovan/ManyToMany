package com.phucngo.ManyToMany.Service.student;

import com.phucngo.ManyToMany.Model.Student;

import java.util.List;

public interface StudentService {
    Student create(Student student);

    Student delete(int id);

    List<Student> findAll();

    Student findById(int id);

    Student update(Student student);
}
