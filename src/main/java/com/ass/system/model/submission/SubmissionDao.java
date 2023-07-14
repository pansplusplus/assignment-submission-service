package com.ass.system.model.submission;

import java.sql.SQLException;
import java.util.List;

public interface SubmissionDao {

    List<Submission> findAll();

    Submission findById(long searchId) throws SQLException;

    void save(Submission savedSubmission);

    void update(Submission updatedSubmission) throws SQLException;

    void delete(long searchId) throws SQLException;

    int idExists(long searchId);
}
