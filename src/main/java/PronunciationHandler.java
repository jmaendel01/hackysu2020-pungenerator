import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PronunciationHandler {
    public HashMap<String,String> pronunciationMap = new HashMap<>();

    public PronunciationHandler() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(main.pronouciationDictionary));
        String line;
        while ((line = br.readLine()) != null) {
            //System.out.println(line);

            String[] lineArray = line.split(",");
            //for (String s : lineArray) { System.out.println(s); }

            StringBuilder pronunciation = new StringBuilder();
            String key = lineArray[0];
            for (int i = 1; i < lineArray.length; i++) {
                pronunciation.append(" " + lineArray[i]);
            }
            pronunciationMap.put(key.toUpperCase(), pronunciation.toString().toUpperCase());
            //System.out.println(key + ":" + pronunciation.toString());
        }
    }

     static boolean compareStringsByLength(String p1, String p2) {
        String largerString;
        String smallerString;
        if (p1.length() > p2.length()) {
            largerString = p1;
            smallerString = p2;
        } else {
            largerString = p2;
            smallerString = p1;
        }

        if (smallerString.length() < 7) {
            return largerString.contains(smallerString);
        }

        for (int i = 0; i < smallerString.length() - 6; i++) {
            if (largerString.contains(smallerString.substring(i)) || largerString.contains(smallerString.substring(0, smallerString.length() - i))) {
                System.out.println(largerString + "contains " + smallerString.substring(i) + "or " + smallerString.substring(0, smallerString.length() - i));
                return true;
            }
        }

        for (int i = 0; i < largerString.length() - 6; i++) {
            if (smallerString.contains(largerString.substring(i)) || smallerString.contains(largerString.substring(0, largerString.length() - i))) {
                System.out.println(smallerString + "contains " + largerString.substring(i) + "or " + largerString.substring(0, largerString.length() - i));
                return true;
            }
        }


        /*for (int i = 0; i < p1.length() - 5; i++){
            for (int j = 0; j > p2.length() ; j--) {

            }
        }

        // Slowly delete the front of p1 and end of p2 until there are 3 characters left to check between
        for (int i = 0; i < p1.length() - 3; i++) {
            for (int j = p2.length(); j > 3 ; j--) {
                if (p1.substring(i).contains(p2.substring(0,j))) {
                    System.out.println();
                    //return true;
                }
            }

        }*/

        return false;
    }

     static boolean compareStringsBySpaces(String p1, String p2) {
        final ArrayList<String> largerStringList;
        final ArrayList<String> smallerStringList;
        if (p1.length() > p2.length()) {
            largerStringList = new ArrayList<>(Arrays.asList(p1.split("/s")));
            smallerStringList = new ArrayList<>(Arrays.asList(p2.split("/s")));
        } else {
            largerStringList = new ArrayList<>(Arrays.asList(p2.split("/s")));
            smallerStringList = new ArrayList<>(Arrays.asList(p1.split("/s")));
        }

        if (smallerStringList.size() < 3) {
            return largerStringList.contains(smallerStringList);
        }

        for (int i = 0; i < largerStringList.size() - 1; i++) {
            if (smallerStringList.contains(largerStringList.subList(0, largerStringList.size() - i))
                    || smallerStringList.contains(largerStringList.subList(i, largerStringList.size()))) {
                return true;
            }
        }

        for (int i = 0; i < smallerStringList.size() - 1; i++) {
            if (largerStringList.contains(smallerStringList.subList(0, smallerStringList.size() - i))
                    || largerStringList.contains(smallerStringList.subList(i, smallerStringList.size()))) {
                return true;
            }
        }

        return false;
    }
}
