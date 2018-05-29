package net.pk.onlineshopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.pk.onlineshopping.exception.ProductNotFoundException;
import net.pk.shoppingbackend.dao.CategoryDAO;
import net.pk.shoppingbackend.dao.ProductDAO;
import net.pk.shoppingbackend.dao.ReviewDAO;
import net.pk.shoppingbackend.dto.Category;
import net.pk.shoppingbackend.dto.Product;
import net.pk.shoppingbackend.dto.ProductReview;

@Controller
public class PageController {

	private static Logger logger=LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ReviewDAO reviewDAO;
	@RequestMapping({"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Home");
		logger.info("Inside page controller index method -Info");
		logger.debug("Inside page controller index method -debug");
		//passing the list of category
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickHome",true);
		return mv;
	}
	
	@RequestMapping({"/contact"})
	public ModelAndView contact()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Contact Us");
		mv.addObject("userClickContact",true);
		return mv;
	}
	@RequestMapping({"/about"})
	public ModelAndView about()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","About Us");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	/*@RequestMapping({"/register"})
	public ModelAndView register()
	{
		ModelAndView mv=new ModelAndView("page");
		//mv.addObject("title","register");
		logger.info("Page Controller membership called!");
	//	mv.addObject("userClickAbout",true);
		return mv;
	}*/
	
	//method to load all products based and based on category
	@RequestMapping(value="/show/all/products")
	public ModelAndView showAllProducts()
	{
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","All Products");
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("userClickAllProducts",true);
		return mv;
	}
	
	@RequestMapping(value="/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id)
	{
		ModelAndView mv=new ModelAndView("page");
		
		
		//categoryDAO to fetch a single category
		Category category=null;
		category=categoryDAO.get(id);
		mv.addObject("title",category.getName());
		mv.addObject("categories",categoryDAO.list());
		mv.addObject("category",category);
		mv.addObject("userClickCategoryProducts",true);
		return mv;
	}
	//viewing a single product
	@RequestMapping(value="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id)  throws ProductNotFoundException
	{
		ModelAndView mv=new ModelAndView("page");
		Product product=productDAO.get(id);
		ProductReview productReview = new ProductReview();
		if(product==null)
			throw new ProductNotFoundException();
		//update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("review",productReview);
		mv.addObject("userClickShowProduct",true);
		return mv;
		
	}
	/*@RequestMapping(value="/submit/review",method=RequestMethod.POST)
	public String handleReviewSubmission(@ModelAttribute("review") ProductReview mReview){
		logger.info(mReview.toString());
		
		reviewDAO.add(mReview);
		
		return "redirect:/contact";
	}*/
	@RequestMapping(value="/view/product/{id}/reviews")
	public ModelAndView viewReviews(@PathVariable int id)  throws ProductNotFoundException
	{
		ModelAndView mv=new ModelAndView("page");
		Product product=productDAO.get(id);
		//ProductReview productReview = new ProductReview();
		if(product==null)
			throw new ProductNotFoundException();
		
		mv.addObject("title","All Reviews");
		mv.addObject("product",product);
		mv.addObject("reviews",reviewDAO.getReviewList(id));
		//mv.addObject("review",productReview);
		mv.addObject("userClickShowReviews",true);
		return mv;
		
	}
	@RequestMapping({"/login"})
	public ModelAndView login(@RequestParam(name="error",required=false)String error,@RequestParam(name="logout",required=false)String logout)
	{
		ModelAndView mv=new ModelAndView("login");
	
		if(error!=null){
			mv.addObject("message","Invalid Username and Password!");
		}
		if(logout!=null){
			mv.addObject("logout","User has successfully logged out!");
		}
		mv.addObject("title","Login");
		return mv;
	}
	@RequestMapping({"/access-denied"})
	public ModelAndView accessDenied()
	{
		ModelAndView mv=new ModelAndView("error");
		mv.addObject("title","403-Access Denied");
		mv.addObject("errorTitle","Aha! caught you.");
		mv.addObject("errorDescription","You are not authorized to view this page");
		logger.info("Page Controller membership called!");
	//	mv.addObject("userClickAbout",true);
		return mv;
	}
	@RequestMapping(value="/perform-logout")
	public String logout(HttpServletRequest request,HttpServletResponse response){
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

		
		return "redirect:/login?logout";
	}
	
}
