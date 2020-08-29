package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/generic/data")
	public ResponseEntity<List<Object>> getGenericData(@RequestParam(defaultValue = "0", required = false) int page,
			@RequestParam(defaultValue = "10", required = false) int size,
			@RequestParam(defaultValue = "ASC") String sortOrder, @RequestParam(required = false) String sortClause,
			@RequestParam(required = false) String filterParam, @RequestParam(required = false) String compareParam,
			@RequestParam(required = false) String filterValue,
			@RequestParam(defaultValue = "Student", required = false) String collectionName

	) {
		List<Object> list = new ArrayList<>();
		ResponseEntity<List<Object>> response = new ResponseEntity<>(HttpStatus.OK);

		switch (collectionName) {// Add your collection name from database in the switch case
		case "Student":
			Page<Student> studentList = studentService.getStudentdata(page, size, sortOrder, sortClause, filterParam,
					compareParam, filterValue);
			list.add(studentList.getContent());
			response = new ResponseEntity<List<Object>>(list, HttpStatus.OK);
			break;

		default:
			break;

		}

		return response;

	}

}
