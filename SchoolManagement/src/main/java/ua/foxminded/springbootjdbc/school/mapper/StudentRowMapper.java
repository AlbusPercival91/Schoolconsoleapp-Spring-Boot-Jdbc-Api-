package ua.foxminded.springbootjdbc.school.mapper;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.springbootjdbc.school.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
  @Override
  public Student mapRow(ResultSet rs, int i) throws SQLException {
    return new Student(rs.getInt("group_id"), rs.getString("first_name"), rs.getString("last_name"));
  }

}
