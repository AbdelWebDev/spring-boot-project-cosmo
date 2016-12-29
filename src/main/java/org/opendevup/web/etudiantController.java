package org.opendevup.web;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.opendevup.dao.EtudiantRepository;
import org.opendevup.entities.Etudiant;
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
@RequestMapping(value = "/etudiants")
public class etudiantController {
	@Autowired

	private EtudiantRepository etudiantRepository;
	
	@Value("${dir.images}")
	private String dirImages;   
	
	@RequestMapping(value = "/Index")
	public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int p,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		Page<Etudiant> pageEtudiants = etudiantRepository.chercherEtudiants("%" + mc + "%", new PageRequest(p, 5));
		int pagesCount = pageEtudiants.getTotalPages();

		int[] pages = new int[pagesCount];
		for (int i = 0; i < pagesCount; i++)
			pages[i] = i;

		model.addAttribute("pages", pages);
		model.addAttribute("pageEtudiants", pageEtudiants);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);

		return "etudiants";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String formEtudiant(Model model) {

		model.addAttribute("etudiant", new Etudiant());
		return "formEtudiant";
	}

	@RequestMapping(value = "/SaveEtudiant", method = RequestMethod.POST)
	public String save(@Valid Etudiant et, BindingResult bindingResult,
			@RequestParam(name = "picture") MultipartFile file) throws Exception, IOException {
		if (bindingResult.hasErrors()) {
			return "formEtudiant";
		}
		
		if (!(file.isEmpty())){
			System.out.println("--------------------"+""+System.getProperty(dirImages+file.getOriginalFilename()));
			et.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(dirImages+file.getOriginalFilename()));
			//file.transferTo(new File(System.getProperty(dirImages+file.getOriginalFilename())));
		}
		
		etudiantRepository.save(et);

		return "redirect:Index";
	}

	
	@Secured("ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteEtudiant(Model model) {

		model.addAttribute("etudiant", new Etudiant());
		return "formEtudiant";
	}

}