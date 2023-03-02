package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.mapper.GroupRowMapper;
import ua.foxminded.springbootjdbc.school.mapper.StudentRowMapper;

@Repository
public class SchoolDAO {

  private final JdbcTemplate jdbcTemplate;

  public SchoolDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(int students) {
    String sql = """
        SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS number_of_students
        FROM school.groups LEFT JOIN school.students ON groups.group_id = students.group_id
        GROUP BY groups.group_id, groups.group_name HAVING COUNT(students.student_id) <= ?
        """;
    return jdbcTemplate.query(sql, new GroupRowMapper(), students);
  }

  public List<Student> findStudentsRelatedToCourse(String courseName) {
    String sql = """
        SELECT group_id, first_name, last_name FROM school.students
        JOIN school.students_courses_checkouts
        ON school.students_courses_checkouts.student_id = school.students.student_id
        JOIN school.course ON school.students_courses_checkouts.course_id = school.course.course_id
        WHERE school.course.course_name = ?
              """;
    return jdbcTemplate.query(sql, new StudentRowMapper(), courseName);
  }

}
