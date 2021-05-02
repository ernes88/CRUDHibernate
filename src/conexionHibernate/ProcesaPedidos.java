package conexionHibernate;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ProcesaPedidos {

	public static void main(String[] args) {

		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).addAnnotatedClass(Detalles_Cliente.class).addAnnotatedClass(Pedido.class).buildSessionFactory();			//En este caso como estoy trabajando con mas de una clase de persistencia, tengo que añadirlas las dos aca, una tras la otra.
		
		Session session=sessionFactory.openSession();
		
		try{
			//Comenzar la transaccion
			session.beginTransaction();
				
			//Cliente al cual se le va a adjuntar pedidos.
			Cliente cliente=session.get(Cliente.class, 7);
			
			//Detalles del cliente
			Detalles_Cliente detalles_Cliente=session.get(Detalles_Cliente.class, 7);
			
			//Creacion de los objetos Pedido que contiene info sobre los pedidos de un determinado cliente.
			Pedido pedido1=new Pedido(new GregorianCalendar(),"credito");
			Pedido pedido2=new Pedido(new GregorianCalendar(),"debito");
			Pedido pedido3=new Pedido(new GregorianCalendar(),"contado");
			
			if(cliente!=null && detalles_Cliente!=null && pedido1!=null && pedido2!=null && pedido3!=null){
				
				//Agregar los detalles del cliente al cliente
				cliente.setDetalles_Cliente(detalles_Cliente);
				
				//Agregar al cliente creado los pedidos construidos
				cliente.agregarPedidos(pedido1);
				cliente.agregarPedidos(pedido2);
				cliente.agregarPedidos(pedido3);
				
				//Guardar los pedidos en la base de datos, en la tabla pedidos.
				session.save(pedido1);
				session.save(pedido2);
				session.save(pedido3);	
				
				//Hacer un commit para que todo lo anterior que hemos hecho quede validado y se haga.
				session.getTransaction().commit();
				
				System.out.println("El cliente "+ cliente.toString() +" con detalles "+ detalles_Cliente.toString()+ " y pedidos: "+ pedido1.toString()+", "+pedido2.toString()+" y "+pedido3.toString()+ "ha sido insertado correctamente");
				
				//Cerrar la sesion
				session.close();				
			}
			else{
				//Cerrar la sesion
				session.close();	
			}
		}
		catch(Exception e){
			session.close();
			e.printStackTrace();
		}
		
		finally
		{
			//tanto si tiene exito como sino lo tiene dicha operacion, deberemos cerrar los recursos
			session.close();
			sessionFactory.close();
		}	
	}
}
