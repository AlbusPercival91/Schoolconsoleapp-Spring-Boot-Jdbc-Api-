package ua.foxminded.springbootjdbc.school.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;

public class StudentCourseRowMapper implements RowMapper<StudentCourseRelation> {

  @Override
  public StudentCourseRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new StudentCourseRelation(rs.getInt("student_id"), rs.getInt("course_id"));
  }

}
