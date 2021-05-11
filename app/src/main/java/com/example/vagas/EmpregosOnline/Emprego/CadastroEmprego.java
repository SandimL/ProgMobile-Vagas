package com.example.vagas.EmpregosOnline.Emprego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.EmpregosOnline.Pessoa.Pessoa;
import com.example.vagas.R;

public class CadastroEmprego extends AppCompatActivity {

    private EditText edtDescricao, edtHorasSemana, edtValor;
    private Button btnVariavel;
    Emprego emprego, altEmprego;
    EmpregosOnlineDatabase empregosDatabase;
    long retornoBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_emprego);
        empregosDatabase = EmpregosOnlineDatabase.getInstance(this);
        Intent it=getIntent();
        altEmprego = (Emprego) it.getSerializableExtra("emprego");
        emprego = new Emprego();
        edtDescricao = findViewById(R.id.edtDescricao);
        edtHorasSemana = findViewById(R.id.edtHorasSemana);
        edtValor = findViewById(R.id.edtValor);
        btnVariavel = findViewById(R.id.btnVariavel);
        if(altEmprego != null){
            btnVariavel.setText("ALTERAR");
            edtDescricao.setText(altEmprego.descricao);
            String horasSemana = Integer.toString(altEmprego.horasSemana);
            edtHorasSemana.setText(horasSemana);
            String valor = Double.toString(altEmprego.valor);
            edtValor.setText(valor);
            emprego.vagaId = altEmprego.vagaId;
        }
        else{
            btnVariavel.setText("SALVAR");
        }

        btnVariavel.setOnClickListener(view -> {
            String descricao = edtDescricao.getText().toString();
            Integer horasSemana = Integer.parseInt(edtHorasSemana.getText().toString());
            Double valor =  Double.parseDouble(edtValor.getText().toString());
            emprego.descricao = descricao;
            emprego.horasSemana = horasSemana;
            emprego.valor = valor;
            if(btnVariavel.getText().toString().equals("SALVAR")) {
                AsyncTask.execute(() -> {
                    long retornoBD = empregosDatabase.IEmpregoDao().insert(emprego);

                    if(retornoBD==-1){
                        alert("Erro ao Cadastrar!");
                    }
                    else{
                        alert("Cadastro realizado com sucesso!");
                    }
                });


            }else{
                AsyncTask.execute(() -> {
                    int retornoBD = empregosDatabase.IEmpregoDao().update(emprego);

                    if(retornoBD==-1){
                        runOnUiThread(()->alert("Erro ao editar!"));
                    }
                    else{
                        runOnUiThread(()->alert("Edição realizado com sucesso!"));
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