package net.sonia.ejemplookhttp;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by soniamerayogonzalez on 7/3/17.
 */

public class ResultBean implements Serializable {
    private int resultado;
    private ArrayList<LibroBean> libros;

    public ResultBean() {
        resultado=1;
    }

    public int getResultado() {
        return resultado;
    }

    public ArrayList<LibroBean> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<LibroBean> libros) {
        this.libros = libros;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static ResultBean fromJson(String json){
        if(json!=null && !json.isEmpty()){
            Gson gson = new Gson();
            return gson.fromJson(json, ResultBean.class);
        }
        return new ResultBean();
    }
}
