package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.Repositories.UserRepository;
import com.app.models.User;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		User user= userRepo.findByEmailId(emailId).orElseThrow(()->new UsernameNotFoundException("Email not found!!"));
		
		return new CustomUserDetails(user);
	}
}
//CustomUserDetailsService:
//
//This class implements the UserDetailsService interface, which is used by Spring Security to load user details based on a username (in this case, the email address).
//It has a dependency on a UserEntityDao bean, which presumably provides methods to interact with the database to retrieve user information.
//The loadUserByUsername(String email) method overrides the method from the UserDetailsService interface. It attempts to find a user entity by the provided email address using the userDao.findByEmail(email) method.
//If a user with the given email address is found in the database, it constructs a new CustomUserDetails object using the retrieved UserEntity. This object encapsulates the user details required by Spring Security.
//If the user is not found, it throws a UsernameNotFoundException.
//This class serves as a bridge between the DAO layer and Spring Security's authentication and authorization mechanisms. It retrieves user information from the database and constructs CustomUserDetails objects for authentication and authorization purposes.