package br.com.rrs.system.escola.academico.dominio.aluno;

import java.util.List;

import br.com.rrs.system.escola.shared.dominio.CPF;

public interface RepositorioDeAlunos {

	void matricular(Aluno aluno);
	Aluno buscarPorCPF(CPF cpf);
	List<Aluno> listarTodosAlunosMatriculados();
	
}
