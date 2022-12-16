package br.com.rrs.system.escola.gamificacao.dominio.selo;

import java.util.List;

import br.com.rrs.system.escola.shared.dominio.CPF;

public interface RepositorioDeSelos {

	void adicionar(Selo selo);
	List<Selo> selosDoAlunoDeCPF(CPF cpf);
	
}
