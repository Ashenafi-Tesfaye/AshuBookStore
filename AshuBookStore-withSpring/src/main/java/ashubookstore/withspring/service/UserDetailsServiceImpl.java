package ashubookstore.withspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ashubookstore.withspring.model.User;
import ashubookstore.withspring.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Users use their email and password to login. Accordingly, email serves as username 
		
		User user = userRepository.findByEmail(username);
		
		if (user == null) {
			
			throw new UsernameNotFoundException("The provided email " + username + "does not exist");
		}
		
		log.info("loadUserByUsername() : {}", username);
		
		return new UsersDetailsImpl(user);
	}

	
}
