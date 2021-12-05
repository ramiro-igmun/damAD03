package exercises;

import student.application.StudentService;
import student.domain.Student;
import infrastructure.HikariConnectionPool;
import student.infraestructure.StudentJdbcRepository;
import student.domain.StudentRepository;

import java.util.Optional;
import java.util.Scanner;

public class Exercise2 {
	
	/**
	 * Bootstrap the application and start the program loop
	 */
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentJdbcRepository(new HikariConnectionPool());
        StudentService service = new StudentService(studentRepository);
        start(service);
    }

    private static void start(StudentService studentService) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Escribe el DNI del alumno que deseas modificar");
        String dni = reader.nextLine();
        Optional<Student> optionalStudent = studentService.findByDni(dni);

        if (optionalStudent.isEmpty()) {
            System.err.println("No existe ningún alumno con el DNI seleccionado");
            System.exit(1);
        }

        Student student = optionalStudent.get();
        System.out.println(student.getFullName());

        System.out.println("Escribe le nuevo nombre para el alumno");
        String newName = reader.nextLine();

        int rowsModified = studentService.updateStudentName(dni, newName);
        if (rowsModified == 1) {
            System.out.println("Alumno modificado correctamente");
        } else {
            System.out.println("Ha ocurrido un error modificando el alumno");
        }
        System.out.println("Fin del programa");
    }

}
