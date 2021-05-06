package com.example.vagas.EmpregosOnline.Pessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vagas.EmpregosOnline.Emprego.Emprego;
import com.example.vagas.EmpregosOnline.Emprego.EmpregoActivity;
import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.R;

import java.util.ArrayList;
import java.util.List;

public class CadastroPessoa extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtEmail, edtTelefone;
    private Spinner edtVagaId;
    private Button btnVariavel;
    Pessoa pessoa, altPessoa;
    EmpregosOnlineDatabase empregosDatabase;
    long retornoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        empregosDatabase = EmpregosOnlineDatabase.getInstance(this);
        Intent it=getIntent();
        altPessoa = (Pessoa) it.getSerializableExtra("pessoa");
        pessoa = new Pessoa();
        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtVagaId = (Spinner) findViewById(R.id.edtVagaId);
        ArrayList<String> arrayIdVagas = (ArrayList<String>) it.getSerializableExtra("empregos");
        ArrayAdapter<String> adapter =  new ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, arrayIdVagas);
        edtVagaId.setAdapter(adapter);

        btnVariavel = findViewById(R.id.btnVariavel);
        if(altPessoa != null){
            btnVariavel.setText("ALTERAR");
            edtNome.setText(altPessoa.nome);
            edtCpf.setText(altPessoa.cpf);
            edtEmail.setText(altPessoa.email);
            edtTelefone.setText(altPessoa.telefone);
            pessoa.pessoaId = altPessoa.pessoaId;
            int spinnerPosition = adapter.getPosition(Integer.toString(altPessoa.vagaId));
            edtVagaId.setSelection(spinnerPosition);
        }
        else{
            btnVariavel.setText("SALVAR");
        }

        btnVariavel.setOnClickListener(view -> {
            String nome = edtNome.getText().toString();
            String cpf = edtCpf.getText().toString();
            String email = edtEmail.getText().toString();
            String telefone = edtTelefone.getText().toString();
            String vaga = edtVagaId.getSelectedItem().toString();
            pessoa.nome = nome;
            pessoa.cpf = cpf;
            pessoa.email = email;
            pessoa.telefone = telefone;
            pessoa.vagaId = Integer.parseInt(vaga);
            if(btnVariavel.getText().toString().equals("SALVAR")) {
                AsyncTask.execute(() -> {
                    long retornoBD = empregosDatabase.IPessoaDao().insert(pessoa);
                    if(retornoBD==-1){
                        alert("Erro ao Cadastrar!");
                    }
                    else{
                        alert("Cadastro realizado com sucesso!");
                    }
                });
            }else{
                AsyncTask.execute(() -> {
                    int retDb = empregosDatabase.IPessoaDao().update(pessoa);
                    empregosDatabase.close();
                    if(retornoBD==-1){
                        alert("Erro ao editar!");
                    }
                    else{
                        alert("Edição realizada com sucesso!");
                    }
                });
            }
            finish();
        });
    }

    private void alert(String s) {
        runOnUiThread(()->Toast.makeText(this, s, Toast.LENGTH_SHORT).show());
    }
}