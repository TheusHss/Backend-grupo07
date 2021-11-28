package br.bandtec.com.projetoimove;

import br.bandtec.com.projetoimove.domains.Bicicleta;
import br.bandtec.com.projetoimove.domains.Usuario;
import br.bandtec.com.projetoimove.repository.BicicletaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

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

    public static void leArquivoRetonadoEgravaTxt(String nomeArq, BicicletaRepository r, PilhaObj<Bicicleta> pilha, int id) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;


        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo: " + erro.getMessage());
        }

        try {
            FilaObj<Bicicleta> bikeFila = new FilaObj<>(500);
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
                    bike.setVelocidade(velocidade);
                    Usuario u = new Usuario();
                    u.setId(id);
                    bike.setUsuario(u);
                    bikeFila.insert(bike);
                    contCadaSete = contCadaSete + 7;
                }

            }
            for (int i = 0; i <= bikeFila.getTamanho(); i++){
                Bicicleta b = bikeFila.poll();
                if (b.getCategoria() != null && b.getMarca() != null && b.getModelo() != null){
                    r.save(b);
                    pilha.push(b);
                }
            }

            entrada.close();

        } catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo " + erro.getMessage());
        }
    }

    public static void criarArquivoTxtBicicletas(String nomeArq, int qtdBikes) {
        File file = new File(nomeArq);
        file.delete();

        String header = "bicicletas";
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

    public static void gravaArquivoTxt(List<Bicicleta> lista, String nomeArq){
        File file = new File(nomeArq);
        file.delete();

        int contaRegistro = 0;

        // Monta o registro de header
        String header = "00BICICLETA20212";
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
            corpo += String.format("%-3.3s", a.getId());
            corpo += String.format("%-15.15s", a.getCor());
            corpo += String.format("%-20.20s", a.getCategoria());
            corpo += String.format("%-20.20s", a.getMarca());
            corpo += String.format("%-20.20s", a.getModelo());
            corpo += String.format("%-10.10s", a.getTamanhoAro());
            corpo += String.format("%-10.10s", a.getVelocidade());
            gravaRegistro(nomeArq, corpo);
            contaRegistro++;
        }

        // Monta e grava o trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegistro);
        gravaRegistro(nomeArq, trailer);
    }

}
