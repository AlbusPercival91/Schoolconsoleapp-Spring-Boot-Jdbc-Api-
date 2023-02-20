package ua.foxminded.springbootjdbc.school.testdatadao;

import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

@Service
public class TestDataService {
  StudentMaker studentMaker = new StudentMaker();
  CourseMaker courseMaker = new CourseMaker();
  GroupMaker groupMaker = new GroupMaker();

  @Autowired
  public void createStudent(TestDataRepository dataRepository) {
    int i = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(i++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      dataRepository.createStudent(student);
    }
  }

  @Autowired
  public void createGroup(TestDataRepository dataRepository) {
    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      dataRepository.createGroup(group);
    }
  }

  @Autowired
  public void createCourse(TestDataRepository dataRepository) {
    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      dataRepository.createCourse(course);
    }
  }

  @Autowired
  public void createCourseStudentRelation(TestDataRepository dataRepository) {
    for (Map.Entry<Integer, Set<Integer>> entry : courseMaker.assignCourseId().entrySet()) {
      Integer key = entry.getKey();
      Set<Integer> value = entry.getValue();

      for (Integer i : value) {
        StudentCourseRelation scRelation = new StudentCourseRelation(key, i);
        dataRepository.createCourseStudentRelation(scRelation);
      }
    }
  }

}
