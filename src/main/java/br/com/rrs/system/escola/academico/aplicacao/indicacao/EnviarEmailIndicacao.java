package br.com.rrs.system.escola.academico.aplicacao.indicacao;

import br.com.rrs.system.escola.academico.dominio.aluno.Aluno;

public interface EnviarEmailIndicacao {

	void enviarPara(Aluno indicado);
}
