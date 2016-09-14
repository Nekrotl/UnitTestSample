package com.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.model.State;

@Service("stateService")
public class StateServiceImpl implements StateService {

	@Override
	public List<State> findAll() {
		//Actual Implementation
		return null;
	}

	@Override
	public State findById(int id) {
		//Actual Implementation
		return null;
	}


}
