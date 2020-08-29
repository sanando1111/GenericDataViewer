package com.example.demo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaSpecificationExecutor,PagingAndSortingRepository<Student, Long> {

}
