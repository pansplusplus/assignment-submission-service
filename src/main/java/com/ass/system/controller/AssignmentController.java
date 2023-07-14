package com.ass.system.controller;


import com.ass.system.model.assignment.Assignment;
import com.ass.system.model.assignment.AssignmentDaoImpl;
import com.ass.system.model.course.Course;
import com.ass.system.model.course.CourseUserDaoImpl;
import com.ass.system.model.user.AppUser;
import com.ass.system.model.user.UserDaoImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;


//@RestController
@RequestMapping("/api/v1/assignment")
@Controller
public class AssignmentController {
    private final AssignmentDaoImpl dao;

    CourseUserDaoImpl courseUserDao;
    UserDaoImpl userDao;

    public AssignmentController(AssignmentDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("/assign-list")
    public String getAssignments(Model model) {
        List<Assignment> subs = dao.findAll();
        model.addAttribute("assign_list", subs);
        return "pages/assign/assign-list";
    }

    @GetMapping(("/assign-by-id/{assignmentId}"))
    public String getAssignmentById(@PathVariable int assignmentId, Model model) throws SQLException {
        Assignment assign = dao.findById(assignmentId);
        model.addAttribute("assign", assign);
        return "pages/assign/assign-by-id";
    }

    @GetMapping("/create")
    public String displayCreateAssignmentForm(Model model) throws SQLException {
        Assignment assignment = new Assignment();
        model.addAttribute("newAssignment", assignment);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDao = new UserDaoImpl();
        AppUser currentUser = userDao.findByEmail(userDetails.getUsername());
        courseUserDao = new CourseUserDaoImpl();
        List<Course> user_courses = courseUserDao.findCoursesByUser(currentUser.getUserId());
        model.addAttribute("user_courses", user_courses);
        return "pages/assign/create-assign";
    }

    @PostMapping("/create")
    public String saveAssignment( Assignment assignment) {
        dao.save(assignment);
        return "pages/assign/success";
    }

    @PostMapping("/update/{assignmentId}")
    public void updateAssignment(Assignment assignment) {
        try {
            dao.update(assignment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/delete/{assignmentId}")
    public void deleteAssignment(@PathVariable int assignmentId) {
        try {
            dao.delete(assignmentId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
