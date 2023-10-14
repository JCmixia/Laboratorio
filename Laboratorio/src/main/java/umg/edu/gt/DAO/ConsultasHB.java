/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umg.edu.gt.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import umg.edu.gt.DTO.DatosDTO;
import umg.edu.gt.DTO.Cliente;
import umg.edu.gt.DTO.Detalle_orden;
import umg.edu.gt.DTO.Orden;
import umg.edu.gt.DTO.Producto;

/**
 *
 * @author Isai
 */
public class ConsultasHB {
//-------------------------------------------CLIENTES-----------------------------------------------------------------

    public List<Cliente> consultarCliente() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("from Cliente");

            List<Cliente> clientes = query.list();

            session.close();

            return clientes;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error HB al consultar clientes " + e.getStackTrace());
            return null;
        }
    }

    public String crearCliente(DatosDTO cliente) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            Cliente client = new Cliente(cliente.getNombre(), cliente.getCorreo(), cliente.getDireccion(), cliente.getTelefono());

            session.beginTransaction();
            //session.save(client);
            session.persist(client);

            session.getTransaction().commit();

            session.close();

            return "Usuario creado";
        } catch (PersistenceException e) {
            e.printStackTrace();
            return "error al registrar usuario: " + e.getStackTrace();
        }
    }

    public String modificarCliente(DatosDTO cliente) {

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Cliente client = session.get(Cliente.class, cliente.getId());

            client.setNombre(cliente.getNombre());
            client.setCorreo(cliente.getCorreo());
            client.setDireccion(cliente.getDireccion());
            client.setTelefono(cliente.getTelefono());

            session.update(client);
            //session.save(client);
            transaction.commit();

            //session.getTransaction().commit();
            //session.close();
            return "Cliente actualizado con exito";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error HB al actualizar el cliente";
        } finally {
            session.close();
        }
    }

    public String eliminarCliente(DatosDTO cliente) {

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Recuperar el Cliente de la base de datos
            Cliente clienteEncontrado = session.get(Cliente.class, cliente.getId());

            // Eliminar el Cliente
            session.delete(clienteEncontrado);

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "Cliente eliminado exitosamente";
    }

    
//----------------------------------------ORDENES-------------------------------------------------------------    
    public List<Orden> consultarOrden() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("from Orden");

            List<Orden> ordenes = query.list();

            session.close();

            return ordenes;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error HB 165al consultar ordenes " + e.getStackTrace());
            return null;
        }
    }

    public int crearOrden(DatosDTO orden) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha;
        try {
            fecha = formato.parse(orden.getFecha());
        } catch (ParseException e) {
            System.out.println("ConsultasHB 175 Error al convertir la fecha: " + e.getStackTrace());
            return 1;
        }

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            Orden ordenHb = new Orden(orden.getCliente_id(), fecha, orden.getTotal());

            session.beginTransaction();
            //session.save(client);
            try {
                session.persist(ordenHb);
            } catch (Exception e) {
                System.out.println("ConsultasHB 193 error al guardar la orden");
            }

            session.getTransaction().commit();

            session.close();

            return 3;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public String modificarOrden(DatosDTO orden){

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha;

        try {
            fecha = formato.parse(orden.getFecha());
        } catch (ParseException e) {
            System.out.println("ConsultasHB 222 Error al convertir la fecha: " + e.getStackTrace());
            return "f";
        }

        try {

            transaction = session.beginTransaction();

            Orden orden1 = session.get(Orden.class, orden.getId());

            orden1.setCliente_id(orden.getCliente_id());
            orden1.setFecha(fecha);
            orden1.setTotal(orden.getTotal());

            session.update(orden1);
            //session.save(client);
            transaction.commit();

            //session.getTransaction().commit();
            //session.close();
            return "241 orden actualizada con exito";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error HB 247 al actualizar la orden";
        } finally {
            session.close();
        }
    }

    public String eliminarOrden(DatosDTO orden) {

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Recuperar el Cliente de la base de datos
            Orden clienteEncontrado = session.get(Orden.class, orden.getId());

            // Eliminar el Cliente
            session.delete(clienteEncontrado);

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "Orden eliminada exitosamente";
    }

//--------------------------------------DETALLE ORDENES-------------------------------------------------------------    
    public List<Detalle_orden> consultarDetalle() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Detalle_orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("from Detalle_orden");

            List<Detalle_orden> detalle = query.list();

            session.close();

            return detalle;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error HB 305 al consultar detalle " + e.getStackTrace());
            return null;
        }
    }

    public int crearDetalle(DatosDTO detalle) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Detalle_orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            Detalle_orden detalle1 = new Detalle_orden(detalle.getOrden_id(), detalle.getProducto_id(), detalle.getCantidad(), detalle.getPrecio());

            session.beginTransaction();
            //session.save(client);
            try {
                session.persist(detalle1);
            } catch (Exception e) {
                System.out.println("ConsultasHB 325 error al guardar el detalle");
            }

            session.getTransaction().commit();

            session.close();

            return 3;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public String modificarDetalle(DatosDTO detalle){

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Detalle_orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Detalle_orden detalle1 = session.get(Detalle_orden.class, detalle.getId());
            
            detalle1.setOrden_id(detalle.getOrden_id());
            detalle1.setProducto_id(detalle.getProducto_id());
            detalle1.setCantidad(detalle.getCantidad());
            detalle1.setPrecio(detalle.getPrecio());


            session.update(detalle1);
            //session.save(client);
            transaction.commit();

            //session.getTransaction().commit();
            //session.close();
            return "365 detalle actualizado con exito";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error HB 371 al actualizar el detalle";
        } finally {
            session.close();
        }
    }

    public String eliminarDetalle(DatosDTO detalle) {

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Detalle_orden.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Recuperar el Cliente de la base de datos
            Detalle_orden detalle1 = session.get(Detalle_orden.class, detalle.getId());

            // Eliminar el Cliente
            session.delete(detalle1);

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "Detalle eliminado exitosamente";
    }
    
    //--------------------------------------PRODUCTO-------------------------------------------------------------    
    public List<Producto> consultarProducto() {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Producto.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {
            Query query = session.createQuery("from Producto");

            List<Producto> prod = query.list();

            session.close();

            return prod;

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Error HB 3428 al consultar Producto " + e.getStackTrace());
            return null;
        }
    }

    public int crearProducto(DatosDTO producto) {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Producto.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        try {

            Producto producto1 = new Producto(producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad());

            session.beginTransaction();
            //session.save(client);
            try {
                session.persist(producto1);
            } catch (Exception e) {
                System.out.println("ConsultasHB 448 error al guardar el producto");
            }

            session.getTransaction().commit();

            session.close();

            return 3;
        } catch (PersistenceException e) {
            e.printStackTrace();
            return 2;
        }
    }

    public String modificarProducto(DatosDTO producto){

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Producto.class).buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Producto prod = session.get(Producto.class, producto.getId());
            
            prod.setNombre(producto.getNombre());
            prod.setDescripcion(producto.getDescripcion());
            prod.setPrecio(producto.getPrecio());
            prod.setCantidad(producto.getCantidad());
            
            session.update(prod);
            //session.save(client);
            transaction.commit();

            //session.getTransaction().commit();
            //session.close();
            return "487 producto actualizado con exito";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error HB 493 al actualizar el producto";
        } finally {
            session.close();
        }
    }

    public String eliminarProducto(DatosDTO producto) {

        System.out.println("Ingresando al metodo modificar cliente");
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Producto.class).buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Recuperar el Cliente de la base de datos
            Producto prod = session.get(Producto.class, producto.getId());

            // Eliminar el Cliente
            session.delete(prod);

            // Confirmar la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "Producto eliminado exitosamente";
    }
}
