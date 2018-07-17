package com.example.webdevsummer22018serverjavasurupac.services;

import java.util.List;
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
import com.example.webdevsummer22018serverjavasurupac.models.Module;
import com.example.webdevsummer22018serverjavasurupac.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/api/module")
	public Iterable<Module> findAllModules() {
		return moduleRepository.findAll();
	}

	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
			@RequestBody Module module, 
			@PathVariable("courseId") int courseId) {
		Optional<Course> courseData = courseRepository.findById(courseId);
		if(courseData.isPresent()) {
			Course course = courseData.get();
			module.setCourse(course);
			return moduleRepository.save(module);
		}
		return null;
		
	}
	
	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(
			@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}
	
	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") int moduleId)
	{
		moduleRepository.deleteById(moduleId);
	}

	
	@GetMapping("/api/module/{moduleId}")
	public Module findModuleById(@PathVariable("moduleId")int moduleId)
	{
		return moduleRepository.findById(moduleId).get();	
	}
	
	@PutMapping("/api/module/{moduleId}")
	public Module updateModule(
			@RequestBody Module module,
			@PathVariable("moduleId") int moduleId) {
		
		Optional<Module> moduleData = moduleRepository.findById(moduleId);
		if(moduleData.isPresent()) {
			Module mod = moduleData.get();
			mod.setTitle(module.getTitle());
			moduleRepository.save(mod);
			return moduleRepository.findById(moduleId).get();
		}
		return null;
	}
}
