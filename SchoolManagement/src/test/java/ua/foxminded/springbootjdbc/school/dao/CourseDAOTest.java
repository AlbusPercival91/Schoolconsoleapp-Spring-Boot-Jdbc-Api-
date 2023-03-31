package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;
import ua.foxminded.springbootjdbc.school.testdata.dao.GeneratedDataService;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CourseDAOTest {

  @Autowired
  private GeneratedDataService testData;

  @Autowired
  private CourseService courseService;

  @MockBean
  private ConsoleMenuManager consoleMenuRunner;

  @Container
  private static GenericContainer<?> container = new GenericContainer<>("openjdk:8-jdk-alpine").withExposedPorts(1521)
      .withEnv("TZ", "UTC").withCommand("/bin/sh", "-c",
          "apk update && apk add --no-cache h2 && java -cp /usr/share/java/h2*.jar org.h2.tools.Server -ifNotExists -tcp -tcpAllowOthers -tcpPort 1521");

  @BeforeEach
  void setUp() {
    container.start();
  }

  @AfterEach
  @Sql(scripts = "/drop_all_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void tearDown() {
    container.stop();
  }

  @ParameterizedTest
  @DisplayName("Should return true if student exist on courses ")
  @CsvSource({ "40", "100", "70" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testFindCoursesWithLessOrEqualsStudents(int number) {
    testData.createStudent();
    testData.createCourse();
    testData.createCourseStudentRelation();
    List<Course> actual = courseService.findCoursesWithLessOrEqualsStudents(number);
    Assertions.assertNotNull(actual);
  }

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testFindCoursesWithLessOrEqualsStudents_WhenStudentsZero() {
    testData.createStudent();
    testData.createCourse();
    testData.createCourseStudentRelation();
    List<Course> actual = courseService.findCoursesWithLessOrEqualsStudents(0);
    Assertions.assertTrue(actual.isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course updated")
  @CsvSource({ "History, TBD, Geography, TBD-2", "Art, TBD, Paint, TBD-3", "Sports, TBD, Yoga, TBD-5",
      "English, TBD, Spanish, TBD-6", "123, TBD, 321, asdf", "%$#, TBD, $%^&, TBDTBD", "!@-@$, )&-%^, Swimming, TBD" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testEditCourseNameAndDescription(String courseName, String courseDescription, String newCourseName,
      String newCourseDescription) {
    Course course = new Course(courseName, courseDescription);
    courseService.createCourse(course);

    Assertions.assertEquals(1,
        courseService.editCourseNameAndDescription(course.getCourseName(), newCourseName, newCourseDescription));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course deleted")
  @CsvSource({ "History, TBD", "Swimming, TBD", "Paint, TBD-3", "Spanish, TBD-6", "Geography, TBD-2" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testDeleteCourseByName(String courseName, String courseDescription) {
    Course course = new Course(courseName, courseDescription);
    courseService.createCourse(course);
    Assertions.assertEquals(1, courseService.deleteCourseByName(course.getCourseName()));
  }

  @Test
  @DisplayName("Should return 10 when initiated group test data")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testShowAllCourses() {
    testData.createCourse();
    List<Course> actual = courseService.showAllCourses();
    Assertions.assertEquals(10, actual.size());
  }

}
