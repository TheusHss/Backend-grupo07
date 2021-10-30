package br.bandtec.com.projetoimove;
import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArquivoTXT {

    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;

        // Abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo " + erro.getMessage());
        }

        // Grava Registro e finaliza
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro na gravação do arquivo " + erro.getMessage());
        }
    }

    public static void gravaArquivoTxt(List<Bicicleta> lista, String nomeArq){

        int contaRegistro = 0;

        // Monta o registro de header
        String header = "00NOTA20212";
        Date dataDeHoje = new Date();
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        header += formataData.format(dataDeHoje);
        header += "01";

        // Grava o resgistro do header
        gravaRegistro(nomeArq, header);

        // Monta e grava o corpo
        String corpo;
        for (Bicicleta a : lista){
            corpo = "02";
            corpo += String.format("%-12.12s", a.getCategoria());
            corpo += String.format("%-15.15s", a.getCor());
            corpo += String.format("%-20.20s", a.getTamanhoAro());
            corpo += String.format("%-20.20s", a.getVelocidade());
            corpo += String.format("%-10.12s", a.getId());
            corpo += String.format("%-10.12s", a.getValorHora());
            corpo += String.format("%-10.12s", a.getMarca());
            corpo += String.format("%-10.12s", a.getModelo());

            gravaRegistro(nomeArq, corpo);
            contaRegistro++;
        }

        // Monta e grava o trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegistro);
        gravaRegistro(nomeArq, trailer);
    }

    public static void leArquivoTxt(String nomeArq){
        BufferedReader entrada = null;
        String registro, tipoRegistro;


        try{
            entrada = new BufferedReader(new FileReader(nomeArq));
        }
        catch (IOException erro){
            System.out.println("Erro na abertura do arquivo: " + erro.getMessage());
        }

        try {
            registro = entrada.readLine();

            while (registro != null){
                tipoRegistro = registro.substring(0,2);

                if (tipoRegistro.equals("00")){
                    System.out.println("Eh um header");
                } else {
                    System.out.println("Não eh um header");
                }
            }
        }
        catch (IOException erro){
            System.out.println("Erro ao ler o arquivo " + erro.getMessage());
        }
    }

}
