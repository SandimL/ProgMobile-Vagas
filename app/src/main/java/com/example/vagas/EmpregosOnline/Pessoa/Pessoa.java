package com.example.vagas.EmpregosOnline.Pessoa;

import com.example.vagas.EmpregosOnline.Emprego.Emprego;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class Pessoa implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int pessoaId;
    public String nome;
    public String cpf;
    public String email;
    public String telefone;

    @ForeignKey(entity = Emprego.class, parentColumns = "parentClassColumn", childColumns = "childClassColumn", onDelete = ForeignKey.CASCADE)
    public int vagaId;

    @Override
    public String toString() {
        return  "Nome: " + nome +
                "\nVagaId: " + vagaId;
    }
}
