import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {
    static final String modelSource = "C:/Users/Judah/Documents/hackysu2020/src/main/resources/GoogleNews-vectors-negative300.bin.gz";
    static final String pronouciationDictionary = "C:/Users/Judah/Documents/hackysu2020/src/main/resources/cmudictionary.csv";

    static String baseWord = "TRUMP";
    static String topic = "SPORTS";

    public static void main(String[] args) throws Exception {
        PronunciationHandler pronunciationHandler = new PronunciationHandler();
        SemanticHandler semanticHandler = new SemanticHandler();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do {
            ArrayList<String> resultWordLists = new ArrayList<>();

            System.out.println("Enter Base word");
            baseWord = reader.readLine().toUpperCase();

            System.out.println("Enter Topic word");
            topic = reader.readLine().toUpperCase();

            semanticHandler.setSimilarWords(topic);

            String basePronunciation = pronunciationHandler.pronunciationMap.get(baseWord).toUpperCase();

            if (basePronunciation != null) {
                for (WordModel wordModel : semanticHandler.semanticWordList) {
                    String semanticPronunciation = pronunciationHandler.pronunciationMap.get(wordModel.word.toUpperCase());
                    if (semanticPronunciation != null) {
                        if (PronunciationHandler.compareStringsBySpaces(basePronunciation, semanticPronunciation)
                                || PronunciationHandler.compareStringsByLength(basePronunciation, semanticPronunciation)) {
                            resultWordLists.add(wordModel.word);
                        }
                        if (basePronunciation.contains(semanticPronunciation)) {
                            resultWordLists.add(wordModel.word);
                        }
                    }//else {System.out.println("semanticPronunciation is null");}
                }
            } //else { System.out.println("basePronunciation" + " is null"); }

        /*double weight;
        for (WordModel wordModel :
                wordModelList) {
            weight = model.similarity(wordModel.word, topic);
            if (weight > similarityWeightLowerBound) {
                resultWordLists.add(wordModel.word);
            }
        }*/

            System.out.println(resultWordLists);
        } while (true);
    }


}