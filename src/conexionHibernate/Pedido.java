package conexionHibernate;

import java.util.*;

import javax.persistence.*;

@Entity	
@Table(name="pedidos")	
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fecha")	
	private GregorianCalendar fecha;
	
	@Column(name="formaPago")	
	private String formaPago;
	
	@JoinColumn(name="clienteId")
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})		//Se establece una relacion de varios pedidos a un cliente. En este caso le establesco las cascadas que quiero que tome, todas excepto cascadeType.REMOVE, pues no quiero que me elimine un cliente por solo eliminar un registro de un pedido que hizo.
	private Cliente cliente;
	
	public Pedido() {}

	public Pedido(GregorianCalendar gregorianCalendar, String formaPago) {
		this.fecha = gregorianCalendar;
		this.formaPago = formaPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GregorianCalendar getFecha() {
		return fecha;
	}

	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", formaPago=" + formaPago + "]";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}	
}
