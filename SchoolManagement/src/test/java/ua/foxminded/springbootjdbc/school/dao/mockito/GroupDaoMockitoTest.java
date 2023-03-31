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
import ua.foxminded.springbootjdbc.school.dao.GroupDAO;
import ua.foxminded.springbootjdbc.school.dao.GroupService;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class GroupDaoMockitoTest {

  @Autowired
  private GroupService groupService;

  @MockBean
  private GroupDAO groupDAO;

  @MockBean
  private ConsoleMenuManager consoleMenuRunner;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @ParameterizedTest
  @DisplayName("Should return 10 if matched groups less than or equal to students")
  @CsvSource({ "30", "100" })
  void testFindGroupsWithLessOrEqualsStudents(int number) {
    List<Group> expected = new ArrayList<>();
    when(groupDAO.findGroupsWithLessOrEqualsStudents(number)).thenReturn(expected);
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(number);

    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Should return an empty list when the maximum number of students is 0")
  void testFindGroupsWithLessOrEqualsStudents_WhenStudentsZero() {
    List<Group> expected = new ArrayList<>();
    when(groupDAO.findGroupsWithLessOrEqualsStudents(0)).thenReturn(expected);
    List<Group> actual = groupService.findGroupsWithLessOrEqualsStudents(0);

    Assertions.assertTrue(actual.isEmpty());
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group created")
  @CsvSource({ "aa-23", "!@-@$", "cc-45", "45-df", "@#-45" })
  void testCreateGroup(String groupName) {
    Integer expectedCount = 1;
    Group group = new Group(groupName);
    when(groupDAO.createGroup(any(Group.class))).thenReturn(expectedCount);
    Integer actualCount = groupService.createGroup(group);

    Assertions.assertNotNull(group.getGroupName());
    Assertions.assertEquals(expectedCount, actualCount);
    verify(groupDAO).createGroup(any(Group.class));
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group updated")
  @CsvSource({ "aa-34, aa-35", "aa-35, 35-aa", "test, test-test", "123, 321", "aa-aa, bb-bb", "00-00, 11-11",
      "!@-@$, )&-%^" })
  void testEditGroupName(String groupName, String newGroupName) {
    Integer expectedCount = 1;
    when(groupDAO.editGroupName(groupName, newGroupName)).thenReturn(expectedCount);
    Integer actualCount = groupService.editGroupName(groupName, newGroupName);

    Assertions.assertEquals(expectedCount, actualCount);
    verify(groupDAO).editGroupName(groupName, newGroupName);
  }

  @ParameterizedTest
  @DisplayName("Should return 1 if 1 group deleted")
  @CsvSource({ "aa-34", "35-aa", "test", "123", "aa-aa", "00-00", "!@-@$" })
  void testDeleteGroupByName(String groupName) {
    Integer expectedCount = 1;
    when(groupDAO.deleteGroupByName(groupName)).thenReturn(expectedCount);
    Integer actualCount = groupService.deleteGroupByName(groupName);

    Assertions.assertEquals(expectedCount, actualCount);
    verify(groupDAO).deleteGroupByName(groupName);
  }

  @Test
  @DisplayName("Should be equals expected and actual group lists")
  void testShowAllGroups() {
    List<Group> expected = new ArrayList<>();
    when(groupDAO.showAllGroups()).thenReturn(expected);
    List<Group> actual = groupService.showAllGroups();

    Assertions.assertEquals(expected, actual);
    verify(groupDAO).showAllGroups();
  }
}
