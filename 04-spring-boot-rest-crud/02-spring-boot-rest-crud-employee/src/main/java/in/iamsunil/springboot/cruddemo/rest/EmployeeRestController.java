package in.iamsunil.springboot.cruddemo.rest;

import in.iamsunil.springboot.cruddemo.dao.EmployeeDAO;
import in.iamsunil.springboot.cruddemo.entity.Employee;
import in.iamsunil.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    private JsonMapper jsonMapper;

//    private EmployeeDAO employeeDAO;
    // quick and dirt: inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper){
        employeeService = theEmployeeService;
        jsonMapper = theJsonMapper;
    }

    //expose "/employess" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not Found - " +employeeId);
        }
        return theEmployee;
    }

    // add mapping for POST /employees
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //also just in case they pass an id in json ...set id to 0

        //this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PATCH
    @PatchMapping("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, @RequestBody Map<String,Object> patchPayload){
        Employee  tempEmployee = employeeService.findById(employeeId);

        //throw exception if null
        if(tempEmployee==null){
            throw new RuntimeException("EmployeeId not found - " + employeeId);
        }

        //throw exception if request body contains id
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }

        Employee patchedEmployee = jsonMapper.updateValue(tempEmployee,patchPayload);

        Employee dbEmployee = employeeService.save(patchedEmployee);

        return dbEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee  tempEmployee = employeeService.findById(employeeId);

        //throw exception if null
        if(tempEmployee==null){
            throw new RuntimeException("EmployeeId not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted employee Id - " + employeeId;

    }



}
