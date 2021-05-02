package conexionHibernate;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class DeleteCliente {

	public static void main(String[] args) {

		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).addAnnotatedClass(Detalles_Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			
			//Obtener de la base de datos el cliente a borrar y almacenarlo en un objeto de la clase Cliente
			Cliente cliente=session.get(Cliente.class, 3);		
			
			//si se encuentra un cliente en la tabla con ese id.... 
			if(cliente!=null){
				session.delete(cliente);
				System.out.println("Registros eliminados correctamente de la base de datos");
			}
			else{
				System.out.println("No se ha podido eliminar nungún registro de la base de datos");
			}
		
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
