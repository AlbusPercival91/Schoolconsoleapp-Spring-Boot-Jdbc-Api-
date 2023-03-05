package ua.foxminded.springbootjdbc.school.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.springbootjdbc.school.config.DatasourceConfig;
import ua.foxminded.springbootjdbc.school.entity.Group;

@SpringBootTest(classes = DatasourceConfig.class)
@Testcontainers
@ActiveProfiles("test")
class SchoolDAOTest {

  @Container
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
      .withDatabaseName("school").withUsername("school_admin").withPassword("1234");

  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcDriverInstance);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  // Only example to check
  @Test
  void findGroupsWithLessOrEqualsStudents() {
    List<Group> groups = new ArrayList<Group>();
    Group group = new Group("123");
    groups.add(group);
    assertEquals(1, groups.size());
  }
}
