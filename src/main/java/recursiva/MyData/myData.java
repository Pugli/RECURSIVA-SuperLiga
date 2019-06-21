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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void exercise1() { //PUNTO 1
        int count = 0;
        try {
            CSVReader reader = null;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine = null;
            while ((nextLine = reader.readNext()) != null) {
                count++;
            }
            reader.close();
            System.out.println("cantidad de socios en total es:  " + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void exercise2() { //PUNTO 2
        int count = 0;
        int total = 0;
        try {
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[2].equals("Racing")) {
                    int age = Integer.parseInt(nextLine[1]);
                    total = total + age;
                    count++;
                }
            }
            reader.close();
            float average = (float) total / (float) count;
            System.out.println("El promedio de edad entre los socios de racing es de: " + average);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void order(List list) {
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void excercise4() { // PUNTO 4
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void orderByname(List<String> nameList) {
        int i = 0;
        int o = 0;
        List<Utilities> newList = new ArrayList<Utilities>();
        while (nameList != null && nameList.size() > i) {
            int counti = 1;
            if (!(nameList.get(i).equals(" "))) {
                String name = nameList.get(i);
                o = i + 1;
                while (nameList.size() > o) {
                    if (nameList.get(i).equals(nameList.get(o))) {
                        counti++;
                        nameList.remove(o);
                        o--;
                    }
                    o++;
                }
                Utilities utilities = new Utilities(counti, name);
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

    private void orderByCounti(List list) {
        Collections.sort(list, new Comparator<Utilities>() {
            @Override
            public int compare(Utilities p1, Utilities p2) {
                return Integer.compare(p2.getCount(), p1.getCount());
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void excercise5() { //PUNTO 5
        try {
            List<Person> teamList = new ArrayList<>();
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Person x = new Person();
                x.setAge(Integer.parseInt(nextLine[1]));
                x.setTeam(nextLine[2]);
                teamList.add(x);
            }
            reader.close();
            orderByteam(teamList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void orderByteam(List<Person> nameList) {
        int i = 0;
        int o = 0;
        List<Utilities> newList = new ArrayList<Utilities>();
        while (nameList != null && nameList.size() > i) {
            int counti = 1;
            if (!(nameList.get(i).equals(""))) {
                String name = nameList.get(i).getTeam();
                int higher = nameList.get(i).getAge();
                int less = nameList.get(i).getAge();
                float total = (float) nameList.get(i).getAge();
                o = i + 1;
                while (nameList.size() > o) {
                    if (nameList.get(i).getTeam().equals(nameList.get(o).getTeam())) {
                        higher = SearchHiger(higher, nameList.get(o).getAge());
                        less = searchLess(less, nameList.get(o).getAge());
                        total = total + (float) nameList.get(o).getAge();
                        counti++;
                        nameList.remove(o);
                        o--;
                    }
                    o++;
                }
                float prom = total / (float) counti;
                Utilities utilities = new Utilities(counti, name, higher, less, prom);
                newList.add(utilities);
            }
            i++;
        }
        orderByCounti(newList);
        for (Utilities us : newList) {
            System.out.println("Equipo: " + us.getName() + " Cantidad de socios: " + us.getCount() + " Edad mayor: " + us.getHihger() + " Edad menor: " + us.getLess() + " edad promedio: " + us.getProm());
        }
    }

    private int SearchHiger(int old, int newi) {
        if (old > newi) {
            return old;
        } else {
            return newi;
        }
    }

    private int searchLess(int old, int newi) {
        if (old > newi) {
            return newi;
        } else {
            return old;
        }
    }
}