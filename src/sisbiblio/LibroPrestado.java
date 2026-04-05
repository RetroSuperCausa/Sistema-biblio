package sisbiblio;
import modelo.Libro;

import java.util.ArrayList;

public class LibroPrestado extends Libro {

    public LibroPrestado(String titulo,String autor,String categoria, int cantidad,String noSerie, boolean disponible) {
        super(titulo,autor,categoria, cantidad, noSerie, disponible);
    }

    //Metodos
    public void aumentarCantidad(int c) {
        cantidad += c;
    }

    public void disminuirCantidad(int c) {
        cantidad -= c;
    }

}
