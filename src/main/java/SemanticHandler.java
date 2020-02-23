import com.google.gson.Gson;
import datamuse.DatamuseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SemanticHandler {
    static Gson gson = new Gson();
    DatamuseQuery api;
    List<WordModel> semanticWordList;

    public SemanticHandler(){
        /*File modelFile = new File(modelSource);
        Word2Vec model = WordVectorSerializer.readWord2VecModel(modelFile);
        System.out.println("model loaded");*/
        api = new DatamuseQuery();
    }

    public void setSimilarWords(String topic) {
         semanticWordList = new ArrayList<>(Arrays.asList(gson.fromJson(api.findSimilar(main.topic + "&max=1000"), WordModel[].class)));
        //for (WordModel wordModel : semanticWordList) { System.out.println(wordModel.word); }

    }
}
