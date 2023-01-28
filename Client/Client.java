package Client;

import ParserURI.ParserURI;
import java.util.HashMap;

/**
 * Client class for using ParserURI
 */
public class Client {
    public Client() {}

    /**
     * Method for testing ParserURI class
     * @param URI
     */
    public void useParser(String URI)  {
        try {
            System.out.println("\n");
            System.out.println("Using parser with URI: " + URI);
            ParserURI parser = new ParserURI(URI);
            HashMap<String, String> parameters = parser.getURIvalues();
            for (String ele: parameters.keySet()) {
                System.out.println(ele + ": " + parameters.get(ele));
            }
            String path = parser.getPath();
            System.out.println("Path: " + path + "\n");
        } catch(Exception e) {
            System.out.println(e);
        }
}
}
    
