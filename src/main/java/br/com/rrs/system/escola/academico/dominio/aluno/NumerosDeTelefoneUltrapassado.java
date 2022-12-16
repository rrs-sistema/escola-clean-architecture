package br.com.rrs.system.escola.academico.dominio.aluno;

public class NumerosDeTelefoneUltrapassado extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NumerosDeTelefoneUltrapassado() {
		super("Numero maximo de telefones ja atingido");
	}

}
