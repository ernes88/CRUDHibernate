package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

class CreateCliente {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			//Creacion del objeto de tipo Cliente que se va a insertar en la base de datos en una nueva fila de la tabla, que pasara a ser la ultima. No se especifica id pues el gestor de base de datos lo genera automaticamente pues es autoincremental en la misma
			Cliente cliente1=new Cliente(9, "Bertha","Piñera","boston 432");
			
			//Comenzar la transaccion
			session.beginTransaction();
			
			//La transaccion guarda el objeto Cliente en la base de datos utilizando el mapeo ORM que se establecio en base a anotaciones en la clase Cliente, la clase de la persistencia.
			session.save(cliente1);
			
			//Hacer un commit para que todo lo anterior que hemos hecho quede validado y se haga.
			session.getTransaction().commit();
			
			System.out.println("Insercion de registro exitosa");
			
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
