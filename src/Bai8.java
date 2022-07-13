import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Bai8 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader("input_8/cities.dat"));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader("input_8/countries.dat"));
        List<City> cities = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        for (String line = bufferedReader1.readLine(); line != null; line = bufferedReader1.readLine()) {
            line = line.substring(line.indexOf("[") + 1, line.length() - 1);
            String[] fields = line.split(", ");
            int id = Integer.parseInt(fields[0].substring(fields[0].indexOf("=") + 1));
            String name = fields[1].substring(fields[1].indexOf("=") + 1);
            int population = Integer.parseInt(fields[2].substring(fields[2].indexOf("=") + 1));
            String countryCode = fields[3].substring(fields[3].indexOf("=") + 1);
            cities.add(new City(id, name, population, countryCode));
        }
        for (String line = bufferedReader2.readLine(); line != null; line = bufferedReader2.readLine()) {
            line = line.substring(line.indexOf("{") + 1, line.length() - 1);
            String[] fields = line.split(", ");
            String code = fields[0].substring(fields[0].indexOf("=") + 1);
            String name = fields[1].substring(fields[1].indexOf("=") + 1);
            String continent = fields[2].substring(fields[2].indexOf("=") + 1);
            double surfaceArea = Double.parseDouble(fields[3].substring(fields[3].indexOf("=") + 1));
            int population = Integer.parseInt(fields[4].substring(fields[4].indexOf("=") + 1));
            double gnp = Double.parseDouble(fields[5].substring(fields[5].indexOf("=") + 1));
            int capital = Integer.parseInt(fields[6].substring(fields[6].indexOf("=") + 1));
            countries.add(new Country(code, name, continent, surfaceArea, population, gnp, capital));
        }
        bufferedReader1.close();
        bufferedReader2.close();

        // Thanh pho la thu do dong dan nhat
        Optional<City> capital = countries.parallelStream().map(country -> {
            for (City city : cities) {
                if (city.id == country.capital) return city;
            }
            return null;
        }).max((city1, city2) -> {
            if (city2 == null && city1 == null) return 0;
            if (city1 == null) return city2.population;
            if (city2 == null) return city1.population;
            return city1.population - city2.population;
        });

        // Thanh pho dong dan nhat moi quoc gia
        Map<Country, Optional<City>> map = cities.stream().collect(Collectors.groupingBy(city -> {
            for (Country country : countries) {
                if (country.code.equals(city.countryCode)) return country;
            }
            return null;
        }, HashMap::new, Collectors.maxBy(Comparator.comparingInt(city -> city.population))));
        // Thanh pho dong dan nhat moi luc dia
        Map<String, Optional<City>> map1 = cities.stream().collect(Collectors.groupingBy(city -> {
            for (Country country : countries) {
                if (country.code.equals(city.countryCode)) return country.continent;
            }
            return null;
        }, HashMap::new, Collectors.maxBy(Comparator.comparingInt(city -> city.population))));

        // Thanh pho la thu do dong dan nhat moi luc dia
        Map<String, Optional<City>> map2 = cities.stream().filter(city -> {
            for (Country country : countries) {
                if (country.capital == city.id) return true;
            }
            return false;
        }).collect(Collectors.groupingBy(city -> {
            for (Country country : countries) {
                if (country.code.equals(city.countryCode)) return country.continent;
            }
            return null;
        }, HashMap::new, Collectors.maxBy(Comparator.comparingInt(city -> city.population))));

        // Sap xep cac quoc gia theo so luong thanh pho giam dan
        ArrayList<Country> countries1 = countries.stream().sorted((country1, country2) -> {
            return (int) (cities.stream().filter(city -> city.countryCode.equals(country2.code)).count() -
                    cities.stream().filter(city -> city.countryCode.equals(country1.code)).count());
        }).collect(Collectors.toCollection(ArrayList::new));

        // Sap xep cac quoc gia theo mat do dan so theo thu tu giam dan
        ArrayList<Country> countries2 = countries.stream()
                .filter(country -> country.population != 0)
                .sorted((country1, country2) -> {
                    long numberOfCity1 = cities.stream().filter(city -> city.countryCode.equals(country1.code)).count();
                    long numberOfCity2 = cities.stream().filter(city -> city.countryCode.equals(country2.code)).count();
                    return (int) (country2.population / numberOfCity2 - country1.population / numberOfCity1);
                })
                .collect(Collectors.toCollection(ArrayList::new));
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

    public Country(String code, String name, String continent, double surfaceArea, int population, double gnp, int capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfaceArea = surfaceArea;
        this.population = population;
        this.gnp = gnp;
        this.capital = capital;
    }
}

class City {
    int id;
    String name;
    int population;
    String countryCode;

    public City(int id, String name, int population, String countryCode) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.countryCode = countryCode;
    }
}


