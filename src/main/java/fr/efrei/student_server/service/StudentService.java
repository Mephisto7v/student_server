package fr.efrei.student_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.efrei.student_server.domain.Student;
import fr.efrei.student_server.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student update(Integer id, Student studentDetails) {
        return studentRepository.findById(id).map(existingStudent -> {
            if (studentDetails.getName() != null) {
                existingStudent.setName(studentDetails.getName());
            }
            if (studentDetails.getAge() != null) {
                existingStudent.setAge(studentDetails.getAge());
            }
            return studentRepository.save(existingStudent);
        }).orElse(null); // If student not found
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}

