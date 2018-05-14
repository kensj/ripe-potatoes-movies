package potatoes.project.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import potatoes.project.domain_objects.Follow;
import potatoes.project.domain_objects.User;

public class FollowRepoWrapper {
	
	@Autowired
	private static FollowRepository repo;
	
	public static List<User> getFollowers(int userID){
		List<Follow> fols = repo.findByFollowedUserID(userID);
		List<User> followers = new ArrayList<>();
		for (Follow f : fols) {
			followers.add(f.getFollower());
		}
		return followers;
	}
	
	public static int getNumFollowers(int userID) {
		return repo.findByFollowedUserID(userID).size();
	}
}
