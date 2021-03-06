package com.naorsantos.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.naorsantos.cursomc.domain.Cidade;
import com.naorsantos.cursomc.domain.Cliente;
import com.naorsantos.cursomc.domain.Endereco;
import com.naorsantos.cursomc.domain.enums.Perfil;
import com.naorsantos.cursomc.domain.enums.TipoCliente;
import com.naorsantos.cursomc.dto.ClienteDTO;
import com.naorsantos.cursomc.dto.ClienteNewDTO;
import com.naorsantos.cursomc.repositories.ClienteRepository;
import com.naorsantos.cursomc.repositories.EnderecoRepository;
import com.naorsantos.cursomc.security.UserSS;
import com.naorsantos.cursomc.services.execpitons.AuthorizationExcepiton;
import com.naorsantos.cursomc.services.execpitons.DataIntegratedExcepitons;
import com.naorsantos.cursomc.services.execpitons.ObjectNotFoundExcepitons;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
		throw new AuthorizationExcepiton("Acesso Negado");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundExcepitons(
				"Objeto não encontrado:" + id + ", Tipo" + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratedExcepitons("Não é possivel excluir Clientes que tenha pedidos");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()),pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogadouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli,cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!=null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
		
	}
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
