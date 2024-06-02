package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.vo.DespesaVO;

public class DespesaDAO {

	//METODO PARA CADASTRAR DESPESA
	public DespesaVO cadastrarDespesaDAO(DespesaVO despesaVO) {
		String query = "INSERT INTO despesa (idusuario, descricao, valor, datavencimento, datapagamento) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setInt(1, despesaVO.getIdUsuario());
			pstmt.setString(2, despesaVO.getDescricao());
			pstmt.setDouble(3, despesaVO.getValor());
			pstmt.setObject(4, despesaVO.getDataVencimento());
			pstmt.setObject(5, despesaVO.getDataPagamento());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if (resultado.next()) {
				despesaVO.setIdDespesa(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método cadastrarDespesaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return despesaVO;
	}
	
	//METODO PARA LISTAR TODAS AS DESPESAS
	public ArrayList<DespesaVO> listarTodasAsDespesasDAO(int idUsuario) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<DespesaVO> listaDespesas = new ArrayList<DespesaVO>();
		String query = "SELECT * FROM despesa WHERE idusuario = " + idUsuario;
		
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				DespesaVO despesaVO = new DespesaVO();
				despesaVO.setIdDespesa(resultado.getInt(1));
				despesaVO.setIdUsuario(resultado.getInt(2));
				despesaVO.setDescricao(resultado.getString(3));
				despesaVO.setValor(resultado.getDouble(4));
				if (resultado.getObject(5) != null) {
					despesaVO.setDataVencimento(resultado.getDate(5).toLocalDate());
				}
				if (resultado.getObject(6) != null) {
					despesaVO.setDataPagamento(resultado.getDate(6).toLocalDate());
				}
				listaDespesas.add(despesaVO);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método listarTodasAsDespesasDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaDespesas;
	}
	
	//METODO PARA PESQUISAR DESPESA
	public DespesaVO consultarDespesaDAO(int idDespesa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		DespesaVO despesa = new DespesaVO();
		String query = "SELECT * FROM despesa WHERE iddespesa = " + idDespesa;
		
		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				despesa.setIdDespesa(resultado.getInt(1));
				despesa.setIdUsuario(resultado.getInt(2));
				despesa.setDescricao(resultado.getString(3));
				despesa.setValor(resultado.getDouble(4));
				if (resultado.getObject(5) != null) {
					despesa.setDataVencimento(resultado.getDate(5).toLocalDate());
				}
				if (resultado.getObject(6) != null) {
					despesa.setDataPagamento(resultado.getDate(6).toLocalDate());
				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método consultarDespesaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return despesa;
	}
	
	//METODO PARA AUALIAZR DESPESA
	public boolean atualizarDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		boolean retorno = false;
		String query = "UPDATE despesa SET descricao = ?, valor = ?, datavencimento = ?, datapagamento = ? WHERE iddespesa = ?";
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		
		try {
			pstmt.setString(1, despesaVO.getDescricao());
			pstmt.setDouble(2, despesaVO.getValor());
			pstmt.setObject(3, despesaVO.getDataVencimento());
			if (despesaVO.getDataPagamento() != null) {
				pstmt.setObject(4, despesaVO.getDataPagamento());
			} else {
				pstmt.setNull(4, Types.DATE);
			}
			pstmt.setInt(5, despesaVO.getIdDespesa());
			if (pstmt.executeUpdate() == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método atualizarDespesaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
	//METODO PARA EXCLUIR DESPESA
	public boolean excluirDespesaDAO(DespesaVO despesaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "DELETE FROM despesa WHERE iddespesa = " + despesaVO.getIdDespesa();
		
		try {
			if (stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar query no método excluirdDespesaDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
	
}
