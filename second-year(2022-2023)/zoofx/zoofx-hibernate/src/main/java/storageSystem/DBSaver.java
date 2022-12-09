package storageSystem;

import logic.Animal;
import logic.AnimalPool;
import logic.Cage;
import logic.Zoo;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBSaver {

    public void SaveZoo(Zoo zoo) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();
            session.createQuery("delete Animal").executeUpdate();
            int idCage = 0;
            for (Cage cage : zoo) {

                AnimalPool animals;
                try {
                    animals = cage.GetAnimals();
                } catch (Exception e) {
                    continue;
                }
                for(Animal animal : animals) {
                    animal.SetCageId(idCage);
                    session.save(animal);
                }
                idCage++;
            }
            t.commit();
        }
    }
}
