package com.ghailene.Springbootwebfluxdemo.controller;

import com.ghailene.Springbootwebfluxdemo.model.Student;
import com.ghailene.Springbootwebfluxdemo.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // without webflux
    @GetMapping("/")
    public List<Student> student() throws InterruptedException {
        return studentService.getStudent();
    }

    // with webflux (reactive programming)
    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> studentList() throws InterruptedException {
        return studentService.getStudentList();
    }
}
