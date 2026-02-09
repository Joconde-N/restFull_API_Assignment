package auca.ac.rw.question2_student_api.Controller;

import auca.ac.rw.question2_student_api.model.Student;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();
    private Long nextId = 106L;

    public StudentController() {
        students.add(new Student(101L, "Aline", "Karlie", "aline@gmail.com", "Computer Science", 3.7));
        students.add(new Student(102L, "Joc", "Ngoga", "joc@gmail.com", "Information Management", 3.2));
        students.add(new Student(103L, "Axel", "Kagabo", "axel@gmail.com", "Computer Science", 3.9));
        students.add(new Student(104L, "Barbara", "Dukuze", "barbara@gmail.com", "Networking", 3.4));
        students.add(new Student(105L, "Didi", "Uwase", "didi@gmail.com", "Software Engineering", 3.6));
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return students;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId){
        for (Student stud : students){
            if(stud.getStudentId().equals(studentId)){
                return stud;
            }
        }
        return null;
    }

    @GetMapping("/major/{major}")
    public List<Student> getByMajor(@PathVariable String major){
        List<Student> result = new ArrayList<>();
        for (Student stud : students){
            if(stud.getMajor().equalsIgnoreCase(major)){
                result.add(stud);
            }
        }
        return result;
    }

    @GetMapping("/filter")
    public List<Student> filterByGpa(@RequestParam Double gpa){
        List<Student> result = new ArrayList<>();
        for ( Student stud : students){
            if(stud.getGpa() >= gpa){
                result.add(stud);
            }
        }
        return result;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        student.setStudentId(nextId++);
        students.add(student);
        return student;
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId, @RequestBody Student updated){
        for (Student stud : students){
            if(stud.getStudentId().equals(studentId)){
                stud.setFirstName(updated.getFirstName());
                stud.setLastName(updated.getLastName());
                stud.setEmail(updated.getEmail());
                stud.setMajor(updated.getMajor());
                stud.setGpa(updated.getGpa());
                return stud;
            }
        }
        return null;
    }


    
}
