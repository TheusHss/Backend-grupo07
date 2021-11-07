package br.bandtec.com.projetoimove;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import org.springframework.data.repository.support.Repositories;

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

    public static void criarArquivoTxt(String nomeArq, int qtdBikes) {


        File file = new File(nomeArq);
        file.delete();

        String header = "Cadastre aqui suas bicicletas de uma vez! - ";
        Date dataDeHoje = new Date();
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        header += formataData.format(dataDeHoje);
        gravaRegistro(nomeArq, header);
        String corpo;

        for (int i = 0; i < qtdBikes; i++) {

            corpo = "01-Categoria:";
            gravaRegistro(nomeArq, corpo);
            corpo = "02-Cor:";
            gravaRegistro(nomeArq, corpo);
            corpo = "03-Tamanho-aro:";
            gravaRegistro(nomeArq, corpo);
            corpo = "04-Velocidade:";
            gravaRegistro(nomeArq, corpo);
            corpo = "05-Valor-hora:";
            gravaRegistro(nomeArq, corpo);
            corpo = "06-Marca:";
            gravaRegistro(nomeArq, corpo);
            corpo = "07-Modelo:";
            gravaRegistro(nomeArq, corpo);
            corpo = (qtdBikes > 1 && i+1 < qtdBikes) ? "PULE ESTA LINHA" : "------------------";
            gravaRegistro(nomeArq, corpo);
        }

        String trailer;
        trailer = "Quantidade de bicicletas "+ qtdBikes + ". " +
                "Verifique se o preenchimento dos campos estão corretos antes de enviar,\n" +
                "caso esteja errado edite na tela de suas bicicletas ou entre em contato.\nIMove Agredece!";
        gravaRegistro(nomeArq,trailer);

    }

    public static void leArquivoRetonadoEgravaTxt(String nomeArq, BicicletaRepository r, PilhaObj<Bicicleta> pilha) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;


        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo: " + erro.getMessage());
        }

        try {
            registro = entrada.readLine();
            String categoria = "";
            String cor = "";
            String tamanhoAro = "";
            String velocidade = "";
            String valorHora = "";
            String marca = "";
            String modelo = "";
            int cont = 0;
            int contCadaSete = 7;
            while (registro != null) {
                tipoRegistro = registro.substring(0,2);

                if (tipoRegistro.equals("01")) {
                    registro = registro.substring(13);
                    System.out.println(registro);
                    categoria = registro;
                    cont++;
                } else if (tipoRegistro.equals("02")) {
                    registro = registro.substring(7);
                    System.out.println(registro);
                    cor = registro;
                    cont++;
                } else if (tipoRegistro.equals("03")) {
                    registro = registro.substring(15);
                    System.out.println(registro);
                    tamanhoAro = registro;
                    cont++;

                } else if (tipoRegistro.equals("04")) {
                    registro = registro.substring(14);
                    System.out.println(registro);
                    velocidade = registro;
                    cont++;

                } else if (tipoRegistro.equals("05")) {
                    registro = registro.substring(14);
                    System.out.println(registro);
                    valorHora = registro;
                    cont++;

                } else if (tipoRegistro.equals("06")) {
                   registro = registro.substring(9);
                    System.out.println(registro);
                    marca = registro;
                    cont++;
                } else if (tipoRegistro.equals("07")) {
                   registro = registro.substring(10);
                    System.out.println(registro);
                    modelo = registro;
                    cont++;
                } else {
                    System.out.println("erro de dados correspondentes ao campo");
                }
                registro = entrada.readLine();
                if (cont == contCadaSete){
                    Bicicleta bike = new Bicicleta();
                    bike.setCategoria(categoria);
                    bike.setCor(cor);
                    bike.setMarca(marca);
                    bike.setModelo(modelo);
                    bike.setTamanhoAro(tamanhoAro);
                    bike.setValorHora(valorHora);
                    bike.setVelocidade(velocidade);
                    r.save(bike);
                    pilha.push(bike);
                    contCadaSete = contCadaSete + 7;
                }

            }
            entrada.close();

        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo " + erro.getMessage());
        }
    }

}
