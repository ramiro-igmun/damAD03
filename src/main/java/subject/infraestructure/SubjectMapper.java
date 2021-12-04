package subject.infraestructure;

import infrastructure.EntityMapper;
import subject.domain.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Maps database query result sets to {@link Subject} entities
 */
public class SubjectMapper implements EntityMapper<Subject, ResultSet> {

    @Override
    public Optional<Subject> toEntity(ResultSet data) {
        Optional<Subject> response = Optional.empty();
        try {
            if (data.next()) {
                Subject subject = new Subject();
                subject.setAbreviation(data.getString("abreviatura"));
                subject.setCode(data.getInt("cod"));
                subject.setName(data.getString("nombre"));
                response = Optional.of(subject);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return response;
    }

    @Override
    public List<Subject> toEntityList(ResultSet data) {
        List<Subject> subjects = new ArrayList<>();
        try {
            while (data.next()) {
                Subject subject = new Subject();
                subject.setAbreviation(data.getString("abreviatura"));
                subject.setCode(data.getInt("cod"));
                subject.setName(data.getString("nombre"));
                subjects.add(subject);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return subjects;
    }

}
