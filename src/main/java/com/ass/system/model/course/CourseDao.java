package com.ass.system.model.course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {

    List<Course> findAll();

    Course findById(long searchId) throws SQLException;

    void save(Course savedCourse);

    void update(Course updatedCourse) throws SQLException;

    void delete(long searchId) throws SQLException;

    int idExists(long searchId);
}
