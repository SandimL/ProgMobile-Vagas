package com.example.vagas.EmpregosOnline.Pessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.MainActivity;
import com.example.vagas.R;

import java.io.Serializable;
import java.util.ArrayList;

public class PessoaActivity extends AppCompatActivity {

    private ListView listPessoa;
    private Button btnNovaPessoa;
    private Pessoa pessoa;
    EmpregosOnlineDatabase empregosDatabase;
    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;
    private int id1, id2; //menu item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPessoa = findViewById(R.id.listPessoa);
        registerForContextMenu(listPessoa);
        btnNovaPessoa = findViewById(R.id.btnNovaPessoa);

        btnNovaPessoa.setOnClickListener(v -> {
            Intent it = new Intent(PessoaActivity.this, CadastroPessoa.class);
            startActivity(it);
        });

        listPessoa.setOnItemClickListener((adapterView, view, position, id) -> {
            Pessoa PessoaEnviada = (Pessoa) arrayAdapterPessoa.getItem(position);
            Intent it = new Intent(PessoaActivity.this, CadastroPessoa.class);
            it.putExtra("pessoaId", (Serializable) PessoaEnviada);
            startActivity(it);
        });

        listPessoa.setOnItemLongClickListener((adapterView, view, position, id) -> {
            pessoa = arrayAdapterPessoa.getItem(position);
            return false;
        });
    }

    public void preencheLista() {
        arrayListPessoa = (ArrayList<Pessoa>) empregosDatabase.IPessoaDao().getAll();
        empregosDatabase.close();
        if (listPessoa != null) {
            arrayAdapterPessoa = new ArrayAdapter<Pessoa>(PessoaActivity.this,
                    android.R.layout.simple_list_item_1, arrayListPessoa);
            listPessoa.setAdapter(arrayAdapterPessoa);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mDelete = menu.add(Menu.NONE, id1, 1, "Deleta Registro");
        MenuItem mSair = menu.add(Menu.NONE, id2, 2, "Cancela");

        mDelete.setOnMenuItemClickListener(menuItem -> {
//            long retornoBD =
            empregosDatabase.IPessoaDao().delete(pessoa.pessoaId);
            empregosDatabase.close();
//            if (retornoBD == -1) {
//                alert("Erro de exclusão!");
//            } else {
//                alert("Registro excluído com sucesso!");
//            }
            preencheLista();
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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}