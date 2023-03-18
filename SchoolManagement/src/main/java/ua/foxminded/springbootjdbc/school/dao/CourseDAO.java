package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO {

  private final JdbcTemplate jdbcTemplate;

  public CourseDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Object> findCoursesWithLessOrEqualsStudents(int students) {
    String sql = """
        SELECT course.course_id, course.course_name, COUNT(*) AS num_students
        FROM school.course
        LEFT JOIN school.students_courses_checkouts ON course.course_id = students_courses_checkouts.course_id
        GROUP BY course.course_id
        HAVING COUNT(*) <= ?
                 """;
    return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("course_name"), students);
  }
}
