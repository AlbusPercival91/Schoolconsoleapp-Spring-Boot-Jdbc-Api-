package ua.foxminded.springbootjdbc.school.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.springbootjdbc.school.entity.Group;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SchoolDAOTest {

  @Autowired
  private TestDataService testData;

  @Autowired
  private SchoolService schoolService;

  @Autowired
  private Flyway flyway;

  @Container
  private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13").withDatabaseName("school")
      .withUsername("school_admin").withPassword("1234");

  @BeforeAll
  static void startContainers() {
    postgres.start();
  }

  @AfterAll
  static void stopContainers() {
    postgres.stop();
  }

  @BeforeEach
  void setUp() {
    flyway.migrate();
  }

  @AfterEach
  @Sql({ "/drop_all_tables.sql" })
  void tearDown() {

  }

  @Test
  void findGroupsWithLessOrEqualsStudents_CheckAllValues_ShouldMatchPattern() {
    testData.createGroup();
    testData.createStudent();
    Pattern pattern = Pattern.compile("[a-z]{2}-[0-9]{2}");
    List<Group> actual = schoolService.findGroupsWithLessOrEqualsStudents(30);
    int matchedPattern = (int) actual.stream().map(Group::toString).map(pattern::matcher).filter(Matcher::find).count();
    assertEquals(10, matchedPattern);
  }

}
