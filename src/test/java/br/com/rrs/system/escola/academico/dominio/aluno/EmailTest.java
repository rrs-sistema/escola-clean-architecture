package br.com.rrs.system.escola.academico.dominio.aluno;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class EmailTest {

	@Test
	void naoDeveririaCriarEmailsComEnderecosInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> new Email(null));
		
		assertThrows(IllegalArgumentException.class, () -> new Email("")); 
		
		assertThrows(IllegalArgumentException.class, () -> new Email("emailinvalido")); 
	}
	
	@Test
	void deveriaCriarEmailsComEnderecosValidos() {
		String emailUm = "rrs.sistema@gmail.com";
		Email email = new Email(emailUm);
		assertEquals(emailUm, email.getEndereco());
		
		String emailDois = "rivaldo.roberto@outlook.com";
		email = new Email(emailDois);
		assertEquals(emailDois, email.getEndereco());		
	}

}
