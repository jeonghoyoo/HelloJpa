package valueType;

import domain.Address;
import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ValueTypeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Address address = new Address("city", "street", "zipcode");
            Member member1 = new Member();
            member1.setOfficeAddress(address);
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setOfficeAddress(address);
            member2.setUsername("member2");
            em.persist(member2);

            address.setCity("newCity");
            tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
