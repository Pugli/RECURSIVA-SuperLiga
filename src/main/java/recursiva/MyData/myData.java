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
        try { //ABRO Y LEO ARCHIVOS
            CSVReader reader = null;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine = null;
            while ((nextLine = reader.readNext()) != null) {
                count++; //CUENTO PERSONAS
            }
            reader.close();
            System.out.println("cantidad de socios en total es:  " + count); //MUESTRO POR PANTALLA
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
        try { //ABRO Y LEO ARCHIVOS
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[2].equals("Racing")) { //FILTRO POR SOCIOS DE RACING Y ASIGNO VARIABLES
                    int age = Integer.parseInt(nextLine[1]);
                    total = total + age;
                    count++;
                }
            }
            reader.close();
            float average = (float) total / (float) count; //CALCULO PROMEDIO Y MUESTRO
            System.out.println("El promedio de edad entre los socios de racing es de: " + average);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void excercise3() { //PUNTO 3
        int count = 0;
        try { //ABRO Y LEO ARCHIVOS
            List<Person> personList = new ArrayList<Person>();
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null && personList.size() < 100) {
                if (nextLine[3].equals("Casado") && nextLine[4].equals("Universitario")) { //FILTRO POR CASADO Y UNIVERSITARIO Y ASIGNO VARIABLES
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
            for (Person us : personList) { //MUESTRO POR PANTALLA
                System.out.println("nombre: " + us.getName() + " Edad: " + us.getAge() + " hincha de: " + us.getTeam());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void order(List list) { //COMPARO POR EDADES
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void excercise4() { // PUNTO 4
        try { //ABRO Y LEO EL ARCHIVO
            List<String> nameList = new ArrayList<String>();
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[2].equals("River")) { //FILTRO POR SOCIOS DE RIVER
                    nameList.add(nextLine[0]);
                }
            }
            reader.close();
            orderByname(nameList); //ORDENO Y AGRUPO POR NOMBRES
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void orderByname(List<String> nameList) {
        int i = 0;
        int o = 0;
        List<Utilities> newList = new ArrayList<Utilities>();
        while (nameList != null && nameList.size() > i) { //MUENTRAS NO ME EXCEDA DE LA LISTA
            int counti = 1;
            if (!(nameList.get(i).equals(" "))) { //TRABAJO SI NO ESTA REMOVIDO EL OBJETO A COMPARAR
                String name = nameList.get(i);
                o = i + 1;
                while (nameList.size() > o) { //MIENTRAS NO ME EXCEDA
                    if (nameList.get(i).equals(nameList.get(o))) { //SI SON IGUALES SUMO Y REMUEVO DE LA LISTA
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
        System.out.println("Los nombres mas comunes entre los hinchas de River son: "); //MUESTRO POR PANTALLA
        for (int n = 0; n < 5; n++) {
            System.out.println(newList.get(n).getName());
        }
    }

    private void orderByCounti(List list) { //COMPARO CANTIDADES
        Collections.sort(list, new Comparator<Utilities>() {
            @Override
            public int compare(Utilities p1, Utilities p2) {
                return Integer.compare(p2.getCount(), p1.getCount());
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void excercise5() { //PUNTO 5
        try { //ABRO Y LEO ARCHIVO
            List<Person> teamList = new ArrayList<>();
            CSVReader reader;
            reader = new CSVReader(new FileReader("C:\\WorkSpace\\SuperLiga\\myData\\socios.csv"), SEPARATOR, QUOTE);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) { //ASIGNO VARIABLES
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

    private void orderByteam(List<Person> nameList) { //ORDENO POR EQUIPOS Y MUESTRO EDADES
        int i = 0;
        int o = 0;
        List<Utilities> newList = new ArrayList<Utilities>();
        while (nameList != null && nameList.size() > i) {
            int counti = 1;
            if (!(nameList.get(i).equals(""))) { // SI NO ESTA REMOVIDO EL DATO A COMPARAR DE LA LISTA
                String name = nameList.get(i).getTeam(); //ASIGNO EDADES PARA LUEGO COMPARAR
                int higher = nameList.get(i).getAge();
                int less = nameList.get(i).getAge();
                float total = (float) nameList.get(i).getAge();
                o = i + 1;
                while (nameList.size() > o) { //MIENTRAS NO ME EXCEDA DE LA LISTA
                    if (nameList.get(i).getTeam().equals(nameList.get(o).getTeam())) { //SI SON DEL MISMO EQUIPO TRABAJO Y COMPARO EDADES
                        higher = SearchHiger(higher, nameList.get(o).getAge());
                        less = searchLess(less, nameList.get(o).getAge());
                        total = total + (float) nameList.get(o).getAge();
                        counti++;
                        nameList.remove(o);
                        o--;
                    }
                    o++;
                }
                float prom = total / (float) counti; //CALCULO PROMEDIO
                Utilities utilities = new Utilities(counti, name, higher, less, prom);
                newList.add(utilities);
            }
            i++;
        }
        orderByCounti(newList);
        for (Utilities us : newList) { //MUESTRO POR PANTALLA
            System.out.println("Equipo: " + us.getName() + " Cantidad de socios: " + us.getCount() + " Edad mayor: " + us.getHihger() + " Edad menor: " + us.getLess() + " edad promedio: " + us.getProm());
        }
    }

    private int SearchHiger(int old, int newi) { //CALCULO MAYOR
        if (old > newi) {
            return old;
        } else {
            return newi;
        }
    }

    private int searchLess(int old, int newi) { //CALCULO MENOR
        if (old > newi) {
            return newi;
        } else {
            return old;
        }
    }
}
