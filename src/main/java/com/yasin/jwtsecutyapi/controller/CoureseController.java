package com.yasin.jwtsecutyapi.controller;

import com.yasin.jwtsecutyapi.model.dto.CourseDTO;
import com.yasin.jwtsecutyapi.services.CoursesService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CoureseController {
    private final CoursesService coursesService;

    public CoureseController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping(path = "/all")
    public List<CourseDTO> courses() {
        return this.coursesService.courses();
    }

    @GetMapping(path = "/my-courses") // user
    public List<CourseDTO> myCourses(@AuthenticationPrincipal Jwt jwt) {
        var username = jwt.getSubject();
        return coursesService.findAllByUsername(username);
    }

    @GetMapping(path = "/my-courses/{username}") // admin
    public List<CourseDTO> anyUserCourses(@PathVariable String username) {
        return coursesService.findAllByUsername(username);
    }
}
