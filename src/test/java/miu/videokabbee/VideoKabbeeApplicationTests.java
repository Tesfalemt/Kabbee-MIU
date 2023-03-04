package miu.videokabbee;

import miu.videokabbee.ExceptionHandling.ExceptionHandling;
import miu.videokabbee.controller.UserController;
import miu.videokabbee.domain.Contact;
import miu.videokabbee.domain.User;
import miu.videokabbee.service.UserServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoKabbeeApplicationTests {

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;

	@Mock
	PasswordEncoder passwordEncoder;

	@Test
	void getUserByID_returnsValidUser() {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Test User");
		user.setContact(new Contact());
		user.getContact().setEmail("testuser@test.com");

		when(userService.findById(1L)).thenReturn(user);

		ResponseEntity<?> response = userController.getUserByID(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void getUserByID_returnsNotFoundForInvalidUser() {
		when(userService.findById(1000L)).thenReturn(null);

		ResponseEntity<?> response = userController.getUserByID(1000L);

		assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
		assertThat(response.getBody(), is(new ExceptionHandling("not available")));
	}

	@Test
	void registerUser_registersValidUser() {
		User user = new User();
		user.setFirstName("Test User");
		user.setContact(new Contact()); // Initialize the Contact object
		user.getContact().setEmail("testuser@test.com");
		user.setPassword("testpassword");

		String encodedPassword = "encodedPassword";
		User registeredUser = new User();
		registeredUser.setId(1L);
		registeredUser.setFirstName(user.getFirstName());
		registeredUser.setContact(new Contact()); // Initialize the Contact object
		registeredUser.getContact().setEmail(user.getContact().getEmail());
		registeredUser.setPassword(encodedPassword);

		when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
		when(userService.register(any(User.class))).thenReturn(registeredUser);

		ResponseEntity<?> response = userController.registerUser(user);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(registeredUser, response.getBody());
	}

	@Test
	void registerUser_returnsNotFoundForInvalidRegistration() {
		User user = new User();
		user.setFirstName("Test User");
		user.setContact(new Contact());
		user.getContact().setEmail("testuser@test.com");
		user.setPassword("testpassword");

		when(userService.register(any(User.class))).thenReturn(null);

		ResponseEntity<?> response = userController.registerUser(user);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(new ExceptionHandling("not Registered"), response.getBody());
	}

}

