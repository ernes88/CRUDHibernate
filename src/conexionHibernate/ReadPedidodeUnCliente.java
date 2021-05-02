//En esta clase se leearan los pedidos de un determinado cliente.

package conexionHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class ReadPedidodeUnCliente {

	public static void main(String[] args) {

		//Construir los objetos sessionFactory y session
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).addAnnotatedClass(Pedido.class).addAnnotatedClass(Detalles_Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			//Comenzar la transaccion
			session.beginTransaction();
			 
			//Obtener el cliente al que se le va a obtener sus pedidos
			Cliente cliente1=session.get(Cliente.class, 7);					//Si en la anotacion @OneToMany en la clase Cliente para el campo listaDePedidos, se deja el valor por defecto para el Fetch, o sea Lazy, cuando se procesa esta linea dentro de la clase ReadPedidodeUnCliente, se carga en memoria solo la informacion sobre el cliente, nada mas, y en la linea de abajo donde se llama getListaDePedidos() es donde se cargan los pedidos, por tanto habria que ejecutar estas dos lineas antes de cerrar el objeto session pues sino daria error.
					
			//Mostrar el cliente escogido
			System.out.println("Cliente: "+cliente1.toString());				
	
			//Mostrar los pedidos del cliente
			System.out.println("Pedidos: "+cliente1.getListaDePedidos());	//Si el Fetch que se usa es EAGER, ya en la linea de arriba donde se obtiene el CLiente, se cargarian los pedidos tambien ademas del cliente por tanto se puede cerrar el objeto session antes de ejecutar esta linea sin problemas. 
			
			
			//Método utilizando HQL para obtener los pedidos de otro cliente.
			//Creo la consulta HQL
			Query<Cliente>consulta=session.createQuery("SELECT CL FROM Cliente CL JOIN FETCH CL.listaDePedidos WHERE CL.id=:cliente2");
			
			//Establezco el parámetro 
			consulta.setParameter("cliente2", 9);
			
			//Le indico que cargue todos lo resultados que me devuelva la consulta HQL en memoria, o sea esto como que conviete en EAGER el proceso de carga en memoria de los resultados, a pesar de que hayamos establecido LAZY en Cliente.
			Cliente cliente2=consulta.getSingleResult();
			
			//Imprimo por pantalla el cliente 2 con sus pedidos		
			System.out.println("Cliente: "+cliente2.toString());
			System.out.println("Pedidos: "+cliente2.getListaDePedidos());
			
			//Hacer un commit para que todo lo anterior que hemos hecho quede validado.
			session.getTransaction().commit();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		
		finally{
			session.close();
			sessionFactory.close();
		}	
	}
}
