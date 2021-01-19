package com.example.springtest.controller;

import com.example.springtest.model.User;
import com.example.springtest.services.UserService;
import com.example.springtest.testdata.TestData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private Controller controller;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void contextLoads() {
		assertThat(mockMvc).isNotNull();
		assertThat(controller).isNotNull();
	}

	@Test
	public void getUserById_Success() throws Exception {
		Mockito.when(userService.getUserById(anyInt())).thenReturn(Optional.of(TestData.createMockUser()));
		String jsonResponse = "{\"id\":1,\"age\":18,\"name\":\"test\",\"email\":\"test@test.com\",\"postcode\":\"le1084j\"}";

		MockHttpServletResponse response = mockMvc.perform(
				get("/users/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals(response.getContentAsString(), jsonResponse);
	}

	@Test
	public void getUserById_Empty() throws Exception {
		Mockito.when(userService.getUserById(anyInt())).thenReturn(Optional.empty());

		MockHttpServletResponse response = mockMvc.perform(
				get("/users/11").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals("null", response.getContentAsString());
	}

	@Test
	public void getUserByEmail_Success() throws Exception {
		Mockito.when(userService.getUserByEmail(anyString())).thenReturn(Optional.of(TestData.createMockUser()));
		String jsonResponse = "{\"id\":1,\"age\":18,\"name\":\"test\",\"email\":\"test@test.com\",\"postcode\":\"le1084j\"}";

		MockHttpServletResponse response = mockMvc.perform(
				get("/users/email/test@test").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals(jsonResponse, response.getContentAsString());
	}

	@Test
	public void getUserByEmail_Empty() throws Exception {
		Mockito.when(userService.getUserByEmail(anyString())).thenReturn(Optional.empty());

		MockHttpServletResponse response = mockMvc.perform(
				get("/users/email/test@test").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals("null", response.getContentAsString());
	}

	@Test
	public void getAllUsers_Success() throws Exception {
		ArrayList<User> user = new ArrayList<>();
		user.add(TestData.createMockUser());
		user.add(TestData.createMockUser());
		Mockito.when(userService.getAllUsers()).thenReturn(user);
		
		String jsonResponse = "[{\"id\":1,\"age\":18,\"name\":\"test\",\"email\":\"test@test.com\",\"postcode\":\"le1084j\"},{\"id\":1,\"age\":18,\"name\":\"test\",\"email\":\"test@test.com\",\"postcode\":\"le1084j\"}]";
		MockHttpServletResponse response = mockMvc.perform(
				get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals(jsonResponse, response.getContentAsString());
	}

	@Test
	public void getAllUsers_Empty() throws Exception {
		ArrayList<User> user = new ArrayList<>();
		Mockito.when(userService.getAllUsers()).thenReturn(user);

		MockHttpServletResponse response = mockMvc.perform(
				get("/users").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals("[]", response.getContentAsString());
	}

	@Test
	public void deleteByUserId_Success() throws Exception {
		Mockito.when(userService.deleteUserById(anyInt())).thenReturn(true);

	  	mockMvc.perform(delete("/users/1"))
				.andExpect(status().is(200));
	}

	@Test
	public void deleteByUserId_Failure() throws Exception {
		Mockito.when(userService.deleteUserById(anyInt())).thenReturn(false);

		mockMvc.perform(delete("/users/11"))
				.andExpect(status().is(400));
	}

	@Test
	public void addUser_Success() throws Exception {
		Mockito.when(userService.addUser(anyString())).thenReturn(true);
		String body = "{\n" +
				"\"age\": 30,\n" +
				"\"email\": \"polly@polly.com\",\n" +
				"\"name\": \"polly\",\n" +
				"\"postcode\": \"nr193dw\"\n" +
				"}";

		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().is(200));
	}

	@Test
	public void addUser_Failure() throws Exception {
		Mockito.when(userService.addUser(anyString())).thenReturn(false);
		String body = "{\n" +
				"\"werwerw\": 30,\n" +
				"\"emrwerwereail\": \"polly@polly.com\",\n" +
				"\"rewrwe\": \"nr193dw\"\n" +
				"}";

		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().is(400));
	}

}
