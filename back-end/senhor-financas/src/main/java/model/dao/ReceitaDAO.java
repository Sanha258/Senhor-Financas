package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.vo.ReceitaVO;

public class ReceitaDAO {

	//METODO PARA CADASTRAR RECEITA
	public ReceitaVO cadastrarReceitaDAO(ReceitaVO receitaVO) {
		String query = "INSERT INTO receita (idusuario, descricao, valor, datareceita) VALUES (?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setInt(1, receitaVO.getIdUsuario());
			pstmt.setString(2, receitaVO.getDescricao());
			pstmt.setDouble(3, receitaVO.getValor());
			pstmt.setObject(4, receitaVO.getDataReceita());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				receitaVO.setIdReceita(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método cadastrarReceitaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return receitaVO;
	}
	
	//METODO PARA LISTAR TODAS AS RECEITAS
	public ArrayList<ReceitaVO> listarTodasAsReceitasDAO(int idUsuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ReceitaVO> listaReceitas = new ArrayList<ReceitaVO>();
		String query = "SELECT * FROM receita WHERE idusuario = " + idUsuario;
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ReceitaVO receitaVO = new ReceitaVO();
				receitaVO.setIdReceita(resultado.getInt(1));
				receitaVO.setIdUsuario(resultado.getInt(2));
				receitaVO.setDescricao(resultado.getString(3));
				receitaVO.setValor(resultado.getDouble(4));
				receitaVO.setDataReceita(resultado.getDate(5).toLocalDate());
				listaReceitas.add(receitaVO);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método listarTodasAsReceitasDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaReceitas;
	}
	
	//METODO PARA PESQUISAR RECEITA
	public ReceitaVO consultarReceitaDAO(int idReceita) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ReceitaVO receita = new ReceitaVO();
		String query = "SELECT * FROM receita WHERE idreceita = " + idReceita;
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				receita.setIdReceita(resultado.getInt(1));
				receita.setIdUsuario(resultado.getInt(2));
				receita.setDescricao(resultado.getString(3));
				receita.setValor(resultado.getDouble(4));
				receita.setDataReceita(resultado.getDate(5).toLocalDate());
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método consultarReceitaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return receita;
	}
	
	//METODO PARA AUALIAZR RECEITA
	public boolean atualizarReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "UPDATE receita SET descricao = '" + receitaVO.getDescricao() + "', valor = " + receitaVO.getValor() + 
				", datareceita = '" + receitaVO.getDataReceita() + "' WHERE idreceita = " + receitaVO.getIdReceita();
		
		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método atualizarReceitaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	//METODO PARA EXCLUIR RECEITA
	public boolean excluirReceitaDAO(ReceitaVO receitaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "DELETE FROM receita WHERE idreceita = " + receitaVO.getIdReceita();
		
		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método excluirReceitaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
}
