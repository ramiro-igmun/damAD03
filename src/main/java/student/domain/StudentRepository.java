package student.domain;

import java.util.List;
import java.util.Optional;

/**
 * Allows access to the {@link Student} datasource and allows creation, update and query operations on it.
 */
public interface StudentRepository {

    Optional<Student> findByDni(String dni);

    List<Student> findAll();

    int updateStudentName(String dni, String fullName);

    void addNewGrade(String dni, int code, int grade);

    void modifyGrade(String dni, int code, int grade);

    String modifyGradeUseStoredFunction(String dni, int code, int grade);
}
