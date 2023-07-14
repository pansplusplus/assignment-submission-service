package com.ass.system.model.submission;

import com.ass.system.config.SpringJdbcConfig;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class SubmissionDaoImpl implements SubmissionDao {
    SpringJdbcConfig config = new SpringJdbcConfig();
    DataSource dataSource = config.mysqlDataSource();
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    @Override
    public List<Submission> findAll() {
        String sql = "SELECT * FROM submission";
        return namedParameterJdbcTemplate.query(sql, new SubmissionMapper());
    }

    @Override
    public Submission findById(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "select * from submission where submissionId = :searchId";
        Submission result = null;
            result = namedParameterJdbcTemplate.queryForObject(sql, params,
                    new SubmissionMapper());
        return result;
    }

    @Override
    public void save(Submission savedSubmission) {
        String sql = "INSERT INTO submission (submissionId, assignmentId, authorId, submissionDate, submissionContent) VALUES " +
                "(:submissionId, :assignmentId, :authorId, :submissionDate, :submissionContent);";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(savedSubmission));
    }

    @Override
    public void update(Submission updatedSubmission) throws SQLException {
        String sql = "UPDATE submission SET submissionId = :submissionId, assignmentId = \" +\n" +
                "                \":assignmentId,authorId = :authorId,  submissionDate = :submissionDate, submissionContent" +
                "= :submissionContent WHERE submissionId = :submissionId";
        namedParameterJdbcTemplate.update(sql,
                getSqlParameterByModel(updatedSubmission));
    }

    @Override
    public void delete(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "DELETE FROM submission WHERE submissionId = :searchId";
        namedParameterJdbcTemplate.update(sql,params);
    }

    @Override
    public int idExists(long searchId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "SELECT count(*) FROM submission WHERE submissionId = :searchId";
        int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count;
    }

    private static final class SubmissionMapper implements
            RowMapper<Submission> {
        public Submission mapRow(ResultSet rs, int rowNum) throws SQLException {
            Submission submission = new Submission();
            submission.setSubmissionId(rs.getInt("submissionId"));
            submission.setAssignmentId(rs.getInt("assignmentId"));
            submission.setAuthorId(rs.getInt("authorId"));
            submission.setSubmissionDate(rs.getDate("submissionDate"));
            submission.setSubmissionContent(rs.getString("submissionContent"));
            return submission;
        }
    }

    private SqlParameterSource getSqlParameterByModel(Submission submission) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("submissionId", submission.getSubmissionId());
        paramSource.addValue("assignmentId", submission.getAssignmentId());
        paramSource.addValue("authorId", submission.getAuthorId());
        paramSource.addValue("submissionDate", submission.getSubmissionDate());
        paramSource.addValue("submissionContent", submission.getSubmissionContent());
        return paramSource;
    }
}
