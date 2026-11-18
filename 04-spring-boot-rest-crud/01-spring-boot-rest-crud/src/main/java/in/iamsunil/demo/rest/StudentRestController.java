package in.iamsunil.demo.rest;

import in.iamsunil.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
   private List<Student> theStudents;

   @PostConstruct
   public void loadData(){
       theStudents = new ArrayList<>();
       theStudents.add(new Student("Poornima", "Patel"));
       theStudents.add(new Student("Sunil", "Singh"));
       theStudents.add(new Student("Rahul", "Kundra"));
   }

    // define endpoint for "/students" - returns a lost of students
    @GetMapping("/students")
    public List<Student> getStudents(){
//        List<Student> theStudents= new ArrayList<>();
//        theStudents.add(new Student("Poornima", "Patel"));
//        theStudents.add(new Student("Sunil", "Singh"));
//        theStudents.add(new Student("Rahul", "Kundra"));
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
       // Just index into the list...keep it simple for now

        // Step3 : Update REST service to throw exception if student not found
        //check the studentId again list size

        if(studentId>= theStudents.size() || (studentId<0)){
            throw new StudentNotFoundException("Student id not found - "+ studentId);
        }

       return theStudents.get(studentId);
    }

//    // Add an exception handler using @ExceptionHandler
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
//       // create a StudentErrorResponse
//
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // return ResponseEntity
//
//        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
//    }
//
//    // Add another exception Handler...to catch any exception ( catch all)
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
//        // create a StudentErrorResponse
//
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exc.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        // return ResponseEntity
//
//        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
//    }



}
