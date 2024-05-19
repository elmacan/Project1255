import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkFlowFile {  //abstract maybe?
    FileParser fileParser = new FileParser();
    private static List<String> taskTypesInText = new ArrayList<>();
    private static List<JobType> jobTypesInText = new ArrayList<>();
    private static List<Station> stationsInText = new ArrayList<>();

    private static HashMap<String, String> taskInfo = new HashMap<String, String>();




    public void getLineFromText(String line){
        String[] pieces=line.split(" ");
        fileParser.parseTaskTypes(pieces,line);





    }


}
