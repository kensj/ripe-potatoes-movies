package potatoes.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import potatoes.project.domain_objects.User;
import potatoes.project.domain_objects.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  VerificationToken findByToken(String token);
  
  VerificationToken findByUser(User user);
  
  @Query("select vt from VerificationToken vt where vt.user=?1")
  List<VerificationToken> findByUserList(User user);
  
}
