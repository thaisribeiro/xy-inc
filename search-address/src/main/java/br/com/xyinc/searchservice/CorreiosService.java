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

	//lista para armazenar os endereços do retorno do cep.
	private List<Address> listEnderecos;

	//lista de string que irá armazenar a lista de cep.
	private List<String> listAddress;

	//Busca os endereços pelo número do CEP.
	public List<Address> getByCep(String cep) throws IOException{


		listEnderecos = new ArrayList<Address>();


		//mapeamento dos parametros que será passado na requisição
		Map<String, String> query = new HashMap<String, String>();  
		query.put("CEP", cep);
		query.put("Metodo", "listaLogradouro");
		query.put("TipoConsulta", "cep");
		query.put("StartRow", "1");
		query.put("EndRow", "10");

		//Faz uma requisição no site do correios (www.buscacep.com.br) com Json, passando os parametros mapeados, 
		//requisição deverá ser do tipo post.
		//Armazena o retorno em uma variavel doc.
		Document doc = Jsoup.connect(Utils.adressCorreios)
				.data(query)
				.header("Origin", "http://www.buscacep.correios.com.br")
				.header("Referer", "http://www.buscacep.correios.com.br")
				.post();

		//Acessa o retorno do doc e percorre o resultado buscando as informações dos endereços
		//Armazena os resultados na lista de endereços criadas e retorna a mesma para que outras classes possam acessar.
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

		//mapeamento dos parametros que será passado na requisição
		Map<String, String> query = new HashMap<String, String>();  

		query.put("relaxation",address);
		query.put("TipoCep","ALL");
		query.put("semelhante","N");
		query.put("cfm","1");
		query.put("Metodo","listaLogradouro");
		query.put("TipoConsulta","relaxation");
		query.put("StartRow","1");
		query.put("EndRow","10");

		//Faz uma requisição no site do correios (www.buscacep.com.br) com Json, passando os parametros mapeados, 
		//requisição deverá ser do tipo post.
		//Armazena o retorno em uma variavel doc.
		Document doc = Jsoup.connect(Utils.adressCorreios)
				.timeout(20000)
				.data(query)
				.header("Origin", "http://www.buscacep.correios.com.br")
				.header("Referer", "http://www.buscacep.correios.com.br")
				.post();

		//Acessa o retorno do doc e percorre o resultado buscando as informações de Cep de acordo com o endereço passado.
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
