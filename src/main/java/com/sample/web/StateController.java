package com.sample.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.State;
import com.sample.service.StateService;

@RestController
public class StateController {
	
	@Autowired
	private StateService stateService;
    
    @RequestMapping(value = "/getStates", method = RequestMethod.GET)
	@ResponseBody
	public List<State> getStates() {
		return stateService.findAll();
	}
    
    @RequestMapping(value = "/getStateById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public State getStateById(@PathVariable("id") int id) {
		return stateService.findById(id);
	}
    
}