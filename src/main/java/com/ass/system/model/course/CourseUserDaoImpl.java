package com.ass.system.model.course;

import java.util.List;
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
public class CourseUserDaoImpl {
    SpringJdbcConfig config = new SpringJdbcConfig();
    DataSource dataSource = config.mysqlDataSource();
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    public List<Course> findCoursesByUser(long searchUserId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchUserId", searchUserId);
        String sql = "SELECT courseId FROM course_users WHERE user_id = :searchUserId";
        return namedParameterJdbcTemplate.query(sql, params, new CourseForUserMapper());
    }

    public static final class CourseForUserMapper implements
            RowMapper<Course> {
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setCourseId(rs.getInt("courseId"));
            course.setCourseName(new CourseDaoImpl().courseNameById(course.getCourseId()));
            return course;
        }
    }
}
