package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;

import externo.Menu;

public class menuPrincipal {

	private static Scanner teclado = new Scanner(System.in);
	private static ArrayList<Livros> livros = new ArrayList<Livros>();
	private static ArrayList<Clientes> clientes = new ArrayList<Clientes>();
	private static ArrayList<Pedidos> pedidos = new ArrayList<Pedidos>();

	public static void main(String[] args) throws IOException {
		// Menu principal
		String[] menuPrincipal = { "0", "Sair", "1", "Menu administrativo", "2", "Menu cliente" };
		Menu menu = new Menu(menuPrincipal);
		//

		do {
			System.out.println("\n\t--Menu Principal--\n");
			menu.drawMenu();
			switch (menu.getInput()) {
			case 0:
				System.out.println("\n\n\tObrigado por visitar nossa loja!");
				return;
			case 1:
				menuAdministrativo();
				break;
			case 2:
				menuCliente();
				break;
			}
		} while (true);
	}

	public static void menuAdministrativo() {
		// Menu Administrativo
		String[] menuAdmin = { "0", "Voltar", "1", "Cadastrar livro", "2", "Listar todos os livros", "3",
				"Listar Todos os clientes", "4", "Listar todos os pedidos" };
		Menu menuAd = new Menu(menuAdmin);
		//
		int op;
		System.out.println("\n\t--Menu Administrativo--\n");
		menuAd.drawMenu();
		op = menuAd.getInput();
		switch (op) {
		case 0:
			return;
		case 1:// Cadastrar Livro'
			cadastraLivro();
			break;
		case 2:// Listar todos os livros
			listaLivro();
			break;
		case 3:// Listar todos os clientes
			listarClientes();
			break;
		case 4:// Listar todos os pedidos
			for (Pedidos pi : pedidos) {
				System.out.println(pi);
			}
			break;
		}

	}

	public static void menuCliente() {
		System.out.println("\n\t--Menu do Cliente--\n");
		// Menu Cliente
		String[] menuCliente = { "0", "Voltar", "1", "Cadastrar Cliente", "2", "Login do cliente" };
		Menu menuCli = new Menu(menuCliente);
		//
		do {
			menuCli.drawMenu();
			switch (menuCli.getInput()) {
			case 0:
				return;
			case 1:// Cadastrar Cliente
				cadastracliente();
				break;
			case 2:// Login do Cliente
				loginclientes();
				break;
			}
		} while (true);
	}

	public static void menuCompras() {
		// Menu Compras
		String[] menuCompras = { "0", "Logoff", "1", "Procurar livros por título", "2", "Procurar livros lista geral",
				"3", "Fechar pedido", "4", "Listar pedidos" };
		Menu menuComp = new Menu(menuCompras);
		//
		System.out.println("\n\t--Menu de Compras--\n");
		menuComp.drawMenu();
		do {
			switch (menuComp.getInput()) {
			case 0:// Logoff
				return;
			case 1:// Procura livro por título
				procuraTitulo();
				break;
			case 2:// Procura por lista geral
				procuraGeral();
				break;
			case 3:// Fecha pedido
				fechaPedido();
				break;
			case 4:// Lista o pedido
			}
		} while (true);
	}

	private static void cadastraLivro() {
		String nome;
		String autor;
		float valor;
		Random ran = new Random();
		int id;
		System.out.println("\n\t--Cadastrar Livros--\n");
		System.out.print("Nome do livro: ");
		nome = teclado.nextLine();
		System.out.print("Nome do autor: ");
		autor = teclado.nextLine();
		System.out.print("Preço do livro: ");
		valor = teclado.nextInt();
		id = ran.nextInt(1000);
		teclado.nextLine();

		for (Livros li : livros) {
			if (li.getId() == id) {
				id = (ran.nextInt(1000) + 1);
			}
		}
		livros.add(new Livros(nome, autor, valor, id));
	}

	private static void listaLivro() {

		System.out.println("\n\t--Lista de Livros--\n");
		Collections.sort(livros, Livros.COMPARE_BY_NAME);
		for (Livros li : livros) {
			System.out.println(li);
		}
	}

	private static void procuraTitulo() {
		String liv;
		System.out.println("\n\t--Procura por título--\n");
		System.out.print("Digite o nome do livro desejado: ");
		liv = teclado.nextLine();
		teclado.nextLine();
		for (Livros li : livros) {
			if (li.getNomeLivro().equalsIgnoreCase(liv)) {
				System.out.println(li);
				break;
			}
		}
	}

	private static void procuraGeral() {
		int id;
		GregorianCalendar data = new GregorianCalendar();
		listaLivro();
		System.out.print("Digite o ID do livro desejado: ");
		id = teclado.nextInt();
		for (Livros li : livros) {
			if (li.getId() == id) {
				pedidos.add(new Pedidos(id, null, loginclientes(), data));
				break;
			}
		}
	}

	private static void fechaPedido() {
		Random ran = new Random();
		int idPedido = ran.nextInt(1000);
		float valorTotal = 0;
		System.out.println("\n\t--Fechamento de Pedido--\n");
		System.out.println("Pedido Nº: " + idPedido);
	}

	private static void listarClientes() {
		Collections.sort(clientes, Clientes.COMPARE_BY_NAME);
		for (Clientes a : clientes) {
			System.out.println(a);
		}
	}

	private static void cadastracliente() {
		int a = 0;
		String n, e, s, s1;
		// FileWriter arqc = new FileWriter (arqcliente);
		System.out.printf("\t--Novos Cadastros--\n");
		System.out.println("Digite o seu nome para cadastro: ");
		n = teclado.nextLine();
		System.out.println("Digite um E-mail para cadastro: ");
		e = teclado.nextLine();
		while (a == 0) {
			System.out.println("Digite uma senha para o seu cadastro: ");
			s = teclado.nextLine();
			System.out.println("Digite sua senha novamente para confirmação: ");
			s1 = teclado.nextLine();
			if (s.equals(s1)) {
				clientes.add(new Clientes(n, e, s));
				a = 1;
			} else
				System.out.println("Senhas não coincidem.");
		}

	}

	private static Clientes loginclientes() {
		int w = 0;
		boolean a = false;
		Clientes cliente = null;
		String e, s;
		System.out.printf("\t--Login Clientes--\n");
		System.out.println("Digite o seu para E-mail para login: ");
		e = teclado.nextLine();
		for (Clientes n1 : clientes) {
			if (n1.getEmail().equalsIgnoreCase(e)) {
				do {
					System.out.println("Digite sua senha: ");
					s = teclado.nextLine();
					if (n1.getSenha().equals(s)) {
						System.out.println("Login efetuado com sucesso!!");
						a = true;
						cliente = n1;
						menuCompras();
						break;
					} else
						System.out.println("Senha incorreta");
					w++;
					if (w == 5) {
						System.out.println("Limite de tentativas atingido.");
						break;
					} else
						System.out.printf("Ainda resta %d\n", 5 - w);

				} while (a == false || w != 5);
			} else {
				System.out.println("E-mail não cadastrado.");

			}
		}
		return cliente;
	}

}
