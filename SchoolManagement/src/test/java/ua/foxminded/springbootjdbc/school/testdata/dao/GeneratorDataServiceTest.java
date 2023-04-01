package ua.foxminded.springbootjdbc.school.testdata.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ua.foxminded.springbootjdbc.school.facade.ConsoleMenuManager;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class GeneratorDataServiceTest {

  @Autowired
  private GeneratedDataService testDataService;

  @MockBean
  private GeneratorDataRepository repository;

  @MockBean
  private ConsoleMenuManager consoleMenuRunner;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("Should randomly generated 200 students")
  void shouldCreateStudent() {
// hello
  }

  @Test
  @DisplayName("Should randomly generated 10 groups")
  void createGroup() {

  }

  @Test
  @DisplayName("Should randomly generated 10 courses")
  void createCourse() {

  }

  @Test
  @DisplayName("Should randomly assigne 200 students to 10 courses")
  void createCourseStudentRelation() {

  }

  @Test
  @DisplayName("Should return all rows in database")
  void rowsCount() {

  }
}
