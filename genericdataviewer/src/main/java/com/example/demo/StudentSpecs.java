package com.example.demo;

import org.springframework.data.jpa.domain.Specification;

public class StudentSpecs {

	public static Specification<Student> filterById(Long id) {
		return (root, query, cb) -> {
			return cb.equal(root.get(Student_.id), id);
		};
	}

	public static Specification<Student> filterByName(String name) {
		return (root, query, cb) -> {
			return cb.like(root.get(Student_.name), name);
		};
	}

	public static Specification<Student> filterByGreaterthanId(Long id) {
		return (root, query, cb) -> {
			return cb.greaterThan(root.get(Student_.id), id);
		};
	}

}
