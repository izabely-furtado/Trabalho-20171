package cgd.crudjdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cdp.classesAnemicas.DadosPessoais;
import cgd.conexao.ConectaPostgreSQL;

public class DadosPessoaisCrudJDBC {
	/*
	 * Objetivo: M�todo que salva um as no banco de dados
	 */
	public static int salvar(DadosPessoais dados) {
		// abre a conexao com o banco de dados MYSQL
		Connection conexao = ConectaPostgreSQL.geraConexao();
		// Objeto para executar o SQL insert
		PreparedStatement insereSt = null;
		// SQL de inser��o
		
		String sqlDadosPessoais = "";
		
		try {
			
			int lastId=0;
			
			sqlDadosPessoais = "insert into DadosPessoais(nome, sexo, responsavel, parentesco, contato,  nis, datanasc)"
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			// recebe o SQL insert
			insereSt = conexao.prepareStatement(sqlDadosPessoais, Statement.RETURN_GENERATED_KEYS);

			// recebe o par�mtros do SQL insert
			insereSt.setString(1, dados.getNome());
			insereSt.setInt(2, dados.getSexo());
			insereSt.setString(3, dados.getResponsavel());
			insereSt.setString(4, dados.getParentesco());
			insereSt.setString(5, dados.getContato());
			insereSt.setString(6, dados.getNis());
			insereSt.setString(7, dados.getDatNasc());

			// executa SQL insert
			insereSt.executeUpdate();
			
			ResultSet rs = insereSt.getGeneratedKeys();
			if (rs.next()) {
			   lastId = rs.getInt("id_dadospessoais");
			   return lastId;
			}

			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao incluir os dados pessoais" + e);
		} finally {
			try {
				// fecha conexao com o banco
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				throw new RuntimeException("Erro ao fechar a opera��o de inser��o" + e);
			}
		}
		return 0;
	}

	/*
	 * Objetivo: M�todo que lista todos os ass do banco de dados
	 */
	public static DadosPessoais getDadosPessoais(int idDadosPessoais) {
		// abre conexao com o banco de dados
		Connection conexao = ConectaPostgreSQL.geraConexao();
		// executa o SQL no banco de dados
		Statement consulta = null;
		// cont�m os dados consultado da tabela
		ResultSet resultado = null;
		// objeto as
		DadosPessoais dados = new DadosPessoais();
		// consulta SQL
		String sql = "select distinct * from DadosPessoais where id_dadospessoais=" + idDadosPessoais;
		try {
			// consulta => objeto que executa o SQL no banco de dados
			consulta = conexao.createStatement();
			// resultado => objeto que cont�m os dados consultado da tabela
			// DadosPessoais
			resultado = consulta.executeQuery(sql);
			// L� cada dados
			while (resultado.next()) {
				dados.setContato(resultado.getString("contato"));
				dados.setDatNasc(resultado.getString("datanasc"));
				dados.setNis(resultado.getString("nis"));
				dados.setNome(resultado.getString("nome"));
				dados.setParentesco(resultado.getString("parentesco"));
				dados.setResponsavel(resultado.getString("responsavel"));
				dados.setSexo(resultado.getInt("sexo"));
				
				// insere o acesso a servi�o na lista
				return dados;

			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar os dados pessoais do aluno: " + e);
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				throw new RuntimeException("Erro ao fechar a conexao - Dados Pessoais" + e);
			}
		}
		// retorna lista de ass
		return dados;
	}

	/*
	 * Objetivo: M�todo que lista todos os ass do banco de dados
	 */
	public static List<DadosPessoais> listar() {
		// abre conexao com o banco de dados
		Connection conexao = ConectaPostgreSQL.geraConexao();
		// variavel lista de ass
		List<DadosPessoais> acessos = new ArrayList<DadosPessoais>();
		// executa o SQL no banco de dados
		Statement consulta = null;
		// cont�m os dados consultado da tabela
		ResultSet resultado = null;
		// objeto as
		// DadosPessoais as = null;
		// consulta SQL
		String sql = "select distinct * from DadosPessoais";
		try {
			// consulta => objeto que executa o SQL no banco de dados
			consulta = conexao.createStatement();
			// resultado => objeto que cont�m os dados consultado da tabela
			// DadosPessoais
			resultado = consulta.executeQuery(sql);
			// L� cada as

			while (resultado.next()) {
				DadosPessoais dados = new DadosPessoais();
				dados.setContato(resultado.getString("contato"));
				dados.setDatNasc(resultado.getString("datNasc"));
				dados.setNis(resultado.getString("nis"));
				dados.setNome(resultado.getString("nome"));
				dados.setParentesco(resultado.getString("parentesco"));
				dados.setResponsavel(resultado.getString("responsavel"));
				dados.setSexo(resultado.getInt("sexo"));
				// insere o as na lista
				acessos.add(dados);

			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar um acesso a servi�os: " + e);
		} finally {
			try {
				consulta.close();
				resultado.close();
				conexao.close();
			} catch (Throwable e) {
				throw new RuntimeException("Erro ao fechar a conexao " + e);
			}
		}
		// retorna lista de ass
		return acessos;
	}

	/*
	 * Objetivo: M�todo que exclui um acesso a servi�os no banco de dados
	 */
	public static boolean excluir(DadosPessoais as) {
		// abre a conexao com o banco de dados PostGresql
		Connection conexao = ConectaPostgreSQL.geraConexao();
		// Objeto para executar o SQL delete
		PreparedStatement excluiPSt = null;
		PreparedStatement excluiSt = null;
		// SQL de exclus�o do as
		String sql = "delete from DadosPessoais where id=?";
		// SQL de exclus�o do as
		String presql = "delete from DadosPessoais where id=?";
		try {
			// recebe o SQL delete para endere�o
			excluiPSt = conexao.prepareStatement(presql);
			// recebe o par�mtros do SQL insert
			excluiPSt.setInt(1, as.getId());
			// executa SQL delete
			excluiPSt.executeUpdate();

			// recebe o SQL delete para as
			excluiSt = conexao.prepareStatement(sql);
			// recebe o par�mtros do SQL insert
			excluiSt.setInt(1, as.getId());
			// executa SQL delete
			excluiSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir as.mensagem:" + e);
		} finally {
			try {
				// fecha conexao com o banco
				excluiPSt.close();
				excluiSt.close();
				conexao.close();
			} catch (Throwable e) {
				throw new RuntimeException("Erro ao fechar a opera��o de exclusao" + e);
			}
		}
	}

	/*
	 * Objetivo: M�todo que altera um acesso a servi�os no banco de dados
	 */
	public static boolean alterar(DadosPessoais dados) {
		// abre a conexao com o banco de dados MYSQL
		Connection conexao = ConectaPostgreSQL.geraConexao();
		// Objeto para executar o SQL update
		PreparedStatement insereSt = null;
		// SQL de inser��o
		String sql = "update DadosPessoais set contato=?, datNasc=?, nis=?, nome=?, "
				+ "	parentesco=?, responsavel=?, sexo=?";
		try {
			// recebe o SQL update
			insereSt = conexao.prepareStatement(sql);

			// recebe o par�mtros do SQL insert
			insereSt.setString(1, dados.getContato());
			insereSt.setString(2, dados.getDatNasc());
			insereSt.setString(2, dados.getNis());
			insereSt.setString(2, dados.getNome());
			insereSt.setString(2, dados.getParentesco());
			insereSt.setString(2, dados.getResponsavel());
			insereSt.setInt(2, dados.getSexo());
			// executa SQL update
			insereSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao alterar a situa��o de acolhimento - mensagem:" + e);
		} finally {
			try {
				// fecha conexao com o banco
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				throw new RuntimeException("Erro ao fechar a opera��o de alteracao" + e);
			}
		}
	}

}
