package aboubakar.SpringBoot.student;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    public void addStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("This email "+student.getEmail()+" is not available");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean studentExists = studentRepository.existsById(studentId);
        if(!studentExists){
            throw new IllegalStateException("Student with id "+studentId+ " does not exist!");
        }
        studentRepository.deleteById(studentId);
    }

    //TODO: verify if the new email is available
    //TODO: verify if the new name/email and actual name/email are not the same before saving.
    public void updateStudent(Student student, Long id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isEmpty()){
            throw new IllegalIdentifierException("Student with id "+id+ " does not exist!");
        }
        optionalStudent.get().setName(student.getName());
        optionalStudent.get().setDob(student.getDob());
        optionalStudent.get().setEmail(student.getEmail());

        studentRepository.save(optionalStudent.get());

    }
}
