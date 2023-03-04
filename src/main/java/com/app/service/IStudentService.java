package com.app.service;

import java.util.List;

import com.app.entities.Student;

public interface IStudentService {
	List<Student> getAllStuDetails();

	Student addStu(Student transientStu);

	String deleteStu(long stuId);

	Student updateStuDetails(Student detachedStu);
}
