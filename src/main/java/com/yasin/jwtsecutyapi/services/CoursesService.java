package com.yasin.jwtsecutyapi.services;

import com.yasin.jwtsecutyapi.model.dto.CourseDTO;
import com.yasin.jwtsecutyapi.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursesService {
    private final CourseRepo courseRepo;

    public CoursesService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<CourseDTO> courses() {
        return this.courseRepo.findAll();
    }

    public List<CourseDTO> findAllByUsername(String username){
        return this.courseRepo.findAllByUsername(username);
    }
}
