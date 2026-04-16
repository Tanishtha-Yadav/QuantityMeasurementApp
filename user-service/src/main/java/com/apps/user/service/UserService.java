package com.apps.user.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.user.dto.ConversionHistoryDTO;
import com.apps.user.dto.ConversionHistoryInputDTO;
import com.apps.user.dto.UserDTO;
import com.apps.user.entity.ConversionHistory;
import com.apps.user.entity.User;
import com.apps.user.repository.ConversionHistoryRepository;
import com.apps.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ConversionHistoryRepository historyRepository;

	// User CRUD
	public UserDTO getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.map(this::convertToDTO).orElse(null);
	}

	public UserDTO getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user != null ? convertToDTO(user) : null;
	}

	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setActive(userDTO.isActive());
		User saved = userRepository.save(user);
		return convertToDTO(saved);
	}

	public UserDTO updateUser(Long id, UserDTO userDTO) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setEmail(userDTO.getEmail());
			user.setName(userDTO.getName());
			user.setActive(userDTO.isActive());
			User updated = userRepository.save(user);
			return convertToDTO(updated);
		}
		return null;
	}

	// Conversion History
	public ConversionHistoryDTO saveConversionHistory(Long userId, ConversionHistoryInputDTO inputDTO) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new RuntimeException("User not found: " + userId);
		}

		ConversionHistory history = new ConversionHistory();
		history.setUser(user);
		history.setType(inputDTO.getType());
		history.setFromUnit(inputDTO.getFromUnit());
		history.setToUnit(inputDTO.getToUnit());
		history.setInputValue(inputDTO.getInputValue());
		history.setOutputValue(inputDTO.getOutputValue());
		history.setAction(inputDTO.getAction());
		history.setCreatedAt(LocalDateTime.now());

		ConversionHistory saved = historyRepository.save(history);
		return convertHistoryToDTO(saved);
	}

	public List<ConversionHistoryDTO> getUserConversionHistory(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new RuntimeException("User not found: " + userId);
		}

		List<ConversionHistory> historyList = historyRepository.findByUserOrderByCreatedAtDesc(user);
		return historyList.stream().map(this::convertHistoryToDTO).collect(Collectors.toList());
	}

	public List<ConversionHistoryDTO> getUserConversionHistoryByType(Long userId, String type) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			throw new RuntimeException("User not found: " + userId);
		}

		List<ConversionHistory> historyList = historyRepository.findByUserAndTypeOrderByCreatedAtDesc(user, type);
		return historyList.stream().map(this::convertHistoryToDTO).collect(Collectors.toList());
	}

	public void deleteConversionHistory(Long historyId) {
		historyRepository.deleteById(historyId);
	}

	// Helper methods
	private UserDTO convertToDTO(User user) {
		return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.isActive());
	}

	private ConversionHistoryDTO convertHistoryToDTO(ConversionHistory history) {
		return new ConversionHistoryDTO(
			history.getId(),
			history.getType(),
			history.getFromUnit(),
			history.getToUnit(),
			history.getInputValue(),
			history.getOutputValue(),
			history.getAction(),
			history.getCreatedAt()
		);
	}
}
