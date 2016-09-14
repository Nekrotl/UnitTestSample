package com.sample.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.sample.service.StateService;
import com.sample.stub.StateServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class StateControllerTest {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(StateControllerTest.class);

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext ctx;

	@Mock
	private StateService stateService;

	@InjectMocks
	private StateController stateController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void verifyNullReferences() {
		assertNotNull(mockMvc);
	}

	@Test
	public void getStates() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getStates").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void validateStatesSizeList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getStates").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}

	@Test
	public void validateStateById() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/getStateById/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.name").value("Aguascalientes"))
		.andExpect(jsonPath("$.id").exists()).andDo(print());
	}
	
	@Configuration
	@EnableWebMvc
	public static class TestConfiguration {

		@Bean
		public StateController stateController() {
			return new StateController();
		}

		@Bean
		public StateService stateService() {
			return new StateServiceImpl();
		}

	}

}