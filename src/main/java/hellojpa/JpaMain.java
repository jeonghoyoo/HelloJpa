package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //#1 회원 등록
            /*
            Member member = new Member();
            member.setId(3L);
            member.setName("HelloC");
            em.persist(member);
            */

            //#2 회원 조회 & 회원 수정
            /*Member foundMember = em.find(Member.class, 1L);
            foundMember.setName("BuyJpa");
            tx.commit();*/

            //#3. 회원 삭제
            /*
            Member foundMember = em.find(Member.class, 1L);
            em.remove(foundMember);
            tx.commit();
             */

            //#4 JPQL을 이용한 조회
            List<Member> members = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : members) {
                System.out.println("member.name = " + member.getName());
            }
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
