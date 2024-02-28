package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Movie movie = new Movie();
 *             movie.setPrice(10000);
 *             movie.setName("sdad");
 *             movie.setDirector("ssss");
 *             movie.setActor("asad");
 *
 *             em.persist(movie);
 *
 *             em.flush();
 *             em.clear();
 *
 *             Item item = em.find(Item.class, movie.getId());
 *
 *
 *             if(item instanceof Book){
 *                 System.out.println("영화오낭혼밍ㄻ");
 *             }
 *             if(item instanceof Movie){
 *                 System.out.println("item은 영화입니다 = ");
 *                 Movie item1 = (Movie) item;
 *                 item1.getActor();
 *             }
 */
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setName("qwe");
            movie.setPrice(1000);
            movie.setActor("asd");
            movie.setDirector("aaaa");
            em.persist(movie);

            Book book = new Book();
            book.setName("sss");
            book.setPrice(100000);
            book.setAuthor("sss");
            book.setIsbn("ssss");
            em.persist(book);

            em.flush();
            em.clear();

            List<Object> aa = new ArrayList<>();
            List<Movie> movi = new ArrayList<>();
            //Item item = em.find(Item.class, movie.getId());
            List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
            for (Item item : items) {
                //System.out.println("item = " + item);
                if(item.getDtype().equals("Movie")){
                    Movie item1 = (Movie) item;
                    aa.add(item1);
                    movi.add(item1);
                }
                if(item.getDtype().equals("Book")){
                    Book item1 = (Book) item;
                    aa.add(item1);
                }
            }
            for (Object o : aa) {
                System.out.println("o = " + o);
            }




            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
