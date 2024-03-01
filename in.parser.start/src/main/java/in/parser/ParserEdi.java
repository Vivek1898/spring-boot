package in.parser;

import io.xlate.edi.stream.EDIOutputFactory;
import io.xlate.edi.stream.EDIStreamWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParserEdi {

    public static void main(String[] args) {
        // Create an EDIOutputFactory
        EDIOutputFactory factory = EDIOutputFactory.newFactory();

        try {
            // Obtain Stream to write the EDI document
            OutputStream stream = new FileOutputStream("invoice.edi");

            // Create an EDIStreamWriter from the factory
            EDIStreamWriter writer = factory.createEDIStreamWriter(stream);

            // Start writing EDI segments
            writer.startInterchange()
                    .writeStartSegment("ISA")
                    .writeElement("00")
                    .writeElement("          ")
                    .writeElement("00")
                    .writeElement("          ")
                    .writeElement("ZZ")
                    .writeElement("ReceiverID     ")
                    .writeElement("ZZ")
                    .writeElement("Sender         ")
                    .writeElement("203001")
                    .writeElement("1430")
                    .writeElement("^")
                    .writeElement("00501")
                    .writeElement("000000001")
                    .writeElement("0")
                    .writeElement("P")
                    .writeElement(":")
                    .writeEndSegment();

            // Write Invoice data
            String json = "{\"invoiceNumber\":\"INV12345\",\"invoiceDate\":\"2024-02-23\",\"customerName\":\"ABC Company\",\"customerAddress\":\"123 Main St, Cityville\",\"totalAmount\":500.00,\"items\":[{\"itemCode\":\"ITEM001\",\"description\":\"Product 1\",\"quantity\":2,\"unitPrice\":100.00,\"totalPrice\":200.00},{\"itemCode\":\"ITEM002\",\"description\":\"Product 2\",\"quantity\":3,\"unitPrice\":80.00,\"totalPrice\":240.00}],\"taxAmount\":30.00,\"discountAmount\":20.00,\"netAmount\":510.00,\"currency\":\"USD\",\"paymentDueDate\":\"2024-03-15\"}";
            JSONObject invoice = new JSONObject(json);

            // Write Invoice Header
            writer.writeStartSegment("BGM")
                    .writeElement("380")
                    .writeElement(invoice.getString("invoiceNumber"))
                    .writeElement(invoice.getString("invoiceDate"))
                    .writeEndSegment();

            // Write Customer Information
            writer.writeStartSegment("NAD")
                    .writeElement("BY")
                    .writeElement(invoice.getString("customerName"))
                    .writeElement("92")
                    .writeElement(invoice.getString("customerAddress"))
                    .writeEndSegment();

            // Write Invoice Items
            JSONArray items = invoice.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                writer.writeStartSegment("LIN")
                        .writeElement(String.valueOf(i + 1))
                        .writeElement(item.getString("itemCode"))
                        .writeEndSegment();

                writer.writeStartSegment("QTY")
                        .writeElement(String.valueOf(item.getInt("quantity")))
                        .writeElement("21")
                        .writeEndSegment();

                writer.writeStartSegment("MOA")
                        .writeElement(String.valueOf(item.getDouble("totalPrice")))
                        .writeElement("203")
                        .writeEndSegment();
            }

            // Write Invoice Summary
            writer.writeStartSegment("MOA")
                    .writeElement(String.valueOf(invoice.getDouble("netAmount")))
                    .writeElement("203")
                    .writeEndSegment();

            writer.writeStartSegment("DTM")
                    .writeElement("137")
                    .writeElement(invoice.getString("paymentDueDate"))
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
