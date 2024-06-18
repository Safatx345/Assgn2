package com.csci3130.assignment_2.service;

import com.csci3130.assignment_2.model.Resume;

import java.util.List;

public interface ResumeService {

    public String createResume(Resume resume);

    List<Resume> getResume(String role);
    List<String> getQualifications(String role);

    List<String> getWorkExperience(String role);

    List<Resume> getResumesByExperience(int minYearsOfExperience);
}
