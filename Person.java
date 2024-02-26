

public class Person {
    private String username;
    private String password;
    private String division;
    private int ID;

    public Person(String username, String password, String division, int ID) {
        this.username = username;
        this.password = password;
        this.division = division;
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDivision() {
        return division;
    }

    public int getID() {
        return ID;
    }

}
