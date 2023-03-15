package ua.foxminded.springbootjdbc.school.dao;

import java.util.stream.Stream;
import org.junit.jupiter.api.*;
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
import ua.foxminded.springbootjdbc.school.console.MenuFacade;
import ua.foxminded.springbootjdbc.school.console.ConsoleMenuRunner;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class StudentDAOTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private TestDataService testData;

  @Autowired
  private StudentService studentService;

  @MockBean
  private MenuFacade consoleFacade;

  @MockBean
  private ConsoleMenuRunner consoleMenuRunner;

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

  @TestFactory
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  Stream<DynamicTest> findStudentsRelatedToCourse__ShouldBeMoreZero() {
    testData.createGroup();
    testData.createCourse();
    testData.createStudent();
    testData.createCourseStudentRelation();
    return Stream.of(DynamicTest.dynamicTest("History course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("History").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Literature course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Literature").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Computer Science course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Computer Science").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Geography course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Geography").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Physical Science course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Physical Science").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Life Science course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Life Science").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("English course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("English").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Mathematics course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Mathematics").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Sports course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Sports").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }), DynamicTest.dynamicTest("Art course should have students", () -> {
      long studentCount = studentService.findStudentsRelatedToCourse("Art").stream()
          .filter(s -> s.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }));
  }

  @TestFactory
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  Stream<DynamicTest> addStudentToTheCourse_ShouldBeOne() {
    testData.createGroup();
    testData.createCourse();
    testData.createStudent();
    return Stream.of(
        DynamicTest.dynamicTest("Add student with ID: 12 to course: Art",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(12, "Art"))),
        DynamicTest.dynamicTest("Add student with ID: 190 to course: Literature",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(190, "Literature"))),
        DynamicTest.dynamicTest("Add student with ID: 19 to course: Computer Science",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(19, "Computer Science"))),
        DynamicTest.dynamicTest("Add student with ID: 21 to course: Geography",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(21, "Geography"))),
        DynamicTest.dynamicTest("Add student with ID: 193 to course: Physical Science",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(193, "Physical Science"))),
        DynamicTest.dynamicTest("Add student with ID: 1 to course: Life Science",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(1, "Life Science"))),
        DynamicTest.dynamicTest("Add student with ID: 9 to course: English",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(9, "English"))),
        DynamicTest.dynamicTest("Add student with ID: 2 to course: Mathematics",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(2, "Mathematics"))),
        DynamicTest.dynamicTest("Add student with ID: 150 to course: Sports",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(150, "Sports"))),
        DynamicTest.dynamicTest("Add student with ID: 7 to course: History",
            () -> Assertions.assertEquals(1, studentService.addStudentToTheCourse(7, "History"))));
  }

  @Test
  @DisplayName("Should return true when actual and inserted student are equals")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void addNewStudent() {
    testData.createGroup();
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
  void deleteStudentByID() {
    testData.createGroup();
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
  void removeStudentFromCourse() {
    testData.createGroup();
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

}
