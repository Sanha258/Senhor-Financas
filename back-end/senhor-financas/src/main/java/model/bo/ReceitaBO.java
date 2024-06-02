package model.bo;

import java.util.ArrayList;

import model.dao.ReceitaDAO;
import model.vo.ReceitaVO;

public class ReceitaBO {

	//METODO PARA CADASTRAR RECEITA
	public ReceitaVO cadastrarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		if (receitaVO.getValor() < 0) {
			System.out.println("Rceita negativa, informar outro valor");
		} else {
			receitaVO = receitaDAO.cadastrarReceitaDAO(receitaVO);
		}
		if (receitaVO.getIdReceita() != 0) {
			System.out.println("Receita cadastrada com sucesso!");
		}
		
		return receitaVO;
	}
	
	//METODO PARA LISTAR TODAS AS RECEITAS
	public ArrayList<ReceitaVO> listarTodasAsReceitasBO(int idUsuario) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ArrayList<ReceitaVO> listaReceitas = receitaDAO.listarTodasAsReceitasDAO(idUsuario);
		return listaReceitas;
	}
	
	//METODO PARA PESQUISAR RECEITA
	public ReceitaVO consultarReceitaBO(int idReceita) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		ReceitaVO receita = receitaDAO.consultarReceitaDAO(idReceita);
		return receita;
	}
	
	//METODO PARA AUALIAZR RECEITA
	public boolean atualizarReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		boolean resultado = false;
		resultado = receitaDAO.atualizarReceitaDAO(receitaVO);

		return resultado;
	}

	//METODO PARA EXLUIR RECEITA
	public boolean excluirReceitaBO(ReceitaVO receitaVO) {
		ReceitaDAO receitaDAO = new ReceitaDAO();
		boolean resultado = false;
		resultado = receitaDAO.excluirReceitaDAO(receitaVO);
		
		return resultado;
	}
	
}
