//package in.parser;
//
//import org.smooks.Smooks;
//import org.smooks.api.ExecutionContext;
//import org.smooks.io.payload.StringSource;
//import org.smooks.support.StreamUtils;
//import org.smooks.io.payload.StringResult;
//
//import org.xml.sax.SAXException;
//import java.io.*;
//
//public class SmooksGenrator {
//
//    public static void main(String[] args) throws IOException, SAXException {
//        // Load the Smooks configuration file
//        File smooksConfigFile = new File("smooks-config.xml");
//
//        // Create a Smooks instance
//        Smooks smooks = new Smooks();
//		 
//
//        try {
//            // Read the JSON input data
//            String inputJson = readInputJson("input.json");
//
//            // Create an ExecutionContext
//            ExecutionContext executionContext = smooks.createExecutionContext();
//
//            // Create a StringResult to capture the transformed EDI output
//            StringResult result = new StringResult();
//
//            // Transform the input JSON data to EDI format
//            smooks.filterSource(executionContext, new StringSource(inputJson), result);
//
//            // Write the transformed EDI data to a file
//            writeOutputToFile(result.toString(), "output.edi");
//
//            System.out.println("EDI file generated successfully.");
//        } finally {
//            // Close the Smooks instance
//            smooks.close();
//        }
//    }
//
//    private static String readInputJson(String filePath) throws IOException {
//        // Read the input JSON data from file
//        try (InputStream inputStream = new FileInputStream(filePath)) {
//            return StreamUtils.readStreamAsString(inputStream);
//        }
//    }
//
//    private static void writeOutputToFile(String outputData, String filePath) throws IOException {
//        // Write the transformed EDI data to a file
//        try (Writer writer = new FileWriter(filePath)) {
//            writer.write(outputData);
//        }
//    }
//}
