package com.remedio.guilherme.curso1.Curso1.remedio;

import java.time.LocalDate;

public record DadoslistagemRemedio(Long id,String nome,Via via,String lote,Laboratorio laboratorio, LocalDate validade) {

	public DadoslistagemRemedio(Remedio remedio){
		this(remedio.getId(),remedio.getNome(),remedio.getVia(), remedio.getLote(), remedio.getLaboratorio(), remedio.getValidade());	
   }
}