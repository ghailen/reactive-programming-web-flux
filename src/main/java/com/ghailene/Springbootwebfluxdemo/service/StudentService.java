package com.ghailene.Springbootwebfluxdemo.service;

import com.ghailene.Springbootwebfluxdemo.model.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {


    public List<Student> getStudent() throws InterruptedException {
        Long start = System.currentTimeMillis();

        List<Student> studentList = new ArrayList<Student>();
        for (int i = 1; i <= 20; i++) {
            studentList.add(new Student(i, "student" + i));
            System.out.println("Student Record : " + i);
            Thread.sleep(1000);
        }

        Long end = System.currentTimeMillis();
        System.out.println("Total time taken By Api :" + (end - start));
        return null;
    }

    // reacting programming approach
    public Flux<Student> getStudentList() {
        Long start = System.currentTimeMillis();


        Flux<Student> studentList = Flux.range(1, 20)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Student Record By Stream :" + i))
                .map(i -> new Student(i, "student " + i));

        Long end = System.currentTimeMillis();
        System.out.println("Total time taken By Api :" + (end - start));

        return studentList;
    }
}
