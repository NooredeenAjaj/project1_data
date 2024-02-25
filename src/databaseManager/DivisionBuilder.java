package databaseManger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import clientUtil.*;

public class DivisionBuilder {

    private static File file = new File(
            "../Database/personInformation");
    private static ArrayList<Division> divisions = new ArrayList<>();
    private static ArrayList<Person> members = new ArrayList<>();
    private static String divisionId = "";
    private static String divisionName = "";

    public void build() {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                String[] divisionInfo = line.split(":");
                divisionId = divisionInfo[0];
                divisionName = divisionInfo[1];

                members = createPersons(scanner);
                divisions.add(new Division(divisionId, divisionName, members));
                members = new ArrayList<>();

                divisionId = "";
                divisionName = "";

            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Person> createPersons(Scanner data) {
        ArrayList<Person> res = new ArrayList<>();
        while (data.hasNextLine()) {
            String line = data.nextLine();
            if (line.startsWith("+++")) {

                return res;
            }
            String[] parts = line.split(":");

            String ID = parts[0];
            String name = parts[1];
            String role = parts[2];
            String password = parts[3];
            switch (role) {
                case "Nurse":
                    res.add(new Nurse(name, password, "Cancer Center", Integer.parseInt(ID)));
                    break;
                case "Doctor":
                    res.add(new Doctor(name, password, "Cancer Center", Integer.parseInt(ID)));
                    break;
                case "Patient":
                    res.add(new Patient(name, password, "Cancer Center", Integer.parseInt(ID)));
                    break;
                case "GovernmentAgency":
                    res.add(new GovernmentAgency(name, password, Integer.valueOf(ID)));
                    break;

            }

        }
        return res;
    }

    public static void main(String[] args) {
        {
            DivisionBuilder builder = new DivisionBuilder();
            builder.build();

            for (Division division : divisions) {
                System.out.println("Division ID: " + division.getID() + ", Name: " +
                        division.getName());
                System.out.println("Members:");
                for (Person person : division.getMembers()) {
                    System.out.println("\t" + person.getUsername() + " ID" + person.getID() + " div "
                            + person.getDivision() + " pas " + person.getPassword());
                }
            }

        }
    }
}
