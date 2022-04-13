package com.example.demo.repositoryes;

import com.example.demo.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    public List<Theme> findAll();
//    public void save(Theme name);
}
