package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.*;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;
import ua.foxminded.springbootjdbc.school.testdata.dao.GeneratorDataRepository;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test-container")
@Sql(scripts = { "/drop_data.sql", "/init_tables.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class GroupDaoTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  private GroupDAO groupDao;
  private TestDataGenerator testData;

  @BeforeEach
  void setUp() {
    groupDao = new GroupDAO(jdbcTemplate);
    testData = new TestDataGenerator(new StudentMaker(), new GroupMaker(), new CourseMaker(),
        new GeneratorDataRepository(jdbcTemplate));
  }

  @ParameterizedTest
  @DisplayName("Should return 10 if matched groups less than or equal to students")
  @CsvSource({ "30", "100" })
  void testFindGroupsWithLessOrEqualsStudents_ShouldReturnGroupsWhenMatchedLessOrEqual(int number) {
    testData.createGroup();
    testData.createStudent();
    Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
    List<Group> actual = groupDao.findGroupsWithLessOrEqualsStudents(number);
    int matchedPattern = (int) actual.stream().map(Group::toString).map(pattern::matcher).filter(Matcher::find).count();
    Assertions.assertEquals(10, matchedPattern);
  }

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  void testFindGroupsWithLessOrEqualsStudents_WhenStudentsZero_ShouldReturnEmptyList() {
    testData.createGroup();
    testData.createStudent();
    List<Group> actual = groupDao.findGroupsWithLessOrEqualsStudents(0);
    Assertions.assertTrue(actual.isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group updated")
  @CsvSource({ "aa-34, aa-35", "aa-35, 35-aa", "test, test-test", "123, 321", "aa-aa, bb-bb", "00-00, 11-11",
      "!@-@$, )&-%^" })
  void testEditGroupName_ShouldReturnOneIfGroupUpdated(Group group, Group newGroup) {
    groupDao.createGroup(group);
    Assertions.assertEquals(1, groupDao.editGroupName(group.getGroupName(), newGroup.getGroupName()));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group deleted")
  @CsvSource({ "aa-34", "35-aa", "test", "123", "aa-aa", "00-00", "!@-@$" })
  void testDeleteGroupByName_ShouldReturnOneIfGroupDeleted(Group group) {
    groupDao.createGroup(group);
    Assertions.assertEquals(1, groupDao.deleteGroupByName(group.getGroupName()));
  }

  @Test
  @DisplayName("Should return 10 when initiated group test data")
  void testShowAllGroups_ShouldReturnAllGroups() {
    testData.createGroup();
    List<Group> actual = groupDao.showAllGroups();
    Assertions.assertEquals(10, actual.size());
  }

}
