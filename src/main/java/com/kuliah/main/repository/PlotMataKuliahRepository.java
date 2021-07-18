package com.kuliah.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kuliah.main.entity.PlotMataKuliah;
import com.kuliah.main.entity.Soal;

public interface PlotMataKuliahRepository extends CrudRepository<PlotMataKuliah, Long> {
	public List<PlotMataKuliah> getAllPlotMataKuliah();
	public PlotMataKuliah addPlotMataKuliah(PlotMataKuliah plotmatakuliah);
	public PlotMataKuliah getPlotMataKuliahById(String id);
	public void deletePlotMataKuliah(String id);

}
