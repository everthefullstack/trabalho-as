package service;

import java.io.IOException;
import java.sql.SQLException;
import repository.ServiceRepository;

public class ThreadModerador implements Runnable {

    @Override
    public void run() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        ServiceRepository sr = new ServiceRepository();

        while (true) {

            try {
                System.out.println("Iniciando a varredura...");
                sr.verifyPalavra();
                System.out.println("Terminando a varredura, nova varredura em 1 minuto.");
                Thread.sleep(60000);
                
            } catch (InterruptedException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
