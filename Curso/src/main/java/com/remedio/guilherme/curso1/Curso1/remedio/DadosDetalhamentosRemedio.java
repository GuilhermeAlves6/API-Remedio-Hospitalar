package com.remedio.guilherme.curso1.Curso1.remedio;

import java.time.LocalDate;

public record DadosDetalhamentosRemedio(
		Long id,
		
		String nome,
		
		Via via,
		
		String lote,
		
		int quantidade,
		
		LocalDate validade,
		
		Laboratorio laboratorio,
		
		Boolean ativo) {
	
	public DadosDetalhamentosRemedio(Remedio remedio){
		this(
				remedio.getId(),
				remedio.getNome(),
				remedio.getVia(),
				remedio.getLote(),
				remedio.getQuantidade(),
				remedio.getValidade(),
				remedio.getLaboratorio(),
				remedio.getAtivo());
		
	}
}
