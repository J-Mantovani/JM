package principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Clientes implements Serializable {

	private String nome;
	private String email;
	private String senha;
	private ArrayList<Livros> carrinho;

	public Clientes(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public ArrayList<Livros> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(ArrayList<Livros> carrinho) {
		this.carrinho = carrinho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cliente: " + this.nome + " \n");
		builder.append("E-mail: " + this.email + " \n");
		return builder.toString();
	}

	public static Comparator<Clientes> COMPARE_BY_NAME = new Comparator<Clientes>() {
		public int compare(Clientes o1, Clientes o2) {
			return o1.getNome().compareTo(o2.getNome());
		}
	};

}
