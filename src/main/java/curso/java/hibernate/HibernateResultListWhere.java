package curso.java.hibernate;

import java.util.List;
import java.util.Scanner;

import curso.java.hibernate.entity.Cliente;
import curso.java.hibernate.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class HibernateResultListWhere {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingrese una forma de pago: ");
        String pago = s.next();	
        query.setParameter(1, pago);
//        query.setMaxResults(1);
        List<Cliente> clientes = query.getResultList();
        clientes.forEach(cliente->System.out.println(cliente));
        em.close();
    }
}
