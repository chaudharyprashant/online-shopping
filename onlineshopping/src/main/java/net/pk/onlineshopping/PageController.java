package net.pk.onlineshopping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping({"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("greeting","Welcome to spring web mvc");
		return mv;
	}
	
	@RequestMapping(value="/test")
	public ModelAndView test(@RequestParam("greeting")String greeting)
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("greeting",greeting);
		return mv;
	}
}
