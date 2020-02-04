import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.net.Socket;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Klient {
    private static String SERVER_ID="127.0.0.1";
    private static int SERVER_PORT=5050;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_ID, SERVER_PORT); //Lager tilbkobling til server

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader inputTastatur = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter utFraKlient = new PrintWriter(socket.getOutputStream(), true);

        System.out.println("Skriv inn en nettadresse");
        String meldingTilServer = inputTastatur.readLine();
        utFraKlient.println(meldingTilServer);


        String serverResponse = input.readLine();
        System.out.println("Retur fra server:  " + serverResponse);

        /////////////////////////////////////////////////////////////////////////////////////////////
        while (true) {
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
        socket.close();
        System.exit(0);

    }


}
