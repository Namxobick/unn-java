package logic;


import jakarta.persistence.*;

@MappedSuperclass
public interface IAnimal {
    String GetName();
    void SetName(String name);
    TypePowerSupply GetTypePowerSupply();
    String GetFamily();
    void SetFamily(String _family);
    String GetVoice();
    void SetVoice(String _voice);
    String GetType();
    void SetCageId(int id);
    int GetCageId();
}
