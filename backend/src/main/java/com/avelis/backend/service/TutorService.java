package com.avelis.backend.service;

import java.util.List;
import java.util.Optional;

import com.avelis.backend.domain.Tutor;
import com.avelis.backend.dto.CreateTutorRequest;

public interface TutorService {
	Tutor createTutor(CreateTutorRequest req);
	
	Optional<Tutor> findTutorById(Long id);
	
	List<Tutor> findAll();
	
	Tutor updateTutor(UpdateTutorRequest req);
	
	void deleteTutor(Long tutorId);
}
