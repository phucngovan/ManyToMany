package com.phucngo.ManyToMany.Repository;

import com.phucngo.ManyToMany.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
}
