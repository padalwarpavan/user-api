package com.neosoft.userapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.userapi.models.UserDetails;
import com.neosoft.userapi.request.UserRequest;
import com.neosoft.userapi.response.MessageResponse;

@Component
public interface UserService {
	
	MessageResponse createUser(UserRequest userRequest, MultipartFile file);

	Optional<UserDetails> updateUser(Integer userId, UserRequest userRequest);

	void deleteUser(Integer userId);

	UserDetails getASingleUser(Integer userId);

	List<UserDetails> getAllUser();
}
