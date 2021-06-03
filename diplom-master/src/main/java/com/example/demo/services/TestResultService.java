package com.example.demo.services;

import com.example.demo.models.TestResult;
import com.example.demo.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Access;
import java.util.List;

@Service
@Component
@Transactional
public class TestResultService {
    @Autowired
    TestRepo testRepo;
    public void addTest(TestResult testResult){
        testRepo.save(testResult);
    }
    public List<TestResult> findAll(){
        return testRepo.findAll();
    }
}
