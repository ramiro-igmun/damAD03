package subject.domain;

import subject.domain.Subject;

import java.util.List;

public interface SubjectRepository {
    List<Subject> findAll();
}
