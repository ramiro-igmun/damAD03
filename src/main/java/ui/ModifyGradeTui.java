package ui;

import student.application.StudentService;
import student.domain.Student;
import subject.application.SubjectService;
import subject.domain.Subject;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ModifyGradeTui {

    private final StudentService studentService;
    private final SubjectService subjectService;

    public ModifyGradeTui(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    public void start() {
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

        //For alternative Exercise4 uncomment next line and comment the.
        System.out.println(studentService.addGrade(dni, code, grade, true));
        //System.out.println(studentService.addGrade(dni, code, grade));
    }
}
