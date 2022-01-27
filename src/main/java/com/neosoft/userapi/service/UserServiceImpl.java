package com.neosoft.userapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.userapi.exception.FileStorageException;
import com.neosoft.userapi.exception.ResourceNotFoundException;
import com.neosoft.userapi.models.UserDetails;
import com.neosoft.userapi.repository.UserRepository;
import com.neosoft.userapi.request.UserRequest;
import com.neosoft.userapi.response.MessageResponse;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public MessageResponse createUser(UserRequest userRequest, MultipartFile file) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    if (fileName.contains("..")) {
		    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		}
		UserDetails newUser = new UserDetails();
		newUser.setFirstName(userRequest.getFirstName());
		newUser.setLastName(userRequest.getLastName());
		newUser.setPhoneNumber(userRequest.getPhoneNumber());
		newUser.setEmail(userRequest.getEmail());
		newUser.setAddress(userRequest.getUserAddress());
		newUser.setUserImage(userRequest.getUserImage());
		userRepository.save(newUser);
		return new MessageResponse("New User created successfully");
    }

	@Override
	public Optional<UserDetails> updateUser(Integer userId, UserRequest userRequest)
			throws ResourceNotFoundException {
//		Optional<UserDetails> user = userRepository.findById(userId);
//		if (user.isEmpty()) {
//			throw new ResourceNotFoundException("User", "id", userId);
//		} else
//			user.get().setFirstName(userRequest.getFirstName());
//		user.get().setLastName(userRequest.getLastName());
//		user.get().setPhoneNumber(userRequest.getPhoneNumber());
//		user.get().setEmail(userRequest.getEmail());
//		user.get().setAddressOne(userRequest.getAddressOne());
//		user.get().setAddressTwo(userRequest.getAddressTwo());
//		userRepository.save(user.get());
//		return user;
		return null;
	}

	@Override
	public UserDetails getASingleUser(Integer userId) throws ResourceNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	@Override
	public List<UserDetails> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Integer userId) throws ResourceNotFoundException {
		if (userRepository.getById(userId).getId().equals(userId)) {
			userRepository.deleteById(userId);
		} else
			throw new ResourceNotFoundException("User", "id", userId);
	}
}
