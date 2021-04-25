//Esta es la clase encargada de almacenar y/o recuperar los objetos de la clase de Persistencia, o sea donde se realiza efectivamente el mapeo ORM.
//Este mapeo se puede hacer de dos formas, dentro del archivo XML, la cual esta obsoleta o mediante anotaciones Java, recomendada.

package pruebasHibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionHibernate {

	public static void main(String[] args) {
		
		String jdbcUrl="jdbc:mysql://localhost:3306/pruebasHibernate?useSSL=false";		//como estamos haciendo pruebas en local no necesitamos el protocolo seguro de comunicacion SSL.	
		String usuario="root";
		String contrasena="";
		
		try{
			System.out.println("Intentando conectar con la base de datos"+jdbcUrl);
			
			Connection objConexion=DriverManager.getConnection(jdbcUrl,usuario,contrasena); 	//se crea e inicializa un objeto de la interface Connection con el metodo getConnection(), que toma como argumentos la ruta a la base de datos, un usuario y una contraseña 
			
			System.out.println("Conexion exitosa");
			
			
		
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("No conecta");
		}
		
	}
}
