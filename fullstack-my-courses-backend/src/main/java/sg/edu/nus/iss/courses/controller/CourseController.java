package sg.edu.nus.iss.courses.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.courses.model.Course;
import sg.edu.nus.iss.courses.repository.CourseRepository;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class CourseController {
  @Autowired
  CourseRepository myCourseRepository;

  @GetMapping("/courses")
  public List<Course> getCourses() {
    return myCourseRepository.findAll();
  }

  @PostMapping("/courses")
  public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    try {
      Course newCourse = myCourseRepository
          .save(new Course(course.getCode(), course.getName(), course.getDescription()));
      return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
  }

  @GetMapping("/courses/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable("id") Integer id) {
    int i = id;
    Optional<Course> sData = myCourseRepository.findById(i);
    if (sData.isPresent()) {
      return new ResponseEntity<>(sData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/courses/edit/{id}")
  public ResponseEntity<Course> editCourse(@PathVariable("id") int id, @RequestBody Course course) {
    Optional<Course> sData = myCourseRepository.findById(id);
    if (sData.isPresent()) {
      Course _course = sData.get();
      _course.setCode(course.getCode());
      _course.setName(course.getName());
      _course.setDescription(course.getDescription());
      return new ResponseEntity<>(myCourseRepository.save(_course), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/courses/{id}")
  public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("id") int id) {
    try {
      myCourseRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }

  @DeleteMapping("/courses")
  public ResponseEntity<HttpStatus> deleteAllCourses() {
    try {
      myCourseRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

  }
}
