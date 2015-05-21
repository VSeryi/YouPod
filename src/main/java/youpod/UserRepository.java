package youpod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

	public interface UserRepository extends JpaRepository<User, Integer> {
		List<User> findByEmail (String email);
		List<User> findByFacebookId (String facebookId);
		List<User> findByGoogleId (String googleId);

	}
