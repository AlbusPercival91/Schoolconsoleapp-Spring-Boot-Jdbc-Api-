package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.mapper.GroupRowMapper;

@Repository
public class GroupDAO {

  private final JdbcTemplate jdbcTemplate;

  public GroupDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(int students) {
    String sql = """
        SELECT groups.group_id, groups.group_name, COUNT(students.student_id) AS number_of_students
        FROM school.groups LEFT JOIN school.students ON groups.group_id = students.group_id
        GROUP BY groups.group_id, groups.group_name HAVING COUNT(students.student_id) <= ?
        """;
    return jdbcTemplate.query(sql, new GroupRowMapper(), students);
  }

  public int createGroup(Group group) {
    String sql = "insert into school.groups(group_name) values(?)";
    return jdbcTemplate.update(sql, group.getGroupName());
  }

  public int editGroupName(String groupName, String newGroupName) {
    String sql = """
        UPDATE school.groups SET
        group_name = ?
        WHERE group_name = ?
                """;
    return jdbcTemplate.update(sql, newGroupName, groupName);
  }

  public int deleteGroupByName(String groupName) {
    String sql = "delete from school.groups where group_name = ?";
    return jdbcTemplate.update(sql, groupName);
  }

  public List<Group> showAllGroups() {
    String sql = "SELECT * FROM school.groups;";
    return jdbcTemplate.query(sql, new GroupRowMapper());
  }

}
