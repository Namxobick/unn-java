package StorageSystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.Zoo;

import java.io.FileWriter;

public class JsonWriter {

    public void WriteZoo(Zoo zoo)
    {
        WriteZoo(zoo, "src/main/resources/files/zoo.json");
    }

    public void WriteZoo(Zoo zoo, String path)
    {
        try (FileWriter out = new FileWriter(path)) {

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Zoo.class, new ZooAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String jsonString = gson.toJson(zoo);
            out.write(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
