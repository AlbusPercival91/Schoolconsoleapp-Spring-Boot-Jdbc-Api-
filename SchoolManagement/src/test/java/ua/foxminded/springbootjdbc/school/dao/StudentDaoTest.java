package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.*;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;
import ua.foxminded.springbootjdbc.school.testdata.dao.GeneratorDataRepository;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test-container")
@Sql(scripts = { "/drop_data.sql", "/init_tables.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudentDaoTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  private StudentDAO studentDao;
  private TestDataGenerator testData;

  @BeforeEach
  void setUp() {
    studentDao = new StudentDAO(jdbcTemplate);
    testData = new TestDataGenerator(new StudentMaker(), new GroupMaker(), new CourseMaker(),
        new GeneratorDataRepository(jdbcTemplate));
  }

  @Test
  @DisplayName("Should return true if number of students at course more than zero")
  void testFindStudentsRelatedToCourse_ShouldReturnNameOfCourses() {
    testData.createCourse();
    testData.createStudent();
    testData.createCourseStudentRelation();

    for (String s : new CourseMaker().generateCourses()) {
      long studentCount = studentDao.findStudentsRelatedToCourse(s).stream()
          .filter(course -> course.toString().trim().contains(" ")).count();
      Assertions.assertTrue(studentCount > 0);
    }
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 student assigned to one course")
  @CsvSource({ "12, Art", "190, Literature", "19, Computer Science", "21, Geography", "193, Physical Science",
      "1, Life Science", "9, English", "2, Mathematics", "150, Sports", "7, History" })
  void testAddStudentToTheCourse_ShouldReturnOneIfStudentAssigned(int studentId, String course) {
    testData.createCourse();
    testData.createStudent();
    Assertions.assertEquals(1, studentDao.addStudentToTheCourse(studentId, course));
  }

  @Test
  @DisplayName("Should return true when actual and inserted student are equals")
  void testAddNewStudent_ShouldReturnEqualsWhenNewStudentCreated() {
    Student student = new Student(4, "Harry", "Potter");
    studentDao.addNewStudent(student);
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
  void testDeleteStudentByID_ShouldReturnOneIfStudentRemoved() {
    String sql = "insert into school.students(group_id, first_name, last_name) values(9,'Albus','Dambldor');";
    jdbcTemplate.update(sql);
    int deleted = 0;

    if (studentDao.getStudentID().contains(1)) {
      deleted = studentDao.deleteStudentByID(1);
    }
    Assertions.assertEquals(1, deleted);
  }

  @Test
  @DisplayName("Should return 1 if student deleted from course Table in DB")
  void testRemoveStudentFromCourse_ShouldReturnOneIfStudentRemovedFromCourse() {
    testData.createCourse();
    testData.createStudent();
    String sql = """
            INSERT INTO school.students_courses_checkouts(student_id, course_id)
            SELECT
        (SELECT student_id FROM school.students WHERE student_id=17),
        (SELECT course_id FROM school.course WHERE course_name = 'Sports');
            """;
    jdbcTemplate.update(sql);
    int deleted = studentDao.removeStudentFromCourse(17, "Sports");
    Assertions.assertEquals(1, deleted);
  }

  @Test
  @DisplayName("Should return 1 if student updated")
  void testUpdateStudentById_ShouldReturnEqualsStringsWhenStudentUpdated() {
    Student student = new Student(4, "Harry", "Potter");
    studentDao.addNewStudent(student);
    Student updatedStudent = new Student(4, "Ron", "Wesley");
    studentDao.updateStudentById(1, updatedStudent);
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
  void testShowAllStudents_ShouldReturnAllStudents() {
    testData.createStudent();
    List<Object> actual = studentDao.showAllStudents();
    Assertions.assertEquals(200, actual.size());
  }

}
