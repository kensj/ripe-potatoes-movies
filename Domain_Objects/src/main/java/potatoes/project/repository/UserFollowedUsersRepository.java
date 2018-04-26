package potatoes.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.User;

public interface UserFollowedUsersRepository extends JpaRepository<User, Integer> {
	//List<Integer> findbyUser__UserID(int id);
}
