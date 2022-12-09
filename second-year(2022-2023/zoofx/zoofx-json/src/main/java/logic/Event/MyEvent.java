package logic.Event;

import logic.Cage;

import java.util.EventObject;

public class MyEvent extends EventObject {
    private Cage _cage;

    private String _additionalInfo;

    private Type _type;

    public String GetAdditionalInfo(){ return _additionalInfo; }

    public Cage GetCage(){return _cage;}

    public Type GetType(){
        return _type;
    }

    public void SetType(Type type){
        _type = type;
    }

    public enum Type{
        CageRemoval,
        SaveZoo,
    }

    public MyEvent(Object source, Type type) {
        super(source);
        _type = type;
    }

    public MyEvent(Object source, Type type, String additionalInfo){
        super(source);
        _type = type;
        _additionalInfo = additionalInfo;
    }

    public MyEvent(Object source, Type type, String additionalInfo, Cage cage){
        super(source);
        _type = type;
        _additionalInfo = additionalInfo;
        _cage = cage;
    }
}
