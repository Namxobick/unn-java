package logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

class PowerSupplyAndAnimalTypes {
    TypePowerSupply _typePowerSupply;
    String _animalType;

    PowerSupplyAndAnimalTypes(TypePowerSupply typePowerSupply, String animalType){
        _typePowerSupply = typePowerSupply;
        _animalType = animalType;
    }
}

public class AnimalPool implements Iterable<Animal> {
    int maxSize = 4;
    PowerSupplyAndAnimalTypes _animalInfo;
    ArrayList<Animal> _animals;

    public AnimalPool(){
        _animals = new ArrayList<>();
    }

    public int GetSize() {
        return _animals.size();
    }

    public void AddAnimal(Animal animal) throws Exception{
        if (_animals.size() == 0)
            _animalInfo = new PowerSupplyAndAnimalTypes(animal.GetTypePowerSupply(), animal.GetType());

        if (_animals.size() == maxSize) throw new Exception("The animalPool is full");

        if (_animalInfo._typePowerSupply != animal.GetTypePowerSupply())
            throw new Exception("The cage for " + _animalInfo._typePowerSupply);

        if (_animalInfo._typePowerSupply == TypePowerSupply.Predatory
                & !Objects.equals(_animalInfo._animalType, animal.GetType()))
            throw new Exception("The cage only for " + _animalInfo._animalType + "s");

        _animals.add(animal);
    }

    public void AddAnimals(ArrayList<Animal> animals) throws Exception {
        for (Animal animal : animals)
            AddAnimal(animal);
    }

    public Animal GetAnimal(int index)throws Exception{
        return _animals.get(index);
    }

    public void DeleteAllAnimals(){
        _animals.clear();
    }

    @Override
    public Iterator<Animal> iterator() {
        return _animals.iterator();
    }
}
