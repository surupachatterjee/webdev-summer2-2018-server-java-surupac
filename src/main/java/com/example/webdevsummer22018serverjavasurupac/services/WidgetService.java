package com.example.webdevsummer22018serverjavasurupac.services;

import java.util.ArrayList;
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
import com.example.webdevsummer22018serverjavasurupac.models.Widget;
import com.example.webdevsummer22018serverjavasurupac.repositories.CourseRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.LessonRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.ModuleRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.TopicRepository;
import com.example.webdevsummer22018serverjavasurupac.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}

	@GetMapping("/api/widget/{wId}")
	public Widget findWidgetById(@PathVariable("wId") int widgetId) {
		return widgetRepository.findById(widgetId).get();
	}

	@GetMapping("/api/topic/{tId}/widget")
	public List<Widget> findAllWidgetsforTopic(@PathVariable("tId") int topicId) {
		Optional<Topic> top = topicRepository.findById(topicId);
		if (top.isPresent()) {
			Topic topic = top.get();
			return topic.getWidgets();
		}
		return null;
	}

	@PostMapping("/api/topic/{tId}/widget")
	public List<Widget> upsertWidget(
			@PathVariable("tId")int topicId,
			@RequestBody List<Widget> widgets){
		
		Optional<Topic> top = topicRepository.findById(topicId);
		if(top.isPresent()){
			Topic topic = top.get();
			List<Widget> savedWidgetList = new ArrayList<Widget>();			
			for(Widget w : widgets) {
				w.setTopic(topic);
				System.out.println("widget id:" + w.getId());
				if (w.getId() < 0)
					w.setId(0);
				System.out.println("widget id:" + w.getId());				
				savedWidgetList.add(widgetRepository.save(w));				
				}
			return savedWidgetList;
			}
		return null;		
	}

	@PutMapping("/api/widget/{wId}")
	public Widget updateWidget(@PathVariable("wId") int widgetId, @RequestBody Widget widget) {
		Optional<Widget> w = widgetRepository.findById(widgetId);
		if (w.isPresent()) {
			Widget wgt = w.get();
			widget.setId(wgt.getId());
			widgetRepository.save(widget);
			return widgetRepository.findById(widgetId).get();

		}
		return null;

	}

	@DeleteMapping("/api/widget/{wId}")
	public void deleteWidget(@PathVariable("wId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}

	@PostMapping("/api/widget")
	public List<Widget> saveWidgets(@RequestBody List<Widget> widgets) {
		return widgets;

	}

}
