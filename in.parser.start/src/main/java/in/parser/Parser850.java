package in.parser;

import io.xlate.edi.stream.EDIOutputFactory;
import io.xlate.edi.stream.EDIStreamWriter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class Parser850 {

    public static void main(String[] args) {
        try {
            // Create an EDIOutputFactory
            EDIOutputFactory factory = EDIOutputFactory.newFactory();
            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);

            // Obtain Stream to write the EDI document
            OutputStream stream = new FileOutputStream("invoice15.edi");

            // Create an EDIStreamWriter from the factory
            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);

            // Read JSON data from file
            String jsonString = new String(Files.readAllBytes(Paths.get("invoice.json")));
            JSONObject jsonInvoice = new JSONObject(jsonString);

            // Start writing EDI segments
            writer.startInterchange();

         // Generate a sequential numeric control number
            int controlNumber = 1; // You can initialize it based on your requirements

            // Write ISA segment
            writer.writeStartSegment("ISA")
                    .writeElement("00")
                    .writeElement("          ")
                    .writeElement("00")
                    .writeElement("          ")
                    .writeElement("01")
                    .writeElement("085767000      ")
                    .writeElement("ZZ")
                    .writeElement("Company        ")
                    .writeElement("161206")
                    .writeElement("2115")
                    .writeElement("U")
                    .writeElement("00401")
                    .writeElement(String.format("%09d", controlNumber)) // Pad with leading zeros if needed
                    .writeElement("0")
                    .writeElement("P")
                    .writeElement(":")
                    .writeEndSegment();


            // Write GS segment
            writer.writeStartSegment("GS")
                    .writeElement("PO")
                    .writeElement("085767338")
                    .writeElement("Company")
                    .writeElement("20161206")
                    .writeElement("2115")
                    .writeElement("5007")
                    .writeElement("X")
                    .writeElement("004010")
                    .writeEndSegment();

            // Write ST segment
            writer.writeStartSegment("ST")
                    .writeElement("850")
                    .writeElement("0001")
                    .writeEndSegment();

            // Write BEG segment
            writer.writeStartSegment("BEG")
                    .writeElement("00")
                    .writeElement("DS")
                    .writeElement("5907867")
                    .writeElement("")
                    .writeElement("20161206")
                    .writeEndSegment();

            // Write CUR segment
            writer.writeStartSegment("CUR")
                    .writeElement("SN")
                    .writeElement("USD")
                    .writeEndSegment();

            // Write REF segment
            writer.writeStartSegment("REF")
                    .writeElement("8M")
                    .writeElement("COMPANYB")
                    .writeElement("ORIGIN")
                    .writeEndSegment();

            // Write PER segment
            writer.writeStartSegment("PER")
                    .writeElement("OC")
                    .writeElement("Donna Person")
                    .writeElement("TE")
                    .writeElement("4255552515")
                    .writeElement("FX")
                    .writeElement("4255553875")
                    .writeEndSegment();

            // Write FOB segment
            writer.writeStartSegment("FOB")
                    .writeElement("PP")
                    .writeElement("ZZ")
                    .writeElement("UPS Ground #442E1W")
                    .writeEndSegment();

            // Write DTM segment
            writer.writeStartSegment("DTM")
                    .writeElement("010")
                    .writeElement("20161206")
                    .writeEndSegment();

            // Write TD5 segment
            writer.writeStartSegment("TD5")
		            .writeElement("")
		            .writeElement("2")
		            .writeElement("UPSN")
		            .writeElement("")
		            .writeElement("UPS Ground #442E2E")
		            .writeElement("")
		            .writeElement("")
		            .writeElement("")
		            .writeElement("ZZ") // Change 'SG' to 'ZZ'
		            .writeEndSegment();

            // Write N9 segment
            writer.writeStartSegment("N9")
                    .writeElement("LI")
                    .writeElement("TEXT")
                    .writeEndSegment();

            // Write MSG segment
            writer.writeStartSegment("MSG")
                    .writeElement("If items are available for partial shipment, please contact companyA@trading.com for authorization prior to release")
                    .writeEndSegment();

            // Write N1 segment
            writer.writeStartSegment("N1")
                    .writeElement("ST")
                    .writeElement("Company Attn 41309514")
                    .writeElement("92")
                    .writeElement("0857673380000")
                    .writeEndSegment();

            // Write N3 segment
            writer.writeStartSegment("N3")
                    .writeElement("1000 BABELWAY PL")
                    .writeEndSegment();

            // Write N4 segment
            writer.writeStartSegment("N4")
                    .writeElement("CHICAGO")
                    .writeElement("IL")
                    .writeElement("60639-1030")
                    .writeElement("US")
                    .writeEndSegment();

            // Write PER segment
            writer.writeStartSegment("PER")
                    .writeElement("ST")
                    .writeElement("Sender")
                    .writeElement("TE")
                    .writeElement("7735555120")
                    .writeEndSegment();

            // Write N1 segment
            writer.writeStartSegment("N1")
                    .writeElement("BT")
                    .writeElement("CompanyA")
                    .writeElement("92")
                    .writeElement("0857673380000")
                    .writeEndSegment();

            // Write N3 segment
            writer.writeStartSegment("N3")
                    .writeElement("1000 BABELWAY PL")
                    .writeEndSegment();

            // Write N4 segment
            writer.writeStartSegment("N4")
                    .writeElement("City")
                    .writeElement("UT")
                    .writeElement("98034")
                    .writeElement("US")
                    .writeEndSegment();

            // Write PER segment
            writer.writeStartSegment("PER")
                    .writeElement("IC")
                    .writeElement("Donna Person")
                    .writeElement("TE")
                    .writeElement("4255552515")
                    .writeEndSegment();

            // Write PO1 segment
            JSONArray lineItems = jsonInvoice.getJSONObject("Structure").getJSONArray("LineItem");
            for (int i = 0; i < lineItems.length(); i++) {
                JSONObject lineItem = lineItems.getJSONObject(i);
                JSONObject poLine = lineItem.getJSONObject("InvoiceLine");
                writer.writeStartSegment("PO1")
                        .writeElement("1")
                        .writeElement("1")
                        .writeElement("EA")
                        .writeElement(String.valueOf(poLine.getDouble("UnitPrice")))
                        .writeElement("")
                        .writeElement("VP")
                        .writeElement(poLine.getString("VendorPartNumber"))
                        .writeEndSegment();
            }

            // Write CTT segment
            writer.writeStartSegment("CTT")
                    .writeElement(String.valueOf(lineItems.length()))
                    .writeEndSegment();

            // Write SE segment
            writer.writeStartSegment("SE")
                    .writeElement("22")
                    .writeElement("0001")
                    .writeEndSegment();

            // Write GE segment
            writer.writeStartSegment("GE")
                    .writeElement("1")
                    .writeElement("5007")
                    .writeEndSegment();

            int controlNumberIEA = 1; // You can initialize it based on your requirements

         // Write IEA segment
         writer.writeStartSegment("IEA")
                 .writeElement("1")
                 .writeElement(String.format("%09d", controlNumberIEA)) // Pad with leading zeros if needed
                 .writeEndSegment();
            // End interchange
            writer.endInterchange();
           
            

            // Close writer and stream
            writer.close();
            stream.close();

            System.out.println("EDI 850 Purchase Order file successfully generated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
