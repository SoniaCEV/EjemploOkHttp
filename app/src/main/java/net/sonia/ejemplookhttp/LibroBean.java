package net.sonia.ejemplookhttp;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by soniamerayogonzalez on 7/3/17.
 */
public class LibroBean implements Serializable{
    private String ISBN;
    private String Titulo;

    public LibroBean() {
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static LibroBean fromJson(String json){
        if(json!=null && !json.isEmpty()){
            Gson gson = new Gson();
            return gson.fromJson(json, LibroBean.class);
        }
        return new LibroBean();
    }
}
