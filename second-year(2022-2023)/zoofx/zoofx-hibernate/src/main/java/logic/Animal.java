package logic;


import jakarta.persistence.*;


@Entity
@Table(name = "animals")
public abstract class Animal implements IAnimal{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    protected int id;
    @Column
    protected int idCage;

    @Column
    private String _name;
    @Column
    private  String _type;
    @Column
    private  String _family;
    @Column
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

    public int GetCageId(){
        return idCage;
    }
    public void SetCageId(int id){ idCage = id;}

    @Override
    public String toString() {
        return "Name: " +_name;
    }

}
