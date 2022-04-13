package com.example.demo.repositoryes;


import com.example.demo.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionsRepository extends JpaRepository<Question, Long> {
    public Question findByText(String text);
    public List<Question> findByTheme_id(Long id);
}
