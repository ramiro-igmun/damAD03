package subject.domain;

import student.domain.Student;

import java.util.List;

/**
 * Allows access to the {@link Subject} datasource and allows query operations on it.
 */
public interface SubjectRepository {
    List<Subject> findAll();
}
