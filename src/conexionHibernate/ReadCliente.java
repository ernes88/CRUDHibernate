package conexionHibernate;

import java.util.List;
import javax.persistence.Column;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ReadCliente {

	public static void main(String[] args) {

		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
		
		Session session=sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			
			//Seleccionando todos los registros de la tabla clientes de la base de datos pruebashibernate
			//Via 1. Sin utilizar objeto de la clase Query			
			//List<Cliente> list=session.createQuery("FROM Cliente").getResultList();			//OJO. Aca la sentencia HQL se ejecuta sobre la clase Cliente que crea los objetos Cliente, no directamente sobre la tabla en la base de datos clientes, por tanto no creamos una sentencia SELECT * FROM CLIENTES
			
			
			//Via 2. Utilizando el objeto de la clase Query se rescatan todos los objetos de la clase Cliente
			Query query=session.createQuery("FROM Cliente");									//OJO. Aca la sentencia HQL se ejecuta sobre la clase Cliente que crea los objetos Cliente, no directamente sobre la tabla en la base de datos clientes, por tanto no creamos una sentencia SELECT * FROM CLIENTES
			List<Cliente> list=query.list();
			
			//Mostrar los objetos recuperados
			System.out.println("Consulta 1:Seleccionar todos los objetos");
			muestraClientes(list);
			
			
			
			
			//Se muestran los Cliente que se apellidan Rodriguez o Perez
			Query query1=session.createQuery("FROM Cliente C WHERE C.apellidos='Rodriguez' or C.apellidos='Perez'");		//Nota 1.Si vamos a hacer referencia a un texto como valor de un atributo dentro de una consulta HQL, ese texto va en comullas simples. Nota 2.el nombre apellidos en la C.apellidos, hace referencia a private String apellidos; en la tabla Cliente, no a @Column(name="apellidos"). O sea podiamos haberle dado cualquier otro nombre al valor de la propiedad name dentro de @Column, pues no tiene que obligatoriamente coincidir con el nombre del campo de clase. 							
			List<Cliente> list1=query1.list();
			
			System.out.println("Consulta 2:Seleccionar todos los objetos cuyo campo apellidos sea Rodriguez o Perez");
			muestraClientes(list1);
			
			
			
			
			//Se muestran los clientes que se apellidan Perez o viven en canelones 1885 ordenados por apellidos descendentemente
			Query query2=session.createQuery("from Cliente C where C.apellidos='Perez' or C.direccion='Canelones 1885' ORDER BY C.nombre ,C.apellidos");		 							
			List<Cliente> list2=query2.list();
			
			System.out.println("Consulta 3:Seleccionar los objetos Cliente cuyo apellido sea Perez o la direccion sea Canelones 1885 ordenados por nombre y luego por apellidos ascendentemente ");
			muestraClientes(list2);
			
			
			
			//Se muestran los clientes cuya direccion comienza por C
			Query query3=session.createQuery("from Cliente C where direccion LIKE 'C%'");		 							
			List<Cliente> list3=query3.list();
			
			System.out.println("Consulta 4:Seleccionar los objetos Cliente cuya direccion comience por C ");
			muestraClientes(list3);
			
			
			
			/*
			//Se muestran los clientes que se apellidan Perez o viven en canelones 1885 ordenados por apellidos descendentemente
			Query query3=session.createQuery("Select C.apellidos from Cliente C");		 							
			List<Cliente> list3=query3.list();
			
			System.out.println("Consulta 4:Seleccionar los apellidos de la Clase Cliente");
			for(Cliente cl:list3){
				System.out.println(cl);
			}
			System.out.println();
			*/
			
			//Validamos lo realizado
			session.getTransaction().commit();
			
			//Cerrar el objeto de tipo Session
			session.close();
		}
		finally
		{
			//tanto si tiene exito como sino lo tiene dicha operacion, deberemos cerrar los recursos
			sessionFactory.close();
		}
		
		
	}

	private static void muestraClientes(List<Cliente> list) {
		for(Cliente cl:list){
			System.out.println(cl);
		}
		System.out.println();
	}

}
