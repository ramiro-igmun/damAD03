package student.infraestructure;

import infrastructure.ConnectionPool;
import student.domain.Student;
import student.domain.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentJdbcRepository implements StudentRepository {

    private final StudentMapper studentMapper = new StudentMapper();
    private final ConnectionPool connectionPool;

    public StudentJdbcRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<Student> findByDni(String dni) {
        try (Connection connection = connectionPool.getConnection()) {

            String query = "SELECT alumnos.dni, apenom, pobla, telef, n.cod, abreviatura, nota FROM alumnos LEFT JOIN (notas n JOIN asignaturas a on n.cod = a.cod) on alumnos.dni = n.dni WHERE alumnos.dni = ? ORDER BY apenom DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);

            ResultSet resultSet = preparedStatement.executeQuery();
            return studentMapper.toEntity(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        try (Connection connection = connectionPool.getConnection()) {

            String query = "SELECT alumnos.dni, apenom, pobla, telef, n.cod, abreviatura, nota FROM alumnos LEFT JOIN (notas n JOIN asignaturas a on n.cod = a.cod) on alumnos.dni = n.dni ORDER BY apenom DESC";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            return studentMapper.toEntityList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public int updateStudentName(String dni, String fullName) {
        try (Connection connection = connectionPool.getConnection()) {

            String query = "UPDATE alumnos SET apenom=? WHERE dni=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, dni);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void addNewGrade(String dni, int code, int grade) {
        try (Connection connection = connectionPool.getConnection()) {

            String query = "INSERT INTO notas VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, String.valueOf(code));
            preparedStatement.setString(3, String.valueOf(grade));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyGrade(String dni, int code, int grade) {
        try (Connection connection = connectionPool.getConnection()) {

            String query = "UPDATE notas SET nota=? WHERE dni=? AND cod=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(grade));
            preparedStatement.setString(2, dni);
            preparedStatement.setString(3, String.valueOf(code));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String modifyGradeUseStoredFunction(String dni, int code, int grade) {
        try (Connection connection = connectionPool.getConnection()) {
            CallableStatement statement = connection.prepareCall("{? = CALL modifyGrade(?, ?, ?)}");
            statement.registerOutParameter(1, Types.CHAR);
            statement.setString(2, dni);
            statement.setInt(3, code);
            statement.setInt(4, grade);
            statement.executeUpdate();
            return statement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Ha ocurrido un error añadiendo la nota";
    }
}
