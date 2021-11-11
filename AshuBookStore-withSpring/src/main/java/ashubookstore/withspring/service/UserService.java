package ashubookstore.withspring.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ashubookstore.withspring.model.User;
import ashubookstore.withspring.repository.UserRepository;

public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderedBookService orderedBookService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AddressService addressService;
	
	
	public void updateUserAccountPassword(HttpServletRequest request, RedirectAttributes redirectAttr, String email) {
		
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("newPassword");
		
		if(!newPassword.equals(confirmPassword)) {
			
			redirectAttr.addFlashAttribute("ERROR", "Error! Your password did not match. Please try again.");
		} else {
			if(isCurrentPasswordValied(currentPassword, email)) {
				updateUserPassword(newPassword, email);
				redirectAttr.addFlashAttribute("Success", "Success! Your password has been updated");
			} else {
				redirectAttr.addFlashAttribute("ERROR", "Error! Your Current password is invalid. Please try again.");
			}
		}
		
		
	}
	
	public void updateUserPassword(String newPassword, String email) {
		User user = getCurrentUserByEmail(email);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
	
	public void updateUserPassword(String newPassword, Long userId) {
		User user = getCurrentUserByUserId(userId);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
	
	public User getUserByUserId(Long userId) {
		return userRepository.findById(userId).get();
	}
	
	public boolean isCurrentPasswordValid(String password, String email) {
		System.out.println(passwordEncoder.matches(password, getPasswordByEmail(email)));
		if(passwordEncoder.matches(password, getPasswordByEmail(email))) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getPasswordByEmail(String email) {
		return userRepository.findByEmail(email).getPassword();
	}
	
	public User getCurrentUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public void updateUserAccountEmail(HttpServletRequest request, RedirectAttributes redirectAttr, String email) {
		String currentEmail = request.getParameter("currentEmail");
		String newEmail = request.getParameter("newEmail");
		String confirmEmail = request.getParameter("confirmEmail");
		
		
		if(!newEmail.equals(confirmEmail)) {
			redirectAttr.addFlashAttribute("ERROR", "Error! Your email did not match. Please try again");
		}else {
			if(currentEmail.equals(email)) {
				if(updateUserEmail(newEmail, currentEmail)) {
					redirectAttr.addFlashAttribute("SUCCESS","Success! Your email has been updated");
					redirectAttr.addFlashAttribute("newEmail", newEmail);
				}else {
					redirectAttr.addFlashAttribute("ERROR", 
							"Error! The provided new email already exists. Please choose a different email.");
				}
			}else {
				redirectAttr.addFlashAttribute("ERROR", "Error! The provided email does not exist. Please try again");
			}
		}
	}
	
	
}
