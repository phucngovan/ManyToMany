package com.phucngo.ManyToMany.Repository;

import com.phucngo.ManyToMany.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
}
