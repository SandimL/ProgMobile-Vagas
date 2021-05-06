package com.example.vagas.EmpregosOnline;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vagas.EmpregosOnline.Emprego.Emprego;
import com.example.vagas.EmpregosOnline.Emprego.IEmpregoDao;
import com.example.vagas.EmpregosOnline.Pessoa.IPessoaDao;
import com.example.vagas.EmpregosOnline.Pessoa.Pessoa;

@Database(entities = {Pessoa.class, Emprego.class}, version = 1, exportSchema = false)
public abstract class EmpregosOnlineDatabase extends RoomDatabase {
    private static final String DB_NAME = "EmpregosOnline";
    private static volatile EmpregosOnlineDatabase instance;

    public static synchronized EmpregosOnlineDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    protected EmpregosOnlineDatabase() {};

    private static EmpregosOnlineDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                EmpregosOnlineDatabase.class,
                DB_NAME).build();
    }

    public abstract IPessoaDao IPessoaDao();
    public abstract IEmpregoDao IEmpregoDao();
}

