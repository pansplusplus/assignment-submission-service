package com.ass.system.controller;

import com.ass.system.model.course.Course;
import com.ass.system.model.course.CourseDaoImpl;
import com.ass.system.model.course.CourseUserDaoImpl;
import com.ass.system.model.user.AppUser;
import com.ass.system.model.user.Role;
import com.ass.system.model.user.UserDaoImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import static com.ass.system.model.user.Role.STUDENT;

@Controller
public class HomeController {
    private final CourseUserDaoImpl courseUserDao;
    private final UserDaoImpl userDao;
    private final CourseDaoImpl courseDao;

    public HomeController(CourseUserDaoImpl courseUserDao, UserDaoImpl userDao, CourseDaoImpl courseDao) {
        this.courseUserDao = courseUserDao;
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    @GetMapping("/")
    public String getCoursesForUser(Model model, Principal principal) throws SQLException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AppUser currentUser = userDao.findByEmail(userDetails.getUsername());
        List<Course> user_courses = courseUserDao.findCoursesByUser(currentUser.getUserId());
        model.addAttribute("user_courses", user_courses);
        return "pages/home/home";
    }
    @GetMapping("/about")
    public String getAbout() {
        return "/pages/about";
    }

}
