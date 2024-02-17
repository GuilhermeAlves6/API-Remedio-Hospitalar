package com.remedio.guilherme.curso1.Curso1.remedio;

public record DadosCadastroRemedios(
	String nome,
	Via via,
	String lote,
	String quantidade,
	String validade,
	Laboratorio laboratorio) {

}
