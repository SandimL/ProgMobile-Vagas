package com.example.vagas.EmpregosOnline.Pessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.R;

public class CadastroPessoa extends AppCompatActivity {

    private EditText edtNome, edtCpf, edtEmail, edtTelefone;
    private Spinner edtVagaId;
    private Button btnVariavel;
    Pessoa pessoa, altPessoa;
    EmpregosOnlineDatabase empregosDatabase;
    Integer retornoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        Intent it=getIntent();
        altPessoa = (Pessoa) it.getSerializableExtra("pessoaId");
        pessoa = new Pessoa();
        edtNome = findViewById(R.id.edtNome);
        edtCpf = findViewById(R.id.edtCpf);
        edtEmail = findViewById(R.id.edtEmail);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtVagaId = findViewById(R.id.edtVagaId);
        btnVariavel = findViewById(R.id.btnVariavel);
        if(altPessoa != null){
            btnVariavel.setText("ALTERAR");
            edtNome.setText(altPessoa.nome);
            edtCpf.setText(altPessoa.cpf);
            edtEmail.setText(altPessoa.email);
            edtTelefone.setText(altPessoa.telefone);
            pessoa.pessoaId = altPessoa.pessoaId;
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
            long retornoBD;
            pessoa.nome = nome;
            pessoa.cpf = cpf;
            pessoa.email = email;
            pessoa.telefone = telefone;
            pessoa.vagaId = Integer.parseInt(vaga);
            if(btnVariavel.getText().toString().equals("SALVAR")) {
                retornoBD = empregosDatabase.IPessoaDao().insert(pessoa);
                if(retornoBD==-1){
                    alert("Erro ao Cadastrar!");
                }
                else{
                    alert("Cadastro realizado com sucesso!");
                }
            }else{
                empregosDatabase.IPessoaDao().update(pessoa);
                empregosDatabase.close();
            }
            finish();
        });
    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}