package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.foxminded.springbootjdbc.school.entity.Group;

@Service
public class SchoolService {

  private final SchoolDAO schoolDao;

  public SchoolService(SchoolDAO schoolDao) {
    this.schoolDao = schoolDao;
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(int students) {
    return schoolDao.findGroupsWithLessOrEqualsStudents(students);
  }

}
