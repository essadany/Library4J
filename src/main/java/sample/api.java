package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api {
    private HttpURLConnection con;

    public void get_response(String choice,String input){
        String result="dc.tiltle";
        switch (choice){
            case "author":
                result="dc.creator";
                break;
            case "title":
                result="dc.title";
                break;
            case "edition_date":
                result="dc.date";
                break;
            case "language":
                result="dc.language";
                break;
            default:
                System.out.println("No choice selected");
                break;


        }
        String creator = "Victor%20Hugo";
        try {
            String url = "https://gallica.bnf.fr/SRU?operation=searchRetrieve&version=1.2&query=("+result+"%20all%20"+input+")%20and%20(dc.type%20all%20%22monographie%22)";
            System.out.println(url);
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            // System.out.println(response.toString());
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));
            NodeList errNodes = doc.getElementsByTagName("oai_dc:dc");
            int i=0;
            while (errNodes.getLength() > 0) {
                Element err = (Element)errNodes.item(i);
                i++;

                System.out.println("author : "+err.getElementsByTagName("dc:creator").item(0).getTextContent());
                System.out.println("title : "+err.getElementsByTagName("dc:title").item(0).getTextContent());
                System.out.println("date : "+err.getElementsByTagName("dc:date").item(0).getTextContent());
                System.out.println("language : "+err.getElementsByTagName("dc:language").item(1).getTextContent());
            }
            // success

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public HttpURLConnection getCon() {
        return con;
    }

}
