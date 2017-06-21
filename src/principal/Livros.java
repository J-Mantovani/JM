package principal;

import java.io.Serializable;
import java.util.Comparator;

public class Livros implements Serializable {
	private String nomeLivro;
	private String autor;
	private float valor;
	private int id;

	public Livros(String nome, String autor, float valor, int id) {
		super();
		this.nomeLivro = nome;
		this.autor = autor;
		this.valor = valor;
		this.id = id;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return String.format("ID:\t%d\nNome:\t%s\nAutor:\t%s\nPreço:\tR$%.2f\n", id, nomeLivro, autor, valor);
	}

	public static Comparator<Livros> COMPARE_BY_NAME = new Comparator<Livros>() {
		public int compare(Livros o1, Livros o2) {
			return o1.getNomeLivro().compareTo(o2.getNomeLivro());
		}
	};

}
