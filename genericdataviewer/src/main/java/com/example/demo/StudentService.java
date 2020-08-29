package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Cacheable("student")
	public Page<Student> getStudentdata(int page, int size, String sortOrder, String sortClause, String filterParam,
			String compareParam, String filterValue) {
		Pageable pageRequest = PageRequest.of(page, size);//setting deafult value in page request
		
		if(Optional.ofNullable(sortClause).isPresent()) //if sort clause is present we will sort the data based on the order 
		{
			switch (sortOrder) {
			case "ASC":
				pageRequest = PageRequest.of(page, size, Sort.by(sortClause).ascending());
				break;

			case "DESC":
				pageRequest = PageRequest.of(page, size, Sort.by(sortClause).descending());
				break;

			default:
				//pageRequest = PageRequest.of(page, size, Sort.by(sortClause).ascending());
				break;

			}
		}

	
		// return studentRepo.findAll(pageRequest);

		Specification<Student> byParamsSpec = null;

		if (Optional.ofNullable(filterParam).isPresent()) {  //if there is any filter pattern we are going to filter it 
			//ex:filterParam=id&compareParam=gt&filterValue=10017
			switch (filterParam) {
			case "id":
				if (compareParam.equalsIgnoreCase("EQ"))
					byParamsSpec = StudentSpecs.filterById(Long.parseLong(filterValue));
				else {
					if (compareParam.equalsIgnoreCase("GT"))
						byParamsSpec = StudentSpecs.filterByGreaterthanId(Long.parseLong(filterValue));
				}
				break;
			case "name":
				if (compareParam.equalsIgnoreCase("LIKE"))
					byParamsSpec = StudentSpecs.filterByName(filterValue);
				break;

			}
			// byParamsSpec=StudentSpecs.filterById(Long.parseLong(filterParam));
		}
		return studentRepo.findAll(byParamsSpec, pageRequest);
	}

}
