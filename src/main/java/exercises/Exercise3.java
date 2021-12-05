package exercises;

import infrastructure.ConnectionPool;
import infrastructure.HikariConnectionPool;
import student.application.StudentService;
import student.domain.StudentRepository;
import student.infraestructure.StudentJdbcRepository;
import subject.application.SubjectService;
import subject.domain.SubjectRepository;
import subject.infraestructure.SubjectJdbcRepository;
import ui.ModifyGradeTui;

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

        ModifyGradeTui app = new ModifyGradeTui(studentService, subjectService);

        app.start();
    }

}
