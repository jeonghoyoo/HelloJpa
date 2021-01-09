package entityMapping2;

import domain.Book;
import domain.Item;
import domain.Member;
import entityMapping.RoleType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EntityMapping2Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Book book = new Book();
            book.setName("JAVA ORM 표준 JPA 프로그래밍");
            book.setPrice(100000);
            book.setAuthor("김영한님");
            book.setIsbn("123456789");

            em.persist(book);

            em.flush();
            em.clear();

            Book book1 = em.find(Book.class, book.getId());
            System.out.println("book1.getName() = " + book1.getName());

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
