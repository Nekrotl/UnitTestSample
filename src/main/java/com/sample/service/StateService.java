package com.sample.service;

import java.util.List;

import com.sample.model.State;

public interface StateService {
    List<State> findAll();
    State findById(int id);

}
