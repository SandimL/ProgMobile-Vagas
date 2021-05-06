package com.example.vagas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vagas.EmpregosOnline.Emprego.Emprego;
import com.example.vagas.EmpregosOnline.Emprego.EmpregoActivity;
import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.EmpregosOnline.Pessoa.PessoaActivity;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPessoa = (Button)findViewById(R.id.PessoaActivity);
        Button btnEmprego = (Button)findViewById(R.id.EmpregoActivity);

        Intent pessoaIntent = new Intent(MainActivity.this, PessoaActivity.class);
        btnPessoa.setOnClickListener(v -> startActivity(pessoaIntent));

        Intent empregoIntent = new Intent(MainActivity.this, EmpregoActivity.class);
        btnEmprego.setOnClickListener(v -> startActivity(empregoIntent));
    }
}