package ua.foxminded.springbootjdbc.school.dao.mockito;

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
import ua.foxminded.springbootjdbc.school.dao.StudentDAO;
import ua.foxminded.springbootjdbc.school.dao.StudentService;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StudentDaoMockitoTest {

  @Autowired
  private StudentService studentService;

  @MockBean
  private StudentDAO studentDAO;

  @MockBean
  private ConsoleMenuManager consoleMenuRunner;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("Should return true if number of students at course more than zero")
  void testFindStudentsRelatedToCourse() {

  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 student assigned to one course")
  @CsvSource({ "12, Art", "190, Literature", "19, Computer Science", "21, Geography", "193, Physical Science",
      "1, Life Science", "9, English", "2, Mathematics", "150, Sports", "7, History" })
  void testAddStudentToTheCourse(int studentId, String course) {

  }

  @Test
  @DisplayName("Should return true when actual and inserted student are equals")
  void testAddNewStudent() {

  }

  @Test
  @DisplayName("Should return 1 if student deleted from DB")
  void testDeleteStudentByID() {

  }

  @Test
  @DisplayName("Should return 1 if student deleted from course Table in DB")
  void testRemoveStudentFromCourse() {

  }

  @Test
  @DisplayName("Should return 1 if student updated")
  void testUpdateStudentById() {

  }

  @Test
  @DisplayName("Should return 200 when initiated students test data")
  void testShowAllStudents() {

  }
}
