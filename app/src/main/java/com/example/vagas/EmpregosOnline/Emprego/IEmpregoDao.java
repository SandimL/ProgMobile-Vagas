package com.example.vagas.EmpregosOnline.Emprego;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IEmpregoDao {

    @Query("SELECT * FROM emprego")
    List<Emprego> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Emprego insert();

    @Update
    Emprego update();

    @Delete
    Emprego delete();
}
