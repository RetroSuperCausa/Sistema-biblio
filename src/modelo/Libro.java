package modelo;

import sisbiblio.LibroPrestado;

public class Libro {
    String titulo;
    String autor;
    String categoria;
    public int cantidad;
    private String noSerie;
    boolean disponible;

    //Constructor
    public Libro(String titulo, String autor, String categoria, int cantidad, String noSerie, boolean disponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.noSerie = noSerie;
        this.disponible = disponible;
    }

    public void mostrarLibro(){
        System.out.println("Titulo: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Categoria: " + categoria);
        System.out.println("Cantidad: " + cantidad);
        if(cantidad>0){
            System.out.println("\u001B[32mDisponible\u001B[0m");
        }else{
            System.out.println("\u001B[33mLibro actualmente no disponible\u001B[0m");
        }
    }
    //get
    public String getNoSerie(){
        return noSerie;
    }
    //set
    public void setNoSerie(String noSerie){
        this.noSerie = noSerie;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor=autor;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }

    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int nuevaCantidad){
        this.cantidad = nuevaCantidad;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
