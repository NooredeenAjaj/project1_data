package databaseManger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import clientUtil.*;

public class DivisionBuilder {

    private static File file = new File("project1_data/Database/personInformation");
    private static ArrayList<Division> divisions = new ArrayList<>();
    private static ArrayList<Person> members = new ArrayList<>();
    private static String divisionId = "";
    private static String divisionName = "";
}