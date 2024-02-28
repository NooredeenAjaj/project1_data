

import java.util.ArrayList;

public class Division {
    private String id;
    private String name;
    private ArrayList<Person> members;

    public Division(String id, String name, ArrayList<Person> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Person> getMembers() {
        return this.members;
    }

}
