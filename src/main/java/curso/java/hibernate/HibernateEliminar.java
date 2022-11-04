package curso.java.hibernate;

import java.util.Scanner;

import curso.java.hibernate.entity.Cliente;
import curso.java.hibernate.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateEliminar {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a eliminar:");
        Long id = scan.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
