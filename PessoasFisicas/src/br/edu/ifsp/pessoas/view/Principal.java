package br.edu.ifsp.pessoas.view;

import br.edu.ifsp.pessoas.model.Pessoa;
import br.edu.ifsp.pessoas.model.PessoaJuridica;
import br.edu.ifsp.pessoas.model.PessoaFisica;


public class Principal {

	public static void main(String[] args) {
		Pessoa cpf1 = new PessoaFisica("Rafael", "Pedro Vaz Dias", "28", "Vila Ferraz", "Campos do Jordão", "999.999.999-99");
		System.out.println(cpf1);
		Pessoa cnpj1 = new PessoaJuridica("Estação Flor", "Floriano Santa Clara", "185", "Vila Ferraz", "Campos do Jordão", "99.999.999/9999-99");
		System.out.println(cnpj1);
	}

}
