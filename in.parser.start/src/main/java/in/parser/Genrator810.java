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
//public class Genrator810 {
//
//    public static void main(String[] args) {
//        try {
//            // Create an EDIOutputFactory
//            EDIOutputFactory factory = EDIOutputFactory.newFactory();
//            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);
//
//            // Obtain Stream to write the EDI document
//            OutputStream stream = new FileOutputStream("invoice810.edi");
//
//            // Create an EDIStreamWriter from the factory
//            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);
//
//            // Read JSON data from file
//            String jsonString = new String(Files.readAllBytes(Paths.get("sample810.json")));
//            JSONObject jsonInvoice = new JSONObject(jsonString);
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
//                    .writeElement(">")
//                    .writeEndSegment();
//
//            // Write GS segment
//            writer.writeStartSegment("GS")
//	            .writeElement("IN") // Functional Identifier Code (e.g., IN for Invoice)
//	            .writeElement("4405197800") // Sender Code (Your sender code)
//	            .writeElement("999999999") // Receiver Code (Receiver's code)
//	            .writeElement("20101205") // Date (Transmission date)
//	            .writeElement("1710") // Time (Transmission time)
//	            .writeElement("1320") // Group Control Number (Incremental value for each GS segment)
//	            .writeElement("X") // Responsible Agency Code (e.g., X for Accredited Standards Committee X12)
//	            .writeElement("004010VICS") // Version/Release/Industry Identifier Code
//	            .writeEndSegment();
//
//            // Write ST segment
//            writer.writeStartSegment("ST")
//                    .writeElement("810")
//                    .writeElement("1004")
//                    .writeEndSegment();
//
//            // Write BIG segment
//            writer.writeStartSegment("BIG")
//                    .writeElement("20101204")
//                    .writeElement("217224")
//                    .writeElement("20101204")
//                    .writeElement("P792940")
//                    .writeEndSegment();
//
//            // Write REF segments
//            writer.writeStartSegment("REF")
//                    .writeElement("DP")
//                    .writeElement("099")
//                    .writeEndSegment();
//
//            writer.writeStartSegment("REF")
//                    .writeElement("IA")
//                    .writeElement("99999")
//                    .writeEndSegment();
//
//            // Write N1 segment
//            writer.writeStartSegment("N1")
//                    .writeElement("ST")
//                    .writeElement("")
//                    .writeElement("92")
//                    .writeElement("123")
//                    .writeEndSegment();
//
//            // Write IT1 segments
//            JSONArray itemList = jsonInvoice.getJSONArray("ItemList");
//            for (int i = 0; i < itemList.length(); i++) {
//                JSONObject item = itemList.getJSONObject(i);
//                writer.writeStartSegment("IT1")
//                        .writeElement(String.valueOf(i + 1))
//                        .writeElement(item.getString("PrdDesc"))
//                        .writeElement(String.valueOf(item.getInt("Qty")))
//                        .writeElement(item.getString("Unit"))
//                        .writeElement(String.valueOf(item.getDouble("UnitPrice")))
//                        .writeElement("")
//                        .writeElement("UP")
//                        .writeElement(item.getString("HsnCd"))
//                        .writeEndSegment();
//            }
//
//            // Write TDS segment
//            writer.writeStartSegment("TDS")
//                    .writeElement("21740")
//                    .writeEndSegment();
//
//            // Write CAD segment
//            writer.writeStartSegment("CAD")
//                    .writeElement("")
//                    .writeElement("")
//                    .writeElement("")
//                    .writeElement("")
//                    .writeElement("GTCT")
//                    .writeElement("")
//                    .writeElement("BM")
//                    .writeElement("99999")
//                    .writeEndSegment();
//
//            // Write CTT segment
//            writer.writeStartSegment("CTT")
//                    .writeElement(String.valueOf(itemList.length()))
//                    .writeEndSegment();
//
//            // Write SE and GE segments
//            writer.writeStartSegment("SE")
//                    .writeElement("18")
//                    .writeElement("1004")
//                    .writeEndSegment();
//
//            writer.writeStartSegment("GE")
//                    .writeElement("1")
//                    .writeElement("1320")
//                    .writeEndSegment();
//
//            // Write IEA segment
//            writer.writeStartSegment("IEA")
//                    .writeElement("1")
//                    .writeElement("000001320")
//                    .writeEndSegment();
//
//            // End interchange
//            writer.endInterchange();
//
//            // Close writer and stream
//            writer.close();
//            stream.close();
//
//            System.out.println("EDI 810 Invoice file successfully generated.");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
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
//public class Genrator810 {
//
//    public static void main(String[] args) {
//        try {
//            // Create an EDIOutputFactory
//            EDIOutputFactory factory = EDIOutputFactory.newFactory();
//            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);
//
//            // Obtain Stream to write the EDI document
//            OutputStream stream = new FileOutputStream("invoice810.edi");
//
//            // Create an EDIStreamWriter from the factory
//            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);
//
//            // Read JSON data from file
//            String jsonString = new String(Files.readAllBytes(Paths.get("sample810.json")));
//            JSONObject jsonInvoice = new JSONObject(jsonString);
//
//            // Start writing EDI segments
//            writer.startInterchange();
//
//           
//          writer.writeStartSegment("ISA")
//          .writeElement("01")
//          .writeElement("0000000000") // Sender ID
//          .writeElement("01")
//          .writeElement("0000000000")
//          .writeElement("ZZ")
//          .writeElement("27AADCG4992P1ZT")
//          .writeElement("ZZ")
//          .writeElement("123456789012345")
//          .writeElement("101127")
//          .writeElement("1719")
//          .writeElement("U")
//          .writeElement("00400")
//          .writeElement("000003438")
//          .writeElement("0")
//          .writeElement("P")
//          .writeElement(">")
//          .writeEndSegment();
//
//
//            // Write GS segment
//          writer.writeStartSegment("GS")
//          .writeElement("IN") // Functional Identifier Code (e.g., IN for Invoice)
//          .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("Gstin")) // Sender Code (Your sender code)
//          .writeElement(jsonInvoice.getJSONObject("BuyerDtls").getString("Gstin")) // Receiver Code (Receiver's code)
//          .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("Dt").replaceAll("-", "")) // Date (Transmission date)
//          .writeElement(jsonInvoice.getString("AckDt").replaceAll(":", "").substring(8, 12)) // Time (Transmission time)
//          .writeElement("1") // Group Control Number (Numeric value)
//          .writeElement("X") // Responsible Agency Code (e.g., X for Accredited Standards Committee X12)
//          .writeElement("004010VICS") // Version/Release/Industry Identifier Code
//          .writeEndSegment();
//
//            // Write ST segment
//            writer.writeStartSegment("ST")
//                    .writeElement("810")
//                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("No"))
//                    .writeEndSegment();
//
//            // Write BIG segment
//            writer.writeStartSegment("BIG")
//                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("Dt").replaceAll("-", ""))
//                    .writeElement(String.valueOf(jsonInvoice.getDouble("AckNo")))
//                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("Dt").replaceAll("-", ""))
//                    .writeElement(String.valueOf(jsonInvoice.getDouble("AckNo")))
//                    .writeEndSegment();
//
//            // Write REF segments
//            writer.writeStartSegment("REF")
//                    .writeElement("DP")
//                    .writeElement("099")
//                    .writeEndSegment();
//
//            writer.writeStartSegment("REF")
//                    .writeElement("IA")
//                    .writeElement("99999")
//                    .writeEndSegment();
//
//            // Write N1 segment
//            writer.writeStartSegment("N1")
//                    .writeElement("ST")
//                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("LglNm"))
//                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("Stcd"))
//                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("Gstin"))
//                    .writeEndSegment();
//
//            // Write IT1 segments
//            JSONArray itemList = jsonInvoice.getJSONArray("ItemList");
//            for (int i = 0; i < itemList.length(); i++) {
//                JSONObject item = itemList.getJSONObject(i);
//                writer.writeStartSegment("IT1")
//                        .writeElement(String.valueOf(i + 1))
//                        .writeElement(item.getString("PrdDesc"))
//                        .writeElement(String.valueOf(item.getInt("Qty")))
//                        .writeElement(item.getString("Unit"))
//                        .writeElement(String.valueOf(item.getDouble("UnitPrice")))
//                        .writeElement(String.valueOf(item.getDouble("TotAmt")))
//                        .writeElement("UP")
//                        .writeElement(item.getString("HsnCd"))
//                        .writeEndSegment();
//            }
//
//            // Write TDS segment
//            writer.writeStartSegment("TDS")
//                    .writeElement(String.valueOf(jsonInvoice.getJSONObject("ValDtls").getInt("AssVal")))
//                    .writeEndSegment();
//
//            // Write CAD segment
//            writer.writeStartSegment("CAD")
//                    .writeElement("")
//                    .writeElement("")
//                    .writeElement("")
//                    .writeElement("")
//                    .writeElement("GTCT")
//                    .writeElement("")
//                    .writeElement("BM")
//                    .writeElement("99999")
//                    .writeEndSegment();
//
//            // Write CTT segment
//            writer.writeStartSegment("CTT")
//                    .writeElement(String.valueOf(itemList.length()))
//                    .writeEndSegment();
//
//            // Write SE and GE segments
//            writer.writeStartSegment("SE")
//                    .writeElement("18")
//                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("No"))
//                    .writeEndSegment();
//
//            writer.writeStartSegment("GE")
//            .writeElement("000000001") // Should be numeric
//            .writeEndSegment();
//
//            // Write IEA segment
//            writer.writeStartSegment("IEA")
//                    .writeElement("1")
//                    .writeElement("000000001")
//                    .writeEndSegment();
//
//            // End interchange
//            writer.endInterchange();
//
//            // Close writer and stream
//            writer.close();
//            stream.close();
//
//            System.out.println("EDI 810 Invoice file successfully generated.");
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class Genrator810 {

    public static void main(String[] args) {
        try {
            // Create an EDIOutputFactory
            EDIOutputFactory factory = EDIOutputFactory.newFactory();
            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);

            // Obtain Stream to write the EDI document
            OutputStream stream = new FileOutputStream("invoice810.edi");

            // Create an EDIStreamWriter from the factory
            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);

            // Read JSON data from file
            String jsonString = new String(Files.readAllBytes(Paths.get("sample810.json")));
            JSONObject jsonInvoice = new JSONObject(jsonString);

            // Start writing EDI segments
            writer.startInterchange();

            // Write ISA segment
          writer.writeStartSegment("ISA")
          .writeElement("01")
          .writeElement("0000000000") // Sender ID
          .writeElement("01")
          .writeElement("0000000000")
          .writeElement("ZZ")
          .writeElement("27AADCG4992P1ZT")
          .writeElement("ZZ")
          .writeElement("123456789012345")
          .writeElement("101127")
          .writeElement("1719")
          .writeElement("U")
          .writeElement("00400")
          .writeElement("000003438")
          .writeElement("0")
          .writeElement("P")
          .writeElement(">")
          .writeEndSegment();


//            writer.writeEndSegment();

            // Write GS segment
            writer.writeStartSegment("GS")
                    .writeElement("IN")
                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("Gstin"))
                    .writeElement(jsonInvoice.getJSONObject("BuyerDtls").getString("Gstin"))
                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("Dt")) // Format date as CCYYMMDD
                    .writeElement(jsonInvoice.getString("AckDt")) // Format time as HHmm
                    .writeElement("1")
                    .writeElement("X")
                    .writeElement("004010VICS")
                    .writeEndSegment();

            // Write ST segment
            writer.writeStartSegment("ST")
                    .writeElement("810")
                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("No"))
                    .writeEndSegment();

            // Write BIG segment
            writer.writeStartSegment("BIG")
                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("Dt")) // Format date as CCYYMMDD
                    .writeElement(String.valueOf(jsonInvoice.getDouble("AckNo")))
                    .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("Dt")) // Format date as CCYYMMDD
                    .writeElement(String.valueOf(jsonInvoice.getDouble("AckNo")))
                    .writeEndSegment();

            // Write REF segments
            writer.writeStartSegment("REF")
                    .writeElement("DP")
                    .writeElement("099")
                    .writeEndSegment();

            writer.writeStartSegment("REF")
                    .writeElement("IA")
                    .writeElement("99999")
                    .writeEndSegment();

            // Write N1 segment
            writer.writeStartSegment("N1")
                    .writeElement("ST")
                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("LglNm"))
                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("Stcd"))
                    .writeElement(jsonInvoice.getJSONObject("SellerDtls").getString("Gstin"))
                    .writeEndSegment();

            // Write IT1 segments
//            JSONArray itemList = jsonInvoice.getJSONArray("ItemList");
            JSONArray itemList = jsonInvoice.getJSONArray("ItemList");
         // Write IT1 segments
            for (int i = 0; i < itemList.length(); i++) {
                JSONObject item = itemList.getJSONObject(i);
                writer.writeStartSegment("IT1")
                        .writeElement(String.valueOf(i + 1))  // IT1-01: Sequence Number
                        .writeElement(String.valueOf(item.getInt("Qty"))) // IT1-02: Quantity
                        .writeElement("EA") // IT1-03: Unit of Measure (Assuming EA is correct)
                        .writeElement(formatDecimal(item.getDouble("UnitPrice"))) // IT1-04: Unit Price
                        .writeElement("") // IT1-05: Blank or predefined value
                        .writeElement("UP") // IT1-07: Price Basis Code
                        .writeElement("999999330023") // IT1-08: Product/Service ID Qualifier
//                        .writeElement(item.getString("HsnCd")) // IT1-09: Product/Service ID
                        .writeEndSegment();
            }


            // Write TDS segment
            writer.writeStartSegment("TDS")
            .writeElement("21740")
            .writeEndSegment();

            // Write CAD segment
            writer.writeStartSegment("CAD")
                    .writeElement("")
                    .writeElement("")
                    .writeElement("")
                    .writeElement("")
                    .writeElement("GTCT")
                    .writeElement("")
                    .writeElement("BM")
                    .writeElement("99999")
                    .writeEndSegment();

            // Write CTT segment
            writer.writeStartSegment("CTT")
                    .writeElement(String.valueOf(itemList.length()))
                    .writeEndSegment();

            // Write SE and GE segments
            int segmentCount = itemList.length() + 12; // Count of segments + ISA, GS, ST, and all other segments
            writer.writeStartSegment("SE")
		            .writeElement(String.valueOf(itemList.length() + 9)) // Count of segments + ISA, GS, ST, and all other segments
		            .writeElement(jsonInvoice.getJSONObject("DocDtls").getString("No"))
                    .writeEndSegment();

            writer.writeStartSegment("GE")
                    .writeElement("1")
                    .writeElement("000000001") // This should match the control number used in ISA
                    .writeEndSegment();

            // Write IEA segment
            writer.writeStartSegment("IEA")
                    .writeElement("1")
                    .writeElement("000003438") // This should match the control number used in ISA
                    .writeEndSegment();

            // End interchange
            writer.endInterchange();

            // Close writer and stream
            writer.close();
            stream.close();

            System.out.println("EDI 810 Invoice file successfully generated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to get current date in CCYYMMDD format
    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(new Date());
    }

    // Helper method to get current time in HHmm format
    private static String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
        return timeFormat.format(new Date());
    }

    // Helper method to format date as CCYYMMDD
    private static String formatDate(String date) {
        String[] parts = date.split("/");
        return parts[2] + parts[1] + parts[0];
    }

    // Helper method to format time as HHmm
    private static String formatTime(String time) {
        String[] parts = time.split(":");
        return parts[0] + parts[1];
    }

    // Helper method to format decimal values
    private static String formatDecimal(double value) {
        return String.format("%.2f", value);
    }
}

