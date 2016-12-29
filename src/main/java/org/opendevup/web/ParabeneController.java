package org.opendevup.web;

import javax.validation.Valid;

import org.opendevup.dao.ParabeneRepository;
import org.opendevup.entities.Parabene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parabenes")
public class ParabeneController {
	@Autowired

	private ParabeneRepository parabeneRepository;

	@Value("${dir.images}")
	private String dirImages;

	@RequestMapping(value = "/Index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		Page<Parabene> pageParabeneRepository = parabeneRepository.chercherParabene("%" + mc + "%", new PageRequest(p, 5));
		int pagesCount = pageParabeneRepository.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		model.addAttribute("pages", pages);
		model.addAttribute("pageParabenes", pageParabeneRepository);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "parabenes";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formProduit(Model model) {

		model.addAttribute("parabene", new Parabene());
		return "formParabene";
	}

	@RequestMapping(value = "/SaveParabene", method = RequestMethod.POST)
	public String save(@Valid Parabene pc, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formParabene";
		}

		parabeneRepository.save(pc);

		return "redirect:Index";
	}
}
