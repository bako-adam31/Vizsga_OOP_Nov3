package org.example;

public class Main {


    public static void main(String[] args) {
        System.out.println("--- Vizsga Program Indul ---");


        DataParser parser = new DataParser();


        RootData data = parser.parse();


        if (data != null) {

            System.out.println("Sikeresen beolvasott félév: " + data.getTerm());

            ReportGenerator generator = new ReportGenerator();

            generator.printConsoleReport(data);

        } else {

            System.err.println("A program leáll, mert az adatok beolvasása sikertelen volt.");

        }

        System.out.println("--- Vizsga Program Vége ---");
    }
}
