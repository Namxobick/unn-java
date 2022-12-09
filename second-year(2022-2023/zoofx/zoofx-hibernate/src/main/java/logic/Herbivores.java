package logic;

import jakarta.persistence.*;


@Entity
public class Herbivores extends Animal{
    @Column
    private static final TypePowerSupply _typePowerSupply = TypePowerSupply.Herbivores;

    public Herbivores() {
        super();
    }

    public  Herbivores(String name, String family, String type, String voice){
        super.SetName(name);
        super.SetFamily(family);
        super.SetType(type);
        super.SetVoice(voice);
    }

    public  TypePowerSupply GetTypePowerSupply(){
        return _typePowerSupply;
    }

    @Override
    public String toString() {
        return "Name: " + super.GetName() + "\nTypePowerSupply: "  + _typePowerSupply + "\nFamily: " + super.GetFamily() + "\nVoice: " + super.GetVoice();
    }
}
