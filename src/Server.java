import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {
    private static int PORT = 5050;

    private static ArrayList<KlientParalell> klienter=new ArrayList<>();
    private static ExecutorService executorService= Executors.newFixedThreadPool(4);


    public static void main(String[] args) throws IOException {
        ServerSocket mottaker = new ServerSocket(PORT);
        Socket klientTilkobling;
        while (true) { //Venter på tilkobling i en evig løkke
            System.out.println("Venter på tilkobling");
            klientTilkobling = mottaker.accept();
            System.out.println("Tilkoblet"); //Vellykket tilkoblet
            //PrintWriter ut = new PrintWriter(klientTilkobling.getOutputStream(), true);
            //BufferedReader in = new BufferedReader(new InputStreamReader(klientTilkobling.getInputStream()));
            KlientParalell klientParalell=new KlientParalell(klientTilkobling);
            klienter.add(klientParalell);
            executorService.execute(klientParalell);
        }


    }


}

