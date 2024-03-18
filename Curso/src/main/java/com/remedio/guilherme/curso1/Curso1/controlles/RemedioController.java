package com.remedio.guilherme.curso1.Curso1.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.remedio.guilherme.curso1.Curso1.remedio.DadosAtualizarRemedio;
import com.remedio.guilherme.curso1.Curso1.remedio.DadosCadastroRemedios;
import com.remedio.guilherme.curso1.Curso1.remedio.DadosDetalhamentosRemedio;
import com.remedio.guilherme.curso1.Curso1.remedio.DadoslistagemRemedio;
import com.remedio.guilherme.curso1.Curso1.remedio.Remedio;
import com.remedio.guilherme.curso1.Curso1.remedio.RemedioRepository;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
	
	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentosRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedios dados, UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		repository.save(remedio);
		
		var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentosRemedio(remedio));
	}
	@GetMapping
	public ResponseEntity<java.util.List<DadoslistagemRemedio>> listar (){
		var lista = repository.findAllByAtivoTrue().stream().map(DadoslistagemRemedio::new).toList();
		
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentosRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		var remedio = repository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentosRemedio(remedio));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir (@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar (@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.inativar();	
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity<Void> Reativar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		remedio.reativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentosRemedio> detalhar(@PathVariable Long id) {
		var remedio = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentosRemedio(remedio));
	}
}
