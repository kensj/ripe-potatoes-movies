package potatoes.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import potatoes.project.domain_objects.ForgotPasswordToken;
import potatoes.project.domain_objects.User;

public interface ForgotPasswordTokenRepository extends JpaRepository<ForgotPasswordToken, Long> {

	ForgotPasswordToken findByToken(String token);
  
	ForgotPasswordToken findByUser(User user);
  
}
