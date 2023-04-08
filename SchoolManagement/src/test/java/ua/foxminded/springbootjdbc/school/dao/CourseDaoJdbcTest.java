package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.springbootjdbc.school.entity.Course;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test-container")
@Sql(scripts = { "/drop_data.sql", "/init_tables.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CourseDaoJdbcTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  private CourseDAO courseDao;

  @BeforeEach
  void setUp() {
    courseDao = new CourseDAO(jdbcTemplate);
  }

  @Test
  void testShowAllCourses() {
    Course course = new Course("Swimming");
    int addCourse = courseDao.createCourse(course);
    List<Course> actual = courseDao.showAllCourses();
    Assertions.assertEquals(1, addCourse);
    Assertions.assertEquals(1, actual.size());
  }
}
