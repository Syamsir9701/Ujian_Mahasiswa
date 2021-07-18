package com.kuliah.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.Mahasiswa;

public interface MahasiswaRepository extends CrudRepository<Mahasiswa, Long> {
	public void deleteMahasiswa(String id);
	public List<Mahasiswa> getAllMahasiswa();
	
	public Mahasiswa addMahasisssswa(Mahasiswa mahasiswa);
	public Mahasiswa getMahasiswaByName(String name);
	public Mahasiswa getMahasisswaById(String id);
	public Mahasiswa findByNamaMahasiswa(String nama);
	public Mahasiswa findByIdMahasiswa(Long id);
	public Mahasiswa findByNim(String nim);

}
