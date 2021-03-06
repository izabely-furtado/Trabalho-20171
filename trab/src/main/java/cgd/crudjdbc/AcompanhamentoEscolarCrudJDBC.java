package cgd.crudjdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import cdp.classesAnemicas.AcompanhamentoEscolar;
import cgd.conexao.ConectaPostgreSQL;

public class AcompanhamentoEscolarCrudJDBC {
		/*
		 * Objetivo: M�todo que salva um as no banco de dados
		 */
		public static int salvar(AcompanhamentoEscolar acompanhamento) {
			// abre a conexao com o banco de dados MYSQL
			Connection conexao = ConectaPostgreSQL.geraConexao();
			// Objeto para executar o SQL insert
			PreparedStatement insereSt = null;
			// SQL de inser��o
			String sqlAcompanhamento = "insert into acompanhamentoescolar(escola, ano1, ano2, ano3, ano4, ano5, ano6, ano7, serie1, serie2, serie3, serie4, serie5, serie6, serie7) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			try {
				
				int lastId=0;
				// recebe o SQL insert
				insereSt = conexao.prepareStatement(sqlAcompanhamento, Statement.RETURN_GENERATED_KEYS);
				// recebe o par�mtros do SQL insert
				insereSt.setString(1, acompanhamento.getEscola());
				insereSt.setInt(2, acompanhamento.getAno1());
				insereSt.setInt(3, acompanhamento.getAno2());
				insereSt.setInt(4, acompanhamento.getAno3());
				insereSt.setInt(5, acompanhamento.getAno4());
				insereSt.setInt(6, acompanhamento.getAno5());
				insereSt.setInt(7, acompanhamento.getAno6());
				insereSt.setInt(8, acompanhamento.getAno7());
				
				insereSt.setString(9, acompanhamento.getSerie1());
				insereSt.setString(10, acompanhamento.getSerie2());
				insereSt.setString(11, acompanhamento.getSerie3());
				insereSt.setString(12, acompanhamento.getSerie4());
				insereSt.setString(13, acompanhamento.getSerie5());
				insereSt.setString(14, acompanhamento.getSerie6());
				insereSt.setString(15, acompanhamento.getSerie7());
				// executa SQL insert
				insereSt.executeUpdate();
				
				ResultSet rs = insereSt.getGeneratedKeys();
				if (rs.next()) {
				   lastId = rs.getInt("id_acompanhamentoescolar");
				   return lastId;
				}
				
				
				
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao incluir o acompnhamento escolar mensagem:" + e);
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
		public static AcompanhamentoEscolar getAcompanhamentoEscolar(int idAcompanhamentoEscolar) {
			// abre conexao com o banco de dados
			Connection conexao = ConectaPostgreSQL.geraConexao();
			// executa o SQL no banco de dados
			Statement consulta = null;
			// cont�m os dados consultado da tabela
			ResultSet resultado = null;
			// objeto as
			AcompanhamentoEscolar acompanhamento = new AcompanhamentoEscolar();
			// consulta SQL
			String sql = "select distinct * from acompanhamentoescolar where id_acompanhamentoescolar=" + idAcompanhamentoEscolar;
			try {
				// consulta => objeto que executa o SQL no banco de dados
				consulta = conexao.createStatement();
				// resultado => objeto que cont�m os dados consultado da tabela
				// AcompanhamentoEscolar
				resultado = consulta.executeQuery(sql);
				
				while (resultado.next()) {
					acompanhamento.setEscola(resultado.getString("escola"));
					acompanhamento.setAno1(resultado.getInt("ano1"));
					acompanhamento.setAno2(resultado.getInt("ano2"));
					acompanhamento.setAno3(resultado.getInt("ano3"));
					acompanhamento.setAno4(resultado.getInt("ano4"));
					acompanhamento.setAno5(resultado.getInt("ano5"));
					acompanhamento.setAno6(resultado.getInt("ano6"));
					acompanhamento.setAno7(resultado.getInt("ano7"));
					acompanhamento.setSerie1(resultado.getString("serie1"));
					acompanhamento.setSerie2(resultado.getString("serie2"));
					acompanhamento.setSerie3(resultado.getString("serie3"));
					acompanhamento.setSerie4(resultado.getString("serie4"));
					acompanhamento.setSerie5(resultado.getString("serie5"));
					acompanhamento.setSerie6(resultado.getString("serie6"));
					acompanhamento.setSerie7(resultado.getString("serie7"));
					/*
					for (int i = 1; i<7; i++) {
						Integer ano = resultado.getInt("anos" + i);
						Integer serie = resultado.getInt("serie"  + i);
						anos.add(ano);
						series.add(serie);	
					}
					situacao.setAnos(anos);
					situacao.setSeries(series);
					*/
					// insere o acesso a servi�o na lista
					return acompanhamento;
					
				}
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao buscar uma situacao de acolhimento: " + e);
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
			return acompanhamento;
		}

		/*
		 * Objetivo: M�todo que lista todos os ass do banco de dados
		 */
		public static List<AcompanhamentoEscolar> listar() {
			// abre conexao com o banco de dados
			Connection conexao = ConectaPostgreSQL.geraConexao();
			// variavel lista de ass
			List<AcompanhamentoEscolar> acessos = new ArrayList<AcompanhamentoEscolar>();
			// executa o SQL no banco de dados
			Statement consulta = null;
			// cont�m os dados consultado da tabela
			ResultSet resultado = null;
			// objeto as
			//AcompanhamentoEscolar as = null;
			// consulta SQL
			String sql = "select distinct * from AcompanhamentoEscolar";
			try {
				// consulta => objeto que executa o SQL no banco de dados
				consulta = conexao.createStatement();
				// resultado => objeto que cont�m os dados consultado da tabela
				// AcompanhamentoEscolar
				resultado = consulta.executeQuery(sql);
				// L� cada as
				//List<Integer> anos = new ArrayList<Integer>();
				//List<Integer> series = new ArrayList<Integer>();
				
				while (resultado.next()) {
					AcompanhamentoEscolar situacao = new AcompanhamentoEscolar();
					situacao.setEscola(resultado.getString("escola"));
					situacao.setAno1(resultado.getInt("ano1"));
					situacao.setAno2(resultado.getInt("ano2"));
					situacao.setAno3(resultado.getInt("ano3"));
					situacao.setAno4(resultado.getInt("ano4"));
					situacao.setAno5(resultado.getInt("ano5"));
					situacao.setAno6(resultado.getInt("ano6"));
					situacao.setAno7(resultado.getInt("ano7"));
					situacao.setSerie1(resultado.getString("serie1"));
					situacao.setSerie2(resultado.getString("serie2"));
					situacao.setSerie3(resultado.getString("serie3"));
					situacao.setSerie4(resultado.getString("serie4"));
					situacao.setSerie5(resultado.getString("serie5"));
					situacao.setSerie6(resultado.getString("serie6"));
					situacao.setSerie7(resultado.getString("serie7"));
					/*
					for (int i = 1; i<7; i++) {
						Integer ano = resultado.getInt("anos" + i);
						Integer serie = resultado.getInt("serie"  + i);
						anos.add(ano);
						series.add(serie);	
					}
					
					situacao.setAnos(anos);
					situacao.setSeries(series);
					*/
					// insere o as na lista
					acessos.add(situacao);
						
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
		public static boolean excluir(AcompanhamentoEscolar as) {
			// abre a conexao com o banco de dados PostGresql
			Connection conexao = ConectaPostgreSQL.geraConexao();
			// Objeto para executar o SQL delete
			PreparedStatement excluiSt = null;
			// SQL de exclus�o do as
			String sql = "delete from AcompanhamentoEscolar where id=?";
			try {
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
		public static boolean alterar(AcompanhamentoEscolar acompanhamento) {
			// abre a conexao com o banco de dados MYSQL
			Connection conexao = ConectaPostgreSQL.geraConexao();
			// Objeto para executar o SQL update
			PreparedStatement insereSt = null;
			// SQL de inser��o
			String sql = "update AcompanhamentoEscolar set escola=?, ano1=?, ano2=?, ano3=?, ano4=?, ano5=?, ano6=?, ano7=?, serie1=?, serie2=?, serie3=?, serie4=?, serie5=?, serie6=?, serie7=?";
			try {
				// recebe o SQL update
				insereSt = conexao.prepareStatement(sql);
				insereSt.setString(1, acompanhamento.getEscola());
				insereSt.setInt(2, acompanhamento.getAno1());
				insereSt.setInt(3, acompanhamento.getAno2());
				insereSt.setInt(4, acompanhamento.getAno3());
				insereSt.setInt(5, acompanhamento.getAno4());
				insereSt.setInt(6, acompanhamento.getAno5());
				insereSt.setInt(7, acompanhamento.getAno6());
				insereSt.setInt(8, acompanhamento.getAno7());
				
				insereSt.setString(9, acompanhamento.getSerie1());
				insereSt.setString(10, acompanhamento.getSerie2());
				insereSt.setString(11, acompanhamento.getSerie3());
				insereSt.setString(12, acompanhamento.getSerie4());
				insereSt.setString(13, acompanhamento.getSerie5());
				insereSt.setString(14, acompanhamento.getSerie6());
				insereSt.setString(15, acompanhamento.getSerie7());
				
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
