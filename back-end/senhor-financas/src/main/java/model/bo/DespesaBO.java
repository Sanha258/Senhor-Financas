package model.bo;

import java.util.ArrayList;

import model.dao.DespesaDAO;
import model.vo.DespesaVO;

public class DespesaBO {

	//METODO PARA CADASTRAR DESPESA
	public DespesaVO cadastrarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		if (despesaVO.getValor() < 0) {
			System.out.println("Despesa negativa, informar outro valor");
		} else {
			despesaVO = despesaDAO.cadastrarDespesaDAO(despesaVO);
		}
		if (despesaVO.getIdDespesa() != 0) {
			System.out.println("Despesa cadastrada com sucesso!");
		}
		return despesaVO;
	}
	
	//METODO PARA LISTAR TODAS AS DESPESAS
	public ArrayList<DespesaVO> listarTodasAsDespesasBO(int idUsuario) {
		DespesaDAO despesaDAO = new DespesaDAO();
		ArrayList<DespesaVO> listaDespesas = despesaDAO.listarTodasAsDespesasDAO(idUsuario);
		return listaDespesas;
	}
	
	//METODO PARA PESQUISAR DESPESA
	public DespesaVO consultarDespesaBO(int idDespesa) {
		DespesaDAO despesaDAO = new DespesaDAO();
		DespesaVO despesa = despesaDAO.consultarDespesaDAO(idDespesa);
		return despesa;
	}
	
	//METODO PARA AUALIAZR DESPESA
	public boolean atualizarDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		boolean resultado = false;
		resultado = despesaDAO.atualizarDespesaDAO(despesaVO);

		return resultado;
	}

	//METODO PARA EXLUIR DESPESA
	public boolean excluirDespesaBO(DespesaVO despesaVO) {
		DespesaDAO despesaDAO = new DespesaDAO();
		boolean resultado = false;
		resultado = despesaDAO.excluirDespesaDAO(despesaVO);
		
		return resultado;
	}
	
}
