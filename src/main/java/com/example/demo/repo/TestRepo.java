package com.example.demo.repo;

import com.example.demo.models.TestResult;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestResult, Long> {
//    TestResult findByLogin(String login);
//    TestResult findById(long id);
    void deleteById(Long id);

}