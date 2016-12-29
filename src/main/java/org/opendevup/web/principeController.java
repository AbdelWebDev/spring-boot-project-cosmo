package org.opendevup.web;

import javax.validation.Valid;

import org.opendevup.dao.PrincipeRepository;
import org.opendevup.entities.Principe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/principes")
public class principeController {
	@Autowired

	private PrincipeRepository principeRepository;

	@RequestMapping(value = "/Index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		Page<Principe> pagePrincipeRepository = principeRepository.chercherPrincipe("%" + mc + "%",
				new PageRequest(p, 5));
		int pagesCount = pagePrincipeRepository.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		model.addAttribute("pages", pages);
		model.addAttribute("pagePrincipes", pagePrincipeRepository);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "principes";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formProduit(Model model) {

		model.addAttribute("principe", new Principe());
		return "formPrincipe";
	}

	@RequestMapping(value = "/SavePrincipe", method = RequestMethod.POST)
	public String save(@Valid Principe pc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formPrincipe";
		}

		principeRepository.save(pc);

		return "redirect:Index";
	}
}
