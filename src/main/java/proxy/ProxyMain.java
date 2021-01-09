package proxy;

import domain.Book;
import domain.Member;
import domain.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProxyMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
                Team team2 = new Team();
                team2.setName("JPA TEAM");
                em.persist(team2);

                Member member2 = new Member();
                member2.setUsername("jpa2");
                member2.changeTeam(team2);
                em.persist(member2);

                Team team = new Team();
                team.setName("JPA TEAM");
                em.persist(team);

                Member member = new Member();
                member.setUsername("jpa1");
                member.changeTeam(team);
                em.persist(member);
                em.flush();
                em.clear();

                List<Member> members = em.createQuery("select m from Member as m join fetch m.team", Member.class)
                        .getResultList();
                for (Member member1 : members) {
                    System.out.println("member1.getTeam().getName() = " + member1.getTeam().getName());
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
