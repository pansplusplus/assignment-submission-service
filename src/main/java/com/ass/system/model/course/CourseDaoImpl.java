package com.ass.system.model.course;


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

public class CourseDaoImpl implements CourseDao {
    static SpringJdbcConfig config = new SpringJdbcConfig();
    static DataSource dataSource = config.mysqlDataSource();
    static NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    @Override
    public List<Course> findAll() {
        String sql = "SELECT * FROM course";
        return namedParameterJdbcTemplate.query(sql, new CourseMapper());
    }

    @Override
    public Course findById(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "select * from course where courseId = :searchId";
        Course result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params,
                    new CourseMapper());
        } catch (EmptyResultDataAccessException e) {
// do nothing, return null
        }
        return result;
    }

    public static String courseNameById(int searchId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "select courseName from course where courseId = :searchId";
        String result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, String.class);
        } catch (EmptyResultDataAccessException e) {
// do nothing, return null
        }
        return result;
    }

    @Override
    public void save(Course savedCourse) {
        String sql = "INSERT INTO course (courseId) VALUES " +
                "(:courseId);";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(savedCourse));
    }

    @Override
    public void update(Course updatedCourse) throws SQLException {
        String sql = "UPDATE course SET courseId = :courseId  WHERE courseId = :submissionId";
        namedParameterJdbcTemplate.update(sql,
                getSqlParameterByModel(updatedCourse));
    }

    @Override
    public void delete(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "DELETE FROM course WHERE courseId = :searchId";
        namedParameterJdbcTemplate.update(sql,params);

    }

    @Override
    public int idExists(long searchId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "SELECT count(*) FROM course WHERE courseId = :searchId";
        int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count;
    }

    public static final class CourseMapper implements
            RowMapper<Course> {
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(rs.getString("courseName"));
            return course;
        }
    }


    private SqlParameterSource getSqlParameterByModel(Course course) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("courseId", course.getCourseId());
        paramSource.addValue("courseName", course.getCourseName());
        return paramSource;
    }
}
