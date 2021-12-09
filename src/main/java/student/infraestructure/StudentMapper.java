package student.infraestructure;

import infrastructure.EntityMapper;
import student.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Maps database query result sets to {@link Student} entities
 */
public class StudentMapper implements EntityMapper<Student, ResultSet> {

    @Override
    public Optional<Student> toEntity(ResultSet data) {
        Optional<Student> response = Optional.empty();
        try {
            Student student = new Student();
            while (data.next()) {
                student.setDni(data.getString("dni"));
                student.setCity(data.getString("pobla"));
                student.setFullName(data.getString("apenom"));
                student.setTelephone(data.getString("telef"));
                if (data.getObject("cod") != null) {
                    student.addGrade(data.getInt("cod"), data.getInt("nota"), data.getString("abreviatura"));
                }
            }
            response = Optional.of(student);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return response;
    }

    @Override
    public List<Student> toEntityList(ResultSet data) {
        Map<String, Student> studentMap = new LinkedHashMap<>();
        try {
            while (data.next()) {
                Student student;
                String dni = data.getString("dni");
                if (studentMap.containsKey(dni)) {
                    student = studentMap.get(dni);
                } else {
                    student = new Student();
                    student.setDni(dni);
                    studentMap.put(dni, student);
                }
                student.setCity(data.getString("pobla"));
                student.setFullName(data.getString("apenom"));
                student.setTelephone(data.getString("telef"));
                if (data.getObject("cod") != null) {
                    student.addGrade(data.getInt("cod"), data.getInt("nota"), data.getString("abreviatura"));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>(studentMap.values());
    }
}
