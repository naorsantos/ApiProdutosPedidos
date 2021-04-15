package com.naorsantos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naorsantos.cursomc.domain.Categoria;
import com.naorsantos.cursomc.repositories.CategoriaRepository;
import com.naorsantos.cursomc.services.execpitons.ObjectNotFoundExcepitons;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExcepitons(
				"Objeto não encontrado:" + id + ", Tipo" + Categoria.class.getName()));
	}
}
