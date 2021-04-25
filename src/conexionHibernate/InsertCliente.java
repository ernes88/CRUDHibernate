package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class InsertCliente {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			//Creacion del objeto de tipo Cliente que se va a insertar en la base de datos utilizando el id especificado.
			Cliente cliente1=new Cliente(6,"Josefina","Pina","Cerro 2132");
			
			session.beginTransaction();
			
			//La transaccion guarda el objeto Cliente en la base de datos utilizando el mapeo ORM que se establecio en base a anotaciones en la clase Cliente, la clase de la persistencia.
			session.save(cliente1);
					
			//Validamos lo realizado
			session.getTransaction().commit();
			
			System.out.println("Insercion de registro exitosa");
					
			//Cerrar el objeto de tipo Session
			session.close();
		}
		finally
		{
			//tanto si tiene exito como sino lo tiene dicha operacion, deberemos cerrar los recursos
			sessionFactory.close();
		}
		
		
	}

}
