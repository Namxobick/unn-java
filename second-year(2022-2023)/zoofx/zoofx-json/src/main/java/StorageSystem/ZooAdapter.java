package StorageSystem;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import logic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZooAdapter extends TypeAdapter<Zoo> {

    @Override
    public void write(JsonWriter jsonWriter, Zoo zoo) throws IOException {

        jsonWriter.beginObject();
        jsonWriter.name("Cages").beginArray();
        for (Cage cage:zoo) {
            jsonWriter.beginObject();
            AnimalPool animalPool;

            try {
                animalPool = cage.GetAnimals();
            }
            catch (Exception e) {
                jsonWriter.name("Animals").beginArray();
                jsonWriter.endArray();
                jsonWriter.endObject();
                continue;
            }

            jsonWriter.name("Animals").beginArray();

            for (Animal animal:animalPool) {
                    jsonWriter.beginObject();
                    jsonWriter.name("Type");
                    jsonWriter.value(animal.GetType());
                    jsonWriter.name("Type power supply");
                    switch (animal.GetTypePowerSupply()){
                        case Predatory -> jsonWriter.value("Predatory");
                        case Herbivores -> jsonWriter.value("Herbivores");
                    }
                    jsonWriter.name("Family");
                    jsonWriter.value(animal.GetFamily());
                    jsonWriter.name("Name");
                    jsonWriter.value(animal.GetName());
                    jsonWriter.name("Voice");
                    jsonWriter.value(animal.GetVoice());
                    jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.endObject();

    }

    @Override
    public Zoo read(JsonReader jsonReader) throws IOException {
        Zoo zoo = new Zoo();
        jsonReader.beginObject();
        while (jsonReader.hasNext())
        {
            jsonReader.nextName();
            jsonReader.beginArray();
            while (jsonReader.hasNext())
            {
                jsonReader.beginObject();
                Cage cage = new Cage();
                while (jsonReader.hasNext())
                {
                    jsonReader.nextName();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext())
                    {
                        Animal animal = new Animal() {
                        @Override
                        public TypePowerSupply GetTypePowerSupply() {
                            return null;
                        }
                    };
                        jsonReader.beginObject();
                        List<String> animalInfo = new ArrayList<>(5);
                        while (jsonReader.hasNext())
                        {
                            switch (jsonReader.nextName())
                            {
                                case "Type" -> animalInfo.add(0, jsonReader.nextString());
                                case "Type power supply" -> animalInfo.add(1, jsonReader.nextString());
                                case "Family" -> animalInfo.add(2, jsonReader.nextString());
                                case "Name" -> animalInfo.add(3, jsonReader.nextString());
                                case "Voice" -> animalInfo.add(4, jsonReader.nextString());
                            }

                        }
                        switch (animalInfo.get(1)){
                        case "Predatory" -> animal = new Predatory(animalInfo.get(3), animalInfo.get(2), animalInfo.get(0), animalInfo.get(4));
                        case "Herbivores" -> animal = new Herbivores(animalInfo.get(3), animalInfo.get(2), animalInfo.get(0), animalInfo.get(4));
                        }

                        try {
                            cage.SetAnimal(animal);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        jsonReader.endObject();
                    }
                    zoo.AddCage(cage);
                    jsonReader.endArray();
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
        }
        jsonReader.endObject();

        return zoo;
    }
}
