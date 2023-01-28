package Client;

import ParserURI.ParserURI;
import java.util.HashMap;

/**
 * Client class for using ParserURI
 */
public class Client {
public static void main(String[] args) {
    String correcConfirmURI = "visma-identity://confirm?source=netvisor&paymentnumber=102226";
    useParser(correcConfirmURI);

    String correctSignURI = "visma-identity://sign?source=vismasign&documentid=105ab44";
    useParser(correctSignURI);

    String correctLoginURI = "visma-identity://login?source=severa";
    useParser(correctLoginURI);
    
    String incorretURI = "vism-identity://login?source=severa"; 
    useParser(incorretURI);
    

    String incorretConfirmURI = "visma-identity://confirm?source=netvisor&paymentnumber=abc102226";
    useParser(incorretConfirmURI);
    }

    /**
     * Method for testing ParserURI class
     * @param URI
     */
    static void useParser(String URI)  {
        try {
            System.out.println("Using parser with URI: " + URI);
            ParserURI parser = new ParserURI(URI);
            HashMap<String, String> parameters = parser.getURIvalues();
            for (String ele: parameters.keySet()) {
                System.out.println(ele + ": " + parameters.get(ele));
            }
            String path = parser.getPath();
            System.out.println("Path: " + path);
        } catch(Exception e) {
            System.out.println(e);
        }
}
}
    
