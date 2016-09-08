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
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sample.service.StateService;
import com.sample.stub.StateServiceImpl;

@Configuration
@ComponentScan(basePackages = {"com.sample.stub"})
class TestApplicationContext {
	@Bean
    public StateService stateleService() {
        return new StateServiceImpl(); 
    }
}


//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestApplicationContext.class})
public class StateControllerTest {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(StateControllerTest.class);

	@Mock
	private StateService stateService;

	@InjectMocks
	private StateController stateController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(stateController).build();
		logger.info("########## " + stateService.findAll());

	}

	@Test
	public void verifyNullReferences() {
		assertNotNull(mockMvc);
	}

	@Test
	public void getStates() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders
				.get("/getStates")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}	
	
	 @Test
	 public void validateStatesSizeList() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders
		 .get("/getStates")
		 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(jsonPath("$", hasSize(3)))
		 .andDo(print());
	 }

}