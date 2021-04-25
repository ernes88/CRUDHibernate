package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UpdateCliente {

	public static void main(String[] args) {

		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			
			//Actualizando un registro de la BD
			String hql="UPDATE Cliente SET id = :id WHERE nombre=:cliente_nombre";
			
			Query query=session.createQuery(hql);
			
			query.setParameter("id", 9);
			
			query.setParameter("cliente_nombre", "Bertha");
			
			int result = query.executeUpdate();
			
			System.out.println("Filas Actualizadas: " + result);
			
			
			//Otra forma de modificar algun parametro, sin usar HQL
			int clienteId=8;
			
			//Almaceno en un objeto de tipo cliente el objeto que quiero modificar, o sea el que tenga clienteId como id.
			Cliente cliente=session.get(Cliente.class, clienteId);
			
			cliente.setNombre("Carlos Manuel");
			
					
			//Validamos lo realizado
			session.getTransaction().commit();
					
			//Cerrar el objeto de tipo Session
			session.close();
		}
		finally{
			//tanto si tiene exito como sino lo tiene dicha operacion, deberemos cerrar los recursos
			sessionFactory.close();
		}	
	}
}
