package local.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	// methods=[],params=[],headers=[],consumes=[],produces=[],custom=[]
	@RequestMapping("/index.cgi")
	public String index(@RequestParam(value = "name", required = false,
			defaultValue = "Guest") String name, Model model)
	{		
		model.addAttribute("name", name);
		
		return "index";
	}
}