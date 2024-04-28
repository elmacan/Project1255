import java.io.File;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("hallo my friendo");
        //System.out.println("commit denemesi betül ");
        System.out.println("repository deneme");
        System.out.println("github desktop deneme");
        System.out.println("elif was here");
        System.out.println("eceeeeeeee");
        System.out.println("eceeeeeeee deneme 2");


        String workFileName = args[0];
        String jobFileName = args[1];

        File workFlowFile = new File(workFileName);
        File jobFile = new File(jobFileName);
        try {
            if (!workFlowFile.exists() || !workFlowFile.canRead()) {
                throw new IOException("File doesn't exists or is not readable!" + workFileName);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());

        }
        try {
            if (!jobFile.exists() || !jobFile.canRead()) {
                throw new IOException("File doesn't exists or is not readable!" + jobFileName);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());

        }



    }
}