package br.com.rrs.system.escola.academico.infra.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rrs.system.escola.academico.dominio.aluno.Aluno;
import br.com.rrs.system.escola.academico.dominio.aluno.AlunoNaoEncontrado;
import br.com.rrs.system.escola.academico.dominio.aluno.Email;
import br.com.rrs.system.escola.academico.dominio.aluno.RepositorioDeAlunos;
import br.com.rrs.system.escola.academico.dominio.aluno.Telefone;
import br.com.rrs.system.escola.shared.dominio.CPF;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAlunos {

	private final Connection connection;

	public RepositorioDeAlunosComJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void matricular(Aluno aluno) {
		String sql = "INSERT INTO Aluno VALUES(?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, aluno.getCpf().getNumero());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
			ps.execute();

			sql = "INSERT INTO Telefone VALUES(?, ?)";
			ps = connection.prepareStatement(sql);
			for (Telefone telefone : aluno.getTelefones()) {
				ps.setString(1, telefone.getDdd());
				ps.setString(2, telefone.getNumero());
				ps.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Aluno buscarPorCPF(CPF cpf) {
		try {
			String sql = "SELECT id, nome, email FROM Aluno WHERE cpf = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cpf.getNumero());

			ResultSet rs = ps.executeQuery();
			boolean enconctrou = rs.next();
			if (!enconctrou) {
				throw new AlunoNaoEncontrado(cpf);
			}

			String nome = rs.getString("nome");
			Email email = new Email(rs.getString("email"));
			Aluno encontrado = new Aluno(cpf, nome, email);

			Long id = rs.getLong("id");
			sql = "SELECT ddd, numero FROM Telefone WHERE aluno_id = ?";
			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			enconctrou = rs.next();
			while (rs.next()) {
				String ddd = rs.getString("ddd");
				String numero = rs.getString("numero");
				encontrado.adicionarTelefone(ddd, numero);
			}
			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<Aluno> listarTodosAlunosMatriculados() {
		List<Aluno> alunos = new ArrayList<>();
		try {
			String sql = "SELECT id, nome, email FROM Aluno";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				CPF cpf = new CPF(rs.getString("cpf"));
				Email email = new Email(rs.getString("email"));
				Aluno aluno = new Aluno(cpf, nome, email);
				
				Long id = rs.getLong("id");
				sql = "SELECT ddd, numero FROM Telefone WHERE aluno_id = ?";
				PreparedStatement psTelefone = connection.prepareStatement(sql);
				psTelefone.setLong(1, id);
				ResultSet rsTelefone = psTelefone.executeQuery();
				while (rsTelefone.next()) {
					String ddd = rsTelefone.getString("ddd");
					String numero = rsTelefone.getString("numero");
					aluno.adicionarTelefone(ddd, numero);
				}	
				alunos.add(aluno);
			}
			
			return alunos;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
