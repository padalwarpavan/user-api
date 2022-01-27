package com.neosoft.userapi.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neosoft.userapi.models.UserDetails;
import com.neosoft.userapi.request.UserRequest;
import com.neosoft.userapi.response.MessageResponse;
import com.neosoft.userapi.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
   
	@Autowired
	UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<UserDetails>> getAllusers() {
		List<UserDetails> footballers = userService.getAllUser();
		return new ResponseEntity<>(footballers, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<UserDetails> getUserById(@PathVariable("id") Integer id) {
		UserDetails user = userService.getASingleUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

//	@PostMapping("/add")
	@PostMapping(value = { "/add" }, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<MessageResponse> addUser(@RequestPart("userRequest") String userJson,
			@RequestPart("file") MultipartFile file) {

		UserRequest userRequest = mapJsonToUser(userJson);
		MessageResponse newUser = userService.createUser(userRequest, file);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	private UserRequest mapJsonToUser(String userJson) {
	// TODO Auto-generated method stub
		UserRequest userRequest = new UserRequest();
		ObjectMapper mapper = new ObjectMapper();
		try {
			userRequest = mapper.readValue(userJson, UserRequest.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return userRequest;
}

	@PostMapping("/add1")
//	@PostMapping(value = { "/add1" }, consumes = { "multipart/form-data" })
	public @ResponseBody ResponseEntity<MessageResponse> addUser1(@RequestBody UserRequest user) {
//		MessageResponse newUser = userService.createUser(user,file);
//		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		System.out.println("");
		return null;
		}


	@PutMapping("/update/{id}")
	public Optional<UserDetails> updateUser(@PathVariable Integer id, @RequestBody UserRequest user) {
		return userService.updateUser(id, user);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
