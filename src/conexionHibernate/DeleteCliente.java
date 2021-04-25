package conexionHibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DeleteCliente {

	public static void main(String[] args) {

		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			
			int clienteEliminar=9;
			
			//Borrando un registro de la BD
			String hql="DELETE FROM Cliente WHERE id = :Cliente_id";
			
			Query query=session.createQuery(hql);
			
			query.setParameter("Cliente_id", clienteEliminar);		
			
			int result = query.executeUpdate();
			
			System.out.println("Filas Eliminadas: " + result);
			
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
