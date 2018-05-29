package net.pk.onlineshopping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.pk.onlineshopping.util.FileUploadUtility;
import net.pk.onlineshopping.validator.ProductValidator;
import net.pk.shoppingbackend.dao.CategoryDAO;
import net.pk.shoppingbackend.dao.ProductDAO;
import net.pk.shoppingbackend.dao.ReviewDAO;
import net.pk.shoppingbackend.dto.Category;
import net.pk.shoppingbackend.dto.Product;
import net.pk.shoppingbackend.dto.ProductReview;

@Controller
@RequestMapping({"/submit","/manage"})
public class ManagementController {

	@Autowired
	private ReviewDAO reviewDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private ProductDAO productDAO;
	
	public int count=0;
	
	private static final Logger logger=LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false) String operation){
		
		
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		Product nProduct=new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		mv.addObject("product",nProduct);
		
		if(operation!=null)
		{
			if(operation.equals("product")){
				mv.addObject("message","Product Submitted Sucessfully!");
			}
		}
		
		return mv;
	}
	
	//Handling product submission
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult results,Model model,HttpServletRequest request){
		
		new ProductValidator().validate(mProduct,results);
		if(results.hasErrors())
		{
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for Product Submission!");
			
			return "page";
		}
		
		
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
			
		}
		logger.info(mProduct.toString());
		productDAO.add(mProduct);
		return "redirect:/manage/products?operation=product";
	}
	
	//returning categories for all request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.list();
	}
	
	
	
	
	
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView submitReview(@RequestParam(name="operation",required=false) String operation){
		ModelAndView mv=new ModelAndView("page");
	
		mv.addObject("userClickShowProduct",true);
	
		
		if(operation!=null){
			if(operation.equals("success")){
				mv.addObject("message","Review submitted successfully");
			}
		}
		
		
		return mv;
		
	}
	
//handling review submission
	@RequestMapping(value="/{id}/review",method=RequestMethod.POST)
	public String handleReviewSubmission(@ModelAttribute("review") ProductReview mReview,@PathVariable int id){
		logger.info(mReview.toString());
		
		Product product=productDAO.get(id);
		String s=mReview.getReviewClass();
		if(s.equals("positive")){
		
			System.out.println(mReview.getReviewClass());
			product.setPositiveCount(product.getPositiveCount()+1);
			productDAO.update(product);
		}
	//	System.out.println(mReview.getReviewClass());
		if(s.equals("negative"))
		{
			System.out.println(mReview.getReviewClass());
			product.setNegativeCount(product.getNegativeCount()+1);
			productDAO.update(product);
		}
        
		if(reviewDAO.add(mReview))
			count=7;
		System.out.println(count);
		return "redirect:/show/{id}/product?operation=success";
	}
}