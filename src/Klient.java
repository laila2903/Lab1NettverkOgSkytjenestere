import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Klient {
    private static String SERVER_IP="127.0.0.1";
    private static int SERVER_PORT=5050;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT); //Lager tilbkobling til server

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));// data fra server
        BufferedReader inputTastatur = new BufferedReader(new InputStreamReader(System.in));// input tastatur
        PrintWriter utFraKlient = new PrintWriter(socket.getOutputStream(), true); //Data som skal sender

        System.out.println("Skriv inn en nettadresse"); // Spør bruker om å sende inn en nettadresse
        String meldingTilServer = inputTastatur.readLine();// Melding fra bruker til server
        utFraKlient.println(meldingTilServer); // Skriver dataen til socket som skal sendes til serveren


        String serverResponse = input.readLine(); // Mottar så responsen fra server
        System.out.println("Retur fra server:  " + serverResponse); // Printer ut data for prdens skyld

        /////////////////////////////////////////////////////////////////////////////////////////////
        while (true) { // Setter i en evig ventende løkke som venter på en respone fra server
            if (serverResponse == null) {
                System.out.println("!!!Server couldn’t find the web page!!");
                break;
            }

            else if (Integer.parseInt(serverResponse) == 0) {
                Funksjoner.printWebside(meldingTilServer);
                break;
            } else if (Integer.parseInt(serverResponse) == 1) {
                System.out.println("!!!No email address found on the page!!!’");
                break;

            } else if (Integer.parseInt(serverResponse) == 2) {
                System.out.println("!!!Server couldn’t find the web page!!");
                break;
            }
        }
        socket.close(); // Slutter tilkoblingen
        System.exit(0);

    }


}
