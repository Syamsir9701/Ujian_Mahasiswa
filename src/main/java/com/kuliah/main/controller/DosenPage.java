package com.kuliah.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Dosen;
import com.kuliah.main.repository.DosenRepository;


@Controller
public class DosenPage {
	
	@Autowired
	DosenRepository dosenRepo;
	

	@GetMapping("/dosen/view")
	public String viewIndexDosen(Model model) {
		
		model.addAttribute("listDosen", dosenRepo.getAllDosen());
		model.addAttribute("active",2);
		return "view_dosen";
	}
	
	@PostMapping("/dosen/view")
	public String addDosen(@ModelAttribute Dosen dosen, Model model) {
		this.dosenRepo.addDosen(dosen);
		model.addAttribute("listDosen",dosenRepo.getAllDosen());
		
		return "redirect:/dosen/view";
	}
	
	
	@GetMapping("/dosen/add")
	public String viewAddDosen(Model model) {
		model.addAttribute("dosen", new Dosen());
		return "add_dosen";
	}

	@GetMapping("/dosen/update/{id}")
	public String viewUpdateDosen(@PathVariable String id, Model model) {
		
		Dosen Dosen = dosenRepo.getDosenById(id);
		model.addAttribute("dosen", Dosen);	
		
		return "add_dosen";
	}
		
		@GetMapping("/dosen/delete/{id}")
		public String deleteDosen(@PathVariable String id, Model model) {
			
			this.dosenRepo.deleteDosen(id);
			model.addAttribute("listDosen",dosenRepo.getAllDosen());
			
			return "redirect:/dosen/view";
	
}
}
