package com.ass.system.model.user;

import com.ass.system.config.SpringJdbcConfig;
import com.ass.system.model.course.Course;
import com.ass.system.model.course.CourseDaoImpl;
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
public class UserDaoImpl implements UserDao {
    SpringJdbcConfig config = new SpringJdbcConfig();
    DataSource dataSource = config.mysqlDataSource();
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    @Override
    public List<AppUser> findAll() {
        String sql = "SELECT * FROM user";
        return namedParameterJdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public AppUser findById(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "select * from user where userId = :searchId";
        AppUser result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params,
                    new UserMapper());
        } catch (EmptyResultDataAccessException e) {
// do nothing, return null
        }
        return result;
    }

    @Override
    public AppUser findByEmail(String searchId) throws SQLException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("searchId", searchId);
        String sql = "select * from user where email = :searchId";
        AppUser result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params,
                    new UserMapper());
        } catch (EmptyResultDataAccessException e) {
// do nothing, return null
        }
        return result;
    }

    @Override
    public void save(AppUser savedUser) {
        String sql = "INSERT INTO user (first_name, last_name, email, password) VALUES " +
                "(:first_name, :last_name, :email, :password);";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(savedUser));
    }

    @Override
    public void update(AppUser updatedUser) throws SQLException {
        String sql = "UPDATE user SET userId = :userId  WHERE userId = :userId";
        namedParameterJdbcTemplate.update(sql,
                getSqlParameterByModel(updatedUser));
    }

    @Override
    public void delete(long searchId) throws SQLException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "DELETE FROM user WHERE userId = :searchId";
        namedParameterJdbcTemplate.update(sql,params);
    }

    @Override
    public int idExists(long searchId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("searchId", searchId);
        String sql = "SELECT count(*) FROM user WHERE userId = :searchId";
        int count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count;
    }

    private static final class UserMapper implements
            RowMapper<AppUser> {
        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            AppUser user = new AppUser();
            user.setUserId(rs.getInt("user_id"));
            return user;
        }
    }

    private SqlParameterSource getSqlParameterByModel(AppUser user) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
//        paramSource.addValue("user_id", user.getUserId());
        paramSource.addValue("email", user.getEmail());
        paramSource.addValue("first_name", user.getFirstName());
        paramSource.addValue("last_name", user.getLastName());
        paramSource.addValue("password", user.getPassword());
        return paramSource;
    }
}
