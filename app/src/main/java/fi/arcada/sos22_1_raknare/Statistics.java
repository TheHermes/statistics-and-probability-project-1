package fi.arcada.sos22_1_raknare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Statistics {
    public static ArrayList<DataItem> getSampleData() {
        ArrayList<DataItem> dataItems = new ArrayList<>();
        String[] names = {"Helsingfors", "Esbo", "Tammerfors", "Vanda", "Uleåborg", "Åbo", "Jyväskylä", "Kuopio", "Lahtis", "Björneborg", "Kouvola", "Joensuu", "Villmanstrand", "Tavastehus", "Vasa", "Seinäjoki", "Rovaniemi", "S:t Michel", "Salo", "Kotka", "Borgå", "Karleby", "Hyvinge", "Lojo", "Träskända", "Raumo", "Kervo", "Kajana", "S:t Karins", "Nokia", "Ylöjärvi", "Kangasala", "Nyslott", "Riihimäki", "Raseborg", "Imatra", "Reso", "Brahestad", "Sastamala", "Torneå", "Idensalmi", "Valkeakoski", "Kurikka", "Kemi", "Varkaus", "Jämsä", "Fredrikshamn", "Nådendal", "Jakobstad", "Heinola", "Äänekoski", "Pieksämäki", "Forssa", "Ackas", "Orimattila", "Loimaa", "Nystad", "Ylivieska", "Kauhava", "Kuusamo", "Pargas", "Lovisa", "Lappo", "Kauhajoki", "Ulvsby", "Kankaanpää", "Kalajoki", "Mariehamn", "Alavo", "Pemar", "Lieksa", "Grankulla", "Nivala", "Kides", "Vittis", "Mänttä-Vilppula", "Närpes", "Keuru", "Nurmes", "Alajärvi", "Saarijärvi", "Orivesi", "Högfors", "Somero", "Letala", "Hangö", "Kuhmo", "Kiuruvesi", "Pudasjärvi", "Nykarleby", "Kemijärvi", "Oulainen", "Kumo", "Suonenjoki", "Ikalis", "Haapajärvi", "Harjavalta", "Haapavesi", "Outokumpu", "Virdois", "Kristinestad", "Parkano", "Viitasaari", "Etseri", "Kannus", "Pyhäjärvi", "Kaskö"};
        double[] values = {658457, 297132, 244223, 239206, 209551, 195137, 144473, 121543, 120027, 83482, 80454, 77261, 72634, 67971, 67615, 64736, 64180, 52122, 51400, 51241, 51149, 47909, 46880, 45988, 45226, 38959, 37232, 36493, 35497, 34884, 33533, 32622, 32547, 28521, 27484, 25655, 24810, 24260, 23998, 21333, 20958, 20695, 20197, 19982, 19973, 19767, 19702, 19579, 19097, 18344, 18318, 17253, 16573, 16467, 15808, 15628, 15463, 15357, 15312, 15165, 15086, 14643, 14203, 12890, 12669, 12662, 12412, 11742, 11197, 11041, 10543, 10396, 10396, 9877, 9870, 9563, 9562, 9443, 9423, 9311, 9117, 8978, 8717, 8563, 8456, 7979, 7928, 7759, 7702, 7497, 7105, 7102, 6951, 6891, 6877, 6802, 6785, 6613, 6506, 6465, 6380, 6286, 6070, 5484, 5390, 4964, 1289};
        for(int i = 0; i < names.length; i++){
            dataItems.add(new DataItem(names[i], values[i]));
        }
        return dataItems;
    }
    // Räkna ut minsta värdet
    public static Double calcMin(ArrayList<Double> values) {
        double min = values.get(0);
        // Gemför ett värde i arraylistan med nästa, om den är nuvarande värdet är större så blir min den nya värdet vi gemförde med
        for (int i = 1;i < values.size();i++){
            if (min > values.get(i)){
                min = values.get(i);
            }
        }
        return min;
    }
    // Räkna ut största värdet
    public static double calcMax(ArrayList<Double> values) {
        double max = values.get(0);
        // Gemför om max är mindre än nästa talet i arraylistan, om mindre, max = values.get(i)
        for (int i = 0; i < values.size(); i++) {
            if (max < values.get(i)){
                max = values.get(i);
            }
        }
        return max;
    }
    // Räkna medelvärdet
    public static Double calcAverage(ArrayList<Double> values){
        int amount = values.size();
        double sum = 0;
        for (int i = 0;i < values.size();i++) {
            sum += values.get(i);
        }
        return sum/amount;
    }
    public static ArrayList<Double> sortValues(ArrayList<Double> values){
        // Skapar en kopia, så vi inte sorterat ursprungliga datamängden
        ArrayList<Double> sortedValues = new ArrayList<>(values);
        Collections.sort(sortedValues);
        return sortedValues;
    }
    // Beräknar medianen
    public static double calcMedian(ArrayList<Double> values){
        ArrayList<Double> sorted = sortValues(values);
        int middle = sorted.size()/2;
        //System.out.println("Middle: "+middle);
        double median = sorted.get(middle);
        // Om data mängden har jämt antal tal
        if (sorted.size() % 2 == 0) {
            median = (sorted.get(middle)+sorted.get(middle-1))/2;
        }
        return median;
    }
    // Beräknar ut typvärdet
    public static double calcMode(ArrayList<Double> values) {
        HashMap<Double, Integer> valueCount = new HashMap<>();
        for (double value: values) {
            Integer count = valueCount.get(value);
            if (count == null){
                count = 0;
            }
            valueCount.put(value, count + 1);
        }
        int maxCount = 0;
        double modeValue = 0.0;

        // Loopar genom en HashMap
        for (Double value: valueCount.keySet()) {
            Integer curCount = valueCount.get(value);
            // Om den senaste funna värdet är större än nuvarande största
            if (curCount > maxCount) {
                maxCount = curCount;
                modeValue = value;
            }
        }
        return modeValue;
    }
    // Beräknar standardavvikelsen
    public static double calcStandDev(ArrayList<Double> values){
        double stdDevSum = 0;
        double avg = calcAverage(values);
        for(double value: values){
            stdDevSum += Math.pow(value-avg, 2);
        }
        double variance = stdDevSum/values.size();
        return Math.sqrt(variance);
    }
    // Beräknar Nedre kvartilen
    public static double calcLQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        //System.out.println(sorted.size());
        if (sorted.size() < 4) {
            return 0;
        }
        int sizeIndex = 0;
        sizeIndex = sorted.size() / 2;
        ArrayList<Double> lqValues = new ArrayList<>();
        for(int i = 0; i < sizeIndex;i++) {
            lqValues.add(sorted.get(i));
        }
        return calcMedian(lqValues);
    }
    // Beräknar övre kvartilen
    public static double calcUQ(ArrayList<Double> values) {
        ArrayList<Double> sorted = sortValues(values);
        if (sorted.size() < 4) {
            return 0;
        }
        int sizeIndex = 0;
        if (sorted.size() % 2 == 0) {
            sizeIndex = sorted.size() / 2;
        } else {
            sizeIndex = (sorted.size()/2)+1;
        }
        ArrayList<Double> uqValues = new ArrayList<>();
        for (int i = sizeIndex; i < values.size();i++) {
            uqValues.add(sorted.get(i));
        }
        return calcMedian(uqValues);
    }
    // Beräknar inre kvartilavståndet
    public static double calcQR(ArrayList<Double> values) {
        return calcUQ(values) - calcLQ(values);
    }
}