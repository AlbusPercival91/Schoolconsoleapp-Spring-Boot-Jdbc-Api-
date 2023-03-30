package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;
import ua.foxminded.springbootjdbc.school.testdata.dao.TestDataService;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class GroupDAOTest {

  @Autowired
  private TestDataService testData;

  @Autowired
  private GroupService groupService;

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
  @DisplayName("Should return 10 if matched groups less than or equal to students")
  @CsvSource({ "30", "100" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testFindGroupsWithLessOrEqualsStudents(int number) {
    testData.createGroup();
    testData.createStudent();
    Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(number);
    int matchedPattern = (int) actual.stream().map(Group::toString).map(pattern::matcher).filter(Matcher::find).count();
    Assertions.assertEquals(10, matchedPattern);
  }

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testFindGroupsWithLessOrEqualsStudents_WhenStudentsZero() {
    testData.createGroup();
    testData.createStudent();
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(0);
    Assertions.assertTrue(actual.isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group updated")
  @CsvSource({ "aa-34, aa-35", "aa-35, 35-aa", "test, test-test", "123, 321", "aa-aa, bb-bb", "00-00, 11-11",
      "!@-@$, )&-%^" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testEditGroupName(Group group, Group newGroup) {
    groupService.createGroup(group);
    Assertions.assertEquals(1, groupService.editGroupName(group.getGroupName(), newGroup.getGroupName()));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group deleted")
  @CsvSource({ "aa-34", "35-aa", "test", "123", "aa-aa", "00-00", "!@-@$" })
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testDeleteGroupByName(Group group) {
    groupService.createGroup(group);
    Assertions.assertEquals(1, groupService.deleteGroupByName(group.getGroupName()));
  }

  @Test
  @DisplayName("Should return 10 when initiated group test data")
  @Sql(scripts = "/init_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  void testShowAllGroups() {
    testData.createGroup();
    List<Group> actual = groupService.showAllGroups();
    Assertions.assertEquals(10, actual.size());
  }

}
