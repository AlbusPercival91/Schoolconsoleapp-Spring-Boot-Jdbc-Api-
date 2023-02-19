package ua.foxminded.springbootjdbc.school.movie;

import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

@Service
public class SchoolDummyDataService {
  StudentMaker studentMaker = new StudentMaker();
  CourseMaker courseMaker = new CourseMaker();
  GroupMaker groupMaker = new GroupMaker();

  
  public void createStudent(JdbcTemplate jdbcTemplate) {
    int i = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(i++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      groupMaker.assignGroupId().get(i++);
      DummyDataDAO dummyData = new DummyDataDAO(jdbcTemplate);
      dummyData.createStudent(student);
    }
  }

  public void createGroup(JdbcTemplate jdbcTemplate) {
    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      DummyDataDAO dummyData = new DummyDataDAO(jdbcTemplate);
      dummyData.createGroup(group);
    }
  }

  public void createCourse(JdbcTemplate jdbcTemplate) {
    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      DummyDataDAO dummyData = new DummyDataDAO(jdbcTemplate);
      dummyData.createCourse(course);
    }

  }

  public void createCourseStudentRelation(JdbcTemplate jdbcTemplate) {
    for (Map.Entry<Integer, Set<Integer>> entry : courseMaker.assignCourseId().entrySet()) {
      Integer key = entry.getKey();
      Set<Integer> value = entry.getValue();

      for (Integer i : value) {
        StudentCourseRelation scRelation = new StudentCourseRelation(key, i);
        DummyDataDAO dummyData = new DummyDataDAO(jdbcTemplate);
        dummyData.createCourseStudentRelation(scRelation);
      }
    }
  }
  
}
