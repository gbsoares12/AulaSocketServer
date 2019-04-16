/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testesockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author 42519630833
 */
public class TesteSockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        int porta = 56000;

        ServerSocket server = new ServerSocket(porta);
        BufferedReader in = null;
        server.setReuseAddress(true);
        PrintWriter out = null;
        
        Scanner sc = new Scanner(System.in);
        
        Socket conn = null;

        try {
            System.out.println("Aguardando conexao de cliente...");
            conn = server.accept();
            System.out.println("Conectado!");
            
        } catch (Exception e) {
            System.out.println("Problema na conexão!");
        }


        out = new PrintWriter(conn.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        while (true) {

            try {


                String mensagemDigitada = sc.nextLine();
                String mensagemRecebida = null;

                while(mensagemDigitada.equalsIgnoreCase("0")){
                    
                    System.out.println(mensagemDigitada);
                    out.println(mensagemDigitada);
                    
                    
                    
                    mensagemRecebida = in.readLine();
                    System.out.println(mensagemRecebida);
                
                }
                
            } catch (UnknownHostException e) {
                System.out.println("Host não encontrado");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Erro de entrada / saída ao criar socket");
                e.printStackTrace();
            } finally {
                try {// fecha input stream e socket
                    if (in != null) {
                        in.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (IOException e) {
                    System.out.println("Erro ao fechar input stream ou socket");
                    e.printStackTrace();
                }

            }

        }
    }

}
