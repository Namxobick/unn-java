package logic;

import java.util.*;

public class Zoo implements Iterable<Cage> {

    private final Set<String> _uniqueAnimals = new HashSet<>();
    private int _herbivoresCounts;
    private int _predatoryCounts ;

    private final ArrayList<Cage> _cages;

    public Zoo() {
        _cages = new ArrayList<>();
    }

    public Zoo(int numberCage) {
        _cages = new ArrayList<>(numberCage);
    }

    public Zoo(ArrayList<Cage> cages) {
        _cages = cages;
    }

    public void AddCage(Cage cage) {
        _cages.add(cage);
    }

    public void DeleteCage(int index) {
        _cages.remove(index);
    }

    public Cage GetCage(int index) throws Exception {
        if (index >= _cages.size() || index < 0) throw new Exception("Error, index out of range");
        return _cages.get(index);
    }

    public ArrayList<Cage> GetCages() {
        return _cages;
    }

    public List<Object> GetZooInfo(){
        UpdateZooInfo();
        return Arrays.asList(_herbivoresCounts, _predatoryCounts, _uniqueAnimals);
    }

    @Override
    public Iterator<Cage> iterator() {
        return _cages.iterator();
    }

    private void UpdateZooInfo() {
        _herbivoresCounts = 0;
        _predatoryCounts = 0;
        _uniqueAnimals.clear();

        for (Cage cage: _cages) {
            AnimalPool animals;
            try {
                animals = cage.GetAnimals();
            } catch (Exception e) {
                continue;
            }

            if (animals._animalInfo._typePowerSupply == TypePowerSupply.Herbivores)
                _herbivoresCounts += animals.GetSize();
            else
                _predatoryCounts += animals.GetSize();

            for (Animal animal: animals) {
                _uniqueAnimals.add(animal.GetType());
            }
        }

    }
}

