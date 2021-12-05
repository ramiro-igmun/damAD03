package student.domain;

import java.util.List;
import java.util.Optional;

/**
 * Allows access to the {@link Student} datasource and allows creation, update and query operations on it.
 */
public interface StudentRepository {

    Optional<Student> findByDni(String dni);

    List<Student> findAll();

    /**
     * @return the number of rows updated (either 0 or 1)
     */
    int updateStudentName(String dni, String fullName);

    void addNewGrade(String dni, int code, int grade);

    void modifyGrade(String dni, int code, int grade);

    /**
     * @return a string with the confirmation or error message
     */
    String modifyGradeUseStoredFunction(String dni, int code, int grade);
}
