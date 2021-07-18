package com.kuliah.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kuliah.main.entity.Soal;
import com.kuliah.main.repository.PertanyaanRepository;
import com.kuliah.main.repository.SoalRepository;


@Controller
public class SoalPage {
	
	@Autowired
	SoalRepository soalRepo;
	
	@Autowired
	PertanyaanRepository pertanyaanRepo;
	
	@GetMapping("/soal/update/{id}")
	public String viewUpdateSoal(@PathVariable String id, Model model) {
		Soal soal = soalRepo.getSoalById(id);
		model.addAttribute("soal", soal);
		
		return "add_soal";
	}
	
	@PostMapping("/soal/view")
	public String addSoal(@ModelAttribute Soal soal, Model model) {
		this.soalRepo.addSoal(soal);
		model.addAttribute("listSoal", soalRepo.getAllSoal());
		
		return"redirect:/soal/view";
	}
	
	
	@GetMapping("/soal/view")
	public String viewIndexSoal(Model model) {
		model.addAttribute("listSoal", soalRepo.getAllSoal());
		model.addAttribute("active", 5);
		return "view_soal";
	}

	
	@GetMapping("/soal/add")
	public String viewAddSoal(Model model) {
		model.addAttribute("soal",new Soal());
		model.addAttribute("listPertaanyaan", pertanyaanRepo.getAllPertanyaan());
		
		return "add_soal";
	}
	
	@GetMapping("/soal/delete/{id}")
	public String deleteSoal(@PathVariable String id, Model model) {
		this.soalRepo.deleteSoal(id);
		model.addAttribute("listSoal", soalRepo.getAllSoal());
		
		return"redirect:/soal/view";
	}
}

