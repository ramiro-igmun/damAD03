package exercises;

import infrastructure.HikariConnectionPool;
import student.application.StudentService;
import student.domain.StudentRepository;
import student.infraestructure.StudentJdbcRepository;
import ui.ModifyNameTui;

public class Exercise2 {

	/**
	 * Bootstrap the application and start the program loop
	 */
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentJdbcRepository(new HikariConnectionPool());
        StudentService service = new StudentService(studentRepository);
        ModifyNameTui app = new ModifyNameTui(service);

        app.start();
    }


}
