package hellojpa;

import domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    //#2. 영속성 컨택스트
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //#2-1. 비영속 컨텍스트 (New or transient)
            Member member = new Member();
            member.setId(100L);
            member.setUsername("Hello JPA");

            //#2-2. 영속 컨텍스트 (managed)
            System.out.println("BEFORE");
            em.persist(member); // <- .persist(entity)에서 DB에 저장하지 않음, Transaction을 commit하는 시점에서 저장
            System.out.println("AFTER");

            //#2-3. 준영속 (detached)
            em.detach(member);

            //#2-4. 삭제 (remove)
            em.detach(member);
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    //#1. Entity Manager를 이용한 CRUD & JPQL
    /*public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //#1 회원 등록

            Member member = new Member();
            member.setId(3L);
            member.setName("HelloC");
            em.persist(member);


            //#2 회원 조회 & 회원 수정
            Member foundMember = em.find(Member.class, 1L);
            foundMember.setName("BuyJpa");
            tx.commit();

            //#3. 회원 삭제

            Member foundMember = em.find(Member.class, 1L);
            em.remove(foundMember);
            tx.commit();


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
    }*/
}
