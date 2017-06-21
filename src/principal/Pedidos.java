package principal;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Pedidos implements Serializable {

	int idPedido;
	private Clientes carrinho;
	private Clientes cliente;
	private GregorianCalendar data;

	public Pedidos(int idPedido, Clientes carrinho, Clientes cliente, GregorianCalendar data) {
		super();
		this.idPedido = idPedido;
		this.carrinho = carrinho;
		this.data = data;
		this.cliente = cliente;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Clientes getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Clientes carrinho) {
		this.carrinho = carrinho;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}


	@Override
	public String toString() {
		return "Pedidos [idPedido=" + idPedido + ", carrinho=" + carrinho + ", cliente=" + cliente + ", data=" + data
				+ "]";
	}
}
