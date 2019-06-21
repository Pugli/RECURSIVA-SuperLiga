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
import recursiva.Utilities.Utilities;

public class myData {
    public final char SEPARATOR = ';';
    public final char QUOTE = '"';

    public void exercise2() { //PUNTO 2
        int count = 0;
        int total = 0;
        try {
            //List<Person> personList = new ArrayList<Person>();
            CSVReader reader = null;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
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
            float average = (float) total / (float) count;
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
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
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

    public void excercise4() {
        try {
            List<String> nameList = new ArrayList<String>();
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[2].equals("River")) {
                    nameList.add(nextLine[0]);
                }
            }
            reader.close();
            orderByname(nameList);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void orderByname(List nameList) {
        int i = 0;
        int o = 0;

        List<Utilities> newList = new ArrayList<Utilities>();

        while (nameList != null && nameList.size() > i) {
            int counti = 1;
            if (!(nameList.equals(""))) {
                Object name = nameList.get(i);
                o = i + 1;
                while (nameList.size() > o) {
                    if (nameList.get(i).equals(nameList.get(o))) {
                        counti++;
                        nameList.remove(o);
                    }
                    o++;
                }
                Utilities utilities = new Utilities(counti, (String) name);
                newList.add(utilities);
            }
            i++;
        }
        orderByCounti(newList);
        System.out.println("Los nombres mas comunes entre los hinchas de River son: ");
        for (int n = 0; n < 5; n++) {
            System.out.println(newList.get(n).getName());
        }

    }

    public void orderByCounti(List list) {
        Collections.sort(list, new Comparator<Utilities>() {
            @Override
            public int compare(Utilities p1, Utilities p2) {
                return new Integer(p2.getCount()).compareTo(new Integer(p1.getCount()));
            }
        });
    }
}