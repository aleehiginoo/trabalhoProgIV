package br.com.progiv.trabalhofinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarPessoa extends Activity implements OnClickListener {
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.form_buscar_pessoa);

        Button btBuscar = (Button) findViewById(R.id.btBuscar);
        btBuscar.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Cancela para não ficar nada pendente na tela
        setResult(RESULT_CANCELED);

        // Fecha a tela
        finish();
    }

    /**
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    public void onClick(View view) {

        EditText nome = (EditText) findViewById(R.id.campoNome);
        TextView cpf = (TextView) findViewById(R.id.campoCpf);
        TextView idade = (TextView) findViewById(R.id.campoIdade);

        // Recupera o nome da pessoa
        String nomePessoa = nome.getText().toString();

        // Busca a pessoa pelo nome
        Pessoa pessoa = buscarPessoa(nomePessoa);

        if (pessoa != null) {
            // Atualiza os campos com o resultado
            nome.setText(pessoa.nome);
            cpf.setText("CPF: " + Pessoa.formatCpf(pessoa.cpf));
            idade.setText("Idade: " + String.valueOf(pessoa.idade) + " ano" + (pessoa.idade > 1 ? "s" : ""));
        } else {
            // Limpa os campos
            cpf.setText("");
            idade.setText("");

            Toast.makeText(BuscarPessoa.this, "Nenhuma pessoa encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    // Busca um carro pelo nome
    protected Pessoa buscarPessoa(String nomePessoa) {
        Pessoa pessoa = CadastroPessoa.repositorio.buscarPessoaPorNome(nomePessoa);
        return pessoa;
    }
}