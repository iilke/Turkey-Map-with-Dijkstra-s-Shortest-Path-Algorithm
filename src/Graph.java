//İlke Durmaz, 02 Jan 2024

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

class Graph {
    private int V;
    private int E;
    private String[] labelsOfNodes; //city names
    private int top=0; //i added this keep track of top index of labelsOfNodes
    private Map<String, List<String>> adj;
    private Map<String, Map<String, Integer>> edgeWeights;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        labelsOfNodes = new String[V];
        adj = new HashMap<>();
        edgeWeights = new HashMap<>();
        for (int i = 0; i < V; i++) {
            edgeWeights.put(labelsOfNodes[i], new HashMap<>());
        }
    }

    public boolean adjacent(String x, String y) {//to check if 2 cities are adjacent
        List<String> neighbors = adj.getOrDefault(x, new ArrayList<>());
        return neighbors.contains(y);
    }

    public List<String> neighbors(String x) {//return the neighbors of a city
        return adj.getOrDefault(x, new ArrayList<>());
    }

    public void add_vertex(String x) {
        if (!adj.containsKey(x)){
            adj.put(x, new ArrayList<>());
            labelsOfNodes[top] = x;
            top++;
        }
    }

    public void remove_vertex(String x) {
        if (adj.containsKey(x)) {
            adj.remove(x);
            int index = getIndex(x);
            if (index != -1) {
                labelsOfNodes[index] = null;
                top--;
            }
        }
    }

    public void add_edge(String x, String y) {
        if (!adjacent(x, y)) {
            adj.get(x).add(y);
            adj.get(y).add(x);
            E++;
        }
    }

    public void remove_edge(String x, String y) {
        if (adjacent(x, y)) {
            adj.get(x).remove(y);
            adj.get(y).remove(x);
            E--;
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public String get_vertex_value(int x) {
        return labelsOfNodes[x];
    }

    public void set_vertex_value(int x, String v) {
        labelsOfNodes[x] = v;
    }

    public void get_edge_value(String x, String y) {
        if (edgeWeights.containsKey(x) && edgeWeights.get(x).containsKey(y)) {
            int weight = edgeWeights.get(x).get(y);
            System.out.println("Edge weight between " + x + " and " + y + " is: " + weight);
        } else {
            System.out.println("Edge between " + x + " and " + y + " does not exist.");
        }
    }

    public void set_edge_value(String x, String y, int v) {
        if (adjacent(x, y)) {
            if (!edgeWeights.containsKey(x)) {
                edgeWeights.put(x, new HashMap<>());
            }
            if (!edgeWeights.containsKey(y)) {//v distance to y city because (Map<String, Map<String, Integer>> edgeWeights)
                edgeWeights.put(y, new HashMap<>());
            }
            edgeWeights.get(x).put(y, v);
            edgeWeights.get(y).put(x, v);
        }
    }

    private int getIndex(String city) {
        for (int i = 0; i < labelsOfNodes.length; i++) {
            if (city.equals(labelsOfNodes[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String node : adj.keySet()) {
            sb.append(node).append(" -> ").append(adj.get(node)).append("\n");
        }
        return sb.toString();
    }

   //**************************************************************MENU OPERATIONS*******************************************************************

    public void closestCities(String sourceCity, int k) {

        Map<String, Integer> sourceCityDistances = new HashMap<>(){{
            put("Adana", null);
            put("Adiyaman", null);
            put("Afyon", null);
            put("Agri", null);
            put("Amasya", null);
            put("Ankara", null);
            put("Antalya", null);
            put("Artvin", null);
            put("Aydin", null);
            put("Balikesir", null);
            put("Bilecik", null);
            put("Bingol", null);
            put("Bitlis", null);
            put("Bolu", null);
            put("Burdur", null);
            put("Bursa", null);
            put("Canakkale", null);
            put("Cankiri", null);
            put("Corum", null);
            put("Denizli", null);
            put("Diyarbakir", null);
            put("Edirne", null);
            put("Elazig", null);
            put("Erzincan", null);
            put("Erzurum", null);
            put("Eskisehir", null);
            put("Gaziantep", null);
            put("Giresun", null);
            put("Gumushane", null);
            put("Hakkari", null);
            put("Hatay", null);
            put("Isparta", null);
            put("Icel", null);
            put("Istanbul", null);
            put("Izmir", null);
            put("Kars", null);
            put("Kastamonu", null);
            put("Kayseri", null);
            put("Kirklareli", null);
            put("Kirsehir", null);
            put("Kocaeli", null);
            put("Konya", null);
            put("Kutahya", null);
            put("Malatya", null);
            put("Manisa", null);
            put("Kahramanmaras", null);
            put("Mardin", null);
            put("Mugla", null);
            put("Mus", null);
            put("Nevsehir", null);
            put("Nigde", null);
            put("Ordu", null);
            put("Rize", null);
            put("Sakarya", null);
            put("Samsun", null);
            put("Siirt", null);
            put("Sinop", null);
            put("Sivas", null);
            put("Tekirdag", null);
            put("Tokat", null);
            put("Trabzon", null);
            put("Tunceli", null);
            put("Sanliurfa", null);
            put("Usak", null);
            put("Van", null);
            put("Yozgat", null);
            put("Zonguldak", null);
            put("Aksaray", null);
            put("Bayburt", null);
            put("Karaman", null);
            put("Kirikkale", null);
            put("Batman", null);
            put("Sirnak", null);
            put("Bartin", null);
            put("Ardahan", null);
            put("Igdir", null);
            put("Yalova", null);
            put("Karabuk", null);
            put("Kilis", null);
            put("Osmaniye", null);
            put("Duzce", null);
        }};
        HashMap<String, Integer> lineIndexes = new HashMap<>();

        lineIndexes.put("Adana", 1);
        lineIndexes.put("Adiyaman", 2);
        lineIndexes.put("Afyon", 3);
        lineIndexes.put("Agri", 4);
        lineIndexes.put("Amasya", 5);
        lineIndexes.put("Ankara", 6);
        lineIndexes.put("Antalya", 7);
        lineIndexes.put("Artvin", 8);
        lineIndexes.put("Aydin", 9);
        lineIndexes.put("Balikesir", 10);
        lineIndexes.put("Bilecik", 11);
        lineIndexes.put("Bingol", 12);
        lineIndexes.put("Bitlis", 13);
        lineIndexes.put("Bolu", 14);
        lineIndexes.put("Burdur", 15);
        lineIndexes.put("Bursa", 16);
        lineIndexes.put("Canakkale", 17);
        lineIndexes.put("Cankiri", 18);
        lineIndexes.put("Corum", 19);
        lineIndexes.put("Denizli", 20);
        lineIndexes.put("Diyarbakir", 21);
        lineIndexes.put("Edirne", 22);
        lineIndexes.put("Elazig", 23);
        lineIndexes.put("Erzincan", 24);
        lineIndexes.put("Erzurum", 25);
        lineIndexes.put("Eskisehir", 26);
        lineIndexes.put("Gaziantep", 27);
        lineIndexes.put("Giresun", 28);
        lineIndexes.put("Gumushane", 29);
        lineIndexes.put("Hakkari", 30);
        lineIndexes.put("Hatay", 31);
        lineIndexes.put("Isparta", 32);
        lineIndexes.put("Icel", 33);
        lineIndexes.put("Istanbul", 34);
        lineIndexes.put("Izmir", 35);
        lineIndexes.put("Kars", 36);
        lineIndexes.put("Kastamonu", 37);
        lineIndexes.put("Kayseri", 38);
        lineIndexes.put("Kirklareli", 39);
        lineIndexes.put("Kirsehir", 40);
        lineIndexes.put("Kocaeli", 41);
        lineIndexes.put("Konya", 42);
        lineIndexes.put("Kutahya", 43);
        lineIndexes.put("Malatya", 44);
        lineIndexes.put("Manisa", 45);
        lineIndexes.put("Kahramanmaras", 46);
        lineIndexes.put("Mardin", 47);
        lineIndexes.put("Mugla", 48);
        lineIndexes.put("Mus", 49);
        lineIndexes.put("Nevsehir", 50);
        lineIndexes.put("Nigde", 51);
        lineIndexes.put("Ordu", 52);
        lineIndexes.put("Rize", 53);
        lineIndexes.put("Sakarya", 54);
        lineIndexes.put("Samsun", 55);
        lineIndexes.put("Siirt", 56);
        lineIndexes.put("Sinop", 57);
        lineIndexes.put("Sivas", 58);
        lineIndexes.put("Tekirdag", 59);
        lineIndexes.put("Tokat", 60);
        lineIndexes.put("Trabzon", 61);
        lineIndexes.put("Tunceli", 62);
        lineIndexes.put("Sanliurfa", 63);
        lineIndexes.put("Usak", 64);
        lineIndexes.put("Van", 65);
        lineIndexes.put("Yozgat", 66);
        lineIndexes.put("Zonguldak", 67);
        lineIndexes.put("Aksaray", 68);
        lineIndexes.put("Bayburt", 69);
        lineIndexes.put("Karaman", 70);
        lineIndexes.put("Kirikkale", 71);
        lineIndexes.put("Batman", 72);
        lineIndexes.put("Sirnak", 73);
        lineIndexes.put("Bartin", 74);
        lineIndexes.put("Ardahan", 75);
        lineIndexes.put("Igdir", 76);
        lineIndexes.put("Yalova", 77);
        lineIndexes.put("Karabuk", 78);
        lineIndexes.put("Kilis", 79);
        lineIndexes.put("Osmaniye", 80);
        lineIndexes.put("Duzce", 81);

        Map<String, Integer> columnIndexes = new HashMap<>(){{
            put("Adana", 2);
            put("Adiyaman", 3);
            put("Afyon", 4);
            put("Agri", 5);
            put("Amasya", 6);
            put("Ankara", 7);
            put("Antalya", 8);
            put("Artvin", 9);
            put("Aydin", 10);
            put("Balikesir", 11);
            put("Bilecik", 12);
            put("Bingol", 13);
            put("Bitlis", 14);
            put("Bolu", 15);
            put("Burdur", 16);
            put("Bursa", 17);
            put("Canakkale", 18);
            put("Cankiri", 19);
            put("Corum", 20);
            put("Denizli", 21);
            put("Diyarbakir", 22);
            put("Edirne", 23);
            put("Elazig", 24);
            put("Erzincan", 25);
            put("Erzurum", 26);
            put("Eskisehir", 27);
            put("Gaziantep", 28);
            put("Giresun", 29);
            put("Gumushane", 30);
            put("Hakkari", 31);
            put("Hatay", 32);
            put("Isparta", 33);
            put("Icel", 34);
            put("Istanbul", 35);
            put("Izmir", 36);
            put("Kars", 37);
            put("Kastamonu", 38);
            put("Kayseri", 39);
            put("Kirklareli", 40);
            put("Kirsehir", 41);
            put("Kocaeli", 42);
            put("Konya", 43);
            put("Kutahya", 44);
            put("Malatya", 45);
            put("Manisa", 46);
            put("Kahramanmaras", 47);
            put("Mardin", 48);
            put("Mugla", 49);
            put("Mus", 50);
            put("Nevsehir", 51);
            put("Nigde", 52);
            put("Ordu", 53);
            put("Rize", 54);
            put("Sakarya", 55);
            put("Samsun", 56);
            put("Siirt", 57);
            put("Sinop", 58);
            put("Sivas", 59);
            put("Tekirdag", 60);
            put("Tokat", 61);
            put("Trabzon", 62);
            put("Tunceli", 63);
            put("Sanliurfa", 64);
            put("Usak", 65);
            put("Van", 66);
            put("Yozgat", 67);
            put("Zonguldak", 68);
            put("Aksaray", 69);
            put("Bayburt", 70);
            put("Karaman", 71);
            put("Kirikkale", 72);
            put("Batman", 73);
            put("Sirnak", 74);
            put("Bartin", 75);
            put("Ardahan", 76);
            put("Igdir", 77);
            put("Yalova", 78);
            put("Karabuk", 79);
            put("Kilis", 80);
            put("Osmaniye", 81);
            put("Duzce", 82);
        }};

        int lineIndex = lineIndexes.get(sourceCity); //find the source city's line


        try (BufferedReader br = Files.newBufferedReader(Paths.get("City Distances.txt"))) {

            //skip until the necessary line
            for (int i = 0; i < (lineIndex); i++) {
                br.readLine();
            }

            //read only source city's line
            String line = br.readLine();
            String[] parts = line.split(";");

            for (String city : columnIndexes.keySet()) {
                int cityIndex = columnIndexes.get(city);
                int distance = Integer.parseInt(parts[cityIndex]);
                sourceCityDistances.put(city, distance);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Map.Entry<String, Integer>> sortedDistances = new ArrayList<>(sourceCityDistances.entrySet());
        sortedDistances.sort(Map.Entry.comparingByValue());


        System.out.println("The closest " + k + " cities to " + sourceCity + ":");
        for (int i = 1; i < k+1; i++) {//starts from 1 so it doesn't include itself
            System.out.println(sortedDistances.get(i).getKey() + ": " + sortedDistances.get(i).getValue() + " distance");
        }

    }



    public void shortestPath(String sourceCity, String destinationCity) {//dijkstra
        Map<String, Integer> distTo = new HashMap<>();
        Map<String, String> edgeTo = new HashMap<>();
        Set<String> known = new HashSet<>();

        for (String node : labelsOfNodes) {//all vertices as infinite
            distTo.put(node, Integer.MAX_VALUE);
        }
        distTo.put(sourceCity, 0);//starting vertex 0

        //priority queue for 'let u be the closest unknown vertex'
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distTo::get));//priority queue with sorted VALUE's of distTo map
        pq.offer(sourceCity);


        while (!pq.isEmpty()) {
            String u = pq.poll(); //get the smallest value
            known.add(u);

            if (u.equals(destinationCity)) {//stop the code if you reach destination
                break;
            }


            for (String v : neighbors(u)) {
                int weight = edgeWeights.get(u).get(v);
                int oldDist = distTo.getOrDefault(v, Integer.MAX_VALUE); //current distance of v, if it's unknown, get the MAX_VALUE
                int newDist = distTo.get(u) + weight; //old shortest way to u + v = new estimated

                //if new found way is shorter, wee add it to priority queue
                if (newDist < oldDist) {
                    distTo.put(v, newDist);
                    edgeTo.put(v, u);
                    pq.offer(v);
                }
            }
        }

        printShortestPath(sourceCity, destinationCity, distTo, edgeTo);
    }



    private void printShortestPath(String source, String destination, Map<String, Integer> distTo, Map<String, String> edgeTo) {
        List<String> path = new ArrayList<>();
        String current = destination;

        while (current != null && !current.equals(source)) {
            path.add(0, current);
            current = edgeTo.get(current);
        }
        path.add(0, source);

        if (distTo.get(destination) < Integer.MAX_VALUE) {
            System.out.println("Shortest Path from " + source + " to " + destination + " is: " + path);
            System.out.println("Shortest Distance: " + distTo.get(destination));
        } else {
            System.out.println("There is no path from " + source + " to " + destination);
        }
    }

}

