package infrastructure;

import student.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import student.domain.StudentRepository;
import student.infraestructure.StudentJdbcRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StudentJdbcRepositoryTest {

    StudentRepository studentRepository = new StudentJdbcRepository(new HikariConnectionPool());

    @Test
    @DisplayName("Given a existing DNI when fetching the student the result is present and correct")
    void findByDniReturnsCorrectStudent() {
        Optional<Student> optionalStudent = studentRepository.findByDni("44444444D");
        assertTrue(optionalStudent.isPresent());
        optionalStudent.ifPresent(student -> assertEquals(student.getDni(), "44444444D"));
    }

    @Test
    @DisplayName("Given a existing DNI when fetching the student the size of the grade list of the student is correct")
    void FetchedStudentHasCorrectNumberOfGrades() {
        Optional<Student> optionalStudent = studentRepository.findByDni("44444444D");
        assertTrue(optionalStudent.isPresent());
        optionalStudent.ifPresent(student -> assertEquals(student.getDni(), "44444444D"));
    }

    @Test
    @DisplayName("When fetching all the students the result list has the correct number of elements")
    void findAllReturnsCorrectNumberOfStudents() {
        List<Student> students = studentRepository.findAll();
        assertEquals(5, students.size());
    }
}
