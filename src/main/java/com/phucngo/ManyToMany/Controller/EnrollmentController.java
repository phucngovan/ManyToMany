package com.phucngo.ManyToMany.Controller;

import com.phucngo.ManyToMany.Model.Enrollment;
import com.phucngo.ManyToMany.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @GetMapping("/enrollments/{id}")
    public Enrollment getEnrollment(@PathVariable Integer id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @PostMapping("/enrollments")
    public Enrollment createEnrollment(@Valid @RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }
    @DeleteMapping("/enrollments/{enrollmentId}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Integer enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).map(enrollment -> {
            enrollmentRepository.delete(enrollment);
            return ResponseEntity.ok().build();
        }).orElse(null);
    }
    @PatchMapping("/enrollments/{id}")
    public Enrollment updateEnrollment(@PathVariable Integer id, @Valid @RequestBody Enrollment enrollmentRequest) {
        return enrollmentRepository.findById(id).map(
                enrollment -> {
                    enrollment.setStartDate(enrollmentRequest.getStartDate());
                    enrollment.setEndDate(enrollmentRequest.getEndDate());
                    enrollment.setStudent(enrollmentRequest.getStudent());
                    enrollment.setCourse(enrollmentRequest.getCourse());
                    return enrollmentRepository.save(enrollment);
                }
        ).orElse(null);
    }
}
