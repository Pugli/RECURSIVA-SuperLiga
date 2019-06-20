package recursiva.MyData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.opencsv.CSVReader;
import recursiva.Models.Person;

public class myData {
    public final char SEPARATOR = ';';
    public final char QUOTE = '"';

    public void exercise2() { //PUNTO 2
        int count = 0;
        int total = 0;
        try {
            //List<Person> personList = new ArrayList<Person>();
            CSVReader reader = null;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\ChallengeSuperliga\\Data\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine = null;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[2].equals("Racing")) {
                    int age = Integer.parseInt(nextLine[1]);
                    total = total + age;
                    count++;
                    // personList.add(new Person(name,age, team, maritalStatus,studies));
                }
            }
            reader.close();
            float average = total / count;
            System.out.println("El promedio de edad entre los socios de racing es de: " + average);
               /* for(Person us : personList){

                    System.out.println("nombre: "+us.getName()+" Edad: "+us.getAge()+" hincha de: "+us.getTeam()+
                            " Estado civil: "+us.getMaritalStatus()+" Estudios: "+us.getStudies());
                } */

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void excercise3() { //PUNTO 3
        int count = 0;
        try {
            List<Person> personList = new ArrayList<Person>();
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\ChallengeSuperliga\\Data\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null && personList.size() < 100) {
                if (nextLine[3].equals("Casado") && nextLine[4].equals("Universitario")) {
                    String name = nextLine[0];
                    int age = Integer.parseInt(nextLine[1]);
                    String team = nextLine[2];
                    String maritalStatus = nextLine[3];
                    String studies = nextLine[4];
                    personList.add(new Person(name, age, team, maritalStatus, studies));
                    count++;
                }
            }
            reader.close();
            order(personList);
            for (Person us : personList) {
                System.out.println("nombre: " + us.getName() + " Edad: " + us.getAge() + " hincha de: " + us.getTeam());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void order(List list) {
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return new Integer(p1.getAge()).compareTo(new Integer(p2.getAge()));
            }
        });
    }


}