import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Bai8 {
    public static void main(String[] args) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("input_8/cities.dat"));
        ArrayList<City> cities = new ArrayList<>();
        for (City city = (City) objectInputStream.readObject(); city != null; city = (City) objectInputStream.readObject()) {
            cities.add(city);
        }
        cities.forEach(city -> System.out.println(city.name));
    }
}

class Country {
    String code;
    String name;
    String continent;
    double surfaceArea;
    int population;
    double gnp;
    int capital;
}

class City {
    int id;
    String name;
    int population;
}


