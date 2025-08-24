package com.yasin.jwtsecutyapi.repository;

import com.yasin.jwtsecutyapi.model.dto.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CourseRepo {

    private final List<CourseDTO> courses = new ArrayList<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    public CourseRepo() {
        this.courses.add(new CourseDTO(seq.getAndIncrement(), 1, "java Course", "for java developers"));
        this.courses.add(new CourseDTO(seq.getAndIncrement(), 1, "Spring boot Course", "for java developer"));
        this.courses.add(new CourseDTO(seq.getAndIncrement(), 2, "Python Course", "for Python developer"));
    }

    public List<CourseDTO> findAll() {
        return List.copyOf(courses);
    }

    public List<CourseDTO> findAllByUserId(Integer userId) {
        return courses.stream()
                .filter(c -> c.userId().equals(userId))
                .toList();
    }

}
