import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ArrList {

    public static void main(String[] args) {
        String StrCom;
        MCommands mCommands;
        Scanner Sc;

        Map<Integer, Person> people = new HashMap<Integer, Person>();
        people.put(1, new Person("Ann", "Moscow", new GregorianCalendar(2002, 2, 30)));
        people.put(2, new Person("Jo", "Irkutsk", new GregorianCalendar(2001, 2, 22)));
        people.put(3, new Person("Maria", "London", new GregorianCalendar(2003, 2, 30)));
        people.put(4, new Person("Alex", "Moscow", new GregorianCalendar(2001, 3, 23)));

        while (true) {
            System.out.print(">>");
            Sc = new Scanner(System.in);
            StrCom = Sc.nextLine();
            try {
                mCommands = MCommands.valueOf(StrCom);
            } catch (Exception ex) {
                System.out.println("Command is not found");
                continue;
            }
            switch (mCommands) {
                case input -> {
                    try {
                        System.out.println("Count of new entry >> ");
                        int count = Sc.nextInt();
                        for (int i = 1; i < count + 1; i++) {
                            System.out.println("id:");
                            int id = Sc.nextInt();
                            System.out.println("Name:");
                            String name = Sc.next();
                            System.out.println("Address:");
                            String address = Sc.next();

                            System.out.println("Year yyyy:");
                            int year = Sc.nextInt();

                            System.out.println("Month mm:");
                            int month = Sc.nextInt();

                            System.out.println("Day:");
                            int day = Sc.nextInt();

                            people.put(id, new Person(name, address, new GregorianCalendar(year, month, day)));
                        }

                        System.out.println("\n~~~List of clients after the change~~~");

                        for (Map.Entry<Integer, Person> item : people.entrySet()) {
                            System.out.printf("ID: %s  Info: %s %s  %s \n", item.getKey(), item.getValue().getName(),
                                    item.getValue().getAddress(), item.getValue().getDate());
                        }
                    } catch (Exception ex) {
                        System.out.println("Error fill in from the beginning!");
                    }

                }
                case remove -> {
                    System.out.println("Number of the entry to remove: ");
                    int choice = Sc.nextInt();
                    people.remove(choice--);
                    System.out.println("\n~~~List of clients after the change~~~");

                    for (Map.Entry<Integer, Person> item : people.entrySet()) {
                        System.out.printf("ID: %s  Info: %s %s  %s \n", item.getKey(), item.getValue().getName(),
                                item.getValue().getAddress(), item.getValue().getDate());
                    }

                    people.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v.getAddress()));


                }
                case sorted -> {
                    List<Map.Entry<Integer, Person>> valuesList = new ArrayList(people.entrySet());
                    Collections.sort(valuesList, new Comparator<Map.Entry<Integer, Person>>() {
                        @Override
                        public int compare(Map.Entry<Integer, Person> o1, Map.Entry<Integer, Person> o2) {
                            return o1.getValue().getDate().compareTo(o2.getValue().getDate());
                        }
                    });
                    for (Map.Entry<Integer, Person> item : valuesList) {
                        System.out.printf("ID: %s  Info: %s %s  %s \n", item.getKey(), item.getValue().getName(),
                                item.getValue().getAddress(), item.getValue().getDate());

                    }

                }

                case exit -> {
                    System.exit(0);
                    System.exit(0);
                }

            }

        }


    }

        /*System.out.println("________________");
        people.forEach((k,v) -> System.out.println("Key = " + k + ", Value = " + v.getAddress()));

         */

}


class Person {
    String name, address;
    Calendar date;

    public Person(String name, String address, Calendar date) {
        this.address = address;
        this.date = date;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("dd MMM yyy ");
        return df.format(date.getTime());

    }


}

enum MCommands {
    sorted,
    input,
    remove,
    exit
}

/*
class ValueCompare{
    Map<String,Person> people;

    public  ValueCompare(Map<String,Person> people){
        this.people=people;
    }
    List <Map.Entry<Integer, Person>> valuesList = new ArrayList(people.entrySet());
Collections.sort(valuesList, new Comparator<Map.Entry<Integer, String>>() {
        @Override
        public int compare(Map.Entry<Integer, Person> o1, Map.Entry<Integer, Person> o2) {
            return o1.getValue().getDate().compareTo(o2.getValue().getDate());
        }
    }
}
*/
