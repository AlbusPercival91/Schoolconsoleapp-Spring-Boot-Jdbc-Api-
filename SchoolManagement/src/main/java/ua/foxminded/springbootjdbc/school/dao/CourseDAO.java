package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.mapper.CourseRowMapper;

@Repository
public class CourseDAO {

  private final JdbcTemplate jdbcTemplate;

  public CourseDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Course> findCoursesWithLessOrEqualsStudents(int students) {
    String sql = """
        SELECT course.course_id, course.course_name, course.course_description, COUNT(school.students_courses_checkouts.student_id) AS student_count
        FROM school.course
        INNER JOIN school.students_courses_checkouts ON course.course_id = students_courses_checkouts.course_id
        GROUP BY course.course_id, course.course_name, course.course_description
        HAVING COUNT(school.students_courses_checkouts.student_id) <= ?;
                                 """;
    return jdbcTemplate.query(sql, new CourseRowMapper(), students);
  }
  
  
}
