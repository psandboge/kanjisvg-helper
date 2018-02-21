package se.sandboge.kanjisvghelper;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Convert {
    public static void main(String[] args) {
        String sourcePath = "hiragana";
        String pathTemplate = "/pathtemplate.xsl";
        String textTemplate = "/texttemplate.xsl";

        String svgFile = sourcePath + "/03041.svg";
        File dir = new File(sourcePath);
        List<String> names = new ArrayList<String>(Arrays.asList(dir.list()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        for (String name: names) {
            String path = parseFile(pathTemplate, sourcePath + "/" + name, factory);
            System.out.println(path);
            String text = parseFile(textTemplate, sourcePath + "/" + name, factory);
            System.out.println(text);
        }
    }

    private static String parseFile(String template, String svgFile, DocumentBuilderFactory factory) {
        try {
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new FileInputStream(svgFile));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource ss = new StreamSource(Convert.class.getResourceAsStream(template));
            Transformer transformer = transformerFactory.newTransformer(ss);

            DOMSource domSource = new DOMSource(document);
            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(domSource, result);
            return stringWriter.toString();

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
