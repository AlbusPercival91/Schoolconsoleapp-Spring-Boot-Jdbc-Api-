package ua.foxminded.springbootjdbc.school.testdatadao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;

@Repository
public class TestDataRepository {

  private final JdbcTemplate jdbcTemplate;

  public TestDataRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int createStudent(Student student) {
    String sql = "INSERT INTO school.students(group_id, first_name, last_name) " + "VALUES(?,?,?)";
    return jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName());
  }

  public int createGroup(Group group) {
    String sql = "INSERT INTO school.groups(group_name) " + "VALUES(?)";
    return jdbcTemplate.update(sql, group.getGroupName());
  }

  public int createCourse(Course course) {
    String sql = "INSERT INTO school.course(course_name, course_description) " + "VALUES(?,?)";
    return jdbcTemplate.update(sql, course.getCourseName(), course.getCourseDescription());
  }

  public int createCourseStudentRelation(StudentCourseRelation scRelation) {
    String sql = "INSERT INTO school.students_courses_checkouts(student_id,course_id) " + "VALUES(?,?)";
    return jdbcTemplate.update(sql, scRelation.getStudentId(), scRelation.getCourseId());
  }

}
