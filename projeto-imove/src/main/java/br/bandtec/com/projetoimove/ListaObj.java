package br.bandtec.com.projetoimove;

public class ListaObj <T> implements Iterator{

    private T[] vetor;
    private int nroElem;
    private int posicao;

    public ListaObj() {
        vetor = (T[]) new Object[100000];
        nroElem = 0;
        posicao = 0;
    }



    public void exibe() {
        while (hasNext()){
            System.out.println(next());
        }
        limpa();
    }

    public int busca(T valorPesquisado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(valorPesquisado)) {
                return i;
            }
        }
        return -1;
    }

    public Boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("Índice inválido!");
            return false;
        }
        else {
            for (int i = indice; i < nroElem-1; i++) {
                vetor[i] = vetor[i+1];
            }
            nroElem--;
            return true;
        }
    }

    public Boolean removeElemento(T valor) {
        return removePeloIndice(busca(valor));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        else {
            return vetor[indice];
        }
    }

    @Override
    public boolean hasNext() {
        if (posicao >= vetor.length || vetor[posicao] == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public T next() {
        return vetor[posicao++];
    }

    @Override
    public void limpa() {
        posicao = 0;
    }

    public Boolean adiciona(T valor) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista cheia!");
            return false;
        }
        else {
            vetor[nroElem++] = valor;
            return true;
        }
    }
}

