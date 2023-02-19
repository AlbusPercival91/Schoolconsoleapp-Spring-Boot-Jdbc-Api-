package ua.foxminded.springbootjdbc.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.springbootjdbc.school.entity.Course;

public class CourseRowMapper implements RowMapper<Course>{

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Course(rs.getString("course_name"), rs.getString("course_description"));
    }
}
