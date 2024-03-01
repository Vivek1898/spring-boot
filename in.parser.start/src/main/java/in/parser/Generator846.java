//package in.parser;
//
//import io.xlate.edi.stream.EDIOutputFactory;
//import io.xlate.edi.stream.EDIStreamWriter;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class Generator846 {
//
//    public static void main(String[] args) {
//        try {
//            // Read inventory data from JSON file
//            String jsonString = new String(Files.readAllBytes(Paths.get("sample846.json")));
//            JSONObject jsonInventory = new JSONObject(jsonString);
//
//            // Create an EDIOutputFactory
//            EDIOutputFactory factory = EDIOutputFactory.newFactory();
//            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);
//
//            // Obtain Stream to write the EDI document
//            OutputStream stream = new FileOutputStream("edi_846.edi");
//
//            // Create an EDIStreamWriter from the factory
//            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);
//
//            // Start writing EDI segments
//            writer.startInterchange();
//
//            // Write ISA segment
//            writer.writeStartSegment("ISA")
//                    .writeElement("01")
//                    .writeElement("0000000000")
//                    .writeElement("01")
//                    .writeElement("0000000000")
//                    .writeElement("ZZ")
//                    .writeElement("ABCDEFGHIJKLMNO")
//                    .writeElement("ZZ")
//                    .writeElement("123456789012345")
//                    .writeElement("101127")
//                    .writeElement("1719")
//                    .writeElement("U")
//                    .writeElement("00400")
//                    .writeElement("000003438")
//                    .writeElement("0")
//                    .writeElement("P")
//                    .writeElement(">");
//            writer.writeEndSegment();
//
//            // Write GS segment
//            writer.writeStartSegment("GS")
//                    .writeElement("IB")
//                    .writeElement("4405197800")
//                    .writeElement("999999999")
//                    .writeElement("20101127")
//                    .writeElement("1719")
//                    .writeElement("1421")
//                    .writeElement("X")
//                    .writeElement("004010VICS");
//            writer.writeEndSegment();
//
//            // Write ST segment
//            writer.writeStartSegment("ST")
//                    .writeElement("846")
//                    .writeElement("1001");
//            writer.writeEndSegment();
//
//            // Write BIA segment
//            writer.writeStartSegment("BIA")
//                    .writeElement("13")
//                    .writeElement("PI")
//                    .writeElement("4589")
//                    .writeElement("20001104");
//            writer.writeEndSegment();
//
//            // Loop through inventory items and write segments
//            JSONArray itemList = jsonInventory.getJSONArray("InventoryItems");
//            for (int i = 0; i < itemList.length(); i++) {
//                JSONObject item = itemList.getJSONObject(i);
//
//                // Write LIN segment
//                writer.writeStartSegment("LIN")
//                        .writeElement(String.valueOf(i + 1))
//                        .writeElement(item.getString("ProductType"))
//                        .writeElement(item.getString("ProductCode"))
//                        .writeElement(item.getString("CountryCode"))
//                        .writeElement(item.getString("Barcode"))
//                        .writeElement(item.getString("LocationType"))
//                        .writeElement(item.getString("LocationCode"));
//                writer.writeEndSegment();
//
//                // Write PID segment with real data
//                writer.writeStartSegment("PID")
//                .writeElement("F")
//                .writeElement("08")
//                .writeElement("")
//                .writeElement("")
//                .writeElement("DRAPE");
////        writer.writeEndSegment();
//                writer.writeEndSegment();
//            }
//
//            // Write CTT segment
//            writer.writeStartSegment("CTT")
//                    .writeElement(String.valueOf(itemList.length()));
//            writer.writeEndSegment();
//
//            // Write SE segment
//            writer.writeStartSegment("SE")
//                    .writeElement(String.valueOf((itemList.length() + 2)*2)) // Add 2 for ISA and GS segments
//                    .writeElement("1001");
//            writer.writeEndSegment();
//
//            // Write GE segment
//            writer.writeStartSegment("GE")
//                    .writeElement("1")
//                    .writeElement("1421");
//            writer.writeEndSegment();
//
//            // Write IEA segment
//            writer.writeStartSegment("IEA")
//                    .writeElement("1")
//                    .writeElement("000003438");
//            writer.writeEndSegment();
//
//            // End interchange
//            writer.endInterchange();
//
//            // Close writer and stream
//            writer.close();
//            stream.close();
//
//            System.out.println("EDI 846 document successfully generated.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
package in.parser;

import io.xlate.edi.stream.EDIOutputFactory;
import io.xlate.edi.stream.EDIStreamWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Generator846 {

    public static void main(String[] args) {
        try {
            // Read inventory data from JSON file
            String jsonString = new String(Files.readAllBytes(Paths.get("sample846.json")));
            JSONObject jsonInventory = new JSONObject(jsonString);

            // Create an EDIOutputFactory
            EDIOutputFactory factory = EDIOutputFactory.newFactory();
            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);

            // Obtain Stream to write the EDI document
            OutputStream stream = new FileOutputStream("edi_846.edi");

            // Create an EDIStreamWriter from the factory
            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);

            // Start writing EDI segments
            writer.startInterchange();

            // Write ISA segment
            writer.writeStartSegment("ISA")
                    .writeElement("01")
                    .writeElement("0000000000")
                    .writeElement("01")
                    .writeElement("0000000000")
                    .writeElement("ZZ")
                    .writeElement("ABCDEFGHIJKLMNO")
                    .writeElement("ZZ")
                    .writeElement("123456789012345")
                    .writeElement("101127")
                    .writeElement("1719")
                    .writeElement("U")
                    .writeElement("00400")
                    .writeElement("000003438")
                    .writeElement("0")
                    .writeElement("P")
                    .writeElement(">");
            writer.writeEndSegment();

            // Write GS segment
            writer.writeStartSegment("GS")
                    .writeElement("IB")
                    .writeElement("4405197800")
                    .writeElement("999999999")
                    .writeElement("20101127")
                    .writeElement("1719")
                    .writeElement("1421")
                    .writeElement("X")
                    .writeElement("004010VICS");
            writer.writeEndSegment();

            // Write ST segment
            writer.writeStartSegment("ST")
                    .writeElement("846")
                    .writeElement("1001");
            writer.writeEndSegment();
            
            // Write BIA segment
            writer.writeStartSegment("BIA")
                    .writeElement("13")
                    .writeElement("PI")
                    .writeElement("4589")
                    .writeElement("20001104");
            writer.writeEndSegment();
            int segmentCount = 3;

            // Loop through inventory items and write segments
            JSONArray itemList = jsonInventory.getJSONArray("InventoryItems");
            for (int i = 0; i < itemList.length(); i++) {
                JSONObject item = itemList.getJSONObject(i);

            

                // Write LIN segment
                writer.writeStartSegment("LIN")
                .writeElement(String.valueOf(i + 1))
                .writeElement("MG")
                .writeElement(item.getString("SKU"))
                .writeElement("UK")
                .writeElement("50387698433528")
                .writeElement("LT")
                .writeElement("43552");
        writer.writeEndSegment();
        segmentCount++;

                // Write PID segment with real data
                writer.writeStartSegment("PID")
                        .writeElement("F")
                        .writeElement("08")
                        .writeElement("")
                        .writeElement("")
                        .writeElement(item.getString("Product Name"));
                writer.writeEndSegment();
                segmentCount++;

                // Write SAC segment (assuming constant values)
//                writer.writeStartSegment("SAC")
//                        .writeElement("C")
//                        .writeElement("A")
//                        .writeElement("T")
//                        .writeElement("");
//                writer.writeEndSegment();

                // Write REF segment for Parent SKU
                writer.writeStartSegment("REF")
                        .writeElement("IB")
                        .writeElement(item.getString("Parent SKU"));
                writer.writeEndSegment();
                segmentCount ++;

                // Write REF segment for Brand Name
                writer.writeStartSegment("REF")
                        .writeElement("MF")
                        .writeElement(item.getString("Brand Name"));
                writer.writeEndSegment();
                segmentCount ++;

                // Write REF segments for Taxonomy/Categories
                JSONArray categories = item.getJSONArray("Taxonomy/Categories");
                for (int j = 0; j < categories.length(); j++) {
                    writer.writeStartSegment("REF")
                            .writeElement("ZX")
                            .writeElement(categories.getString(j));
                    writer.writeEndSegment();
                    segmentCount ++;
                }

              
            }
            // Write CTT segment after processing each item
            writer.writeStartSegment("CTT")
                    .writeElement("1"); // Count of line items
            writer.writeEndSegment();
            segmentCount++;
            // Write SE segment
            writer.writeStartSegment("SE")
                    .writeElement( String.valueOf(segmentCount) ) 
                    .writeElement("1001"); // Transaction set control number
            writer.writeEndSegment();

            // Write GE segment
            writer.writeStartSegment("GE")
                    .writeElement("1")
                    .writeElement("1421");
            writer.writeEndSegment();

            // Write IEA segment
            writer.writeStartSegment("IEA")
                    .writeElement("1")
                    .writeElement("000003438");
            writer.writeEndSegment();

            // End interchange
            writer.endInterchange();

            // Close writer and stream
            writer.close();
            stream.close();

            System.out.println("EDI 846 document successfully generated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
