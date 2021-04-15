package com.naorsantos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naorsantos.cursomc.domain.Pedido;
import com.naorsantos.cursomc.repositories.PedidoRepository;
import com.naorsantos.cursomc.services.execpitons.ObjectNotFoundExcepitons;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExcepitons(
				"Objeto não encontrado:" + id + ", Tipo" + Pedido.class.getName()));
	}
}
