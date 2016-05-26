package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;


import daoservice.DaoIServ;
import dao.DaoI;

import model.MyProduct;

@Controller
public class MyController {
	@Autowired
	DaoIServ dc;
	List<MyProduct> plist=null;
	@RequestMapping("/")
	public String myhome(){
		
		return "myhome";
	}
	
	@RequestMapping("/login")
	public String login(){
		
		return "login";
	}
	/*list all products*/
	@RequestMapping("/productlist")
		public String product()
	{
		
	
		return "productlist";
	}
	@RequestMapping("/p")
	public @ResponseBody String getValues()
	{	
		plist=dc.getAll();
		Gson gson=new Gson();
		String result= gson.toJson(plist);
		return result;
		//return "login";
		 
		}
	
	/*add product*/
	@RequestMapping("/add")
	 public ModelAndView getForm(@ModelAttribute("p") MyProduct p) {
 return new ModelAndView("myproduct");
}
	
	@RequestMapping(value={"/addProduct"},method=RequestMethod.POST)
	public ModelAndView adding(@ModelAttribute("p") MyProduct p)
	{
		
		dc.addproduct(p);;//calls service method
		

	return new ModelAndView("redirect:productlist");
	}
	
	 @RequestMapping("edit")
	 public ModelAndView editUser(@RequestParam int id, @ModelAttribute("p") MyProduct p,BindingResult result) {
	  MyProduct pObject = dc.getRowById(id);
	  return new ModelAndView("editproduct", "employeeObject", pObject);
	 }
	 @RequestMapping("update")
	 public ModelAndView updateUser(@ModelAttribute MyProduct p) {
	  dc.updateRow(p);
	  return new ModelAndView("redirect:productlist");
	 }
	 @RequestMapping("delete")
	 public ModelAndView deletepro(@RequestParam int id)
	 {
		 MyProduct po=dc.getRowById(id);
		 dc.deleterow(po);
		 return new ModelAndView("redirect:productlist");
		 
	 }
	 @RequestMapping(value = "/admin**", method = RequestMethod.GET)
		public ModelAndView adminPage() {

			ModelAndView model = new ModelAndView();
			model.addObject("title", "Spring Security Hello World");
			model.addObject("message", "This is protected page!");
			model.setViewName("admin");

			return model;

		}
	

}
