package com.ass.system.model.assignment;

import java.sql.SQLException;
import java.util.List;

public interface AssignmentDao {

    List<Assignment> findAll();

    Assignment findById(long searchId) throws SQLException;

    List<Assignment> findByCourseId(long searchId) throws SQLException;
    void save(Assignment savedAssignment);

    void update(Assignment updatedAssignment) throws SQLException;

    void delete(long searchId) throws SQLException;

    int idExists(long searchId);
}
