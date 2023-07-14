package com.ass.system.model.assignment;


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
public class AssignmentDaoImpl implements AssignmentDao {

    SpringJdbcConfig config = new SpringJdbcConfig();
    DataSource dataSource = config.mysqlDataSource();
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    @Override
    public List<Assignment> findAll() {
        String sql = "SELECT * FROM assignment";
        return namedParameterJdbcTemplate.query(sql, new AssignmentMapper());
    }

    @Override
    public Assignment findById(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "select * from assignment where assignmentId = :searchId";
        Assignment result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params,
                    new AssignmentMapper());
        } catch (EmptyResultDataAccessException e) {
// do nothing, return null
        }
        return result;
    }

    @Override
    public List<Assignment> findByCourseId(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "select * from assignment where courseId = :searchId";
        List<Assignment> result = null;
        result = namedParameterJdbcTemplate.query(sql, params, new AssignmentMapper());
        return result;
    }

    @Override
    public void save(Assignment savedAssignment) {

        String sql = "INSERT INTO assignment (assignmentId, courseId, assignmentName, assignmentCredits, dueDate) VALUES " +
                "(:assignmentId, :courseId, :assignmentName, :assignmentCredits, :dueDate);";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(savedAssignment));
    }

    @Override
    public void update(Assignment updatedAssignment) throws SQLException {
        String sql = "UPDATE assignment SET assignmentId = :assignmentId, courseId = :courseId  WHERE assignmentId = :assignmentId";
        namedParameterJdbcTemplate.update(sql,
                getSqlParameterByModel(updatedAssignment));
    }

    @Override
    public void delete(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "DELETE FROM assignment WHERE assignmentId = :searchId";
        namedParameterJdbcTemplate.update(sql,params);
    }



    @Override
    public int idExists(long searchId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "SELECT count(*) FROM assignment WHERE assignmentId = :searchId";
        int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count;
    }

    private static final class AssignmentMapper implements
            RowMapper<Assignment> {
        public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assignment assignment = new Assignment();
            assignment.setAssignmentId(rs.getInt("assignmentId"));
            assignment.setCourseId(rs.getInt("courseId"));
            assignment.setAssignmentName(rs.getString("assignmentName"));
            assignment.setAssignmentCredits(rs.getInt("assignmentCredits"));
            assignment.setDueDate(rs.getDate("dueDate"));
            return assignment;
        }
    }

    private SqlParameterSource getSqlParameterByModel(Assignment assignment) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("assignmentId", assignment.getAssignmentId());
        paramSource.addValue("courseId", assignment.getCourseId());
        paramSource.addValue("assignmentName", assignment.getAssignmentName());
        paramSource.addValue("assignmentCredits", assignment.getAssignmentCredits());
        paramSource.addValue("dueDate", assignment.getDueDate());
        return paramSource;
    }
}
