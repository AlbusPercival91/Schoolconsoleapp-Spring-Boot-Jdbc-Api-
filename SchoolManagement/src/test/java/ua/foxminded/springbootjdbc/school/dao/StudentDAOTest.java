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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.dao.GeneratorDataService;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StudentDAOTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private GeneratorDataService testData;

  @Autowired
  private StudentService studentService;

  @Autowired
  private CourseMaker courseMaker;

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

  @Test
  @DisplayName("Should return true if number of students at course more than zero")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testFindStudentsRelatedToCourse() {
    testData.createCourse();
    testData.createStudent();
    testData.createCourseStudentRelation();

    for (String s : courseMaker.generateCourses()) {
      long studentCount = studentService.findStudentsRelatedToCourse(s).stream()
          .filter(course -> course.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 student assigned to one course")
  @CsvSource({ "12, Art", "190, Literature", "19, Computer Science", "21, Geography", "193, Physical Science",
      "1, Life Science", "9, English", "2, Mathematics", "150, Sports", "7, History" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testAddStudentToTheCourse(int studentId, String course) {
    testData.createCourse();
    testData.createStudent();
    Assertions.assertEquals(1, studentService.addStudentToTheCourse(studentId, course));
  }

  @Test
  @DisplayName("Should return true when actual and inserted student are equals")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testAddNewStudent() {
    Student student = new Student(4, "Harry", "Potter");
    studentService.addNewStudent(student);
    String sql = "SELECT * FROM school.students;";
    String actual = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      return rs.getInt("student_id") + " " + rs.getInt("group_id") + " " + rs.getString("first_name") + " "
          + rs.getString("last_name");
    });
    String expected = 1 + " " + student.getGroupId() + " " + student.getFirstName() + " " + student.getLastName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Should return 1 if student deleted from DB")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testDeleteStudentByID() {
    String sql = "insert into school.students(group_id, first_name, last_name) values(9,'Albus','Dambldor');";
    jdbcTemplate.update(sql);
    int deleted = 0;

    if (studentService.getStudentID().contains(1)) {
      deleted = studentService.deleteStudentByID(1);
    }
    Assertions.assertEquals(1, deleted);
  }

  @Test
  @DisplayName("Should return 1 if student deleted from course Table in DB")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testRemoveStudentFromCourse() {
    testData.createCourse();
    testData.createStudent();
    String sql = """
            INSERT INTO school.students_courses_checkouts(student_id, course_id)
            SELECT
        (SELECT student_id FROM school.students WHERE student_id=17),
        (SELECT course_id FROM school.course WHERE course_name = 'Sports');
            """;
    jdbcTemplate.update(sql);
    int deleted = studentService.removeStudentFromCourse(17, "Sports");
    Assertions.assertEquals(1, deleted);
  }

  @Test
  @DisplayName("Should return 1 if student updated")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testUpdateStudentById() {
    Student student = new Student(4, "Harry", "Potter");
    studentService.addNewStudent(student);
    Student updatedStudent = new Student(4, "Ron", "Wesley");
    studentService.updateStudentById(1, updatedStudent);
    String sql = "SELECT * FROM school.students;";

    String actual = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
      return rs.getInt("student_id") + " " + rs.getInt("group_id") + " " + rs.getString("first_name") + " "
          + rs.getString("last_name");
    });
    String expected = 1 + " " + updatedStudent.getGroupId() + " " + updatedStudent.getFirstName() + " "
        + updatedStudent.getLastName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Should return 200 when initiated students test data")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testShowAllStudents() {
    testData.createStudent();
    List<Object> actual = studentService.showAllStudents();
    Assertions.assertEquals(200, actual.size());
  }

}
