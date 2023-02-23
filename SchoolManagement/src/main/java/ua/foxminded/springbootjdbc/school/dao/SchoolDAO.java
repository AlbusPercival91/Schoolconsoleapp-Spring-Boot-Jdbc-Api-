package ua.foxminded.springbootjdbc.school.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.mapper.GroupRowMapper;

@Repository
public class SchoolDAO {

  private final JdbcTemplate jdbcTemplate;

  public SchoolDAO(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<String> findGroupIDWithLessOrEqualsStudents(int number) {
    String sql = "SELECT group_id, COUNT (*) FROM school.students GROUP BY group_id HAVING COUNT(*)<=" + number + ";";
    return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("group_id"));
  }

  public List<Group> findGroupsWithLessOrEqualsStudents(String s) {
    String sql = "SELECT * FROM school.groups WHERE group_id = " + s + ";";
    return jdbcTemplate.query(sql, new GroupRowMapper());
  }

}
