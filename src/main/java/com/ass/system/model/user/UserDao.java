package com.ass.system.model.user;

import com.ass.system.model.course.Course;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<AppUser> findAll();

    AppUser findById(long searchId) throws SQLException;

    AppUser findByEmail(String searchId) throws SQLException;
    void save(AppUser savedUser);

    void update(AppUser updatedUser) throws SQLException;

    void delete(long searchId) throws SQLException;

    int idExists(long searchId);
}
