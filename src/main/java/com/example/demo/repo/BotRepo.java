package com.example.demo.repo;

import com.example.demo.models.BotMessage;
import com.example.demo.models.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepo extends JpaRepository<BotMessage, Long> {
}
