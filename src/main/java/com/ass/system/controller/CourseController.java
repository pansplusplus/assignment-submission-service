package com.ass.system.controller;


import com.ass.system.model.assignment.AssignmentDaoImpl;
import com.ass.system.model.course.Course;
import com.ass.system.model.course.CourseDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseDaoImpl courseDao;

    private final AssignmentDaoImpl assignmentDao;

    public CourseController(CourseDaoImpl courseDao, AssignmentDaoImpl assignmentDao) {
        this.courseDao = courseDao;
        this.assignmentDao = assignmentDao;
    }

    @GetMapping("/all")
    public List<Course> getSubmissions() {
        return courseDao.findAll();
    }

    @GetMapping(("/{courseId}"))
    public String getCourseAndAssignmentsById(@PathVariable int courseId, Model model) throws SQLException {
        model.addAttribute("course", courseDao.findById(courseId));
        model.addAttribute("assign_for_course", assignmentDao.findByCourseId(courseId));
        return "/pages/course/course-by-id";
    }

    @PostMapping("/create/{courseId}")
    public void saveCourse(@PathVariable int courseId, Course course) {
        try {
            courseDao.save(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/update/{courseId}")
    public void updateCourse(@PathVariable int courseId, Course course) {
        try {
            courseDao.update(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/delete/{courseId}")
    public void deleteCourse(@PathVariable int courseId) {
        try {
            courseDao.delete(courseId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
