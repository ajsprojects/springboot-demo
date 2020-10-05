package com.example.springtest;

import com.example.springtest.Controller.Controller;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new Controller()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void getUserById() throws Exception {

		/*MockHttpServletResponse response = mockMvc.perform(
				get("/hello").accept(MediaType.ALL))
				.andExpect(status().isOk())
				.andReturn().getResponse();

		System.out.println(response.getContentAsString());
		assertEquals(response.getContentAsString(), "Hello everyone!");*/
	}



}
