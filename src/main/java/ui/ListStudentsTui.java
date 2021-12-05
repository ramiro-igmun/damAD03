package ui;

import student.application.StudentService;
import student.domain.Grade;
import student.domain.Student;

import java.util.List;

public class ListStudentsTui {

    private final StudentService studentService;

    public ListStudentsTui(StudentService studentService) {
        this.studentService = studentService;
    }

    public void start() {
        List<Student> students = studentService.findAll();
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
