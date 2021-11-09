package ashubookstore.withspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ashubookstore.withspring.model.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository {
	public PasswordResetToken findFirstByUserIdOrderByExpirationDateDesc(Long id);
	
	public PasswordResetToken findFirstByToken(String token);
	
}
