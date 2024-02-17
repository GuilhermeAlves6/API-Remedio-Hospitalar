package com.remedio.guilherme.curso1.Curso1.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remedio.guilherme.curso1.Curso1.remedio.DadosCadastroRemedios;
import com.remedio.guilherme.curso1.Curso1.remedio.Remedio;
import com.remedio.guilherme.curso1.Curso1.remedio.RemedioRepository;

@RestController
@RequestMapping("/remedios")
public class RemedioController {
	
	@Autowired
	private RemedioRepository repository;
	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroRemedios dados) {
		repository.save(new Remedio(dados));	
	}
}
