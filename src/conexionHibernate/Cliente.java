//Esta es la llamada clase de la Persistencia, es la encargada de crear los objetos que representaran a la tabla de la base de 
//datos a modelar.

package conexionHibernate;

import javax.persistence.*;

@Entity									//Anotacion que le permite a Hibernate transformar nuestra clase Java en una entidad para poder realizar el mapeo
@Table(name="clientes")					//Anotacion que  especifica a que tabla estara enlazada esta clase. Ojo, aca debemos poner clientes porque la tabla en la base de datos se llama asi, para hacer el mapeo 
public class Cliente {					//La clase se llama Cliente pues crea un objeto CLiente, a diferencia de la tabla clientes que es una coleccion de objetos Cliente. 
	
	//campos de clase que se deben corresponder con las columnas de la tabla CLientes de la base de datos pruebasHibernate
	@Id														//Anotacion que especifica cual va a ser el campo de clase que mapea el campo clave de la tabla de la BD
	@GeneratedValue(strategy=GenerationType.IDENTITY)		//Creacion de una clave principal en Hibernate, para poder rescatar la info de la tabla
	@Column(name="id")										//Anotacion que especifica el campo de clase mapeado a la columna id de la tabla.
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="direccion")
	private String direccion;
	
	//Constructor sin argumentos. 
	public Cliente(){}
	
	//Este constructor se usa para insertar un nuevo registro en la tabla especificando el id. Es util cuando se ha eliminado algun registro de la tabla y queda un id vacio, con este se puede rellenar esa fila vacia de la tabla.
	public Cliente(int id, String nombre, String apellidos, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
	}

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

	//Metodo toString()
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion + "]";
	}
	
}
