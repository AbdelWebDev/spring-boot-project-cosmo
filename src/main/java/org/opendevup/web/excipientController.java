package org.opendevup.web;

import javax.validation.Valid;

import org.opendevup.dao.ExcipientRepository;
import org.opendevup.entities.Excipient;
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
@RequestMapping("/excipients")
public class excipientController {
	@Autowired

	private ExcipientRepository excipientRepository;

	@RequestMapping(value = "/Index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		Page<Excipient> pageExcipientRepository = excipientRepository.chercherExcipient("%" + mc + "%", new PageRequest(p, 5));
		int pagesCount = pageExcipientRepository.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		model.addAttribute("pages", pages);
		model.addAttribute("pageExcipients", pageExcipientRepository);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "excipients";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formProduit(Model model) {

		model.addAttribute("excipient", new Excipient());
		return "formExcipient";
	}

	@RequestMapping(value = "/SaveExcipient", method = RequestMethod.POST)
	public String save(@Valid Excipient pc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formExcipient";
		}

		excipientRepository.save(pc);

		return "redirect:Index";
	}
}
