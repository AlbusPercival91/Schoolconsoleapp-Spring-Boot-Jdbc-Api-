package ua.foxminded.springbootjdbc.school.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.springbootjdbc.school.entity.Group;

@Testcontainers
@SpringBootTest
class SchoolDAOTest {

//  @Test
//  void findGroupsWithLessOrEqualsStudents() {
//    dataService.createGroup();
//    dataService.createStudent();
//    List<Group> groups = schoolService.findGroupsWithLessOrEqualsStudents(30);
//    assertEquals(10, groups.size());
//  }
}
