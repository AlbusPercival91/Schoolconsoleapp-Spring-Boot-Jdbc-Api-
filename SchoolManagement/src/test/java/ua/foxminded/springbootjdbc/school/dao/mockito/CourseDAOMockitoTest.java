package ua.foxminded.springbootjdbc.school.dao.mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.springbootjdbc.school.dao.CourseDAO;
import ua.foxminded.springbootjdbc.school.dao.CourseService;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CourseDAOMockitoTest {

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

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  void testFindCoursesWithLessOrEqualsStudents_WhenStudentsZero() {
    List<Course> expected = new ArrayList<>();
    when(courseDAO.findCoursesWithLessOrEqualsStudents(0)).thenReturn(expected);
    List<Course> actual = courseService.findCoursesWithLessOrEqualsStudents(0);

    Assertions.assertTrue(actual.isEmpty());
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
    Integer expectedCount = 1;
    when(courseDAO.editCourseNameAndDescription(courseName, newCourseName, newCourseDescription))
        .thenReturn(expectedCount);
    Integer actualCount = courseService.editCourseNameAndDescription(courseName, newCourseName, newCourseDescription);

    Assertions.assertEquals(expectedCount, actualCount);
    verify(courseDAO).editCourseNameAndDescription(courseName, newCourseName, newCourseDescription);
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 course deleted")
  @CsvSource({ "History", "Swimming", "Paint", "Spanish", "Geography" })
  void testDeleteCourseByName(String courseName) {
    Integer expectedCount = 1;
    when(courseDAO.deleteCourseByName(courseName)).thenReturn(expectedCount);
    Integer actualCount = courseService.deleteCourseByName(courseName);

    Assertions.assertEquals(expectedCount, actualCount);
    verify(courseDAO).deleteCourseByName(courseName);
  }

  @Test
  @DisplayName("Should be equals expected and actual course lists")
  void testShowAllCourses() {
    List<Course> expected = new ArrayList<>();
    when(courseDAO.showAllCourses()).thenReturn(expected);
    List<Course> actual = courseService.showAllCourses();

    Assertions.assertEquals(expected, actual);
    verify(courseDAO).showAllCourses();
  }
}
