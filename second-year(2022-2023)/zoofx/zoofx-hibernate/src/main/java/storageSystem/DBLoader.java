package storageSystem;

import logic.Animal;
import logic.Cage;
import logic.Zoo;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DBLoader {

    public Zoo LoadZoo()
    {
        Zoo zoo = new Zoo();

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List<Animal> animals = session.createQuery("from Animal").list();
            ArrayList<Cage> cages = new ArrayList<>();
            for (Animal animal : animals) {
                int tempCageId = animal.GetCageId();
                while(cages.size() - 1 != tempCageId)
                {
                    Cage cage = new Cage();
                    cages.add(cage);
                }

                try {
                    cages.get(tempCageId).SetAnimal(animal);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            zoo.AddCages(cages);
        }

        return zoo;
    }
}