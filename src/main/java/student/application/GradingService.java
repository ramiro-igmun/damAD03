package student.application;

import student.domain.Grade;
import student.domain.Student;
import student.domain.StudentRepository;

import java.util.Optional;

public class GradingService {

    private final StudentRepository studentRepository;

    public GradingService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

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

    public String addGrade(String dni, int code, int grade, boolean useDatabaseFunction) {
        if (useDatabaseFunction) {
            return studentRepository.modifyGradeUseStoredFunction(dni, code, grade);
        } else {
            return addGrade(dni, code, grade);
        }
    }
}
