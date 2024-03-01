package in.parser;

import io.xlate.edi.schema.Schema;
import io.xlate.edi.schema.SchemaFactory;
import io.xlate.edi.stream.EDIOutputFactory;
import io.xlate.edi.stream.EDIStreamConstants;
import io.xlate.edi.stream.EDIStreamWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

public class EdiRetail810InvoiceGenerator {

    public static void main(String[] args) {
    	try {
        EDIOutputFactory factory = EDIOutputFactory.newFactory();
        // Optionally specify delimiters - here the given values are the same as default
        factory.setProperty(EDIStreamConstants.Delimiters.SEGMENT, '~');
        factory.setProperty(EDIStreamConstants.Delimiters.DATA_ELEMENT, '*');
        factory.setProperty(EDIOutputFactory.PRETTY_PRINT, true);
        FileOutputStream stream = new FileOutputStream("invoicevivek.edi");
        EDIStreamWriter writer = factory.createEDIStreamWriter(stream);

        writer.startInterchange();

        writer.writeStartSegment("ISA")
              .writeElement("00")
              .writeElement("          ")
              .writeElement("00")
              .writeElement("          ")
              .writeElement("ZZ")
              .writeElement("Receiver       ")
              .writeElement("ZZ")
              .writeElement("Sender         ")
              .writeElement("200301")
              .writeElement("1430")
              .writeElement("^")
              .writeElement("00501")
              .writeElement("000000001")
              .writeElement("0")
              .writeElement("P")
              .writeElement(":")
              .writeEndSegment();

        writer.writeStartSegment("TA1")
              .writeElement("000000050")
              .writeElement("200229")
              .writeElement("1200")
              .writeElement("A")
              .writeElement("000")
              .writeEndSegment();

        writer.writeStartSegment("IEA")
              .writeElement("1")
              .writeElement("000000001")
              .writeEndSegment();

        	writer.endInterchange();
        	writer.close();
        	
        	

            System.out.println("EDI invoice file successfully generated.");
    

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
