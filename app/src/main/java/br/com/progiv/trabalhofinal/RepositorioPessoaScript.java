package br.com.progiv.trabalhofinal;

import android.content.Context;

public class RepositorioPessoaScript extends RepositorioPessoa {

    // Script para fazer drop na tabela
    private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS pessoa";

    // Cria a tabela com o "_id" sequencial
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
        "create table pessoa (_id integer primary key autoincrement, nome text not null, cpf text not null, idade text not null);",
        "insert into pessoa(nome, cpf, idade) values('Alexandre Higino','01234567890', 27);",
        "insert into pessoa(nome, cpf, idade) values('Amanda Oliveira','09876543210', 24);",
        "insert into pessoa(nome, cpf, idade) values('Eduardo','55533322211', 55);" };

    // Nome do banco
    private static final String NOME_BANCO = "base_pessoas";

    // Controle de versão
    private static final int VERSAO_BANCO = 2;

    // Nome da tabela
    public static final String TABELA_PESSOA = "pessoa";

    // Classe utilitária para abrir, criar, e atualizar o banco de dados
    private SQLiteHelper dbHelper;

    // Cria o banco de dados com um script SQL
    public RepositorioPessoaScript(Context ctx) {
        // Criar utilizando um script SQL
        dbHelper = new SQLiteHelper(ctx, RepositorioPessoaScript.NOME_BANCO,
        RepositorioPessoaScript.VERSAO_BANCO,
        RepositorioPessoaScript.SCRIPT_DATABASE_CREATE,
        RepositorioPessoaScript.SCRIPT_DATABASE_DELETE);

        // abre o banco no modo escrita para poder alterar também
        db = dbHelper.getWritableDatabase();
    }

    // Fecha o banco
    @Override
    public void fechar() {
        super.fechar();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}