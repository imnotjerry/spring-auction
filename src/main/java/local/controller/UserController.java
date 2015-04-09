package local.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import local.model.User;
import local.service.UserService;
import local.service.Util;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired(required = false)
	private final UserService userService = new UserService();

	public UserController()
	{
		Util.log("UserController constructor");
	}

	/*
	 * List the registred users.
	 */
	@RequestMapping("/user/list.cgi")
	public String list(Model model)
	{
		Util.log("User list");

		List users = userService.findAll();

		for (User user : (List<User>) users) {
			Util.log(user.toString());
		}

		model.addAttribute("users", users);

		return "user/list";
	}

	/*
	 * Display a ditail information about the user.
	 */
	@RequestMapping("/user/user.cgi")
	public String user(@RequestParam(value = "id", required = true) String id,
			Model model, HttpServletRequest request)
	{
		/* Restricted access. Only for signed in users. */
		if (request.getSession().getAttribute("userId") == null) {
			Util.log("User is not logged in.");
		}
		
		User user = userService.find(id);
		
		if (user == null) {
			Util.log("User wasn't found.");
			return "redirect:list.cgi";
		}
		Util.log("User: " + user);

//		userService.updateStatistics((long) request.getSession().getAttribute("userId"));
		
		model.addAttribute("user", user);

		return "user/user";
	}

	@RequestMapping("/user/signin.cgi")
	public String signin(Model model, HttpServletRequest request)
	{
		/* Check if a user is already signed in. */
		if (request.getSession().getAttribute("userId") != null) {
			Util.log("User is logged in.");
			return "redirect:list.cgi";
		}

		/* Create a demo data for the User model. */
		User user = new User();
		user.setName("John");
		
		model.addAttribute("user", user);

		return "user/signin";
	}
	
	@RequestMapping(value = "/user/signin.cgi", method = RequestMethod.POST)
	public String signin(@Valid @ModelAttribute("user") User user,
			BindingResult result, HttpServletRequest request)
	{
		if (result.hasErrors()) {
			Util.log("Validation fail");

			return "user/signin";
		}

		/*
		 * TMP. Sign in a fake user with id = 1.
		 */
		request.getSession(true).setAttribute("userId", 2L);
		
		Util.log("User was sign in: " + user);
		
		/* Redirect to the signed in user. */
		return "redirect:user.cgi?id=" + 2;
	}

	/*
	 * Sign out the user.
	 * 
	 */
	@RequestMapping("/user/signout.cgi")
	public String signout(Model model, HttpServletRequest request)
	{
		if (request.getSession().getAttribute("userId") == null) {
			Util.log("No user is logged in.");
			return "redirect:list.cgi";
		}
		request.getSession().setAttribute("userId", null);

		return "redirect:list.cgi";
	}
	
	/*
	 * Create a new user.
	 */
	@RequestMapping("/user/signup.cgi")
	public String signup(Model model, HttpServletRequest request)
	{
		/* Check if a user is already signed in. */
		if (request.getSession().getAttribute("userId") != null) {
			Util.log("User is logged in.");
			return "redirect:list.cgi";
		}

		/* Create a demo user data (for the Model). */
		User user = new User();
		user.setName("John");
		user.setBirthday(new Date(new Date().getTime() - 1000L * 60 * 60 * 24 * 30 * 12 * 18));
		user.setEmail("user@localhost");

		model.addAttribute("user", user);

		return "user/signup";
	}

	@RequestMapping(value = "/user/signup.cgi", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute("user") User user,
			BindingResult result)
	{
		if (result.hasErrors()) {
			Util.log("Validation fail");

			return "user/signup";
		}

		userService.create(user);
		Util.log("User was created: " + user);

		return "redirect:signin.cgi";
	}

	/*
	 * Update an user.
	 * 
	 */
	@RequestMapping("/user/update.cgi")
	public String update(Model model, @RequestParam(value = "id", required = true) String id)
	{
		User user = userService.find(id);

		if (user == null) {
			Util.log("Update: no user with such id");
			return "user/list";
		}

		Util.log("User to update: " + user.getId());

		model.addAttribute("user", user);

		return "user/update";
	}

	@RequestMapping(value = "/user/update.cgi", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") User user,
			BindingResult result, @RequestParam(value = "id", required = true) Long id)
	{
		if (result.hasErrors()) {
			Util.log("Validation fail");

			return "user/update";
		}
		
//		user.setId(id);
		userService.update(user);

		Util.log("User was updated: " + user);

		/* Redirect to the updated user. */
		return "redirect:user.cgi?id=" + user.getId();
	}

	/*
	 * Delete an user.
	 */
	@RequestMapping("/user/delete.cgi")
	public String delete(@RequestParam(value = "id", required = true) String id,
			HttpServletRequest request)
	{
		/* Sign out the user */
		request.getSession().setAttribute("userId", null);
		
		Util.log("Delete id = " + id);
		userService.delete(userService.find(id));

		return "redirect:list.cgi";
	}

}
