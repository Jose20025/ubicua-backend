package com.joseca.restapi.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joseca.restapi.beans.Student;

@RestController
@RequestMapping("/students")
public class StudentController {

    @GetMapping
    public Student getStudent() {
        return new Student(1, "Joseca", "Gutierrez");
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return new Student(id, "Jose Carlos", "Gutierrez");
    }

    // API REST query params
    @GetMapping("/query")
    public Student studentVariables(@RequestParam int id, @RequestParam String firstName,
            @RequestParam String lastName) {
        return new Student(id, firstName, lastName);
    }

    // PostMapping y RequestMapping
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getLastName());

        return student;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);

        return student;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public HashMap<String, String> deleteStudent(@PathVariable int id) {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("message", "Se eliminó correctamente");

        return map;
    }

}
