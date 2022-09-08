package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Queue;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   public User getUserFromCar (String model, int series) {
      User user = null;
      Query query = sessionFactory.getCurrentSession().createQuery("from User as user where user.car.model = :model and user.car.series = :series");
      query.setParameter("model", model);
      query.setParameter("series", series);
      query.setMaxResults(1);
      try {
         user = (User) query.getSingleResult();
      } catch (NoResultException e) {
         System.out.println("Юзер не найден");
      }
      return user;
   }

}
