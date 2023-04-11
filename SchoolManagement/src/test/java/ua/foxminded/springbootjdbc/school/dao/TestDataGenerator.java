package ua.foxminded.springbootjdbc.school.dao;

import java.util.Map;
import java.util.Set;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;
import ua.foxminded.springbootjdbc.school.testdata.dao.GeneratorDataRepository;

public class TestDataGenerator {

  public void createStudent(GeneratorDataRepository dataRepository) {
    StudentMaker studentMaker = new StudentMaker();
    GroupMaker groupMaker = new GroupMaker();
    int i = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(i++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      dataRepository.createStudent(student);
    }
  }

  public void createGroup(GeneratorDataRepository dataRepository) {
    GroupMaker groupMaker = new GroupMaker();

    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      dataRepository.createGroup(group);
    }
  }

  public void createCourse(GeneratorDataRepository dataRepository) {
    CourseMaker courseMaker = new CourseMaker();

    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      dataRepository.createCourse(course);
    }
  }

  public void createCourseStudentRelation(GeneratorDataRepository dataRepository) {
    CourseMaker courseMaker = new CourseMaker();

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
