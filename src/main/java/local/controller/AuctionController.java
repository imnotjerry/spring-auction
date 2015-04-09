package local.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import local.model.Auction;
import local.model.Bid;
import local.model.User;
import local.service.AuctionService;
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
public class AuctionController {

	@Autowired(required = false)
	private final AuctionService auctionService = new AuctionService();
	private final UserService userService = new UserService();

	public AuctionController() {
		Util.log("AuctionController constructor");
	}

	/*
	 * List active auctions.
	 */
	@RequestMapping("/auction/list.cgi")
	public String list(Model model) {
		Util.log("Auction list");

		List auctions = auctionService.findAll();

		for (Auction auction : (List<Auction>) auctions) {
			Util.log(auction.toString());
		}

		model.addAttribute("auctions", auctions);

		return "auction/list";
	}

	/*
	 * Display ditail information about the auction.
	 */
	@RequestMapping("/auction/auction.cgi")
	public String auction(@RequestParam(value = "id", required = true) String id,
			Model model) {
		Auction auction = auctionService.find(id);

		if (auction == null) {
			Util.log("Auction wasn't found.");
			return "redirect:list.cgi";
		}

		Util.log("Details: " + auction);
		model.addAttribute("auction", auction);
		model.addAttribute("bid", null);

		if (auction.isActive()) {
			Bid bid = new Bid();
			bid.setAmount(auction.getCurrentPrice() + 1.0F);
			model.addAttribute("bid", bid);
		}

		return "auction/auction";
	}
	
	/*
	 * Add a bid to the auction.
	 */
	@RequestMapping(value = "/auction/auction.cgi", method = RequestMethod.POST)
	public String auction(@RequestParam(value = "id", required = true) String id,
			@ModelAttribute("auction") Auction auction,
			@Valid @ModelAttribute("bid") Bid bid,
			BindingResult result, HttpServletRequest request) {
		if (request.getSession().getAttribute("userId") == null) {
			Util.log("Create: user is not logged in.");
			return "redirect:../user/signin.cgi";
		}

		if (result.hasErrors()) {
			Util.log("Validation fail");
			return "redirect:auction.cgi?id=" + id;
		}

		auction = auctionService.find(id);

		if (bid.getAmount() <= auction.getCurrentPrice()
				|| bid.getAmount() <= auction.getStartPrice()) {
			return "redirect:auction.cgi?id=" + id;
		}

		Util.log("Price: " + auction.getCurrentPrice() + "; Bid: " + bid.getAmount());
		long userId = (long) request.getSession().getAttribute("userId");
		auctionService.addBid(bid, auction.getId(), userId);

		/* Redirect to the auction. */
		return "redirect:auction.cgi?id=" + auction.getId();
	}

	/*
	 * Create a new auction.
	 * 
	 */
	@RequestMapping("/auction/create.cgi")
	public String create(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("userId") == null) {
			Util.log("Create: user is not logged in.");
			return "redirect:../user/signin.cgi";
		}

		model.addAttribute("auction", new Auction("Lamp",
				"A small plastic white lamp with a red switch and a handler for a pen",
				"lamp.jpg", new Date(), new Date(new Date().getTime() + 1000 * 60 * 60 * 24), 5.50f, 0.0f));

		return "auction/create";
	}

	@RequestMapping(value = "/auction/create.cgi", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("auction") Auction auction,
			BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			Util.log("Validation fail");

			return "auction/create";
		}

		long auctionId = auctionService.create(auction);

		/* Create a demo user; add the user to the auction. */
		User user = new User();
		user.setName("John");
		user.getStatistics().addVisit();
		long userId = userService.create(user);

		auctionService.addAuctionToUser(auctionId, userId);

		/* Create a demo category. */
		long categoryId = auctionService.createCategory("Miscellaneous");
		auctionService.addAuctionToCategory(auctionId, categoryId);

		Util.log("Auction was created: " + auction);

		/* Redirect to the created auction. */
		return "redirect:auction.cgi?id=" + auctionId;
	}

	/*
	 * Update an auction.
	 * 
	 */
	@RequestMapping("/auction/update.cgi")
	public String update(Model model, @RequestParam(value = "id", required = true) String id) {
		Auction auction = auctionService.find(id);

		if (auction == null) {
			Util.log("Update failed: no auction with such id");
			return "auction/list";
		}

		Util.log("Auction to update: " + auction.getId());

		model.addAttribute("auction", auction);

		return "auction/update";
	}

	@RequestMapping(value = "/auction/update.cgi", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("auction") Auction auction,
			BindingResult result, @RequestParam(value = "id", required = true) Long id) {
		if (result.hasErrors()) {
			Util.log("Validation fail");

			return "auction/update";
		}
		auction.setId(id);
		auctionService.update(auction);

		Util.log("Auction was updated: " + auction);

		/* Redirect to the updated auction. */
		return "redirect:auction.cgi?id=" + auction.getId();
	}

	/*
	 * Delete an auction.
	 */
	@RequestMapping("/auction/delete.cgi")
	public String delete(@RequestParam(value = "id", required = true) String id) {
		Util.log("Delete id = " + id);
		auctionService.delete(id);

		return "redirect:list.cgi";
	}

}
