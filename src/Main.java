//İlke Durmaz, 02 Jan 2024

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph cityGraph = new Graph(81);

        //******************************************************SETTING ADJACENT CITIES*********************************************************************
        try (BufferedReader br = new BufferedReader(new FileReader("adjacent cities.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] cities = line.split(",");
                String currentCity = cities[0];
                cityGraph.add_vertex(currentCity);
                for (int i = 1; i < cities.length; i++) {
                    cityGraph.add_vertex(cities[i]);
                    cityGraph.add_edge(currentCity, cities[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cityGraph);

        //******************************************************SETTING DISTANCES*****************************************************************************
        HashMap<String, Integer> indexInFile = new HashMap<>();

        indexInFile.put("Adana", 2);
        indexInFile.put("Adiyaman", 3);
        indexInFile.put("Afyon", 4);
        indexInFile.put("Agri", 5);
        indexInFile.put("Amasya", 6);
        indexInFile.put("Ankara", 7);
        indexInFile.put("Antalya", 8);
        indexInFile.put("Artvin", 9);
        indexInFile.put("Aydin", 10);
        indexInFile.put("Balikesir", 11);
        indexInFile.put("Bilecik", 12);
        indexInFile.put("Bingol", 13);
        indexInFile.put("Bitlis", 14);
        indexInFile.put("Bolu", 15);
        indexInFile.put("Burdur", 16);
        indexInFile.put("Bursa", 17);
        indexInFile.put("Canakkale", 18);
        indexInFile.put("Cankiri", 19);
        indexInFile.put("Corum", 20);
        indexInFile.put("Denizli", 21);
        indexInFile.put("Diyarbakir", 22);
        indexInFile.put("Edirne", 23);
        indexInFile.put("Elazig", 24);
        indexInFile.put("Erzincan", 25);
        indexInFile.put("Erzurum", 26);
        indexInFile.put("Eskisehir", 27);
        indexInFile.put("Gaziantep", 28);
        indexInFile.put("Giresun", 29);
        indexInFile.put("Gumushane", 30);
        indexInFile.put("Hakkari", 31);
        indexInFile.put("Hatay", 32);
        indexInFile.put("Isparta", 33);
        indexInFile.put("Icel", 34);
        indexInFile.put("Istanbul", 35);
        indexInFile.put("Izmir", 36);
        indexInFile.put("Kars", 37);
        indexInFile.put("Kastamonu", 38);
        indexInFile.put("Kayseri", 39);
        indexInFile.put("Kirklareli", 40);
        indexInFile.put("Kirsehir", 41);
        indexInFile.put("Kocaeli", 42);
        indexInFile.put("Konya", 43);
        indexInFile.put("Kutahya", 44);
        indexInFile.put("Malatya", 45);
        indexInFile.put("Manisa", 46);
        indexInFile.put("Kahramanmaras", 47);
        indexInFile.put("Mardin", 48);
        indexInFile.put("Mugla", 49);
        indexInFile.put("Mus", 50);
        indexInFile.put("Nevsehir", 51);
        indexInFile.put("Nigde", 52);
        indexInFile.put("Ordu", 53);
        indexInFile.put("Rize", 54);
        indexInFile.put("Sakarya", 55);
        indexInFile.put("Samsun", 56);
        indexInFile.put("Siirt", 57);
        indexInFile.put("Sinop", 58);
        indexInFile.put("Sivas", 59);
        indexInFile.put("Tekirdag", 60);
        indexInFile.put("Tokat", 61);
        indexInFile.put("Trabzon", 62);
        indexInFile.put("Tunceli", 63);
        indexInFile.put("Sanliurfa", 64);
        indexInFile.put("Usak", 65);
        indexInFile.put("Van", 66);
        indexInFile.put("Yozgat", 67);
        indexInFile.put("Zonguldak", 68);
        indexInFile.put("Aksaray", 69);
        indexInFile.put("Bayburt", 70);
        indexInFile.put("Karaman", 71);
        indexInFile.put("Kirikkale", 72);
        indexInFile.put("Batman", 73);
        indexInFile.put("Sirnak", 74);
        indexInFile.put("Bartin", 75);
        indexInFile.put("Ardahan", 76);
        indexInFile.put("Igdir", 77);
        indexInFile.put("Yalova", 78);
        indexInFile.put("Karabuk", 79);
        indexInFile.put("Kilis", 80);
        indexInFile.put("Osmaniye", 81);
        indexInFile.put("Duzce", 82);

        try (BufferedReader br = new BufferedReader(new FileReader("City Distances.txt"))) {
            String line;
            boolean skipFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (skipFirstLine) {
                    skipFirstLine = false;
                    continue; //skip the first line because it only contains headers
                }

                String[] parts = line.split(";");
                String sourceCity = parts[1];

                List<String> neighborsOfSourceCity = cityGraph.neighbors(sourceCity);//check out Adana's adjacent cities(Hatay for example)

                for (int i = 0; i < neighborsOfSourceCity.size(); i++) {
                    String destination = neighborsOfSourceCity.get(i); //check out every adjacent cities of it
                    int index = indexInFile.get(destination); //find the line index that represents that adjacent (Hatay) from indexInFile -which is the value
                    int distance = Integer.parseInt(parts[index]); //the number that is on that index of line is the distance
                    cityGraph.set_edge_value(sourceCity, destination, distance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //******************************************************MENU*************************************************************
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        Scanner scan4 = new Scanner(System.in);
        Scanner scan5 = new Scanner(System.in);
        //i get errors when i use only 1 scanner, i didn't want to solve it naively

        String selecetedCity = null;
        boolean isRunning = true;

        do {
            System.out.println();
            System.out.println("a. Enter city(or select)\n" +
                    "b. Print selected(or entered) city\n" +
                    "c. List k closest  cities (to selected city)\n" +
                    "d. Find shortest path to\n" +
                    "x. exit\n");

            String choice = scan1.nextLine();

            if(choice.equals("a") || choice.equals("A")){
                System.out.println("City Name:");
                selecetedCity = scan2.nextLine();
            }
            else if(choice.equals("b") || choice.equals("B")){
                if(selecetedCity!=null){
                    System.out.println(selecetedCity);
                }
                else {
                    System.out.println("You haven't selected a city yet.");
                }
            }
            else if(choice.equals("c") || choice.equals("C")){
                if(selecetedCity!=null){
                    System.out.println("Enter an integer to find k closest cities to " + selecetedCity);
                    int k = scan4.nextInt();
                    cityGraph.closestCities(selecetedCity, k);
                }
                else {
                    System.out.println("You haven't selected a city yet. Select a city.");
                    selecetedCity = scan5.nextLine();
                    System.out.println("Enter an integer to find k closest cities to " + selecetedCity);
                    int k = scan4.nextInt();
                    cityGraph.closestCities(selecetedCity, k);
                }
            }
            else if(choice.equals("d") || choice.equals("D")){
                if(selecetedCity!=null){
                    System.out.println("Enter a city to find shortest destination to from " + selecetedCity);
                    String destinationCity = scan3.nextLine();
                    cityGraph.shortestPath(selecetedCity,destinationCity);
                }
                else {
                    System.out.println("You haven't selected a city yet.");
                }
            }
            else if(choice.equals("x") || choice.equals("X")){
                System.out.println("Exiting...");
                isRunning = false;
            }
            else{
                System.out.println("Wrong input!");
            }


        }while(isRunning);
    }
}


