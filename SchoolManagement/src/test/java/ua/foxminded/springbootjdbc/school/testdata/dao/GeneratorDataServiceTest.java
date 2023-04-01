package ua.foxminded.springbootjdbc.school.testdata.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class GeneratorDataServiceTest {

  @Autowired
  private GeneratedDataService service;

  @Autowired
  private GroupMaker groupMaker;

  @Autowired
  private StudentMaker studentMaker;

  @Autowired
  private CourseMaker courseMaker;

  @MockBean
  private GeneratorDataRepository repository;

  @MockBean
  private ConsoleMenuManager consoleMenuRunner;

  @Test
  @DisplayName("Should randomly generated 200 students")
  void shouldCreateStudent() {
    service = mock(GeneratedDataService.class);
    service.createStudent();
    verify(service).createStudent();
    int count = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(count++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      when(repository.createStudent(student)).thenReturn(count);
    }
    Assertions.assertEquals(200, count);
  }

  @Test
  @DisplayName("Should randomly generated 10 groups")
  void shouldCreateGroup() {
    service = mock(GeneratedDataService.class);
    service.createGroup();
    verify(service).createGroup();
    int count = 0;

    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      when(repository.createGroup(group)).thenReturn(count++);
    }
    Assertions.assertEquals(10, count);
  }

  @Test
  @DisplayName("Should randomly generated 10 courses")
  void shouldCreateCourse() {
    service = mock(GeneratedDataService.class);
    service.createCourse();
    verify(service).createCourse();
    int count = 0;

    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      when(repository.createCourse(course)).thenReturn(count++);
    }
    Assertions.assertEquals(10, count);
  }

  @Test
  @DisplayName("Should randomly assigne 200 students to 10 courses")
  void shouldCreateCourseStudentRelation() {
    service = mock(GeneratedDataService.class);
    service.createCourseStudentRelation();
    verify(service).createCourseStudentRelation();

    for (Map.Entry<Integer, Set<Integer>> entry : courseMaker.assignCourseId().entrySet()) {
      Integer key = entry.getKey();
      Set<Integer> value = entry.getValue();

      for (Integer i : value) {
        StudentCourseRelation scRelation = new StudentCourseRelation(key, i);
        when(repository.createCourseStudentRelation(scRelation)).thenReturn(value.size());
      }
      Assertions.assertTrue(value.size() <= 3 && value.size() >= 1);
    }
  }

  @Test
  @DisplayName("Should return all rows in database")
  void shouldGiveRowsCount() {
    service = mock(GeneratedDataService.class);
    service.databaseIsEmpty();
    verify(service).databaseIsEmpty();
    int expectedRows = 10;
    when(repository.rowsCount()).thenReturn(expectedRows);
    int actualRows = repository.rowsCount();
    Assertions.assertEquals(expectedRows, actualRows);
  }
}
