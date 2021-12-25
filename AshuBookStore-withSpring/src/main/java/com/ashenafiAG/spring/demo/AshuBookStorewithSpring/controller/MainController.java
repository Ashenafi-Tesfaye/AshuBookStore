package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Book;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Cart;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.User;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.forms.AccountRegistrationForm;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.repository.UserRepository;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.BookService;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.ShoppingCartService;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.UserService;

@Controller
@SessionAttributes({"URL_REF", "user", "cart"})
public class MainController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/query")
	public @ResponseBody List<Book> getQueryResults(@RequestParam String qry){
		return bookService.getQueryResults(qry);
	}
	
	@RequestMapping("/search")
	public String getQuerySearch(@RequestParam String qry, ModelMap model) {
		model.put("query", qry);
		model.put("title", qry + " - " + "OnlineBookstore");
		
		List<Book> list = bookService.getSearchResults(qry);
		model.put("numOfResults", list.size());
		model.put("serachResultList", list);
		
		return "search-results";
	}

	@RequestMapping("/")
	public String userHomePage(Model model, ModelMap modelMap) {
		//List of Books
		
		modelMap.put("topPopularBooksList", bookService.getTopRatedBooks());
		modelMap.put("topRatedBooksList", bookService.getTopRatedBooks());
		modelMap.put("topRatedBooksByYearList", bookService.getTopRatedBooksByYear());
		modelMap.put("ancientListBooksList", bookService.getAncientLitratureBooks());
		return "home";
	}
	
	@RequestMapping("/register")
	public String userRegisterationPage(ModelMap model, AccountRegistrationForm accountRegistrationForm) {
		return "register";
	}
	
	@PostMapping("/register.do")
	public String validateUserRegistrationForm(ModelMap model, HttpServletRequest request,
			RedirectAttributes redirectAttr, @Valid AccountRegistrationForm accountRegistrationForm ) {
		
		return userService.createNewAccount(accountRegistrationForm, model, redirectAttr);
	}
	
	@RequestMapping("/login")
	public String userLoginPage(ModelMap model, HttpServletRequest request) {
		model.put("userLogin", "/login.do");
		System.out.println("Referer: " + request.getHeader("Referer"));
		model.addAttribute("URL-REF", request.getHeader("Referer"));
		
		return "login";
	}
	
	@RequestMapping("/account")
	public String userAccountPage(ModelMap model, Principal principal) {
		model.put("welcomeMessage", "Welcome");
		return "account";
	}
	
	public String adminHomePage(ModelMap model) {
		return "admin";
	}
	
	@RequestMapping("/loginSuccessful")
	public String userLoginSuccessful(@RequestParam String role, ModelMap model, HttpServletRequest request,
			Principal principal) {
		
		User user = userRepository.findByEmail(principal.getName());
		user.setPassword(null);
		
		model.put("user", user);
		Cart cart = shoppingCartService.getSavedUsersShoppingCart(user);
		
		model.put("cart", cart);
		
		String url_ref = request.getSession().getAttribute("URL_REF").toString();
		String url = null;
		
		if(url_ref != null) {
			System.out.println("-------------------------"+ url_ref);
			url_ref = "/";
			url = "redirect:" + url_ref;
		}
		else {
			System.out.println("hello : " + url_ref);
			url = "redirect:/";
		}
		
		return url;
	}
	
	public String userLogoutSuccessful(ModelMap model, RedirectAttributes redirectAttr) {
		System.out.println("You have logged out");
		redirectAttr.addFlashAttribute("LOGOUT_SUCCESSFUL_MESSAGE", "Success! You have successfully logged out.");
		return "redirect:/login";
	}
}
