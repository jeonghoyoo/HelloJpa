package entityMapping;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityMappingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member memberA = new Member();
            memberA.setUsername("A");

            Member memberB = new Member();
            memberB.setUsername("B");

            Member memberC = new Member();
            memberC.setUsername("C");

            System.out.println("==============Start============");
            //DB Seq = 1 <- 생성 후 제일 처음 호출되면, 1
            //DB Seq = 51
            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);
            System.out.println("memberA ID : " + memberA.getId());
            System.out.println("memberB ID : " + memberB.getId());
            System.out.println("memberC ID : " + memberC.getId());
            System.out.println("==============End============");
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
