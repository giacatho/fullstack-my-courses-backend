package sg.edu.nus.iss.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.iss.courses.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
