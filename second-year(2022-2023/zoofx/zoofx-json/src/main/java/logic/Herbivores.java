package logic;

public class Herbivores extends Animal{
    private static final TypePowerSupply _typePowerSupply = TypePowerSupply.Herbivores;

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
