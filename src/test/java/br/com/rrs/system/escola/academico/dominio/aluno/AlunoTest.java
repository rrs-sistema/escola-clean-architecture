package br.com.rrs.system.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.rrs.system.escola.shared.dominio.CPF;

class AlunoTest {

	private Aluno aluno;
	
	@BeforeEach
	void beforeEach() {
		this.aluno = new Aluno(
				new CPF("123.456.789-00"), 
				"Fulando da Silva", 
				new Email("fulano@email.com"));
	}
	
	@Test
	void deveriaPermitirAdicionar1Telefone() {
		this.aluno.adicionarTelefone("41", "982441615");
		assertEquals(1, this.aluno.getTelefones().size());
	}
	
	@Test
	void deveriaPermitirAdicionar2Telefone() {
		this.aluno.adicionarTelefone("41", "982441615");
		this.aluno.adicionarTelefone("41", "992442871");
		assertEquals(2, this.aluno.getTelefones().size());
	}
	
	@Test
	void deveriaPermitirAdicionar3Telefone() {
		assertThrows(NumerosDeTelefoneUltrapassado.class, () -> {
			this.aluno.adicionarTelefone("41", "982441615");
			this.aluno.adicionarTelefone("41", "992442871");
			this.aluno.adicionarTelefone("41", "998894554");
		});
	}
}
