package ua.foxminded.springbootjdbc.school.dao.mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.springbootjdbc.school.dao.StudentDAO;
import ua.foxminded.springbootjdbc.school.dao.StudentService;
import ua.foxminded.springbootjdbc.school.entity.Student;
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

  @ParameterizedTest
  @DisplayName("Should return true if number of students at course more than zero")
  @CsvSource({ "Art", "Sports", "History" })
  void testFindStudentsRelatedToCourse(String courseName) {
    List<Student> expected = new ArrayList<>();
    when(studentDAO.findStudentsRelatedToCourse(courseName)).thenReturn(expected);
    List<Student> actual = studentService.findStudentsRelatedToCourse(courseName);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected, actual);
    verify(studentDAO).findStudentsRelatedToCourse(courseName);
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 student assigned to one course")
  @CsvSource({ "12, Art", "190, Literature", "19, Computer Science", "21, Geography", "193, Physical Science",
      "1, Life Science", "9, English", "2, Mathematics", "150, Sports", "7, History" })
  void testAddStudentToTheCourse(int studentId, String courseName) {
    Integer expectedCount = 1;
    when(studentDAO.addStudentToTheCourse(studentId, courseName)).thenReturn(expectedCount);
    Integer actualCount = studentService.addStudentToTheCourse(studentId, courseName);

    Assertions.assertNotNull(actualCount);
    Assertions.assertEquals(expectedCount, actualCount);
    verify(studentDAO).addStudentToTheCourse(studentId, courseName);
  }

  @ParameterizedTest
  @DisplayName("Should return true when actual and inserted student are equals")
  @CsvSource({ "1, Harry, Potter", "1, Ron, Wesley", "1, Herminone, Granger", "4, Draco, Malfoy " })
  void testAddNewStudent(int groupId, String name, String surname) {
    Integer expectedCount = 1;
    Student student = new Student(groupId, name, surname);
    when(studentDAO.addNewStudent(any(Student.class))).thenReturn(expectedCount);
    Integer actualCount = studentService.addNewStudent(student);

    Assertions.assertNotNull(student.getGroupId());
    Assertions.assertNotNull(student.getFirstName());
    Assertions.assertNotNull(student.getLastName());
    Assertions.assertEquals(expectedCount, actualCount);
    verify(studentDAO).addNewStudent(any(Student.class));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if student deleted from DB")
  @CsvSource({ "1", "2", "3" })
  void testDeleteStudentByID(int studentId) {
    Integer expectedCount = 1;
    when(studentDAO.deleteStudentByID(studentId)).thenReturn(expectedCount);
    Integer actualCount = studentService.deleteStudentByID(studentId);

    Assertions.assertNotNull(actualCount);
    Assertions.assertEquals(expectedCount, actualCount);
    verify(studentDAO).deleteStudentByID(studentId);
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if student deleted from course Table in DB")
  @CsvSource({ "12, Art", "190, Literature", "19, Computer Science", "21, Geography", "193, Physical Science",
      "1, Life Science", "9, English", "2, Mathematics", "150, Sports", "7, History" })
  void testRemoveStudentFromCourse(int studentId, String courseName) {
    Integer expectedCount = 1;
    when(studentDAO.removeStudentFromCourse(studentId, courseName)).thenReturn(expectedCount);
    Integer actualCount = studentService.removeStudentFromCourse(studentId, courseName);

    Assertions.assertNotNull(actualCount);
    Assertions.assertEquals(expectedCount, actualCount);
    verify(studentDAO).removeStudentFromCourse(studentId, courseName);
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if student updated")
  @CsvSource({ "1, 2, James, Potter", "3, 1, Fred, Wesley", "9, 1, Bartey, Krauch" })
  void testUpdateStudentById(int updateId, int groupId, String name, String surname) {
    Integer expectedCount = 1;
    Student studentUpdate = new Student(groupId, name, surname);
    when(studentDAO.updateStudentById(updateId, studentUpdate)).thenReturn(expectedCount);
    Integer actualCount = studentService.updateStudentById(updateId, studentUpdate);

    Assertions.assertNotNull(actualCount);
    Assertions.assertEquals(expectedCount, actualCount);
    verify(studentDAO).updateStudentById(updateId, studentUpdate);
  }

  @ParameterizedTest
  @DisplayName("Should be equals expected and actual student lists")
  @CsvSource({ "1, Harry, Potter", "1, Ron, Wesley", "1, Herminone, Granger", "4, Draco, Malfoy " })
  void testShowAllStudents(int groupId, String name, String surname) {
    List<Object> expected = new ArrayList<>();
    Student student = new Student(groupId, name, surname);
    expected.add(student);
    when(studentDAO.showAllStudents()).thenReturn(expected);
    List<Object> actual = studentService.showAllStudents();
   
    Assertions.assertTrue(!expected.isEmpty() && !actual.isEmpty());
    Assertions.assertNotNull(student.getGroupId());
    Assertions.assertNotNull(student.getFirstName());
    Assertions.assertNotNull(student.getLastName());
    Assertions.assertEquals(expected, actual);
    verify(studentDAO).showAllStudents();
  }
}
