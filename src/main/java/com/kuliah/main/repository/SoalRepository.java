package com.kuliah.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Soal;

public interface SoalRepository extends CrudRepository<Soal, Long>{
	
	public List<Soal> getAllSoal();
	public Soal getSoalByName(String name);
	public Soal getSoalById(String id);
	public Soal addSoal(Soal soal);
	public void deleteSoal(String id);
	public Soal findByNamaSoal(String nama);

}
