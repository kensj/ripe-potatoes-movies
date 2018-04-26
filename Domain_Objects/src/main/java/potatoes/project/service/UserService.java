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
	
	public boolean authenticate(String username, String password) {
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(findByUsername(username).getPassword());
		String hashToCompare = findByUsername(username).getPassword();
		return passwordAuthentication.authenticate(password.toCharArray(), hashToCompare);
	}
	
	public User findByUsername(String name) {
		return userRepository.findByName(name);
	}
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public User findByUserID(int id) {
		return userRepository.findByUserID(id);
	}
	
	//public List<Integer> findFollowedUsers(int userID) {
	//	return userRepository.findFollowedUsers(userID);
	//}
}
