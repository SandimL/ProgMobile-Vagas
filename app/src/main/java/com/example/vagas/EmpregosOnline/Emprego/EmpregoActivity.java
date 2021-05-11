package com.example.vagas.EmpregosOnline.Emprego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.EmpregosOnline.Pessoa.CadastroPessoa;
import com.example.vagas.EmpregosOnline.Pessoa.Pessoa;
import com.example.vagas.EmpregosOnline.Pessoa.PessoaActivity;
import com.example.vagas.R;

import java.io.Serializable;
import java.util.ArrayList;

public class EmpregoActivity extends AppCompatActivity {

    private ListView listEmprego;
    private Button btnNovoEmprego;
    private Emprego emprego;
    EmpregosOnlineDatabase empregosDatabase;
    ArrayList<Emprego> arrayListEmprego;
    ArrayAdapter<Emprego> arrayAdapterEmprego;
    private int id1, id2; //menu item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprego);
        empregosDatabase = EmpregosOnlineDatabase.getInstance(this);
        listEmprego = findViewById(R.id.listEmpregos);
        registerForContextMenu(listEmprego);
        btnNovoEmprego = findViewById(R.id.btnNovoEmprego);
        btnNovoEmprego.setOnClickListener(v -> {
            Intent it = new Intent(EmpregoActivity.this, CadastroEmprego.class);
            startActivity(it);
        });

        listEmprego.setOnItemClickListener((adapterView, view, position, id) -> {
            Emprego empregoEnviado = (Emprego) arrayAdapterEmprego.getItem(position);
            Intent it = new Intent(EmpregoActivity.this, CadastroEmprego.class);
            it.putExtra("emprego", (Serializable) empregoEnviado);
            startActivity(it);
        });

        listEmprego.setOnItemLongClickListener((adapterView, view, position, id) -> {
            emprego = arrayAdapterEmprego.getItem(position);
            return false;
        });
    }

    public void preencheLista() {
        AsyncTask.execute(() -> {
            arrayListEmprego = (ArrayList<Emprego>) empregosDatabase.IEmpregoDao().getAll();
            if (arrayListEmprego != null) {
                runOnUiThread(()-> {
                    arrayAdapterEmprego = new ArrayAdapter<Emprego>(EmpregoActivity.this,
                            android.R.layout.simple_list_item_1, arrayListEmprego);
                    listEmprego.setAdapter(arrayAdapterEmprego);
                });
            }
        });
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancela");

        mDelete.setOnMenuItemClickListener(menuItem -> {
            AsyncTask.execute(() -> {
                empregosDatabase.IEmpregoDao().delete(emprego);
                preencheLista();
            });
            return false;
        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    protected void onResume() {
        super.onResume();
        preencheLista();
    }


    private void alert(String s) {
        runOnUiThread(()->Toast.makeText(this, s, Toast.LENGTH_SHORT).show());
    }
}