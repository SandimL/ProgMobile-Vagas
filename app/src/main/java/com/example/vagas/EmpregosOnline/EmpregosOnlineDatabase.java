package com.example.vagas.EmpregosOnline;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.vagas.EmpregosOnline.Emprego.Emprego;
import com.example.vagas.EmpregosOnline.Emprego.IEmpregoDao;
import com.example.vagas.EmpregosOnline.Pessoa.IPessoaDao;
import com.example.vagas.EmpregosOnline.Pessoa.Pessoa;

@Database(entities = {Pessoa.class, Emprego.class}, version = 1)
public abstract class EmpregosOnlineDatabase extends RoomDatabase {
    public abstract IPessoaDao IPessoaDao();
    public abstract IEmpregoDao IEmpregoDao();
}

