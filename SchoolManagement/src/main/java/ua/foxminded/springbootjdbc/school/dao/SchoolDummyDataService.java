package ua.foxminded.springbootjdbc.school.dao;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.interfaces.DummyDataDao;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

@Service
public class SchoolDummyDataService {

  @Autowired
  private DummyDataDao data;

  StudentMaker studentMaker = new StudentMaker();
  CourseMaker courseMaker = new CourseMaker();
  GroupMaker groupMaker = new GroupMaker();

  public void createStudent() {
    int i = 0;
    int result = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(i++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      groupMaker.assignGroupId().get(i++);
      result = data.createStudent(student);
    }

//    if (result != studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))
//        .size()) {
//      throw new IllegalStateException("oops something went wrong");
//    }
  }

  public void createGroup() {
    int result = 0;

    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      result = data.createGroup(group);
    }

//    if (result != groupMaker.generateGroups().size()) {
//      throw new IllegalStateException("oops something went wrong");
//    }
  }

  public void createCourse() {
    int result = 0;

    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      result = data.createCourse(course);
    }

//    if (result != courseMaker.generateCourses().size()) {
//      throw new IllegalStateException("oops something went wrong");
//    }
  }

  public void createCourseStudentRelation() {
    int result = 0;

    for (Map.Entry<Integer, Set<Integer>> entry : courseMaker.assignCourseId().entrySet()) {
      Integer key = entry.getKey();
      Set<Integer> value = entry.getValue();

      for (Integer i : value) {
        StudentCourseRelation scRelation = new StudentCourseRelation(key, i);
        result = data.createCourseStudentRelation(scRelation);
      }

//      if (result != value.size()) {
//        throw new IllegalStateException("oops something went wrong");
//      }
    }
  }

}
