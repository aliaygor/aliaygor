package com.mygradleproject.publictransportation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mygradleproject.publictransportation.entity.User;
import com.mygradleproject.publictransportation.repository.UserRepository;
import com.mygradleproject.publictransportation.response.BaseResponse;
import com.mygradleproject.publictransportation.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUserById(int userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User addOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User deleteUser(int userId) throws Exception {
		User deletedUser = null;
		try {
			deletedUser = userRepository.findById(userId).orElse(null);
			if(deletedUser == null) {
				throw new Exception("user not found");
			}else {
				userRepository.deleteById(userId);
			}
		} catch (Exception e) {
			throw e;
		}
		return deletedUser;
	}
	
	@Override
	public BaseResponse getUsers() {
		
		BaseResponse response = new BaseResponse();
		//response.setData((List<User>) userRepository.findAll());
		response.setStatus(response.getStatus());
		
		return response;
		
		
	}

}
