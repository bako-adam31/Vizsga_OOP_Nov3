package org.example;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class DataParser {

    public RootData parse() {
        ObjectMapper mapper = new ObjectMapper();

        try (
                InputStream inputStream = DataParser.class.getClassLoader().getResourceAsStream("data.json");
                Reader reader = new InputStreamReader(inputStream)
        ) {

            if (inputStream == null) {
                throw new FileNotFoundException("HIBA: A 'data.json' fájl nem található a 'resources' mappában!");
            }

            RootData data = mapper.readValue(reader, RootData.class);
            System.out.println("JSON beolvasás (Jackson) sikeres.");
            return data;
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (JsonParseException e) {
            System.err.println("HIBA: A JSON fájl szintaktikája hibás!");
            e.printStackTrace();
        } catch (IOException e) { // Ez elkapja a 'reader.close()' hibáit is
            System.err.println("HIBA: Váratlan I/O hiba a beolvasás során!");
            e.printStackTrace();
        }
        return null;
    }
}