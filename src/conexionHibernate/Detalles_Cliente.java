//Esta es la llamada clase de la Persistencia, es la encargada de crear los objetos que representaran a la tabla de la base de 
//datos a modelar.

package conexionHibernate;

import javax.persistence.*;

@Entity														//Anotacion que le permite a Hibernate transformar nuestra clase Java en una entidad para poder realizar el mapeo
@Table(name="detalles_clientes")							//Anotacion que  especifica a que tabla estara enlazada esta clase. Ojo, aca debemos poner clientes porque la tabla en la base de datos se llama asi, para hacer el mapeo 
public class Detalles_Cliente {								//La clase se llama Detalles_Cliente pues crea un objeto de tipo Detalles_Cliente, a diferencia de la tabla clientes que es una coleccion de objetos Cliente. 
	
	//campos de clase que se deben corresponder con las columnas de la tabla CLientes de la base de datos pruebasHibernate
	@Id														//Anotacion que especifica cual va a ser el campo de clase que mapea el campo clave de la tabla de la BD
	@GeneratedValue(strategy=GenerationType.IDENTITY)		//Creacion de una clave principal en Hibernate, para poder rescatar la info de la tabla
	@Column(name="id")										//Anotacion que especifica el campo de clase mapeado a la columna id de la tabla.
	private int id;
	
	@Column(name="web")										//el valor de la propiedad name debe coincidir exactamente con el nombre de la columna asociada.
	private String web;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="comentarios")
	private String comentarios;
	
	@OneToOne(mappedBy="detalles_Cliente", cascade=CascadeType.ALL )		//mappedBy indica el objeto al cual se va a mapear cliente, o sea al objeto detalles_Cliente que se creo en la clase Cliente
	@JoinColumn(name="id")
	private Cliente cliente;
	
	//Constructor sin argumentos. 
	public Detalles_Cliente(){}

	//Constructor que reciba como parametros todas las propiedades de la clase, exceptuando el id ya que el id es autonumerico en la tabla por tanto en ningun momento necesitaremos cuando queramos crear un objeto de tipo Cliente para mapearlo a la base de datos el mapear el id pues la base de datos crea ese campo por ella sola.
	//Constructor para crear un nuevo objeto que se insertara en la base de datos con el id siguiente al mayor id actual de la tabla
	public Detalles_Cliente(String web, String telefono, String comentarios) {
		this.web = web;
		this.telefono = telefono;
		this.comentarios = comentarios;
	}
	
	//Metodos getter y setters de la clase
	//Para los getters y setters si creo los mismos para el campo id pues es muy probable se utilicen estos metodos en consultas de tipo CRUD
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Detalles_Cliente [id=" + id + ", web=" + web + ", telefono=" + telefono + ", comentarios=" + comentarios
				+ "]";
	}
	
}
