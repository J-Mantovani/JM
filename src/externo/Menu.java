package externo;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	ArrayList<Item> itens = new ArrayList<Item>();
	Item item;
	Scanner scan = new Scanner(System.in);

	private class Item {
		private int id;
		private String tag;

		public Item(int id, String tag) {
			this.id = id;
			this.tag = tag;
		}

		public int getId() {
			return this.id;
		}

		public String getTag() {
			return this.tag;
		}
	}

	public Menu(String[] itens) {
		int id = -1;
		String tag = null;

		for (int i = 0; i < itens.length; i++) {
			if (i % 2 == 0)
				id = Integer.parseInt(itens[i]);
			else
				tag = itens[i];
			if (id != -1 && tag != null) {
				this.itens.add(new Item(id, tag));
				id = -1;
				tag = null;
			}
		}
	}

	public void drawMenu() {
		Item item;
		StringBuilder texto = new StringBuilder();
		for (int i = 0; i < itens.size(); i++) {
			item = itens.get(i);
			texto.append(item.getId() + " - " + item.getTag());
			System.out.println(texto.toString());
			texto = new StringBuilder();
		}
		System.out.println();
		System.out.print("Escolha uma opção: ");
	}

	public int getInput() {
		return scan.nextInt();
	}
}
