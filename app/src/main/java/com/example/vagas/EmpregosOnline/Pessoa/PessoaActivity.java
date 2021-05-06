package com.example.vagas.EmpregosOnline.Pessoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.vagas.EmpregosOnline.Emprego.Emprego;
import com.example.vagas.EmpregosOnline.EmpregosOnlineDatabase;
import com.example.vagas.MainActivity;
import com.example.vagas.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PessoaActivity extends AppCompatActivity {

    private ListView listPessoa;
    private Button btnNovaPessoa;
    private Pessoa pessoa;
    EmpregosOnlineDatabase empregosDatabase;
    ArrayList<Pessoa> arrayListPessoa;
    ArrayAdapter<Pessoa> arrayAdapterPessoa;
    ArrayList<Emprego> arrayListEmprego;
    ArrayList<String> arrayListVagasId = new ArrayList<String>();
    private int id1, id2; //menu item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
        empregosDatabase = EmpregosOnlineDatabase.getInstance(this);
        listPessoa = findViewById(R.id.listPessoa);
        registerForContextMenu(listPessoa);
        btnNovaPessoa = findViewById(R.id.btnNovaPessoa);
        btnNovaPessoa.setOnClickListener(v -> {
            Intent it = new Intent(PessoaActivity.this, CadastroPessoa.class);
            AsyncTask.execute(() -> {
                arrayListEmprego = (ArrayList<Emprego>) empregosDatabase.IEmpregoDao().getAll();
                empregosDatabase.close();
                arrayListVagasId.add("-1");
                for (Emprego emprego: arrayListEmprego) {
                    arrayListVagasId.add(Integer.toString(emprego.vagaId));
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("empregos", arrayListVagasId);
                it.putExtras(bundle);
                startActivity(it);
            });
        });

        listPessoa.setOnItemClickListener((adapterView, view, position, id) -> {
            Pessoa PessoaEnviada = (Pessoa) arrayAdapterPessoa.getItem(position);
            Intent it = new Intent(PessoaActivity.this, CadastroPessoa.class);
            it.putExtra("pessoa",  PessoaEnviada);
            AsyncTask.execute(() -> {
                arrayListEmprego = (ArrayList<Emprego>) empregosDatabase.IEmpregoDao().getAll();
                empregosDatabase.close();
                arrayListVagasId.add("-1");
                for (Emprego emprego: arrayListEmprego) {
                    arrayListVagasId.add(Integer.toString(emprego.vagaId));
                }
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("empregos", arrayListVagasId);
                it.putExtras(bundle);
                startActivity(it);
            });
        });

        listPessoa.setOnItemLongClickListener((adapterView, view, position, id) -> {
            pessoa = arrayAdapterPessoa.getItem(position);
            return false;
        });
    }

    public void preencheLista() {
        AsyncTask.execute(() -> {
            arrayListPessoa = (ArrayList<Pessoa>) empregosDatabase.IPessoaDao().getAll();
            empregosDatabase.close();

            if (arrayListPessoa != null) {
                runOnUiThread(()-> {
                    arrayAdapterPessoa = new ArrayAdapter<Pessoa>(PessoaActivity.this,
                            android.R.layout.simple_list_item_1, arrayListPessoa);
                    listPessoa.setAdapter(arrayAdapterPessoa);
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
                empregosDatabase.IPessoaDao().delete(pessoa);
                empregosDatabase.close();
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
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}