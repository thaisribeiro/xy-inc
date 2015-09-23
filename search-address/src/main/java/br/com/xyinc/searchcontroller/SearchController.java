package br.com.xyinc.searchcontroller;

import java.io.IOException;
import java.util.List;


import br.com.xyinc.searchservice.CorreiosService;
import br.com.xyinc.entities.Address;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/address")
public class SearchController {

	private CorreiosService correioService = new CorreiosService();

	//Met�do usado para acessar a classe de servi�o e capturar o resultado dos endere�os buscados por cep
	//Em Path utilizados o nome do m�todo e o parametro para acessarmos no navegador ex.: (localhos:8080/search-address/rest/address/getByCep/38415273)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getByCep/{cep}")
	public List<Address> getByCep(@PathParam(value = "cep") String cep) throws IOException  

	{

		return correioService.getByCep(cep);
	}
	//Met�do usado para acessar a classe de servi�o e capturar o resultado dos cep buscados pelo endere�o
	//Em Path utilizados o nome do m�todo e o parametro para acessarmos no navegador ex.: (localhos:8080/search-address/rest/address/getByAddress/joao naves)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getByAddress/{address}")
	public List<String> getByAddress(@PathParam(value = "address") String address) throws IOException   

	{
		return correioService.getByAdress(address);
	}

}
