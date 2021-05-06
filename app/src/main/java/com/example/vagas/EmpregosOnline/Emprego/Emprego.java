package com.example.vagas.EmpregosOnline.Emprego;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Emprego {
    @PrimaryKey
    public int vagaId;
    public String descricao;
    public int horasSemana;
    public double valor ;
}
