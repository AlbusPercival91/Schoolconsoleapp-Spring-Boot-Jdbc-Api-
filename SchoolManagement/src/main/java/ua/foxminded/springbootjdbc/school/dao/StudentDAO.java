package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.mapper.StudentRowMapper;

@Repository
public class StudentDAO {

  private final JdbcTemplate jdbcTemplate;

  public StudentDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int addNewStudent(Student student) {
    String sql = "insert into school.students(group_id, first_name, last_name) values(?,?,?)";
    return jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName());
  }

  public int deleteStudentByID(int id) {
    String sql = "delete from school.students where student_id = ?";
    return jdbcTemplate.update(sql, id);
  }

  public List<Integer> getStudentID() {
    String sql = "select student_id from school.students;";
    return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("student_id"));
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

  public int addStudentToTheCourse(Integer studentId, String courseName) {
    String sql = """
              INSERT INTO school.students_courses_checkouts(student_id, course_id)
              SELECT
              (SELECT student_id FROM school.students WHERE student_id= ?),
              (SELECT course_id FROM school.course WHERE course_name = ?);
        """;
    return jdbcTemplate.update(sql, studentId, courseName);
  }

  public int removeStudentFromCourse(Integer studentId, String courseName) {
    String sql = """
        DELETE
        FROM school.students_courses_checkouts
        WHERE student_id= ?
        AND
        course_id IN (SELECT course_id
        FROM school.course
        WHERE course_name = ?);
        """;
    return jdbcTemplate.update(sql, studentId, courseName);
  }

  public List<Object> showAllStudents() {
    String sql = "SELECT * FROM school.students;";
    return jdbcTemplate.query(sql,
        (rs, rowNum) -> "ID: " + rs.getInt("student_id") + " Group_ID: " + rs.getInt("group_id") + " First_Name: "
            + rs.getString("first_name") + " Last_Name: " + rs.getString("last_name"));
  }

}
