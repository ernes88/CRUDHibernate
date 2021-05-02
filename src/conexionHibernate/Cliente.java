//Esta es la llamada clase de la Persistencia, es la encargada de crear los objetos que representaran a la tabla de la base de 
//datos a modelar.

package conexionHibernate;

import java.util.*;

import javax.persistence.*;

@Entity														//Anotacion que le permite a Hibernate transformar nuestra clase Java en una entidad para poder realizar el mapeo
@Table(name="clientes")										//Anotacion que  especifica a que tabla estara enlazada esta clase. Ojo, aca debemos poner clientes porque la tabla en la base de datos se llama asi, para hacer el mapeo 
public class Cliente {										//La clase se llama Cliente pues crea un objeto CLiente, a diferencia de la tabla clientes que es una coleccion de objetos Cliente. 
	
	//campos de clase que se deben corresponder con las columnas de la tabla CLientes de la base de datos pruebasHibernate
	@Id														//Anotacion que especifica cual va a ser el campo de clase que mapea el campo clave de la tabla de la BD
	@GeneratedValue(strategy=GenerationType.IDENTITY)		//Creacion de una clave principal en Hibernate, para poder rescatar la info de la tabla
	@Column(name="id")										//Anotacion que especifica el campo de clase mapeado a la columna id de la tabla.
	private int id;
	
	@Column(name="nombre")									//el valor de la propiedad name debe coincidir exactamente con el nombre de la columna asociada.
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="direccion")
	private String direccion;
	
	//Mapeo ORM de uno a uno unidireccional entre la Clase Cliente y Detalles_Cliente
	//1.Creacion de un objeto de la clase Detalles_CLiente con la que quiero establecer el mapeo
		
	@OneToOne(cascade=CascadeType.ALL)				//se establece la anotacion para el mapeo uno a uno de esta tabla con Detalles_Cliente. Se establece el tipo de mapeo en cascada.
	@JoinColumn(name="id")							//se establece el campo de relacionamiento de las tablas con esta anotacion, y se indica bajo que columna se relacionaran uno a uno.
	private Detalles_Cliente detalles_Cliente;		//La creacion de este campo de tipo Detalles_Cliente en la clase Cliente junto con las dos anotaciones de arriba brinda la relacion de esta clase con Detalles_Cliente. Si no hacemos algo analogo en Detalles_Cliente, la relacion entre ambas sera unidireccional, o sea se podraconsultar CLiente y que nos de valores relacionados en DEtalles_Cliente pero no viceversa.
	
	
	//Mapeo ORM de UNO a VARIOS bidireccional 	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})		//Aca la anotacion que se usa es OneToMany y en Pedidos se uso ManyToOne. "cliente" es el nombre del objeto que creamos de la clase Cliente en la calse Pedido. EL Fetch de tipo Lazy es por defecto aca en la relacion de Uno a Varios, no habria que declararlo explicitamente
	private List<Pedido>listaDePedidos;			//Utilizo un campo de clase de tamaño variable, o sea una List porque no sabemos cuantos pedidos hara cada cliente.
	
	//Constructor sin argumentos. 
	public Cliente(){}

	//Constructor que reciba como parametros todas las propiedades de la clase, exceptuando el id ya que el id es autonumerico en la tabla por tanto en ningun momento necesitaremos cuando queramos crear un objeto de tipo Cliente para mapearlo a la base de datos el mapear el id pues la base de datos crea ese campo por ella sola.
	//Constructor para crear un nuevo objeto que se insertara en la base de datos con el id siguiente al mayor id actual de la tabla
	public Cliente(String nombre, String apellidos, String direccion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
	}

	//Metodos getter y setters de la clase
	//Para los getters y setters si creo los mismos para el campo id pues es muy probable se utilicen estos metodos en consultas de tipo CRUD
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Detalles_Cliente getDetalles_Cliente() {
		return detalles_Cliente;
	}

	public void setDetalles_Cliente(Detalles_Cliente detalles_Cliente) {
		this.detalles_Cliente = detalles_Cliente;
	}
	
	public List<Pedido> getListaDePedidos() {
		return listaDePedidos;
	}

	public void setListaDePedidos(List<Pedido> listaDePedidos) {
		this.listaDePedidos = listaDePedidos;
	}

	//Metodo toString()
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + "]";
	}
	
	//Metodo para agregar el pedido para el cliente que lo haya hecho en concreto.

	public void agregarPedidos(Pedido pedido){	
			listaDePedidos=new ArrayList<>();
			listaDePedidos.add(pedido);
			pedido.setCliente(this);						//Se establece el cliente asociado a un determinado pedido. el operador this nos da en cada momento que objeto Cliente se encuentra activo. 		
	}	
}
