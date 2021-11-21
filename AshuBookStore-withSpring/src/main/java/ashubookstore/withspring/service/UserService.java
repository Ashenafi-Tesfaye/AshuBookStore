package ashubookstore.withspring.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ashubookstore.withspring.model.Address;
import ashubookstore.withspring.model.Book;
import ashubookstore.withspring.model.Item;
import ashubookstore.withspring.model.Order;
import ashubookstore.withspring.model.User;
import ashubookstore.withspring.model.enums.RoleType;
import ashubookstore.withspring.model.forms.AccountRegistrationForm;
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
			if(isCurrentPasswordValid(currentPassword, email)) {
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
		User user = getUserByUserId(userId);
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

	public boolean updateUserEmail(String newEmail, String currentEmail) {
		User user = getCurrentUserByEmail(newEmail);
		if(user == null) {
			User user2 = getCurrentUserByEmail(currentEmail);
			user2.setEmail(newEmail);
			userRepository.save(user2);
			return true;
		}else {
			return false;
		}
		
	}
	
	public void getAccountUserRecommendations(Principal principal, ModelMap model) {
		
		User user = getCurrentUserByEmail(principal.getName());
		List<Book> recommendedBooks = new ArrayList<>();
		List<Book> allOrderedBooks = new ArrayList<>();
		
		List<Order> orders = orderService.getAllOrdersByUserId(user.getId());
		for(Order order: orders) {
			List<Item> orderedBooks = orderedBookService.getOrderedBooksBYOrderId(order.getOrderId());
			allOrderedBooks.addAll(getAllBooksFromItems(orderedBooks));
			for(Item item:orderedBooks) {
				for(Book book : bookService.getBooksByAuthor(item.getBook().getAuthors())){
					if(!allOrderedBooks.contains(book))
						recommendedBooks.add(book);
					if(recommendedBooks.size()>25) {
						break;
					}
				}
			} if(recommendedBooks.size()>25) {
				break;
			}
		
		
		if(recommendedBooks.size()>25) {
			break;
		}
	}
		model.put("recommendedBooks", recommendedBooks);
}
	
		
	private List<Book> getAllBooksFromItems(List<Item> orderedBooks) {
		List<Book> list = new ArrayList<>();
		for(Item item : orderedBooks) {
			list.add(item.getBook());
		}
		return list;
	}
	
	public String createNewAccount (@Valid AccountRegistrationForm accountRegistrationForm, ModelMap model,
		RedirectAttributes redirectAttr){
		
		if(!accountRegistrationForm.getPassword().equals(accountRegistrationForm.getConfirmedPassword())) {
			model.put("ERROR", "Error! Your password does not match. Please try again.");
			return "register";
		}else {
			if(userRepository.findByEmail(accountRegistrationForm.getEmail()) != null) {
				model.put(null, "Error! The email provided already exists. Please try again.");
				return "register";
			} else {
				createNewUser(accountRegistrationForm);
				redirectAttr.addFlashAttribute("REGISTRATION_SUCCESSFUL_MESSAGE", 
						"Success! You have successfully registered for an account.");
				sendNewUserEmailWelcome(accountRegistrationForm);
				return "redirect:/login";
			}
		}
		
	}

	private void sendNewUserEmailWelcome(@Valid AccountRegistrationForm accountRegistrationForm) {
		String to = accountRegistrationForm.getEmail();
		String from = "OnlineBookStore.com <auto-confirm@onlinebookstore.com>";
		String subject = accountRegistrationForm.getFirstName() + ", welcome to OnlineBookstore!";
		String body = accountRegistrationForm.getFirstName() + ", welcome to OnlineBookstore!\r\n" + "\r\n"
				+ "Browse your favorite books, our editorial picks, bestsellers, or customer favorites.\r\n"
				+"\r\n";
		emailService.sendSimpleMessage(to, from, subject, body);
		
	}

	private void createNewUser(@Valid AccountRegistrationForm accountRegistrationForm) {
		User user = new User();
		user.setFirstName(accountRegistrationForm.getFirstName());
		user.setLastName(accountRegistrationForm.getLastName());
		user.setEmail(accountRegistrationForm.getEmail());
		user.setPassword(passwordEncoder.encode(accountRegistrationForm.getPassword()));
		user.setUserRole(RoleType.CUSTOMER);
		user.setDateCreated(new Date());
		userRepository.save(user);
	}

	public void sendOrderDetailsEmail(String hash, User user) {
		Order order = orderService.getOrderByHash(hash);
		Address address = addressService.getAddressById(order.getAddressId());
		
		String to = user.getEmail();
		String from = "OnlineBookStore.com <auto-confirm@onlinebookstore.com>";
		String subject = "your OnlineBookStore.com order Details";
		String body = "Hello "+ user.getFirstName() + " " + user.getLastName() + ",\n\n "
				+ " Thank you for shopping wih us. We will send a confirmation when your item ships.\n\n" +
				"Details: \n"
				+ "Order #: " + hash + "\n\n" + "Ship to: \n" + " " + address.getFirstName() + " "
				+ address.getLastName() + "\n" + address.getAddress1() + " " + address.getAddress2() +"\n"
				+ address.getCity() + " , " + address.getState() + " " + address.getZip() + "\n\n"
				+ "Total Before Tax: $ " + order.getTotal() + "\n" + "Estmiated Tax : $0.00\n" + "Order Total : $"
				+order.getTotal() + "\n\n" + "We hope to see you again soon.\r\n" + "OnlineBookStore.com \r\n"
				+ " ";
		emailService.sendSimpleMessage(to, from, subject, body);
		
		
	}
	
	
}
