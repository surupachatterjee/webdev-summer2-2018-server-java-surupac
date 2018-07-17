package com.example.webdevsummer22018serverjavasurupac.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer22018serverjavasurupac.models.Course;
import com.example.webdevsummer22018serverjavasurupac.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	
	@Autowired
	CourseRepository courseRepository;
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses(){
		return courseRepository.findAll();
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course)
	{
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId")int id)
	{
		courseRepository.deleteById(id);
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId")int id) 
	{
		return courseRepository.findById(id).get();
		
	}
	
	@PutMapping("/api/course/{courseId}")
	public Course updateCourse(@PathVariable("courseId")int id,@RequestBody Course course)
	{
		Optional<Course> courseData = courseRepository.findById(id);
		if(courseData.isPresent())
		{
			Course crs = courseData.get();
			crs.setTitle(course.getTitle());
			//crs.setCreated(course.getCreated());
			crs.setModified(course.getModified());
			courseRepository.save(crs);
			return courseRepository.findById(id).get();
		}
		return null;
		
	}
	
}
