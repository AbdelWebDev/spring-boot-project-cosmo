package org.opendevup.web;

import javax.validation.Valid;

import org.opendevup.dao.ConservateurRepository;
import org.opendevup.entities.Conservateur;
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
@RequestMapping("/conservateurs")
public class conservateurController {
	@Autowired

	private ConservateurRepository conservateurRepository;

	@RequestMapping(value = "/Index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		Page<Conservateur> pageConservateurRepository = conservateurRepository.chercherConservateur("%" + mc + "%", new PageRequest(p, 5));
		int pagesCount = pageConservateurRepository.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		model.addAttribute("pages", pages);
		model.addAttribute("pageConservateurs", pageConservateurRepository);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "conservateurs";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formProduit(Model model) {

		model.addAttribute("conservateur", new Conservateur());
		return "formConservateur";
	}

	@RequestMapping(value = "/SaveConservateur", method = RequestMethod.POST)
	public String save(@Valid Conservateur pc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formConservateur";
		}

		conservateurRepository.save(pc);

		return "redirect:Index";
	}
}
