package exercises;

import infrastructure.ConnectionPool;
import infrastructure.HikariConnectionPool;
import infrastructure.ViburConnectionPool;
import student.application.StudentService;
import student.domain.Student;
import student.domain.StudentRepository;
import student.infraestructure.StudentJdbcRepository;
import subject.application.SubjectService;
import subject.domain.Subject;
import subject.domain.SubjectRepository;
import subject.infraestructure.SubjectJdbcRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Exercise3 {
	
	/**
	 * Bootstrap the application and start the program loop
	 */
    public static void main(String[] args) {
        ConnectionPool connectionPool = new HikariConnectionPool();
        
        StudentRepository studentRepository = new StudentJdbcRepository(connectionPool);
        StudentService studentService = new StudentService(studentRepository);
        
        SubjectRepository subjectRepository = new SubjectJdbcRepository(connectionPool);
        SubjectService subjectService = new SubjectService(subjectRepository);

        start(studentService, subjectService);
    }

    private static void start(StudentService studentService, SubjectService subjectService) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Escribe el DNI del alumno al que quieres calificar");
        String dni = reader.nextLine();
        Optional<Student> optionalStudent = studentService.findByDni(dni);

        if (optionalStudent.isEmpty()) {
            System.err.println("No existe ningún alumno con el DNI seleccionado");
            System.exit(1);
        }

        Student student = optionalStudent.get();
        System.out.println(student.getFullName());

        System.out.println("Listado de asignaturas disponibles:");
        List<Subject> subjects = subjectService.findAll();

        for (int i = 1; i <= subjects.size(); i++) {
            Subject subject = subjects.get(i - 1);
            System.out.printf("%d-. %s (%s)\n", i, subject.getName(), subject.getAbreviation());
        }

        System.out.println("Escribe el código de la asignatura a evaluar:");
        int code = reader.nextInt();
        System.out.println("Escribe la nota del alumno:");
        int grade = reader.nextInt();

        System.out.println(studentService.addGrade(dni, code, grade, true));
    }
}
