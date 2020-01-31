import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private static int PORT = 5050;


    public static void main(String[] args) throws IOException {
        ServerSocket mottaker = new ServerSocket(PORT);
        Socket klientTilkobling;
        while (true) { //Venter på tilkobling i en evig løkke
            System.out.println("Venter på tilkobling");
            klientTilkobling = mottaker.accept();
            System.out.println("Tilkoblet"); //Vellykket tilkoblet
            PrintWriter ut = new PrintWriter(klientTilkobling.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(klientTilkobling.getInputStream()));
            String lestInnString = in.readLine();
            String responsfraServer= Funksjoner.seEtterWebside(lestInnString);
            ut.println(responsfraServer);
        }


    }


}

