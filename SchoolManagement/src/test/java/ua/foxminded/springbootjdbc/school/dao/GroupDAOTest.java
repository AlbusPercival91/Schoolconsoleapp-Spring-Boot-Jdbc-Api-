package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.*;
import org.testcontainers.containers.*;
import org.testcontainers.junit.jupiter.*;
import org.testcontainers.junit.jupiter.Container;
import ua.foxminded.springbootjdbc.school.console.StudentMenuComponents;
import ua.foxminded.springbootjdbc.school.console.ConsoleMenuRunner;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class GroupDAOTest {

  private static final int MAX_STUDENTS = 30;

  @Autowired
  private TestDataService testData;

  @Autowired
  private GroupService groupService;

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

  @Test
  @DisplayName("Should find all groups with less than or equal to " + MAX_STUDENTS + " students")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void findGroupsWithLessOrEqualsStudents() {
    testData.createGroup();
    testData.createStudent();
    Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(MAX_STUDENTS);
    int matchedPattern = (int) actual.stream().map(Group::toString).map(pattern::matcher).filter(Matcher::find).count();
    Assertions.assertEquals(10, matchedPattern);
  }

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void findGroupsWithZeroMaxStudents() {
    testData.createGroup();
    testData.createStudent();
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(0);
    Assertions.assertTrue(actual.isEmpty());
  }

  @Test
  @DisplayName("Should return all groups when the maximum number of students is greater than the number of students in any group")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void findGroupsWithLargeMaxStudents() {
    testData.createGroup();
    testData.createStudent();
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(100);
    Assertions.assertEquals(10, actual.size());
  }

}
