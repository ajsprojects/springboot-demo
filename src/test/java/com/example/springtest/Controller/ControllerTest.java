package com.example.springtest.Controller;

import com.example.springtest.Model.User;
import com.example.springtest.Services.UserService;
import com.example.springtest.TestData.CreateUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
		CreateUser createUser = new CreateUser();
		Mockito.when(userService.getUserById(anyInt())).thenReturn(java.util.Optional.ofNullable(createUser.createNewUser()));
		String jsonResponse = "{\"id\":14,\"age\":14,\"name\":\"Test\",\"email\":\"test@test.com\",\"postcode\":\"pe914f\"}";

		MockHttpServletResponse response = mockMvc.perform(
				get("/getUserById/14").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals(response.getContentAsString(), jsonResponse);
	}

	@Test
	public void getUserById_Failure() throws Exception {
		Mockito.when(userService.getUserById(anyInt())).thenReturn(Optional.empty());

		MockHttpServletResponse response = mockMvc.perform(
				get("/getUserById/14").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404))
				.andReturn().getResponse();

		assertEquals("", response.getContentAsString());
	}

	@Test
	public void getUserByEmail_Success() throws Exception {
		CreateUser createUser = new CreateUser();
		Mockito.when(userService.getUserByEmail(anyString())).thenReturn(java.util.Optional.ofNullable(createUser.createNewUser()));
		String jsonResponse = "{\"id\":14,\"age\":14,\"name\":\"Test\",\"email\":\"test@test.com\",\"postcode\":\"pe914f\"}";

		MockHttpServletResponse response = mockMvc.perform(
				get("/getUserByEmail/test@test").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals(jsonResponse, response.getContentAsString());
	}

	@Test
	public void getUserByEmail_Failure() throws Exception {

		Mockito.when(userService.getUserByEmail(anyString())).thenReturn(Optional.empty());

		MockHttpServletResponse response = mockMvc.perform(
				get("/getUserByEmail/test@test.com").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(404))
				.andReturn().getResponse();

		assertEquals("", response.getContentAsString());
	}

	@Test
	public void getAllUsers_Success() throws Exception {
		CreateUser createUser = new CreateUser();
		ArrayList<User> user = new ArrayList<>();
		user.add(createUser.createNewUser());
		user.add(createUser.createNewUser());
		Mockito.when(userService.getAllUsers()).thenReturn(user);
		
		String jsonResponse = "[{\"id\":14,\"age\":14,\"name\":\"Test\",\"email\":\"test@test.com\",\"postcode\":\"pe914f\"},{\"id\":14,\"age\":14,\"name\":\"Test\",\"email\":\"test@test.com\",\"postcode\":\"pe914f\"}]";
		MockHttpServletResponse response = mockMvc.perform(
				get("/getAllUsers").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals(jsonResponse, response.getContentAsString());
	}

	@Test
	public void getAllUsers_Empty() throws Exception {
		ArrayList<User> user = new ArrayList<>();
		Mockito.when(userService.getAllUsers()).thenReturn(user);

		MockHttpServletResponse response = mockMvc.perform(
				get("/getAllUsers").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andReturn().getResponse();

		assertEquals("[]", response.getContentAsString());
	}

	@Test
	public void deleteByUserId_Success() throws Exception {
		Mockito.when(userService.deleteUserById(anyInt())).thenReturn(HttpStatus.OK);

	  	mockMvc.perform(post("/deleteUserById/14"))
				.andExpect(status().is(200));
	}

	@Test
	public void deleteByUserId_Failure() throws Exception {
		Mockito.when(userService.deleteUserById(anyInt())).thenReturn(HttpStatus.NOT_FOUND);

		mockMvc.perform(post("/deleteUserById/14"))
				.andExpect(status().is(404));
	}

	@Test
	public void addUser_Success() throws Exception {
		Mockito.when(userService.addUser(anyString())).thenReturn(HttpStatus.OK);
		String body = "{\n" +
				"\"age\": 30,\n" +
				"\"email\": \"polly@polly.com\",\n" +
				"\"name\": \"polly\",\n" +
				"\"postcode\": \"nr193dw\"\n" +
				"}";

		mockMvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().is(200));
	}

	@Test
	public void addUser_Failure() throws Exception {
		Mockito.when(userService.addUser(anyString())).thenReturn(HttpStatus.BAD_REQUEST);
		String body = "{\n" +
				"\"werwerw\": 30,\n" +
				"\"emrwerwereail\": \"polly@polly.com\",\n" +
				"\"rewrwe\": \"nr193dw\"\n" +
				"}";

		mockMvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().is(400));
	}

}
