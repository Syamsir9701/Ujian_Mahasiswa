package com.kuliah.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuliah.main.entity.LembarPenilaian;
import com.kuliah.main.entity.Pertanyaan;
import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.entity.Soal;
import com.kuliah.main.entity.UjianHasil;
import com.kuliah.main.repository.DosenRepository;
import com.kuliah.main.repository.MahasiswaRepository;
import com.kuliah.main.repository.MataKuliahRepository;
import com.kuliah.main.repository.PlotMataKuliahRepository;
import com.kuliah.main.repository.SoalRepository;

@Controller
public class PlotMataKuliahPage {
	
	@Autowired
	PlotMataKuliahRepository plotmatakuliahRepo;
	
	@Autowired
	MataKuliahRepository matakuliahRepo;
	
	@Autowired
	MahasiswaRepository mahasiswaRepo;
	
	@Autowired
	DosenRepository dosenRepo;
	
	@Autowired
	SoalRepository soalRepo;	
	
	@PostMapping("/plotmatakuliah/view")
	public String addPlotMataKuliah(@ModelAttribute PlotMataKuliah plotmatakuliah, Model model) {
		this.plotmatakuliahRepo.addPlotMataKuliah(plotmatakuliah);
		model.addAttribute("listPlotMataKuliah",plotmatakuliahRepo.getAllPlotMataKuliah());
		
		return "redirect:/plotmatakuliah/view";
	}
	
	@GetMapping("/plotmatakuliah/view")
	public String viewIndexPlotMataKuliah(Model model) {
		model.addAttribute("listPlotMataKuliah", plotmatakuliahRepo.getAllPlotMataKuliah());
		model.addAttribute("active", 6);
		return "view_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/add")
	public String viewAddPlotMataKuliah(Model model) {
		
		model.addAttribute("plotmatakuliah", new PlotMataKuliah());
		model.addAttribute("listMataKuliah", matakuliahRepo.getAllMataKuliah());
		model.addAttribute("listMahasiswa", mahasiswaRepo.getAllMahasiswa());
		model.addAttribute("listDosen", dosenRepo.getAllDosen());
		model.addAttribute("listSoal", soalRepo.getAllSoal());
		
		return "add_plotmatakuliah";
	}
	
	@GetMapping("/plotmatakuliah/ujian{id}")
	public String viewUjian (@PathVariable String id, Model model) {
		List<Pertanyaan> lstPertanyaan = new ArrayList<Pertanyaan>();
		
		PlotMataKuliah plotmatakuliah = plotmatakuliahRepo.getPlotMataKuliahById(id);
		
		for (int x = 0 ; x < plotmatakuliah.getLstSoal().size(); x++) {
			
			for (int y = 0 ; y < plotmatakuliah.getLstSoal().get(x).getLstPertanyaan().size(); y++) {
				lstPertanyaan.add(plotmatakuliah.getLstSoal().get(x).getLstPertanyaan().get(y));
			}
		}
		
		List<String> lsJawaban = new ArrayList<String>(lstPertanyaan.size());
		
		UjianHasil ujian = new UjianHasil();
		ujian.setPertanyaan(lstPertanyaan);
		ujian.setJawaban(lsJawaban);
		model.addAttribute("ujian", ujian);
		model.addAttribute("idplotmata", id);
		
		return"view_ujian";
	}

	@PostMapping("/plotmatakuliah/ujian/hasil")
	public String viewHasilUjian(@ModelAttribute UjianHasil ujian,@RequestParam("idPlotmata") String id, Model model) {
		List<Pertanyaan> lstPertanyaan = new ArrayList<Pertanyaan>();
		
		PlotMataKuliah plotmatakuliah = plotmatakuliahRepo.getPlotMataKuliahById(id);
		for (int x = 0 ; x < plotmatakuliah.getLstSoal().size(); x++) {
			
			for (int y = 0 ; y < plotmatakuliah.getLstSoal().get(x).getLstPertanyaan().size();y++) {
			lstPertanyaan.add(plotmatakuliah.getLstSoal().get(x).getLstPertanyaan().get(y));
			
			}
		}
		
		ujian.setPertanyaan(lstPertanyaan);
		
		LembarPenilaian lembar = hitungNilai(ujian);
		model.addAttribute("nilai",lembar);
		
		return "view_ujianhasil";
	}
	
	private LembarPenilaian hitungNilai (UjianHasil ujian) {
		LembarPenilaian nilai = new LembarPenilaian();
		
		for(int x=0 ; x  < ujian.getJawaban().size();x++) {
			if(ujian.getJawaban().get(x).equalsIgnoreCase(ujian.getPertanyaan().get(x).getJawabanBenar())) {
			nilai.setBenar(nilai.getBenar()+1);
			}else {
				nilai.setSalah(nilai.getSalah()+1);
			
			}
		}
		
		nilai.setNilai(nilai.getBenar()/ujian.getPertanyaan().size()*100);
		
		return nilai;
	}
}

