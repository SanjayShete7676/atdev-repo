package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.entities.Student;
import com.app.service.IStudentService;
// Student controller holds all REST endpoint for Student
@RestController
@RequestMapping("/api/students")
public class StudentController {
	//dependency:Service layer i/f
	@Autowired
	private IStudentService stuService;
	
	public StudentController() {
		
	}
	//add REST endpoint to return list of all Student
	@GetMapping
	public List<Student> listStudent(){
		return stuService.getAllStuDetails();
	}
	// add REST API endpoint to add stu details (create new resource)
	@PostMapping
	public ResponseEntity<?> addStuDetails(@RequestBody Student transientStu){
		try {
		//invoke service layer method
		return new ResponseEntity<>(stuService.addStu(transientStu),HttpStatus.CREATED);
		}catch(RuntimeException e) {
			System.out.println("err in add stu"+e);
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);// invalid data from client
		}
	}
	// add REST API endpoint to delete stu details
	@DeleteMapping("/{stuId}")
	public ResponseEntity<?> deleteStuDetails(@PathVariable long stuId){
		try {
		System.out.println("in del stu"+stuId);
		return new ResponseEntity<>(stuService.deleteStu(stuId),HttpStatus.OK);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	// add REST API endpoint to update stu details
		@PutMapping
		public ResponseEntity<?> updateStuDetails(@RequestBody Student detachedStu){
			try {
			System.out.println("in update stu");
			return new ResponseEntity<>(stuService.updateStuDetails(detachedStu),HttpStatus.OK);
			}catch (RuntimeException e) {
				return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
		}
}
