package exercises;

import student.domain.Grade;
import student.domain.Student;
import infrastructure.HikariConnectionPool;
import student.infraestructure.StudentJdbcRepository;
import student.domain.StudentRepository;

import java.util.List;

public class Exercise1 {

    /**
     *  Bootstrap the application and inject dependencies;
     */
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentJdbcRepository(new HikariConnectionPool());
        printStudentsProgram(studentRepository);
    }

    /**
     * Program entrypoint
     */
    private static void printStudentsProgram(StudentRepository studentRepository) {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            System.out.println(student.getFullName());
            System.out.println("------------------------------");
            for (Grade grade : student.getGrades()) {
                System.out.printf("%-5s%10d\n", grade.getSubjectAbr(), grade.getGrade());
            }
            System.out.println();
        }
    }
}