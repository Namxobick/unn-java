package storageSystem;

import logic.Animal;
import logic.Herbivores;
import logic.IAnimal;
import logic.Predatory;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;
    private HibernateSessionFactory(){

    }
    public static SessionFactory getSessionFactory()
    {
        if(sessionFactory == null)
        {
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(IAnimal.class);
                configuration.addAnnotatedClass(Animal.class);
                configuration.addAnnotatedClass(Herbivores.class);
                configuration.addAnnotatedClass(Predatory.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }
}
