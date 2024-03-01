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

public class InvoiceGenrator {

    public static void main(String[] args) {
        try {
            // Create an EDIOutputFactory
            EDIOutputFactory factory = EDIOutputFactory.newFactory();
            factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);

            // Obtain Stream to write the EDI document
            OutputStream stream = new FileOutputStream("invoicet887.edi");

            // Create an EDIStreamWriter from the factory
            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);

            // Read JSON data from file
            String jsonString = new String(Files.readAllBytes(Paths.get("invoice.json")));
            JSONObject jsonInvoice = new JSONObject(jsonString);

            // Start writing EDI segments
            writer.startInterchange();

            // Write ISA segment
            writer.writeStartSegment("ISA")
                    .writeElement("00")
                    .writeElement("          ")
                    .writeElement("00")
                    .writeElement("          ")
                    .writeElement("ZZ")
                    .writeElement("ReceiverID     ")
                    .writeElement("ZZ")
                    .writeElement("Sender         ")
                    .writeElement("202302")
                    .writeElement("1011")
                    .writeElement("U")
                    .writeElement("00401")
                    .writeElement(UUID.randomUUID().toString().substring(0, 9)) // Generate unique interchange control number
                    .writeElement("0")
                    .writeElement("P")
                    .writeElement(">")
                    .writeEndSegment();

            // Write ST segment
            writer.writeStartSegment("ST")
                    .writeElement("810")
                    .writeElement("0001")
                    .writeEndSegment();

            // Write BIG segment
            JSONObject header = jsonInvoice.getJSONObject("Header");
            JSONObject invoiceHeader = header.getJSONObject("InvoiceHeader");
            writer.writeStartSegment("BIG")
                    .writeElement(invoiceHeader.getString("InvoiceNumber"))
                    .writeElement(invoiceHeader.getString("InvoiceDate"))
                    .writeElement(invoiceHeader.getString("TransactionTypeCode"))
                    .writeEndSegment();

            // Write REF segments
            JSONArray references = header.getJSONArray("References");
            for (int i = 0; i < references.length(); i++) {
                JSONObject reference = references.getJSONObject(i);
                writer.writeStartSegment("REF")
                        .writeElement(reference.getString("ReferenceQualifier"))
                        .writeElement(reference.getString("ReferenceID"))
                        .writeEndSegment();
            }

            // Write N1 segments
            JSONArray addresses = header.getJSONArray("Address");
            for (int i = 0; i < addresses.length(); i++) {
                JSONObject address = addresses.getJSONObject(i);
                writer.writeStartSegment("N1")
                        .writeElement(address.getString("AddressTypeCode"))
                        .writeElement(address.getString("Name"))
                        .writeElement(address.getString("IDCodeQualifier"))
                        .writeElement(address.getString("IDCode"))
                        .writeEndSegment();
            }

            // Write IT1 segments (Line Items)
            JSONObject structure = jsonInvoice.getJSONObject("Structure");
            JSONArray lineItems = structure.getJSONArray("LineItem");
            for (int i = 0; i < lineItems.length(); i++) {
                JSONObject lineItem = lineItems.getJSONObject(i);
                JSONObject invoiceLine = lineItem.getJSONObject("InvoiceLine");
                writer.writeStartSegment("IT1")
                        .writeElement(String.valueOf(i + 1))
                        .writeElement(String.valueOf(invoiceLine.getInt("InvoicedQty")))
                        .writeElement(invoiceLine.getString("ItemUOM"))
                        .writeElement(String.valueOf(invoiceLine.getDouble("UnitPrice")))
                        .writeElement("CP")
                        .writeElement(invoiceLine.getString("VendorPartNumber"))
                        .writeEndSegment();

                // Write PID segment (Product Description)
                JSONObject productDescription = lineItem.getJSONObject("ProductOrItemDescription");
                writer.writeStartSegment("PID")
                        .writeElement("F")
                        .writeElement(productDescription.getString("ProductDescription"))
                        .writeEndSegment();
            }

            // Write SE segment
            writer.writeStartSegment("SE")
                    .writeElement(String.valueOf(lineItems.length() + 2))
                    .writeElement("0001")
                    .writeEndSegment();

            // Write GE segment
            writer.writeStartSegment("GE")
                    .writeElement("1")
                    .writeElement("0001")
                    .writeEndSegment();

            // Write IEA segment
            writer.writeStartSegment("IEA")
                    .writeElement("1")
                    .writeElement(UUID.randomUUID().toString().substring(0, 9)) // Generate unique interchange control number
                    .writeEndSegment();

            // End interchange
            writer.endInterchange();

            // Close writer and stream
            writer.close();
            stream.close();

            System.out.println("EDI invoice file successfully generated.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
