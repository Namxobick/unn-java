package StorageSystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.Zoo;

import java.io.FileReader;

public class JsonReader {

    public Zoo ReadZoo()
    {
        return ReadZoo("src/main/resources/files/zoo.json");
    }

    public Zoo ReadZoo(String path) {
        Zoo zoo = new Zoo();

        try (FileReader out = new FileReader(path)) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Zoo.class, new ZooAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            zoo = gson.fromJson(out, Zoo.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return zoo;
    }
}
