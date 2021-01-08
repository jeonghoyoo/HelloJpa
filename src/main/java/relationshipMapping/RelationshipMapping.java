package relationshipMapping;

import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RelationshipMapping {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("TEAM_A");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.changeTeam(team);
            em.persist(member1);

            Team team1 = em.find(Team.class, team.getId());
            List<Member> members = team1.getMembers();
            System.out.println("team1 = " + team1);
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
