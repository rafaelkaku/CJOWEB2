package br.edu.ifsp.pessoas.model;

public class PessoaJuridica extends Pessoa {

	private String cnpj;

	public PessoaJuridica(String nome, String lougadoro, String numero, String bairro, String cidade, String cnpj) {
		super(nome, lougadoro, numero, bairro, cidade);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [cnpj=" + cnpj + ", nome=" + nome + ", lougadoro=" + lougadoro + ", numero=" + numero
				+ ", bairro=" + bairro + ", cidade=" + cidade + "]";
	}

}
