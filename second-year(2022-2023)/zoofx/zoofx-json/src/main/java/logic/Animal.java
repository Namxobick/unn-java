package logic;

public abstract class Animal implements IAnimal{
    private String _name;
    private  String _type;
    private  String _family;
    private  String _voice;

    public  Animal(){
        _name = null;
    }

    public Animal(String name) {
        _name = name;
    }

    public String GetName() {
        return _name;
    }
    public void SetName(String name){ _name = name; }

    public String GetFamily() {
        return _family;
    }
    public void SetFamily(String family) {
        _family = family;
    }

    public String GetVoice() {
        return _voice;
    }
    public void SetVoice(String voice) {
        _voice = voice;
    }

    public String GetType(){
        return _type;
    }
    public void SetType(String type) {
        _type = type;
    }

    @Override
    public String toString() {
        return "Name: " +_name;
    }

}
