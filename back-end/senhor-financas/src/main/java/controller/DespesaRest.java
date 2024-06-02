package controller;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.vo.DespesaVO;
import model.bo.DespesaBO;

@Path("/despesa")
public class DespesaRest {

	@POST
	@Path("/cadastrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DespesaVO cadastrarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.cadastrarDespesaBO(despesaVO);
	}
	
	@GET
	@Path("/listar/{idusuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DespesaVO> consultarTodasAsDespesasController(@PathParam("idusuario") int idUsuario) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.listarTodasAsDespesasBO(idUsuario);
	}
	
	@GET
	@Path("/pesquisar/{iddespesa}")
	@Produces(MediaType.APPLICATION_JSON)
	public DespesaVO consultarDespesaController(@PathParam("iddespesa") int idDespesa) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.consultarDespesaBO(idDespesa);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean atualizarDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.atualizarDespesaBO(despesaVO);
	}
	
	@DELETE
	@Path("/excluir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean excluirDespesaController(DespesaVO despesaVO) {
		DespesaBO despesaBO = new DespesaBO();
		return despesaBO.excluirDespesaBO(despesaVO);
	}
	
}
