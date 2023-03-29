package ua.foxminded.springbootjdbc.school.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MockCourseDAOTest {

  @Autowired
  private TestDataService testData;

  @Autowired
  private CourseService courseService;

  @MockBean
  private CourseDAO courseDAO;

  @MockBean
  private ConsoleMenuManager consoleMenuRunner;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @ParameterizedTest
  @DisplayName("Should return true if student exist on courses ")
  @CsvSource({ "40", "100", "70" })
  void testFindCoursesWithLessOrEqualsStudents(int number) {
    List<Course> expected = new ArrayList<>();
    when(courseDAO.findCoursesWithLessOrEqualsStudents(number)).thenReturn(expected);
    List<Course> actual = courseService.findCoursesWithLessOrEqualsStudents(number);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected, actual);
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course created")
  @CsvSource({ "History, TBD", "Art, TBD", "Sports, TBD", "English, TBD", "123, TBD", "%$#, TBD", "!@-@$, )&-%^" })
  void testCreateCourse(String courseName, String courseDescription) {
    Integer expectedCount = 1;
    Course course = new Course(courseName, courseDescription);
    when(courseDAO.createCourse(any(Course.class))).thenReturn(expectedCount);
    Integer actualCount = courseService.createCourse(course);

    Assertions.assertNotNull(course.getCourseName());
    Assertions.assertNotNull(course.getCourseDescription());
    Assertions.assertEquals(expectedCount, actualCount);
    verify(courseDAO).createCourse(any(Course.class));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course updated")
  @CsvSource({ "History, TBD, Geography, TBD-2", "Art, TBD, Paint, TBD-3", "Sports, TBD, Yoga, TBD-5",
      "English, TBD, Spanish, TBD-6", "123, TBD, 321, asdf", "%$#, TBD, $%^&, TBDTBD", "!@-@$, )&-%^, Swimming, TBD" })
  void testEditCourseNameAndDescription(String courseName, String courseDescription, String newCourseName,
      String newCourseDescription) {
    Integer expectedCreationCount = 1;
    Integer expectedUpdateCount = 1;
    Course course = new Course(courseName, courseDescription);
    when(courseDAO.createCourse(any(Course.class))).thenReturn(expectedCreationCount);
    when(courseDAO.editCourseNameAndDescription(course.getCourseName(), newCourseName, newCourseDescription))
        .thenReturn(expectedUpdateCount);
    Integer actualCreatedCount = courseService.createCourse(course);
    Integer actualUpdateCount = courseService.editCourseNameAndDescription(courseName, newCourseName,
        newCourseDescription);

    Assertions.assertEquals(expectedCreationCount, actualCreatedCount);
    Assertions.assertEquals(expectedUpdateCount, actualUpdateCount);
    verify(courseDAO).editCourseNameAndDescription(courseName, newCourseName, newCourseDescription);
  }
}
