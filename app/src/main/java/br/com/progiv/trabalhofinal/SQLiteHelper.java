package br.com.progiv.trabalhofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class SQLiteHelper extends SQLiteOpenHelper {

    private String[] scriptSQLCreate;
    private String scriptSQLDelete;

    /**
     * Cria uma instância de SQLiteHelper
     *
     * @param context
     * @param nomeBanco nome do banco de dados
     * @param versaoBanco versão do banco de dados (se for diferente é para atualizar)
     * @param scriptSQLCreate SQL com o create table..
     * @param scriptSQLDelete SQL com o drop table...
     */
    SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[]
            scriptSQLCreate, String scriptSQLDelete) {
        super(context, nomeBanco, null, versaoBanco);
        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }

    @Override
    // Criar novo banco...
    public void onCreate(SQLiteDatabase db) {
        // Executa cada sql passado como parâmetro
        for (int i = 0; i < scriptSQLCreate.length; i++) {
            String sql = scriptSQLCreate[i];

            // Cria o banco de dados executando o script de criação
            db.execSQL(sql);
        }
    }

    @Override
    // Mudou a versão...
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
        // Deleta as tabelas...
        db.execSQL(scriptSQLDelete);
        // Cria novamente...
        onCreate(db);
    }
}