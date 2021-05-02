package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDetallesCliente {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).addAnnotatedClass(Detalles_Cliente.class).buildSessionFactory();			//En este caso como estoy trabajando con mas de una clase de persistencia, tengo que añadirlas las dos aca, una tras la otra.
		
		Session session=sessionFactory.openSession();
		
		try{
			//Creacion del objeto de tipo Cliente que se va a insertar en la base de datos en una nueva fila de la tabla, que pasara a ser la ultima. No se especifica id pues el gestor de base de datos lo genera automaticamente pues es autoincremental en la misma
			Cliente cliente=new Cliente("Leopoldo","Bronk","San Luis 632");
			
			//Creacion de la instancia de la tabla relacionada de detalles de los clientes
			Detalles_Cliente detalles_Cliente=new Detalles_Cliente("mlb.com","343-763-1111","Basaball site");
			
			//Relacion bidirecional.
			
			//Asociar los objetos utilizando el setter creado para la instancia de la clase Cliente, dentro de la clase Detalles_Cliente
			detalles_Cliente.setCliente(cliente);
	
			//Comenzar la transaccion
			session.beginTransaction();
			
			//La transaccion guarda el objeto detalles_Cliente en la base de datos utilizando el mapeo ORM que se establecio en base a anotaciones en la clase detalles_Cliente, la clase de la persistencia.
			session.save(detalles_Cliente);			//Como la relacion es bidireccional se puede salvar los detalles y salvara el cliente asociado.
			
			//Hacer un commit para que todo lo anterior que hemos hecho quede validado y se haga.
			session.getTransaction().commit();
			
			System.out.println("El cliente "+ cliente.toString() +" con detalles "+ detalles_Cliente.toString()+" ha sido insertado correctamente");
			
			//Cerrar la sesion
			session.close();
		}
		finally
		{
			//tanto si tiene exito como sino lo tiene dicha operacion, deberemos cerrar los recursos
			sessionFactory.close();
		}		
		
		
		
	}

}
