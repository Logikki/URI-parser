import Client.Client;

class Main {
    public static void main(String[] args) {
        Client test = new Client();

        String correcConfirmURI = "visma-identity://confirm?source=netvisor&paymentnumber=102226";
        test.useParser(correcConfirmURI);
    
        String correctSignURI = "visma-identity://sign?source=vismasign&documentid=105ab44";
        test.useParser(correctSignURI);
    
        String correctLoginURI = "visma-identity://login?source=severa";
        test.useParser(correctLoginURI);
        
        String incorretURI = "vism-identity://login?source=severa"; 
        test.useParser(incorretURI);
        
    
        String incorretConfirmURI = "visma-identity://confirm?source=netvisor&paymentnumber=abc102226";
        test.useParser(incorretConfirmURI);
        }
}
