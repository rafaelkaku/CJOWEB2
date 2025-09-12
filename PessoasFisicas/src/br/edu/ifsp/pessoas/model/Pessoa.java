package br.edu.ifsp.pessoas.model;

public class Pessoa {
	
	protected String nome;
	protected String lougadoro;
	protected String numero;
	protected String bairro;
	protected String cidade;
	
	public Pessoa(String nome, String lougadoro, String numero, String bairro, String cidade) {
		super();
		this.nome = nome;
		this.lougadoro = lougadoro;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLougadoro() {
		return lougadoro;
	}
	public void setLougadoro(String lougadoro) {
		this.lougadoro = lougadoro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}