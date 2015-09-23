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

	//Metódo usado para acessar a classe de serviço e capturar o resultado dos endereços buscados por cep
	//Em Path utilizados o nome do método e o parametro para acessarmos no navegador ex.: (localhos:8080/search-address/rest/address/getByCep/38415273)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getByCep/{cep}")
	public List<Address> getByCep(@PathParam(value = "cep") String cep) throws IOException  

	{

		return correioService.getByCep(cep);
	}
	//Metódo usado para acessar a classe de serviço e capturar o resultado dos cep buscados pelo endereço
	//Em Path utilizados o nome do método e o parametro para acessarmos no navegador ex.: (localhos:8080/search-address/rest/address/getByAddress/joao naves)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getByAddress/{address}")
	public List<String> getByAddress(@PathParam(value = "address") String address) throws IOException   

	{
		return correioService.getByAdress(address);
	}

}
