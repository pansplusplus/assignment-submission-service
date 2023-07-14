package com.ass.system.controller;

import com.ass.system.model.assignment.Assignment;
import com.ass.system.model.assignment.AssignmentDaoImpl;
import com.ass.system.model.course.Course;
import com.ass.system.model.course.CourseUserDaoImpl;
import com.ass.system.model.submission.Submission;
import com.ass.system.model.submission.SubmissionDaoImpl;
import com.ass.system.model.user.AppUser;
import com.ass.system.model.user.UserDaoImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/v1/submission")
//@Component
@Controller
public class SubmissionController {
    private final SubmissionDaoImpl dao;

    CourseUserDaoImpl courseUserDao;
    UserDaoImpl userDao;

    public SubmissionController(SubmissionDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("/list")
    public String getSubmissions(Model model) {
        List<Submission> subs = dao.findAll();
        model.addAttribute("sub_list", subs);
        return "/pages/sub/submission-list";
    }

    @GetMapping(value = "/sub-by-id/{submissionId}")
    public String getSubmissionById(@PathVariable int submissionId, Model model) throws SQLException {
        Submission sub = dao.findById(submissionId);
        model.addAttribute("sub", sub);
        List<Course> userCourses = coursesForUser();
        model.addAttribute("userCourses", userCourses);
        AssignmentDaoImpl assignmentDao = new AssignmentDaoImpl();
        Assignment assignForSub = assignmentDao.findById(sub.getAssignmentId());
        model.addAttribute("assignForSub", assignForSub);
        return "/pages/sub/sub-by-id";
    }

    @GetMapping(value = "sub-by-id/edit/{submissionId}")
    public String displaySubmissionEditForm(@PathVariable int submissionId, @ModelAttribute("submission") Submission submission, Model model) {
        return "/pages/sub/editsub";
    }

    @PostMapping(value = "/sub-by-id/{submissionId}")
    public String updateSubmissionById(@ModelAttribute("submission") Submission submission, Model model) throws SQLException {
        Submission sub = dao.findById(submission.getSubmissionId());
        model.addAttribute("sub", sub);
        List<Course> userCourses = coursesForUser();
        model.addAttribute("userCourses", userCourses);
        AssignmentDaoImpl assignmentDao = new AssignmentDaoImpl();
        Assignment assignForSub = assignmentDao.findById(sub.getAssignmentId());
        model.addAttribute("assignForSub", assignForSub);
        dao.update(submission);
        return "/pages/sub/editsub";
    }

    @GetMapping("/submit")
    public String newSub(Model model) throws SQLException {
        Submission submission = new Submission();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDao = new UserDaoImpl();
        AppUser currentUser = userDao.findByEmail(userDetails.getUsername());
        model.addAttribute("submission", submission);
        courseUserDao = new CourseUserDaoImpl();
        List<Course> user_courses = courseUserDao.findCoursesByUser(currentUser.getUserId());
        AssignmentDaoImpl assDao = new AssignmentDaoImpl();
        List<Assignment> myAssignments = new ArrayList<>();
        for (Course userCourse_i : user_courses) {
            List<Assignment> assignTemp = assDao.findByCourseId(userCourse_i.getCourseId());
            myAssignments.addAll(assignTemp);
        }
        model.addAttribute("myAssignments", myAssignments);
        return "/pages/sub/create-sub";
    }


    @PostMapping("/submit")
    public String saveSubmission(@ModelAttribute("submission") Submission submission, BindingResult result) throws SQLException {
        if (result.hasErrors()) {
            System.out.println("Errors: " + result.getAllErrors());
            return "/pages/sub/create-sub";
        }
        submission.setSubmissionDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userDao = new UserDaoImpl();
        AppUser currentUser = userDao.findByEmail(userDetails.getUsername());
        submission.setAuthorId(currentUser.getUserId());
        dao.save(submission);
        return "pages/sub/success";
    }

    @PostMapping("/update/{submissionId}")
    public void updateSubmission(@PathVariable int submissionId, Submission submission) {
        try {
            dao.update(submission);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/delete/{submissionId}")
    public void deleteCourse(@PathVariable int submissionId) {
        try {
            dao.delete(submissionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
private static List<Course> coursesForUser() throws SQLException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDaoImpl myUserDao = new UserDaoImpl();
    AppUser currentUser = myUserDao.findByEmail(userDetails.getUsername());
    CourseUserDaoImpl myCourseUserDao = new CourseUserDaoImpl();
    List<Course> user_courses = myCourseUserDao.findCoursesByUser(currentUser.getUserId());
    return user_courses;
}

}

