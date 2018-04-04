package potatoes.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import potatoes.project.domain_objects.PasswordAuthentication;
import potatoes.project.domain_objects.User;
import potatoes.project.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordAuthentication passwordAuthentication;
	
	public void save(User user) {
		user.setPassword(passwordAuthentication.hash(user.getPassword().toCharArray()));
		userRepository.save(user);
	}
	
	public User findByUsername(String name) {
		return userRepository.findByName(name);
	}
}
