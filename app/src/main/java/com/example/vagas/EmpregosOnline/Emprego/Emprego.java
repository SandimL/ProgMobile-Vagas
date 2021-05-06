package com.example.vagas.EmpregosOnline.Emprego;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Emprego implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int vagaId;
    public String descricao;
    public int horasSemana;
    public double valor;

//    public Emprego(){}

    @Override
    public String toString() {
        return  "ID: "+ vagaId +
                "\nDescrição: " + descricao +
                "\nHoras/Semana: " + horasSemana +
                "\nValor: " + valor;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeInt(vagaId);
//        parcel.writeString(descricao);
//        parcel.writeInt(horasSemana);
//        parcel.writeDouble(valor);
//    }
//
//    public Emprego(Parcel in) {
//        vagaId = in.readInt();
//        descricao = in.readString();
//        horasSemana = in.readInt();
//        valor = in.readDouble();
//    }
//
//    public static final Parcelable.Creator<Emprego> CREATOR = new Parcelable.Creator<Emprego>() {
//        public Emprego createFromParcel(Parcel in) {
//            return new Emprego(in);
//        }
//
//        public Emprego[] newArray(int size) {
//            return new Emprego[size];
//        }
//    };
}
