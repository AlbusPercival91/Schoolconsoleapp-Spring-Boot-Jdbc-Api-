package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.foxminded.springbootjdbc.school.entity.Group;

@Service
public class GroupService {
  
  private final GroupDAO groupDao;

  public GroupService(GroupDAO groupDao) {
    this.groupDao = groupDao;
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(int students) {
    return groupDao.findGroupsWithLessOrEqualsStudents(students);
  }

}
