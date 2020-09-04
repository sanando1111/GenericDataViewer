package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@CrossOrigin("*")
	@GetMapping("/generic/data")
	public ResponseEntity<Object> getGenericData(
			@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(defaultValue = "ASC") String sortOrder, @RequestParam(required = false) String sortClause,
			@RequestParam(required = false) String filterParam, @RequestParam(required = false) String compareParam,
			@RequestParam(required = false) String filterValue,
			@RequestParam(defaultValue = "Student", required = false) String collectionName

	) {
		
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);
		switch (collectionName) {// Add your collection name from database in the switch case
		case "Student":
			Page<Student> studentList = studentService.getStudentdata(page, size, sortOrder, sortClause, filterParam,
					compareParam, filterValue);
			response = new ResponseEntity<>(studentList, HttpStatus.OK);
			break;

		default:
			break;

		}

		return response;

	}

}
