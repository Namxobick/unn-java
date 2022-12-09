package logic;

import java.util.List;

public class ZooViewer {
    public void View(Zoo zoo)
    {
        for (Cage cage : zoo)
            System.out.println((cage + "\n"));
    }

    public void PrintInfoZoo(Zoo zoo)
    {
        List<Object> infoZoo = zoo.GetZooInfo();
        System.out.println("Number of herbivores: " + infoZoo.get(0));
        System.out.println("Number of predators: " + infoZoo.get(1));
        System.out.println(infoZoo.get(2));
    }
}
