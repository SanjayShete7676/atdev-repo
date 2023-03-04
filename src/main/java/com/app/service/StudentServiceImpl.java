package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.StudentRepository;
import com.app.entities.Student;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
	//dependancy:dao i/f
	@Autowired
	private StudentRepository stuRepo;
	
	@Override
	public List<Student> getAllStuDetails() {
		// TODO Auto-generated method stub
		return stuRepo.findAll();
	}

	@Override
	public Student addStu(Student transientStu) {
		// TODO Auto-generated method stub
		return stuRepo.save(transientStu);
	}//return DETACHED stu instance to the caller

	@Override
	public String deleteStu(long stuId) {
		stuRepo.deleteById(stuId);
		return "stu details deleted for stu id"+stuId;
	}

	@Override
	public Student updateStuDetails(Student detachedStu) {
		if(stuRepo.existsById(detachedStu.getId()))
			return stuRepo.save(detachedStu);
		throw new ResourceNotFoundException("Invalid Stu ID : Updation Failed !!!!!!!!!" + detachedStu.getId());
	}
	
}
