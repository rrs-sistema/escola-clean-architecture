package br.com.rrs.system.escola.academico.dominio.aluno;

import br.com.rrs.system.escola.shared.dominio.CPF;

// Usando o padrão de projeto BUILD
public class FabricaDeAluno {
	
	private Aluno aluno;
	
	public FabricaDeAluno comNomeCPFEmail(String nome, String cpf, String email) {
		this.aluno = new Aluno(new CPF(cpf), nome, new Email(email)); 
		return this;
	}
	
	public FabricaDeAluno comTelefone(String ddd, String numero) {
		this.aluno.adicionarTelefone(ddd, numero);
		return this;
	}
	
	public Aluno criar() {
		return this.aluno;
	}

	public Aluno getAluno() {
		return aluno;
	}
	
}	
