package com.example;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>();

    @PostMapping("/students")
    public void createStudent(@RequestBody Student student) {
        students.add(student);
    }

    @GetMapping(value="/set/student", params={"firstName", "lastName"})
    public @ResponseBody String setStudent(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName)
    {
        System.err.println("Going to set " + firstName + " " + lastName);

        Student student = new Student(firstName, lastName);
        students.add(student);
        return "Created " + student.toString();
    }

    @DeleteMapping("/students/{index}")
    public void deleteStudent(@PathVariable int index) {
        students.remove(index);
    }

    @GetMapping("/students")
    public String getAllStudents() {
        String str = "";
        for(int i=0; i<students.size(); i++)
        {
            str += students.get(i).getFirstName();
        };
        return str;
    }

    //localhost:8080/set/student?firstName=Alena&lastName=Yakovleva
}
