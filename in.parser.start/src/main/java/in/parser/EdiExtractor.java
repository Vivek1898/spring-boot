package in.parser;

import io.xlate.edi.stream.EDIInputFactory;
import io.xlate.edi.stream.EDIStreamReader;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class EdiExtractor {

    public static JSONObject parseEdi810(byte[] ediData) {
        try {
            System.out.println("Parsing EDI 810 Invoice");

            // Create an EDIInputFactory
            EDIInputFactory factory = EDIInputFactory.newFactory();

            // Create an InputStream from the EDI data
            InputStream stream = new ByteArrayInputStream(ediData);

            // Create an EDIStreamReader from the factory
            EDIStreamReader reader = factory.createEDIStreamReader(stream);

            // Create a JSON object to store the parsed data
            JSONObject json = new JSONObject();
            JSONObject currentSegment = null;

            // Loop through the EDI segments
            while (reader.hasNext()) {
                switch (reader.next()) {
                    case START_SEGMENT:
                        // If there was a previous segment, add it to JSON
                        if (currentSegment != null) {
                            json.append("segments", currentSegment);
                        }
                        // Create a new JSON object for the current segment
                        currentSegment = new JSONObject();
                        // Retrieve the segment name
                        String segmentName = reader.getText();
                        currentSegment.put("name", segmentName);
                        break;

                    case ELEMENT_DATA:
                        // Retrieve the value of the current element
                        String data = reader.getText();
                        // Add the data to the current segment JSON
                        currentSegment.append("data", data);
                        break;
                }
            }
            // Add the last segment to JSON
            if (currentSegment != null) {
                json.append("segments", currentSegment);
            }

            // Close the reader and stream
            reader.close();
            stream.close();

            System.out.println("Finished parsing EDI 810 Invoice");

            return json;
        } catch (Exception e) {
            // Handle parsing errors
            e.printStackTrace();
            return null;
        }
    }
}
