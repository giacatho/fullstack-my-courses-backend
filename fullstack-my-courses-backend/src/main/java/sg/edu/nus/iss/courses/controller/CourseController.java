package sg.edu.nus.iss.courses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	    Course newCourse = myCourseRepository.save(
	        new Course(course.getCode(), course.getName(), course.getDescription())
      );
	    return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	  }
	}
	
	
}
