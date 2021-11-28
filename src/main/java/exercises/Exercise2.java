package exercises;

import student.domain.Student;
import infrastructure.HikariConnectionPool;
import student.infraestructure.StudentJdbcRepository;
import student.domain.StudentRepository;

import java.util.Optional;
import java.util.Scanner;

public class Exercise2 {

    /**
     * Bootstrap the application and inject dependencies
     */
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentJdbcRepository(new HikariConnectionPool());
        modifyStudentNameProgram(studentRepository);
    }

    /**
     * Program entrypoint
     */
    private static void modifyStudentNameProgram(StudentRepository studentRepository) {
        Scanner reader = new Scanner(System.in);

        System.out.println("Escribe el DNI del alumno que deseas modificar");
        String dni = reader.nextLine();
        Optional<Student> optionalStudent = studentRepository.findByDni(dni);

        if (optionalStudent.isEmpty()) {
            System.err.println("No existe ningún alumno con el DNI seleccionado");
            System.exit(1);
        }

        Student student = optionalStudent.get();
        System.out.println(student.getFullName());

        System.out.println("Escribe le nuevo nombre para el alumno");
        String newName = reader.nextLine();

        int rowsModified = studentRepository.updateStudentName(dni, newName);
        if (rowsModified == 1) {
            System.out.println("Alumno modificado correctamente");
        } else {
            System.out.println("Ha ocurrido un error modificando el alumno");
        }
        System.out.println("Fin del programa");
    }

}