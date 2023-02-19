package ua.foxminded.springbootjdbc.school.dao;

import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import ua.foxminded.springbootjdbc.school.entity.Course;
import ua.foxminded.springbootjdbc.school.entity.Group;
import ua.foxminded.springbootjdbc.school.entity.Student;
import ua.foxminded.springbootjdbc.school.entity.StudentCourseRelation;
import ua.foxminded.springbootjdbc.school.interfaces.SchoolRepository;
import ua.foxminded.springbootjdbc.school.testdata.CourseMaker;
import ua.foxminded.springbootjdbc.school.testdata.GroupMaker;
import ua.foxminded.springbootjdbc.school.testdata.StudentMaker;

@Service
public class SchoolDataService {

  private final SchoolRepository schoolRepository;

  StudentMaker studentMaker = new StudentMaker();
  CourseMaker courseMaker = new CourseMaker();
  GroupMaker groupMaker = new GroupMaker();

  public SchoolDataService(SchoolRepository schoolRepository) {
    this.schoolRepository = schoolRepository;
  }

  public void createStudent() {
    int i = 0;

    for (String s : studentMaker.generateStudents(studentMaker.generateNames(20), studentMaker.generateSurnames(20))) {
      Student student = new Student(groupMaker.assignGroupId().get(i++), s.substring(0, s.indexOf(" ")),
          s.substring(s.indexOf(" ")));
      groupMaker.assignGroupId().get(i++);
      schoolRepository.createStudent(student);
    }
  }

  public void createGroup() {
    for (String s : groupMaker.generateGroups()) {
      Group group = new Group(s);
      schoolRepository.createGroup(group);
    }
  }

  public void createCourse() {
    for (String s : courseMaker.generateCourses()) {
      Course course = new Course(s, "TBD");
      schoolRepository.createCourse(course);
    }
  }

  public void createCourseStudentRelation() {
    for (Map.Entry<Integer, Set<Integer>> entry : courseMaker.assignCourseId().entrySet()) {
      Integer key = entry.getKey();
      Set<Integer> value = entry.getValue();

      for (Integer i : value) {
        StudentCourseRelation scRelation = new StudentCourseRelation(key, i);
        schoolRepository.createCourseStudentRelation(scRelation);
      }
    }
  }

}
