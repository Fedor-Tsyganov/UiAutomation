package utils;

import constants.MC2PlatformTest;
import login_signup.LoginTC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class TestResultWriter {

    public static void write(String fileName, ArrayList<MC2PlatformTest> testResults){
        File file = new File(fileName);
        try {
            // Delete old file if exists
            file.delete();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(MC2PlatformTest e : testResults)
            {
                writer.write(e.getMethod()+","+e.getIssueId()+","+e.getResult());
                writer.newLine();
            }
            writer.flush();
            writer.close();
            fileWriter.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
