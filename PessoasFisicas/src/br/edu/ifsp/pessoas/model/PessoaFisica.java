package br.edu.ifsp.pessoas.model;

public class PessoaFisica extends Pessoa {
	
	private String cpf;

	public PessoaFisica(String nome, String lougadoro, String numero, String bairro, String cidade, String cpf) {
		super(nome, lougadoro, numero, bairro, cidade);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "PessoaFisica [cpf=" + cpf + ", nome=" + nome + ", lougadoro=" + lougadoro + ", numero=" + numero
				+ ", bairro=" + bairro + ", cidade=" + cidade + "]";
	}

}
