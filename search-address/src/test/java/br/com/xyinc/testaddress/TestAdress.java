package br.com.xyinc.testaddress;

import java.io.IOException;
import java.util.List;

import org.junit.*;


import br.com.xyinc.searchcontroller.*;
import br.com.xyinc.entities.*;

public class TestAdress {
	String cep;
	String endereco;
	SearchController controller = new SearchController();

	//Classe que realizamos os testes com JUnit
	@Test
	public void getAdressByCep() throws IOException {
		//Teste feito com cep válido
		this.cep = "38415150"; 

		List<Address> buscaCep = controller.getByCep(cep);

		for (Address item : buscaCep ){
			System.out.println(item.getLogradouro());
			System.out.println(item.getBairro());
			System.out.println(item.getLocalidade());
			
		}

	}
	
	@Test
	public void getAdressByCepInvalido() throws IOException {
		//Teste feito com cep inválido
		this.cep = "31215214"; 

		List<Address> buscaCep = controller.getByCep(cep);

		for (Address item : buscaCep ){
			System.out.println(item.getLogradouro());
			System.out.println(item.getBairro());
			System.out.println(item.getLocalidade());
			
		}

	}
	
	@Test
	public void getAdressByAddress() throws IOException {
		//Teste com Endereço completo
		this.cep = "Cleso da Cunha Resende";

		List<String> buscaCep = controller.getByAddress(cep);

		for (String item : buscaCep ){
			System.out.println(item);
		}

	}
	
	@Test
	public void getAdressByAddressIncompleto() throws IOException {
		//Teste com Endereço icompleto
		this.cep = "joao naves";

		List<String> buscaCep = controller.getByAddress(cep);

		for (String item : buscaCep ){
			System.out.println(item);
		}

	}
	

}
