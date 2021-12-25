package com.ashenafiAG.spring.demo.AshuBookStorewithSpring.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Address;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Item;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.Order;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.model.User;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.AddressService;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.OrderService;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.OrderedBookService;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.PasswordResetTokenService;
import com.ashenafiAG.spring.demo.AshuBookStorewithSpring.service.UserService;

@Controller
@RequestMapping("/account")
public class CustomerAccountController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private OrderedBookService orderedBooksService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	public String getOrderDetails (@RequestParam String orderID, ModelMap model, HttpServletRequest request,
			Principal principal) {
		if(orderID != null) {
			System.out.println(orderID);
			String hash = orderID;
			User user = (User) request.getSession().getAttribute("user");
			Order order = orderService.getOrderByHashAndByUserId(hash, user.getId());
			if(order != null) {
				model.put("orderedDate", orderService.formatDate(order.getDateOrdered()));
				model.put("order", order);
				
				Address address = addressService.getAddressById(order.getAddressId());
				model.put("userAddress", address);
				
				List<Item> orderedBooks = orderedBooksService.getOrderedBooksBYOrderId(order.getOrderId());
				model.put("orderedBooks", orderedBooks);
				
				model.put("totalItemsOrdered", orderedBooksService.getTotalQuantatity(orderedBooks));
			}
			else {
				return "redirect:/account/order-history";
			}
			
		}
		
		return "order-details";
	}
	
	@RequestMapping("/order-history")
	public String getOrderHistory(ModelMap model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user);
		HashMap<Order, List<Item>> orders = orderedBooksService.getOrderedBooks(orderService.getAllOrdersByUserId(user.getId()));
		model.put("orders", orders);
		System.out.println(orders);
		
		return "order-history";
	}
	
	@RequestMapping("/profile")
	public String getAccountProfile(ModelMap model, HttpSession session) {
		String newEmail = (String) model.get("newEmail");
		if(newEmail != null) {
			User user = userService.getCurrentUserByEmail(newEmail);
			session.setAttribute("user", user);
			}
		return "account-profile";
	}

	@PostMapping("/password/update")
	public String updateAccountPassword(RedirectAttributes redirectAttr, HttpServletRequest request,
			Principal principal) {
		userService.updateUserAccountPassword(request, redirectAttr, principal.getName());
		return "redirect:/account/profile";
	}
	
	@PostMapping("/email/update")
	public String updateAccountEmail(RedirectAttributes redirectAttr, HttpServletRequest request, Principal principal) {
		userService.updateUserAccountEmail(request, redirectAttr, principal.getName());
		return "redirect:/account/profile";
	}
	
	@RequestMapping("/recommendations")
	public String getAccountReccomendations(Principal principal, ModelMap model) {
		userService.getAccountUserRecommendations(principal, model);
		return "recommendations";
	}
	
	@RequestMapping("/password/reset")
	@PostMapping("/password/reset.do")
	public String changePassword(ModelMap model, HttpServletRequest request) {
		if(request.getParameter("email") != null) {
			return passwordResetTokenService.reserPassWordByEmail(request.getParameter("email"), model);
		}
		
		return "change-password";
	}
	
	@RequestMapping("/password/reset")
	public String resetPassword(@RequestParam String token, ModelMap model) {
		if(token != null) {
			passwordResetTokenService.verifyToke(token, model);
		}
		
		return "reset-password";
	}

	@PostMapping("/password/reset/change.do")
	public String updatePassword(ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttr) {
		return passwordResetTokenService.updatePassword(model, request, redirectAttr);
	}
}
