package ua.foxminded.springbootjdbc.school.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.interfaces.DummyDataDao;

@Repository
public class DummyDataDAO implements DummyDataDao {
  private final JdbcTemplate jdbcTemplate;

  public DummyDataDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int createStudent(Student student) {
    String sql = "INSERT INTO school.students(group_id, first_name, last_name) " + "VALUES(?,?,?)";
    return jdbcTemplate.update(sql, student.getGroupId(), student.getFirstName(), student.getLastName());
  }

  @Override
  public int createGroup(Group group) {
    String sql = "INSERT INTO school.groups(group_name) " + "VALUES(?)";
    return jdbcTemplate.update(sql, group.getGroupName());
  }

  @Override
  public int createCourse(Course course) {
    String sql = "INSERT INTO school.course(course_name, course_description) " + "VALUES(?,?)";
    return jdbcTemplate.update(sql, course.getCourseName(), course.getCourseDescription());
  }

  @Override
  public int createCourseStudentRelation(StudentCourseRelation scRelation) {
    String sql = "INSERT INTO school.students_courses_checkouts(student_id,course_id) " + "VALUES(?,?)";
    return jdbcTemplate.update(sql, scRelation.getStudentId(), scRelation.getCourseId());
  }

}
