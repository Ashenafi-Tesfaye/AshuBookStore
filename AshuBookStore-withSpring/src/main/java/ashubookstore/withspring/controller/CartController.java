package ashubookstore.withspring.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ashubookstore.withspring.model.Address;
import ashubookstore.withspring.model.Cart;
import ashubookstore.withspring.model.User;
import ashubookstore.withspring.model.forms.AddressForm;
import ashubookstore.withspring.service.AddressService;
import ashubookstore.withspring.service.BookService;
import ashubookstore.withspring.service.CartService;
import ashubookstore.withspring.service.OrderService;
import ashubookstore.withspring.service.OrderedBookService;
import ashubookstore.withspring.service.PaymentService;
import ashubookstore.withspring.service.ShoppingCartService;
import ashubookstore.withspring.service.UserService;

@ControllerAdvice
@RequestMapping("/cart")
@SessionAttributes({"cart", "userAddress"})
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderedBookService orderedBookService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/card.do")
	public String cartItem(ModelMap model, HttpServletRequest request) {
		cartService.addItemToCart(model, request);
		return "redirect:/cart/view";
	}
	
	@PostMapping("/update")
	public String updateItem(ModelMap model, HttpServletRequest request) {
		cartService.updateItem(model, request);
		return "redirect:/cart/view";
	}
	
	@RequestMapping("/view")
	public String getShoppingCart(ModelMap model) {
		return "shopping-cart";
	}

	@RequestMapping("/checkout")
	@PostMapping("/checkout")
	public String checkoutCart (ModelMap model, HttpServletRequest request, AddressForm addressForm) {
		//if shopping cart is empty redirect user to the home page
		//most recently used shipping address
		User user = (User) request.getSession().getAttribute("user");
		addressService.getRecentlyUsedAddress(model, user.getId());
		return "checkout";
	}

	//Generate a braintreegateway token for payment transaction 
	/*
	 * @RequestMapping(value= "/payment/token", method = RequestMethod.GET, produces
	 * = "application/json") public @ResponseBody Map<String, String>
	 * getClientToken(){ return paymentService.getClientToken(); }
	 */
	
	@PostMapping("/address/add")
	public String addNewShippingAddress(ModelMap model, @Valid AddressForm addressForm, BindingResult bindingResult,
			HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			System.out.println(
					"--------------------Error : Adding New Shipping Address-------------------");
			System.out.println(bindingResult.getFieldError());
			return "/cart/checkout";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		addressService.addAddress(addressForm, user.getId());
		
		return "redirect:/cart/checkout";
	}
	
	@PostMapping("place-order")
	public String placeOrder(@RequestParam("payment_method_nonce") String paymentMethodNonce, ModelMap model,
			HttpServletRequest request, RedirectAttributes redirectAttr) {
	
		User user = (User) request.getSession().getAttribute("user");
		Cart cart = (Cart) model.get("cart");
		
		Address address = (Address) request.getSession().getAttribute("userAddress");
		Long orderId = orderService.placeOrder(model, user.getId() , cart.getTotalCost(), address.getAddressId());
		
		Boolean paymentSuccessful = paymentService.makePayment(orderId, cart.getTotalCost(), paymentMethodNonce);
		
		if(paymentSuccessful) {
			String hash = orderService.getOrderHash(orderId);
					
			orderService.updatePaid(orderId);
			orderedBookService.insert(cart, orderId);
			shoppingCartService.clearUserCart(user.getId(), cart, request, model);
			bookService.updateBooksStock(cart);
			userService.sendOrderDetailsEmail(hash, user);
			
			redirectAttr.addFlashAttribute("orderId", orderId);
			return "redirect:/account/order-details?orderId=" + hash;
		} else {
			redirectAttr.addFlashAttribute("PAYMENT_METHOD_ERROR_MESSAGE_1", "Error! Your payment was declined");
			redirectAttr.addFlashAttribute("PAYMENT_METHOD_ERROR_MESSAGE_2", 
					"Please try again or choose a different payment method");
			
			return "redirect:/cart/checkout";
		}
}
}