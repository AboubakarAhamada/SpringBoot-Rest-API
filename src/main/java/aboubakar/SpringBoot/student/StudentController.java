package aboubakar.SpringBoot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value="/students")
    public List<Student> getStudents (){

        return studentService.getStudents();
    }

    @PostMapping(value = "/student")
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "/student/{studentId}" )
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(value = "/student/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestBody Student student)
    {
        studentService.updateStudent(student, studentId);
    }
}
