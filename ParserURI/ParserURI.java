package ParserURI;
import java.util.HashMap;
import java.util.ArrayList;
import ExceptionMsg.ExceptionMsg;

public class ParserURI {
    protected String source;
    protected String path;
    protected String documentID;
    protected Integer paymentNumber;
    
    /**
     * Class constructor parses given string
     * @param URI
     * @throws ExceptionMsg
     * example inputs:
     */
    public ParserURI(String URI) throws ExceptionMsg {
        this.parseURI(URI);
    }
    /* Empty constructor, if URI needs to be parsed after creating class */
    public ParserURI() {}

    /**
     * Uri parser method. First it parses the uri into parts, and then
     * send these parts to their own parser methods.
     * URI values are parsed into the classes attributes. 
     * @param URI String
     * @throws ExceptionMsg
     */
    public void parseURI (String URI) throws ExceptionMsg {
        String parseScheme = "[:?&]";
        String[] parameters = URI.split(parseScheme);
        String scheme = parameters[0];
        String path = parameters[1];
        String source = parameters[2];
        //Checks that the URI contains correct scheme.
        if (!scheme.equals("visma-identity")) {
            throw new ExceptionMsg("Invalid scheme. Correct scheme is 'visma-identity'");
        }
        this.parseSource(source);

        //Checks URI path and calls right parser method.
        if (path.equals("//confirm")) {
            this.path = "confirm";
            try {
                String parameter = parameters[3];
                this.parsePaymentNumber(parameter);
            } catch(Exception e) {
                throw new ExceptionMsg("Invalid URI");
            }
        }
        else if (path.equals("//sign")) {
            this.path = "sign";
            try {
                String parameter = parameters[3];
                this.parseDocumentId(parameter);
            } catch (Exception e) {
                throw new ExceptionMsg("Invalid URI");
            }
        }
        else if (path.equals("//login")) {
            this.path = "login";
        }
        else {
            throw new ExceptionMsg("Invalid path in URI");
        }
    }

    /**
     * Parses paymentnumber parameter
     * @param URIparameter String
     * ex. URIparameter paymentnumber=102226
     * @throws ExceptionMsg 
     */
    private void parsePaymentNumber (String URIparameter) throws ExceptionMsg {
        String[] parsed = URIparameter.split("=");
        String paymentNumber = parsed[0];
        String paymentNumberValue = parsed[1];
        if (!paymentNumber.equals("paymentnumber") || parsed.length != 2) {
            throw new ExceptionMsg("Invalid URI payment number");
        }
        try {
            Integer paymentNumberURI = Integer.parseInt(paymentNumberValue);
            this.paymentNumber = paymentNumberURI;
        } catch (Exception e) {
            throw new ExceptionMsg("Invalid payment number in URI");
        }
    }
    /**
     * Parses documentID parameter
     * @param URIparameter String
     * ex. parameter documentid=105ab44
     * @throws ExceptionMsg
     */
    private void parseDocumentId (String URIparameter) throws ExceptionMsg {
        String[] parsed = URIparameter.split("=");
        String documentId = parsed[0];
        String documentIdValue = parsed[1];
        if (!documentId.equals("documentid") || parsed.length != 2) {
            throw new ExceptionMsg("Invalid URI document id");
        }
        this.documentID = documentIdValue;
    }

    /**
     * Parses source parameter
     * @param URIparameter String
     * ex. parameter source=netvisor
     * @throws ExceptionMsg
     */
    private void parseSource (String URIparameter) throws ExceptionMsg {
        String[] parsed = URIparameter.split("[=&]");
        String source = parsed[0];
        String sourceValue = parsed[0];
        if (!source.equals("source")) {
            throw new ExceptionMsg("Invald URI source");
        }
        this.source = sourceValue;
    }
    /**
     * 
     * @return HashMap<String, String>
     * Returns URI values that were parsed
     */
    public HashMap<String, String> getURIvalues() {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("source", source);
        if (this.path.equals("confirm")) {
            parameters.put("paymentNumber", Integer.toString(paymentNumber));
        }
        else if (this.path.equals("sign")) {
            parameters.put("documentID", documentID);   
        }
        return parameters;
    }
    /**
     * 
     * @return String 
     * Returns this.path
     */
    public String getPath() {
        return this.path;
    }
}