package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class InsertaRecuperaCliente {

	public static void main(String[] args) {
		
		//Crear objeto SessionFactory para que lea el archivo de configuracion.Se le indica con configure() cual es el archivo de configuracion que queremos que lea, que sera el archivo hibernate.cfg.xml que creamos ya. 
		//Ademas hay que indicarle la clase con la que vamos a trabajar y eso se hace con el metodo addAnnotatedClass() donde se le pasa como argumento la clase de la persistencia, o sea la que mapea la tabla.
		//Finalmente hay que indicarle que construya el Sessionfactory. 
		
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		//Crear el objeto de tipo Session para lo cual se utiliza el objeto SessionFactory y su metodo openSession()
		Session session=sessionFactory.openSession();
		
		try{
			//Creacion del objeto de tipo Cliente que se va a insertar en la base de datos.
			Cliente cliente1=new Cliente("Ana","Menendez","Canelones 432");
			
			//Comenzar la transaccion
			session.beginTransaction();
			
			//La transaccion guarda el objeto Cliente en la base de datos utilizando el mapeo ORM que se establecio en base a anotaciones en la clase Cliente, la clase de la persistencia.
			session.save(cliente1);
			
			//Hacer un commit para que todo lo anterior que hemos hecho quede validado y se haga.
			session.getTransaction().commit();
			
			System.out.println("Insercion de registro exitosa");
			
			//Lectura del registro insertado, inicio otra transaccion, puesto que la que se uso para insertar la nueva fila ya termino.
			session.beginTransaction();
			
			System.out.println("Leyendo el registro con Id: " +cliente1.getId());
			
			Cliente clienteleido=session.get(Cliente.class,cliente1.getId());
			
			System.out.println("Registro: "+ clienteleido);
			
			session.getTransaction().commit();
			
			System.out.println("Proceso terminado");
	
			//Cerrar el objeto de tipo Session
			session.close();
		}
		finally{
			//tanto si tiene exito como sino la operacion, deberemos cerrar los recursos
			sessionFactory.close();
		}
		
	}

}
