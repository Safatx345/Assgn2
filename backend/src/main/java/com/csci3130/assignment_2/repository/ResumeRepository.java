package com.csci3130.assignment_2.repository;

import com.csci3130.assignment_2.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findByRole(String role);

}
