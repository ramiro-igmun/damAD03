package subject.infraestructure;

import infrastructure.ConnectionPool;
import subject.domain.Subject;
import subject.domain.SubjectRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectJdbcRepository implements SubjectRepository {

    private final SubjectMapper subjectMapper = new SubjectMapper();
    private final ConnectionPool connectionPool;

    public SubjectJdbcRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Subject> findAll() {
        try (Connection connection = connectionPool.getConnection()) {

            String query = "SELECT * FROM asignaturas";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            return subjectMapper.toEntityList(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
