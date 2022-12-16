package br.com.rrs.system.escola.academico.aplicacao.aluno.matricular;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.rrs.system.escola.academico.dominio.aluno.Aluno;
import br.com.rrs.system.escola.academico.dominio.aluno.LogDeAlunoMatriculado;
import br.com.rrs.system.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import br.com.rrs.system.escola.shared.dominio.CPF;
import br.com.rrs.system.escola.shared.dominio.evento.PublicadorDeEventos;

class MatricularAlunoTest {

	@Test
	void alunoDeveriaSerPersistido() {
		RepositorioDeAlunosEmMemoria repositorio = new RepositorioDeAlunosEmMemoria();
		
		PublicadorDeEventos publicador = new PublicadorDeEventos();
		publicador.adicionar(new LogDeAlunoMatriculado());
		
		MatricularAluno useCase = new MatricularAluno(repositorio, publicador);
		
		MatricularAlunoDto dados = new MatricularAlunoDto("Fulano", "123.456.785-00", "fulando@email.com");
		useCase.executa(dados);
		
		Aluno encontrado = repositorio.buscarPorCPF(new CPF("123.456.785-00"));
		
		assertEquals("Fulano", encontrado.getNome());
		assertEquals("123.456.785-00", encontrado.getCpf().getNumero());
		assertEquals("fulando@email.com", encontrado.getEmail());
	}

}
