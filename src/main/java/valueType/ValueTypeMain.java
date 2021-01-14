package valueType;

import domain.Address;
import domain.AddressEntity;
import domain.Member;
import domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ValueTypeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setOfficeAddress(new Address("officeAddress", "street", "zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            AddressEntity removeAddressEntity = new AddressEntity(new Address("oldAddress1", "street", "zipcode"));
            member.getAddressHistory().add(removeAddressEntity);
            member.getAddressHistory().add(new AddressEntity(new Address("oldAddress2", "street", "zipcode")));
            em.persist(member);

            em.flush();
            em.clear();
            System.out.println("=========================");
            Member member1 = em.find(Member.class, member.getId());
            member1.getFavoriteFoods().remove("치킨");
            member1.getFavoriteFoods().add("냉면");


            member1.getAddressHistory().remove(removeAddressEntity);
            member1.getAddressHistory().add(new AddressEntity(new Address("newAddress1", "street", "zipcode")));

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
