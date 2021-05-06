package com.example.vagas.EmpregosOnline.Pessoa;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IPessoaDao {

    @Query("SELECT * FROM pessoa")
    List<Pessoa> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Pessoa pessoa);

    @Update
    int update(Pessoa pessoa);

    @Delete
    void delete(Pessoa pessoa);
}
