package br.com.rrs.system.escola;

import br.com.rrs.system.escola.academico.aplicacao.aluno.matricular.MatricularAluno;
import br.com.rrs.system.escola.academico.aplicacao.aluno.matricular.MatricularAlunoDto;
import br.com.rrs.system.escola.academico.dominio.aluno.LogDeAlunoMatriculado;
import br.com.rrs.system.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import br.com.rrs.system.escola.gamificacao.aplicacao.GeraSeloAlunoNovato;
import br.com.rrs.system.escola.gamificacao.infra.selo.RepositorioDeSelosEmMemoria;
import br.com.rrs.system.escola.shared.dominio.evento.PublicadorDeEventos;

public class MatricularAlunoPorLinhaDeComando {

	public static void main(String[] args) {
		String nome = "Fulando da Silva";
		String cpf = "123.456.785-00";
		String email = "fulando@gmail.com";
		
		PublicadorDeEventos publicador = new PublicadorDeEventos();
		publicador.adicionar(new LogDeAlunoMatriculado());
		publicador.adicionar(new GeraSeloAlunoNovato(new RepositorioDeSelosEmMemoria()));
		
		MatricularAluno matricular = new MatricularAluno(new RepositorioDeAlunosEmMemoria(), publicador);
		matricular.executa(new MatricularAlunoDto(nome, cpf, email));
	}

}
