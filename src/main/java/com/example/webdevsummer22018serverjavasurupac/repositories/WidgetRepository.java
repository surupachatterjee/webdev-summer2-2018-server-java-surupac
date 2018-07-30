package com.example.webdevsummer22018serverjavasurupac.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdevsummer22018serverjavasurupac.models.User;
import com.example.webdevsummer22018serverjavasurupac.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget,Integer> {
	 
}
