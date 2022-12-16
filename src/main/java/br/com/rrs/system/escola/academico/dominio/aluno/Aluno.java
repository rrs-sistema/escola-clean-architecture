package br.com.rrs.system.escola.academico.dominio.aluno;

import java.util.ArrayList;
import java.util.List;

import br.com.rrs.system.escola.shared.dominio.CPF;

public class Aluno {
	
	private String nome;
	private CPF cpf;
	private Email email;
	private List<Telefone> telefones = new ArrayList<>();
	
	public Aluno(CPF cpf, String nome, Email email) { 
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}
	
	public void adicionarTelefone(String ddd, String numero) {
		if(telefones.size() == 2) {
			throw new NumerosDeTelefoneUltrapassado();
		}
		this.telefones.add(new Telefone(ddd, numero));
	}

	public String getNome() {
		return nome;
	}

	public CPF getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email.getEndereco();
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	
}
