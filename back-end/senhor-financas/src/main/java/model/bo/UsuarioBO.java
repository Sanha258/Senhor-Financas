package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	//METODO DE CADASTRO DE USUÁRIO
	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarCadastroUsuarioDAO(usuarioVO)) {
			System.out.println("Usuário já cadastrado!");
		} else {
			usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		if (usuarioVO.getIdUsuario() != 0) {
			System.out.println("Cadastro realizado com sucesso!");
		}
		return usuarioVO;
	}
	
	//METODO DE LOGIN DO USUARIO
	public UsuarioVO logarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioVO usuario = new  UsuarioVO();
		usuario = usuarioDAO.logarUsuarioDAO(usuarioVO);
		if (usuario.getIdUsuario() != 0) {
			System.out.println("Usuário logado com sucesso!");
		}
		return usuario;
	}
	
}
