package br.com.poc.redis.controller;

import br.com.poc.redis.model.Student;
import br.com.poc.redis.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public String saveStudent(@RequestBody Student student) throws JsonProcessingException {
        return studentService.saveStudent(student);
    }

}
