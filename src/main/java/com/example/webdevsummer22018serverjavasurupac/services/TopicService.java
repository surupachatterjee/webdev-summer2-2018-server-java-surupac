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
import com.example.webdevsummer22018serverjavasurupac.models.Lesson;
import com.example.webdevsummer22018serverjavasurupac.models.Module;
import com.example.webdevsummer22018serverjavasurupac.models.Topic;
import com.example.webdevsummer22018serverjavasurupac.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.ModuleRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.TopicRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TopicService {
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@GetMapping("/api/topic")
	public Iterable<Topic> findAllTopics(){
		return topicRepository.findAll();	
	}
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public Topic createTopic(
			@RequestBody Topic topic,
			@PathVariable("cid") int cid,
			@PathVariable("mid") int mid,
			@PathVariable("lid") int lid)
	{	
		Optional<Course> courseData = courseRepository.findById(cid);
		if(courseData.isPresent())
		{
			Optional<Module> moduleData = moduleRepository.findById(mid);
			if(moduleData.isPresent())
			{
				Optional<Lesson> lessonData = lessonRepository.findById(lid);
				if(lessonData.isPresent())
				{
					Lesson l = lessonData.get();
					topic.setLesson(l);
					return topicRepository.save(topic);
				}
				
			}
		}
		return null;
		
	}
	
	
	@DeleteMapping("/api/topic/{id}")
	public void deleteTopic(@PathVariable("id")int id)
	{
		topicRepository.deleteById(id);
	}

	
	@GetMapping("/api/topic/{id}")
	public Topic findTopicById(@PathVariable("id")int id) {
		return topicRepository.findById(id).get();
		
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson/{lid}/topic")
	public List<Topic> findAllTopicForLesson(
			@PathVariable("cid") int cid,
			@PathVariable("mid") int mid,
			@PathVariable("lid") int lid)
	{
		Optional<Course> courseData = courseRepository.findById(cid);
		if(courseData.isPresent())
		{
			Optional<Module> moduleData = moduleRepository.findById(mid);
			if(moduleData.isPresent())
			{
				Optional<Lesson> lessonData = lessonRepository.findById(lid);
				if(lessonData.isPresent())
				{
					Lesson l = lessonData.get();
					return l.getTopic();
				}
			}
		}
		return null;	
	}
	
	@PutMapping("/api/topic/{id}")
	public Topic updateTopic(
			@PathVariable("id")int id,
			@RequestBody Topic topic)
	{
		Optional<Topic> tpc = topicRepository.findById(id);
		if (tpc.isPresent())
		{
			Topic t = tpc.get();
			t.setTitle(topic.getTitle());
			topicRepository.save(t);
			return topicRepository.findById(id).get();
		}
		return null;
		
	}
	
	
}
