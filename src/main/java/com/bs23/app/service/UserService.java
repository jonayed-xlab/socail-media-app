package com.bs23.app.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bs23.app.entity.User;
import com.bs23.app.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private KafkaProducer kafkaProducer;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUserById(Long userId) {
		logger.info("Fetching user by ID: {}", userId);
		return userRepository.findById(userId);
	}

	@Transactional
	public User createUser(User user) {
		logger.info("Creating a new user: {}", user);

		// Send to redis for caching
		/*
		try {
			redisTemplate.opsForValue().set("userId", user.toString();
			logger.info("Successfully inserted value '{}' with key '{}'", user, user.getUserId());
		} catch (Exception e) {
			logger.error("Failed to insert value '{}' with key '{}'", user, user.getUserId(), e);
		}
		 */
		
		// Send to kafka topic
		try {
			kafkaProducer.sendMessage(user.toString());
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		}

		return userRepository.save(user);
	}

	@Transactional
	public Optional<User> updateUserById(Long userId, User userDetails) {
		logger.info("Updating user with ID {}: {}", userId, userDetails);

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			User existingUser = user.get();
			existingUser.setFirstName(userDetails.getFirstName());
			existingUser.setLastName(userDetails.getLastName());
			existingUser.setEmail(userDetails.getEmail());
			existingUser.setDob(userDetails.getDob());
			return Optional.of(userRepository.save(existingUser));
		} else {
			return Optional.empty();
		}

	}

	public void deleteUserById(Long userId) {
		logger.info("Deleting user with ID: {}", userId);
		userRepository.deleteById(userId);
	}

}
