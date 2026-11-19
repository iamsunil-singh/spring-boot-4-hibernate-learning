package in.iamsunil.springboot.cruddemo.dao;

import in.iamsunil.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it ... there no need to write any code LOL!

}
