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
            //쿼리를 통해 DB에 저장된 값을 가져옴.
            Member findMember1 = em.find(Member.class, 101L);

            //1차 캐시에서 값을 가져옴.
            Member findMember2 = em.find(Member.class, 101L);

            System.out.println("findMember1 = " + findMember1);
            System.out.println("findMember2 = " + findMember2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
