package modelo;
import sisbiblio.Biblioteca;
import sisbiblio.LibroPrestado;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Usuario extends Persona {
    private String nombreUsuario;
    private String contrasenia;

    private ArrayList<LibroPrestado> librosPrestados;

    //Constructor
    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno,String nombreUsuario,
                   String contrasenia) {
        super(nombre, apellidoPaterno, apellidoMaterno);
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;


        librosPrestados = new ArrayList<>();
    }

    //Getters
    public String getContrasenia() {
        return contrasenia;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    //Set
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    //LOGIN
    public boolean iniciarSesion(String nombreUsuario, String contrasenia){
        return this.nombreUsuario.equals(nombreUsuario)
                && this.contrasenia.equals(contrasenia);
    }

    //Iniciacion del scanner
    Scanner sc = new Scanner(System.in);

    //PRESTAR LIBRO
    public void prestarLibro(Libro libro, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("\u001B[31mCantidad invalida\u001B[0m");
            return;
        }

        for (LibroPrestado lp : librosPrestados) {
            if (lp.getTitulo().equalsIgnoreCase(libro.getTitulo()) &&
                    lp.getAutor().equalsIgnoreCase(libro.getAutor())) {
                lp.aumentarCantidad(cantidad);
                return;
            }
        }

        librosPrestados.add(new LibroPrestado(libro.getTitulo(),libro.getAutor(),libro.getCategoria(), cantidad,libro.getNoSerie(), libro.disponible));
    }

    //GET LISTA
    public ArrayList<LibroPrestado> getLibrosPrestados() {
        return librosPrestados;
    }

    //DEVOLVER LIBRO
    public void devolverLibro(LibroPrestado lp, int cantidad) {

        if (cantidad <= 0) {
            System.out.println("\u001B[31mCantidad invalida\u001B[0m");
            return;
        }

        if (cantidad > lp.getCantidad()) {
            System.out.println("\u001B[31mNo puedes devolver mas de lo que tienes\u001B[0m");
            return;
        }

        lp.disminuirCantidad(cantidad);

        if (lp.getCantidad() == 0) {
            librosPrestados.remove(lp);
        }
    }

    //Nuevos metodos de aqui para abajo brayan
    public void seleccionarLibro(Biblioteca biblio){
        while (true) {
            int numeroLibro=0;
            System.out.print("Ingrese el Numero libro: ");
            try {
                numeroLibro = sc.nextInt();
                sc.nextLine();

                if (numeroLibro > 0 && numeroLibro <= biblio.getListaLibros().size()) {
                    biblio.getListaLibros().get(numeroLibro - 1).mostrarLibro();
                    break;
                } else {
                    System.out.println("\u001B[31mNumero fuera de rango\u001B[0m");
                }

            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mDato no valido\u001B[0m");
                sc.nextLine();
            }
        }
    }

    public void buscarLibro(Biblioteca biblio,Usuario usuarioActual){
        System.out.print("ingrese el titulo: ");
        String titulo = sc.nextLine();
        System.out.print("ingrese el autor: ");
        String autor = sc.nextLine();

        biblio.prestarLibroPorTitulo(titulo,autor,usuarioActual);
    }

    public void devolucionLibro(Biblioteca biblio,Usuario usuarioActual){
        if (usuarioActual.getLibrosPrestados().isEmpty()) {
            System.out.println("\u001B[93mNo tienes libros prestados\u001B[0m");
            return;
        }

        // Mostrar libros prestados
        int i = 1;
        for (LibroPrestado lp : usuarioActual.getLibrosPrestados()) {
            System.out.println(i + ". " + lp.getTitulo() + " | " + lp.getAutor() + " | Cantidad: " + lp.getCantidad());
            i++;
        }

        int numDev;

        while (true) {
            System.out.print("Selecciona un libro a devolver: ");
            try {
                numDev = sc.nextInt();
                sc.nextLine();

                if (numDev > 0 && numDev <= usuarioActual.getLibrosPrestados().size()) {
                    break;
                } else {
                    System.out.println("\u001B[31mNumero fuera de rango\u001B[0m");
                }

            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mDato no valido\u001B[0m");
                sc.nextLine();
            }
        }

        int cantidadDev;

        LibroPrestado libroDev = usuarioActual.getLibrosPrestados().get(numDev - 1);
        while (true) {
            System.out.print("Cantidad a devolver (máx " + libroDev.getCantidad() + "): ");
            try {
                cantidadDev = sc.nextInt();
                sc.nextLine();

                if (cantidadDev > 0 && cantidadDev <= libroDev.getCantidad()) {
                    break;
                } else {
                    System.out.println("\u001B[31mCantidad fuera de rango\u001B[0m");
                }

            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mDato no valido\u001B[0m");
                sc.nextLine();
            }
        }

        // Devolver libro
        usuarioActual.devolverLibro(libroDev, cantidadDev);
        biblio.devolverLibro(libroDev.getTitulo(), libroDev.getAutor(), cantidadDev);
    }

    public void mostrarListaPrestamos(Usuario usuarioActual){
        if (usuarioActual.getLibrosPrestados().isEmpty()) {
            System.out.println("\u001B[93mNo tienes libros\u001B[0m");
        } else {
            System.out.println("Tus libros: ");
            for (LibroPrestado lp : usuarioActual.getLibrosPrestados()) {
                System.out.println("- " + lp.getTitulo() + " | " + lp.getAutor() + " | Cantidad: " + lp.getCantidad());
            }
        }
    }
    @Override
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Usuario: " + nombreUsuario);
    }

}