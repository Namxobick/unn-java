package logic;

import java.util.ArrayList;

public class Cage {
    AnimalPool _animals = new AnimalPool();

    public  Cage(){};

    public  Cage(ArrayList<Animal> animals) throws Exception {
        _animals.AddAnimals(animals);
    }

    public AnimalPool GetAnimals() throws Exception{
        if (_animals.GetSize() == 0) throw new Exception("The cage is empty");
        return _animals;
    }

    public Animal GetAnimal(int index) throws Exception {
        if (index < 0 || index > _animals.GetSize()) throw new Exception("Index out of range");
        return _animals.GetAnimal(index);
    }

    public void SetAnimal(Animal animal) throws Exception{
        _animals.AddAnimal(animal);
    }

    public void ClearCage(){
        _animals.DeleteAllAnimals();
    }

    @Override
    public String toString() {
        if (_animals.GetSize() != 0) {
            StringBuilder animalsInfo = new StringBuilder();
            for (Object animal:_animals)
                animalsInfo.append("-----------" + '\n').append(animal.toString()).append('\n');
            return "Info about animal in cage:\n" + animalsInfo;
        }

        return "The cage is empty";
    }
}
