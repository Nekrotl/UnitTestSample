package com.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.model.State;

@Service("stateService")
public class StateServiceImpl implements StateService {

	@Override
	public List<State> findAll() {
		List<State> states = new ArrayList<State>();
		State state1 = new State(1, "Aguascalientes");
		states.add(state1);
		State state2 = new State(2, "Baja California");
		states.add(state2);
		State state3 = new State(3, "Baja California Sur");
		states.add(state3);
		return states;
	}

	@Override
	public State findById(int id) {
		State state = new State(1, "Aguascalientes");
		return state;
	}


}
