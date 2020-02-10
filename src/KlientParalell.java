import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class KlientParalell implements Runnable { //Implementerer runnable, som tillater tråder
    private Socket klient; // Lager en socket klient
    private BufferedReader in; //Reader for å lese inn datea
    private PrintWriter ut; //printwriter

    public KlientParalell(Socket klientSocket) throws IOException {
        this.klient=klientSocket; //Oppretter ny klient på klienSocketen
        in=new BufferedReader(new InputStreamReader(klient.getInputStream())); //reader
        ut=new PrintWriter(klient.getOutputStream(),true); // printwriter
    }


    @Override
    public void run() { // Run metode for paralell tilkobling av

        String lestInnString = null; // Lest inn string fra klient
        String responsfraServer=null; // Responsen fra server
        try {
                lestInnString = in.readLine();
                responsfraServer= Funksjoner.seEtterWebside(lestInnString);
                ut.println(responsfraServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                ut.close();
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
