package com.csci3130.assignment_2.service;

import com.csci3130.assignment_2.model.Resume;
import com.csci3130.assignment_2.repository.ResumeRepository;
import com.csci3130.assignment_2.service.Implementations.ResumeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResumeServiceImplTest {

    @Mock
    private ResumeRepository resumeRepository;

    @InjectMocks
    private ResumeServiceImpl resumeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }
    Resume resume1 = new Resume();
    Resume resume2 = new Resume();
    Resume resume3 = new Resume();
    @Test
    void testGetQualifications() {

        resume1.setRole("developer");
        resume1.setQualifications("B.Sc. in CS");

        resume2.setRole("tutor");
        resume2.setQualifications("M.Sc. in CS");

        when(resumeRepository.findByRole("tutor")).thenReturn(Collections.singletonList(resume2));

        List<String> qualifications = resumeService.getQualifications("tutor");


        assertEquals("M.Sc. in CS", qualifications.get(0));
    }

    @Test
    void testGetWorkExperience() {
        resume1.setRole("tutor");
        resume1.setWorkExperience("Ph.D. in CS");


        resume2.setRole("tutor");
        resume2.setWorkExperience("M.ED.in Education");

        when(resumeRepository.findByRole("tutor")).thenReturn(Arrays.asList(resume1, resume2));

        List<String> workExperiences = resumeService.getWorkExperience("tutor");

        assertEquals(2, workExperiences.size());
        assertEquals("Ph.D. in CS", workExperiences.get(0));
        assertEquals("M.ED.in Education", workExperiences.get(1));
    }
    @Test
    void testCreateResume() {
        Resume resume = new Resume("Alice", "Johnson", "alice.johnson@example.com", "tutor", "Ph.D. in Computer Science", "C++, Data Structures", "Published 10 research papers", "Algorithms, Data Structures", "10 years", "Professor at University");

        resumeService.createResume(resume);

        ArgumentCaptor<Resume> capResume = ArgumentCaptor.forClass(Resume.class);
        verify(resumeRepository, times(1)).save(capResume.capture());

        Resume capturedResume = capResume.getValue();
        assertEquals("Alice", capturedResume.getFirstName());
        assertEquals("Johnson", capturedResume.getLastName());
        assertEquals("alice.johnson@example.com", capturedResume.getEmail());
        assertEquals("tutor", capturedResume.getRole());
        assertEquals("Ph.D. in Computer Science", capturedResume.getQualifications());
        assertEquals("C++, Data Structures", capturedResume.getTechSkills());
        assertEquals("Published 10 research papers", capturedResume.getBusinessImpact());
        assertEquals("Algorithms, Data Structures", capturedResume.getCoursesTaught());
        assertEquals("10 years", capturedResume.getYearsOfExperience());
        assertEquals("Professor at University", capturedResume.getWorkExperience());
    }
    @Test
    void testGetResumeByRole() {
        resume1 = new Resume("John", "Doe", "john.doe@example.com", "developer", "B.Sc. Computer Science", "Java, Spring Boot", "Improved system performance", "N/A", "5 years", "Software Developer at XYZ");
        resume2 = new Resume("Jane", "Smith", "jane.smith@example.com", "tutor", "M.Sc. Software Engineering", "ReactJS, NodeJS", "Increased user engagement", "N/A", "7 years", "Frontend Developer at ABC");
        when(resumeRepository.findByRole("developer")).thenReturn(Collections.singletonList(resume1));

        List<Resume> resumes = resumeService.getResume("developer");

        assertEquals(1, resumes.size());
        assertEquals("John", resumes.get(0).getFirstName());

    }
    @Test
    void testGetResumesByExperience() {
        // Arrange
        resume1 = new Resume("John", "Doe", "john.doe@example.com", "developer", "B.Sc. Computer Science", "Java, Spring Boot", "Improved system performance", "N/A", "4 years", "Software Developer at XYZ");
        resume2 = new Resume("Jane", "Smith", "jane.smith@example.com", "tutor", "M.Sc. Software Engineering", "ReactJS, NodeJS", "Increased user engagement", "N/A", "7 years", "Frontend Developer at ABC");
        resume3 = new Resume("Bob", "Johnson", "bob.johnson@example.com", "Developer", "B.Sc. in IT", "C++, SQL", "Led team projects", "Operating Systems", "8 years", "Amazon");

        when(resumeRepository.findAll()).thenReturn(Arrays.asList(resume2, resume3));


        List<Resume> result = resumeService.getResumesByExperience(5);


        assertEquals(2, result.size());
        assertEquals(7, getYearsOfExperience(result.get(0)));
        assertEquals(8, getYearsOfExperience(result.get(1)));

    }
    private int getYearsOfExperience(Resume resume) {
        String years = resume.getYearsOfExperience();
        return Integer.parseInt(years.split(" ")[0]);
    }

}
