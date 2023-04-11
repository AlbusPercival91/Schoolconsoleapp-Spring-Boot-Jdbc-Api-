package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.testdata.dao.GeneratorDataRepository;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test-container")
@Sql(scripts = { "/drop_data.sql", "/init_tables.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CourseDaoJdbcTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  private CourseDAO courseDao;
  private TestDataGenerator testData;
  private GeneratorDataRepository testRepository;

  @BeforeEach
  void setUp() {
    courseDao = new CourseDAO(jdbcTemplate);
    testRepository = new GeneratorDataRepository(jdbcTemplate);
    testData = new TestDataGenerator();
  }

  @ParameterizedTest
  @DisplayName("Should return true if student exist on courses ")
  @CsvSource({ "40", "100", "70" })
  void testFindCoursesWithLessOrEqualsStudents(int number) {
    testData.createStudent(testRepository);
    testData.createCourse(testRepository);
    testData.createCourseStudentRelation(testRepository);
    List<Course> actual = courseDao.findCoursesWithLessOrEqualsStudents(number);
    Assertions.assertNotNull(actual);
    Assertions.assertTrue(actual.size() > 0);
  }

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  void testFindCoursesWithLessOrEqualsStudents_WhenStudentsZero() {
    testData.createStudent(testRepository);
    testData.createCourse(testRepository);
    testData.createCourseStudentRelation(testRepository);
    List<Course> actual = courseDao.findCoursesWithLessOrEqualsStudents(0);
    Assertions.assertTrue(actual.isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course updated")
  @CsvSource({ "History, TBD, Geography, TBD-2", "Art, TBD, Paint, TBD-3", "Sports, TBD, Yoga, TBD-5",
      "English, TBD, Spanish, TBD-6", "123, TBD, 321, asdf", "%$#, TBD, $%^&, TBDTBD", "!@-@$, )&-%^, Swimming, TBD" })
  void testEditCourseNameAndDescription(String courseName, String courseDescription, String newCourseName,
      String newCourseDescription) {
    Course course = new Course(courseName, courseDescription);
    courseDao.createCourse(course);
    Assertions.assertEquals(1,
        courseDao.editCourseNameAndDescription(course.getCourseName(), newCourseName, newCourseDescription));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course deleted")
  @CsvSource({ "History, TBD", "Swimming, TBD", "Paint, TBD-3", "Spanish, TBD-6", "Geography, TBD-2" })
  void testDeleteCourseByName(String courseName, String courseDescription) {
    Course course = new Course(courseName, courseDescription);
    courseDao.createCourse(course);
    Assertions.assertEquals(1, courseDao.deleteCourseByName(course.getCourseName()));
  }

  @Test
  @DisplayName("Should return 10 when initiated group test data")
  void testShowAllCourses() {
    testData.createCourse(testRepository);
    List<Course> actual = courseDao.showAllCourses();
    Assertions.assertEquals(10, actual.size());
  }

}
