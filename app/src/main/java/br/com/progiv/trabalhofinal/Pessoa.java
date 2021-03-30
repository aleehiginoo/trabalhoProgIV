package br.com.progiv.trabalhofinal;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class Pessoa {

    public static String[] colunas = new String[] { Pessoas._ID, Pessoas.NOME,
            Pessoas.CPF, Pessoas.IDADE };

    public static final String AUTHORITY = "br.com.progiv.trabalhofinal.provider.pessoa";

    public long id;
    public String nome;
    public String cpf;
    public int idade;

    public Pessoa() {

    }

    public Pessoa(String nome, String cpf, int idade) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public Pessoa(long id, String nome, String cpf, int idade) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    /**
     * Classe interna para representar as colunas e ser utilizada por um Content
     * Provider
     *
     * Filha de BaseColumns que já define (_id e _count), para seguir o padrão
     * Android
     */
    public static final class Pessoas implements BaseColumns {

        // Não pode instanciar esta Classe
        private Pessoas() {
        }

        public static final String NOME = "nome";
        public static final String CPF = "cpf";
        public static final String IDADE = "idade";
    }

    public static String formatCpf(String cpf){
        int complete = 11 - cpf.length();

        for (int i = 0; i < complete; i++){
            cpf = '0' + cpf;
        }

        return cpf.substring(0,3) + '.' + cpf.substring(3,6) + '.' + cpf.substring(6,9) + '-' + cpf.substring(9);
    }
}