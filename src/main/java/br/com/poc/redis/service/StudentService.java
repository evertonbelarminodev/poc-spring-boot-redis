package br.com.poc.redis.service;

import br.com.poc.redis.model.Student;
import br.com.poc.redis.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${spring.cache.redis.key}")
    private String redisKey;

    public String saveStudent (Student student) throws JsonProcessingException {
        String msgReturn = "";
        student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);

        try {
            String infoStudent = objectMapper.writeValueAsString(student);
            stringRedisTemplate.opsForHash().put(student.getId(),infoStudent, infoStudent);
            msgReturn = "Informação gravada no banco com sucesso!!";
        }catch (Exception e){
            msgReturn = "Não foi possível gravar a informação";
            throw (e);
        }

        return msgReturn;
    }

}
