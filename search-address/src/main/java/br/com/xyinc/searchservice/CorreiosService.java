package br.com.xyinc.searchservice;

import java.util.*;
import java.io.IOException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import br.com.xyinc.entities.Address;
import br.com.xyinc.utils.Utils;




public class CorreiosService {

	//lista para armazenar os endere�os do retorno do cep.
	private List<Address> listEnderecos;

	//lista de string que ir� armazenar a lista de cep.
	private List<String> listAddress;

	//Busca os endere�os pelo n�mero do CEP.
	public List<Address> getByCep(String cep) throws IOException{


		listEnderecos = new ArrayList<Address>();


		//mapeamento dos parametros que ser� passado na requisi��o
		Map<String, String> query = new HashMap<String, String>();  
		query.put("CEP", cep);
		query.put("Metodo", "listaLogradouro");
		query.put("TipoConsulta", "cep");
		query.put("StartRow", "1");
		query.put("EndRow", "10");

		//Faz uma requisi��o no site do correios (www.buscacep.com.br) com Json, passando os parametros mapeados, 
		//requisi��o dever� ser do tipo post.
		//Armazena o retorno em uma variavel doc.
		Document doc = Jsoup.connect(Utils.adressCorreios)
				.data(query)
				.header("Origin", "http://www.buscacep.correios.com.br")
				.header("Referer", "http://www.buscacep.correios.com.br")
				.post();

		//Acessa o retorno do doc e percorre o resultado buscando as informa��es dos endere�os
		//Armazena os resultados na lista de endere�os criadas e retorna a mesma para que outras classes possam acessar.
		Elements elements = doc.select("table").eq(2);  
		Elements rows = elements.select("tr");

		Iterator<Element> rowIterator = rows.iterator();

		while(rowIterator.hasNext()) {
			Address enderecos = new Address();

			Element element = rowIterator.next();

			Elements logradouro = element.children().select("td").eq(0);
			enderecos.setLogradouro(logradouro.text());
			Elements bairro = element.children().select("td").eq(1);
			enderecos.setBairro(bairro.text());
			Elements cidade = element.children().select("td").eq(2);
			Elements estado = element.children().select("td").eq(3);
			StringBuilder sbLocalidade = new StringBuilder();
			sbLocalidade.append(cidade.text());
			sbLocalidade.append("/");
			sbLocalidade.append(estado.text());
			enderecos.setLocalidade(sbLocalidade.toString());
			Elements codigopostal = element.children().select("td").eq(4);
			enderecos.setCEP(codigopostal.text());

			listEnderecos.add(enderecos);
		}



		return listEnderecos;


	}


	//Busca o Cep pelo logradouro.
	public List<String> getByAdress(String address) throws IOException{

		listAddress = new ArrayList<String>();

		//mapeamento dos parametros que ser� passado na requisi��o
		Map<String, String> query = new HashMap<String, String>();  

		query.put("relaxation",address);
		query.put("TipoCep","ALL");
		query.put("semelhante","N");
		query.put("cfm","1");
		query.put("Metodo","listaLogradouro");
		query.put("TipoConsulta","relaxation");
		query.put("StartRow","1");
		query.put("EndRow","10");

		//Faz uma requisi��o no site do correios (www.buscacep.com.br) com Json, passando os parametros mapeados, 
		//requisi��o dever� ser do tipo post.
		//Armazena o retorno em uma variavel doc.
		Document doc = Jsoup.connect(Utils.adressCorreios)
				.timeout(20000)
				.data(query)
				.header("Origin", "http://www.buscacep.correios.com.br")
				.header("Referer", "http://www.buscacep.correios.com.br")
				.post();

		//Acessa o retorno do doc e percorre o resultado buscando as informa��es de Cep de acordo com o endere�o passado.
		//Armazena os resultados na lista criada e retorna a mesma para que outras classes possam acessar
		Elements elements = doc.select("table").eq(2);  
		Elements rows = elements.select("tr");

		Iterator<Element> rowIterator = rows.iterator();

		while(rowIterator.hasNext()) {
			Address enderecos = new Address();

			Element element = rowIterator.next();

			Elements codigopostal = element.children().select("td").eq(4);

			enderecos.setCEP(codigopostal.text());

			listAddress.add(enderecos.getCEP());
		}



		return listAddress;


	}


}
