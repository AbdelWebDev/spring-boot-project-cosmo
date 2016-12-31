package org.opendevup.web;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.opendevup.dao.UserRepository;
import org.opendevup.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/users")
public class userController {
	@Autowired

	private UserRepository UserRepository;
	
	@Value("${dir.images}")
	private String dirImages;   
	
	@RequestMapping(value = "/Index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		Page<User> pageUsers = UserRepository.chercherUsers("%" + mc + "%", new PageRequest(p, 5));
		int pagesCount = pageUsers.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		model.addAttribute("pages", pages);
		model.addAttribute("pageUsers", pageUsers);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "users";
	}

	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formUser(Model model) {
		
		model.addAttribute("User", new User());
	
		return "formUser";
	}

	@RequestMapping(value = "/SaveUser", method = RequestMethod.POST)
	public String save(@Valid User user, BindingResult bindingResult,
			@RequestParam(name = "picture") MultipartFile file) throws Exception, IOException {
		if (bindingResult.hasErrors()) {
			return "formUser";
		}
		
		if (!(file.isEmpty())){
			System.out.println("--------------------"+""+System.getProperty(dirImages+file.getOriginalFilename()));
			user.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(dirImages+file.getOriginalFilename()));
			//file.transferTo(new File(System.getProperty(dirImages+file.getOriginalFilename())));
		}
		
		UserRepository.save(user);
		return "redirect:Index";
	}

	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteUser(Model model) {

		model.addAttribute("User", new User());
		return "formUser";
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//	public String login(Model model, String error, String logout) {
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
//
//		if (logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
//
//		return "login";
//	}


	@RequestMapping(value = {"/welcome" }, method = RequestMethod.GET)
	public String welcome() {
		return "redirect:principes/Index";
	}

}
