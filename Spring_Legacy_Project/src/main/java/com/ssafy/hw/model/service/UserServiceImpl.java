package com.ssafy.hw.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.hw.model.dao.UserDao;
import com.ssafy.hw.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	 UserDao userDao;

	@Override
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	
}
