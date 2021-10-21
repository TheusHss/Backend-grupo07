package br.bandtec.com.projetoimove;

import br.bandtec.com.projetoimove.domains.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArquivoCSV {
    public static void gravaLista(ListaObj<Usuario> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        nomeArq += ".csv";
        try {
            arq = new FileWriter(nomeArq, true);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }
        try {
            while(lista.hasNext()) {
                Usuario user = lista.next();
                saida.format("%d;%s;%s;%s;%s;%s;%s\n", user.getId(),
                        user.getNome(), user.getSobrenome(), user.getEmail(),
                        user.getCpf(), user.getTelefone(), user.getTipoUsuario());
            }
        } catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leExibeArquivo(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        boolean deuRuim = false;

        nomeArq += ".csv";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.err.println("Arquivo não encontrado");
            System.exit(1);
        }

        try {
            System.out.printf("%4s %-15s %-20s %-35s %-15s %-20s %-10s\n", "ID", "NOME", "SOBRENOME", "EMAIL", "CPF", "TELEFONE", "TIPO DE USUARIO");
            while (entrada.hasNext()) {
                int id = entrada.nextInt();
                String nome = entrada.next();
                String sobrenome = entrada.next();
                String email = entrada.next();
                String cpf = entrada.next();
                String telefone = entrada.next();
                String tipoUser = entrada.next();
                System.out.printf("%4d %-15s %-20s %-35s %-15s %-20s %-10s\n", id, nome, sobrenome, email, cpf, telefone, tipoUser);
            }
        } catch (NoSuchElementException erro) {
            System.err.println("Arquivo com problemas.");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.err.println("Erro na leitura do arquivo.");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}
