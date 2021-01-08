package entityMapping;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EntityMappingMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member memberA = new Member();
            memberA.setUsername("A");
            memberA.setRoleType(RoleType.ADMIN);

            Member memberB = new Member();
            memberB.setUsername("B");
            memberB.setRoleType(RoleType.USER);

            Member memberC = new Member();
            memberC.setUsername("C");
            memberC.setRoleType(RoleType.ADMIN);

            System.out.println("==============Start============");
            //DB Seq = 1 <- 생성 후 제일 처음 호출되면, 1
            //DB Seq = 51
            em.persist(memberA);
            em.persist(memberB);
            em.persist(memberC);
            long memberAId = memberA.getId();
            System.out.println("memberA ID : " + memberA.getId());
            System.out.println("memberB ID : " + memberB.getId());
            System.out.println("memberC ID : " + memberC.getId());
            System.out.println("==============End============");

            List<Member> members = em.createQuery("select m from Member as m").getResultList();
            for (Member member : members) {
                System.out.println("member roleTypeCode = " + member.getRoleType().getRoleCode());
            }
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
