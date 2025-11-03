package org.example;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Vizsga Program Indul ---");

        DataParser parser = new DataParser();
        RootData data = parser.parse();

        if (data == null) {
            System.err.println("A program leáll, mert az adatok beolvasása sikertelen volt.");
            return;
        }

        System.out.println("Sikeresen beolvasott félév: " + data.getTerm());

        ReportGenerator generator = new ReportGenerator();


        System.out.println("\n--- Riport generálása a KONZOLRA (Tömör)... ---");

        PrintWriter consoleWriter = new PrintWriter(System.out);


        generator.generateSummaryReport(data, consoleWriter);

        consoleWriter.flush();



        System.out.println("\n--- Riport generálása a 'fajl.txt'-be (Részletes)... ---");

        try (PrintWriter fileWriter = new PrintWriter(new FileWriter("fajl.txt"))) {

            // MÓDOSÍTÁS: A 'generateDetailedReport'-ot hívjuk
            generator.generateDetailedReport(data, fileWriter);

            System.out.println("A 'fajl.txt' sikeresen létrejött.");

        } catch (IOException e) {
            System.err.println("HIBA: A 'fajl.txt' fájl írása sikertelen!");
            e.printStackTrace();
        }

        System.out.println("--- Vizsga Program Vége ---");
    }
}