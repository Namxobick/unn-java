package logic;

public class Test {

    public Zoo Test1(){

       Zoo zoo = new Zoo();
       Animal cat1 = new Predatory("Sam","feline", "cat" ,"Meow");
       Animal cat2 = new Predatory("Casey","feline", "cat", "Meow");
       Animal dog = new Predatory("Dig", "canine", "dog" ,"Bow-wow");
       Animal cow = new Herbivores("Gru", "bovids", "cow", "Muuu");
       Animal goat = new Herbivores("Step", "bovids", "goat", "Meee");

       Cage first = new Cage();
       Cage second = new Cage();
       Cage third = new Cage();

        try{
            first.SetAnimal(dog);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        try{
            second.SetAnimal(cat1);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        try{
            second.SetAnimal(cat2);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        try{
            third.SetAnimal(cow);
            third.SetAnimal(goat);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }

        zoo.AddCage(first);
        zoo.AddCage(second);
        zoo.AddCage(third);


        return zoo;
    }
}