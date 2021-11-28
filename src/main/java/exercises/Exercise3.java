package exercises;

import infrastructure.ConnectionPool;
import infrastructure.HikariConnectionPool;
import student.application.GradingService;
import student.domain.Student;
import student.domain.StudentRepository;
import student.infraestructure.StudentJdbcRepository;
import subject.domain.Subject;
import subject.domain.SubjectRepository;
import subject.infraestructure.SubjectJdbcRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Exercise3 {

    /**
     * Bootstrap the application and inject dependencies
     */
    public static void main(String[] args) {
        ConnectionPool connectionPool = new HikariConnectionPool();
        StudentRepository studentRepository = new StudentJdbcRepository(connectionPool);
        SubjectRepository subjectRepository = new SubjectJdbcRepository(connectionPool);

        modifyGradeProgram(studentRepository, subjectRepository);
    }

    /**
     * Program entrypoint
     */
    private static void modifyGradeProgram(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        Scanner reader = new Scanner(System.in);
        GradingService gradingService = new GradingService(studentRepository);

        System.out.println("Escribe el DNI del alumno al que quieres calificar");
        String dni = reader.nextLine();
        Optional<Student> optionalStudent = studentRepository.findByDni(dni);

        if (optionalStudent.isEmpty()) {
            System.err.println("No existe ningún alumno con el DNI seleccionado");
            System.exit(1);
        }

        Student student = optionalStudent.get();
        System.out.println(student.getFullName());

        System.out.println("Listado de asignaturas disponibles:");
        List<Subject> subjects = subjectRepository.findAll();

        for (int i = 1; i <= subjects.size(); i++) {
            Subject subject = subjects.get(i - 1);
            System.out.printf("%d-. %s (%s)\n", i, subject.getName(), subject.getAbreviation());
        }

        System.out.println("Escribe el código de la asignatura a evaluar:");
        int code = reader.nextInt();
        System.out.println("Escribe la nota del alumno:");
        int grade = reader.nextInt();

        System.out.println(gradingService.addGrade(dni, code, grade, true));
    }
}
