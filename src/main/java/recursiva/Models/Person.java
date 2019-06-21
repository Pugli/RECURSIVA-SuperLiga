package recursiva.Models;

public class Person {
    private String name;
    private int age;
    private String team;
    private String maritalStatus;
    private String studies;

    public Person() {
    }

    public Person(String name, int age, String team, String maritalStatus, String studies) {
        this.name = name;
        this.age = age;
        this.team = team;
        this.maritalStatus = maritalStatus;
        this.studies = studies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }
}
