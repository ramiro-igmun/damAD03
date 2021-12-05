package subject.application;

import java.util.List;

import student.domain.StudentRepository;
import subject.domain.Subject;
import subject.domain.SubjectRepository;

public class SubjectService {
	
	private final SubjectRepository subjectRepository;
	
	public SubjectService(SubjectRepository subjectRepository ) {
		this.subjectRepository = subjectRepository;
	}
	
	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}

}
