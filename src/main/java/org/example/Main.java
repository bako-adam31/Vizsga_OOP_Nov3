package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Vizsga Program Indul ---");

        // --- 1. LÉPÉS: BEOLVASÁS (Változatlan) ---
        DataParser parser = new DataParser();
        RootData data = parser.parse();

        if (data == null) {
            System.err.println("A program leáll, mert az adatok beolvasása sikertelen volt.");
            return;
        }

        System.out.println("Sikeresen beolvasott félév: " + data.getTerm());


        // --- 2. LÉPÉS: RIPORTOLÁS (Módosítva) ---

        // Hozzuk létre a generátort
        ReportGenerator generator = new ReportGenerator();

        // --- A) Kiírás a KONZOLRA ---
        System.out.println("\n--- Riport generálása a KONZOLRA... ---");

        // Létrehozunk egy PrintWriter-t, ami a konzolra (System.out) mutat
        PrintWriter consoleWriter = new PrintWriter(System.out);

        // Átadjuk a konzol-írót a generátornak
        generator.generateReport(data, consoleWriter);

        // Fontos: A konzolra írás után 'flush'-t kell hívni
        consoleWriter.flush();


        // --- B) Kiírás a 'fajl.txt'-be ---
        System.out.println("\n--- Riport generálása a 'fajl.txt'-be... ---");

        // Létrehozunk egy PrintWriter-t, ami egy fájlba (fajl.txt) mutat
        // A 'try-with-resources' biztosítja, hogy a fájl automatikusan bezáródjon
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter("fajl.txt"))) {

            // UGYANAZT a generátort hívjuk, de most a FÁJL-írót adjuk át neki
            generator.generateReport(data, fileWriter);

            System.out.println("A 'fajl.txt' sikeresen létrejött.");

        } catch (IOException e) {
            // Hiba elkapása, ha a fájlba írás sikertelen
            System.err.println("HIBA: A 'fajl.txt' fájl írása sikertelen!");
            e.printStackTrace();
        }

        System.out.println("--- Vizsga Program Vége ---");
    }
}