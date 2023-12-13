package fr.efrei.student_server.web.rest;

import fr.efrei.student_server.domain.Student;
import fr.efrei.student_server.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class StudentResourceIT {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setName("John");
        student.setAge(21);
    }

    @Test
    @Transactional
    void testGetStudent() {
        studentRepository.save(student);
        Student found = studentRepository.findById(student.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("John", found.getName());
        assertEquals(21, found.getAge());
    }

    @Test
    @Transactional
    void testCreateStudent() {
        long initialCount = studentRepository.count();

        studentRepository.save(new Student());
        long newCount = studentRepository.count();

        assertEquals(initialCount + 1, newCount);
    }

    @Test
    @Transactional
    void testUpdateStudent() {
        studentRepository.save(student);

        student.setName("Jane");
        studentRepository.save(student);

        Student updated = studentRepository.findById(student.getId()).orElse(null);
        assertNotNull(updated);
        assertEquals("Jane", updated.getName());
    }

    @Test
    @Transactional
    void testDeleteStudent() {
        studentRepository.save(student);
        assertNotNull(studentRepository.findById(student.getId()));

        studentRepository.deleteById(student.getId());
        assertFalse(studentRepository.findById(student.getId()).isPresent());
    }
}
