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

    @Query("UPDATE pessoa " +
        "SET nome = :nome, " +
        "cpf = :cpf," +
        "email = :email," +
        "telefone = :telefone," +
        "vagaId = :vagaId " +
        "WHERE pessoaId = :pessoa_id")
    long update(int pessoa_id, String nome, String cpf, String email, String telefone, String vagaId);

    @Query("DELETE FROM pessoa WHERE pessoaId = :pessoa_id")
    void delete(int pessoa_id);
}
