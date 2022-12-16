package br.com.rrs.system.escola.gamificacao.dominio.selo;

import br.com.rrs.system.escola.shared.dominio.CPF;

public class SeloNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SeloNaoEncontrado(CPF cpf) {
		super("Selo nao encontrado com CPF: " + cpf.getNumero());
	}

}
