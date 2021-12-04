package student.application;

import student.domain.Grade;
import student.domain.Student;
import student.domain.StudentRepository;

import java.util.Optional;

/**
 * Service to manage the students' grades creation
 */
public class GradingService {

    private final StudentRepository studentRepository;

    public GradingService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Add a grade for a specific student and subject
     *
     * @param dni of the student
     * @param code of the subject to grade
     * @param grade the grade to add
     * @return returns a string with the result of the operation
     */
    public String addGrade(String dni, int code, int grade) {
        Optional<Student> optionalStudent = studentRepository.findByDni(dni);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            for (Grade studentGrade : student.getGrades()) {
                if (studentGrade.getCode() == code) {
                    studentRepository.modifyGrade(dni, code, grade);
                    return "La nota se ha modificado";
                } else {
                    studentRepository.addNewGrade(dni, code, grade);
                    return "La nota se ha añadido";
                }
            }
        }
        return "El dni no corresponde a ningún estudiante";
    }

    /**
     * Overload of addGrade method. Accepts an argument to the operation using a database function directly
     * @see #addGrade(String, int, int)
     *
     * @param useDatabaseFunction if true, a database function will be used for the grade creation operation
     * @return returns a string with the result of the operation
     */
    public String addGrade(String dni, int code, int grade, boolean useDatabaseFunction) {
        if (useDatabaseFunction) {
            return studentRepository.modifyGradeUseStoredFunction(dni, code, grade);
        } else {
            return addGrade(dni, code, grade);
        }
    }
}
