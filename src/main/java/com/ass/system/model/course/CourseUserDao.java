package com.ass.system.model.course;

import java.util.List;

public interface CourseUserDao {

    List<Course> findCoursesByUser();

}
