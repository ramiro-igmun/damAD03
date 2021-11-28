package student.infraestructure;

import infrastructure.EntityMapper;
import student.domain.Grade;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class GradeMapper implements EntityMapper<Grade, ResultSet> {

    @Override
    public Optional<Grade> toEntity(ResultSet data) {
        return null;
    }

    @Override
    public List<Grade> toEntityList(ResultSet data) {
        return null;
    }
}
