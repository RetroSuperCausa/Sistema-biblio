package sisbiblio;
import modelo.Libro;
import modelo.Usuario;
import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Libro> listaLibros;
    ArrayList<Usuario> listaUsuarios;

    //Constructor
    public Biblioteca(){
        this.listaLibros = new ArrayList<>();
        this.listaUsuarios  = new ArrayList<>();
    }

    //Getters
    public ArrayList<Libro> getListaLibros(){
        return listaLibros;
    }

    //Setters
    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    //Metodos
    //Metodo que muestra cada libro para los usuarios
    public void mostrarCatalogo() {
        int i = 1;
        for (Libro l : listaLibros) {
            System.out.println(i + ". " + l.getTitulo() + " | " +
                    l.getAutor() + " | Stock: " + l.getCantidad());
            i++;
        }
    }

    //Metodo que muestra a detalle cada libro para los bibliotecarios
    public void mostrarLibrosEmp(){
        int i= 1;
        System.out.println(" | Titulo " + " | Autor " + " | Categoria " + " | Stock " + " | NoSerie" + " | Disponibilidad ");
        for (Libro l : listaLibros){
            System.out.print(i + ". " + l.getTitulo() + " | " + l.getAutor() + " | " + l.getCategoria() + " | " + l.getCantidad() + " | " +l.getNoSerie() + " | ");
            if(l.getCantidad()>0){
                System.out.println("\u001B[32mDisponible\u001B[0m");
            }else{
                System.out.println("\u001B[33mLibro actualmente no disponible\u001B[0m");
            }
            i++;
        }
    }

    //AGREGAR LIBRO
    public void agregarLibro(Libro libroNuevo, boolean mostrarMensaje) {
        boolean existe = false;

        for (Libro l : listaLibros) {
            if (l.getTitulo().trim().equalsIgnoreCase(libroNuevo.getTitulo().trim()) &&
                    l.getAutor().trim().equalsIgnoreCase(libroNuevo.getAutor().trim())) {

                int nuevaCantidad = l.getCantidad() + libroNuevo.getCantidad();
                l.setCantidad(nuevaCantidad);

                System.out.println("El libro ya existía. Nueva cantidad: " + nuevaCantidad);
                existe = true;
                break;
            }
        }

        if (!existe) {
            listaLibros.add(libroNuevo);
            if(mostrarMensaje){
                System.out.println("\u001B[32mLibro agregado correctamente\u001B[0m");
            }
        }
    }

    //Metodo para eliminar un libro buscandolo por numero de serie
    public void eliminarLibro(String noSerie,int cantidad) {
        boolean encontrado = false;

        for (Libro l : listaLibros) {
            if (l.getNoSerie().equals(noSerie)) {
                int stockActual = l.getCantidad();
                if (cantidad < stockActual) {
                    l.setCantidad(stockActual - cantidad);
                    System.out.println("Se eliminaron " + cantidad +  ". Quedan " + l.getCantidad());
                }
                else if (cantidad == stockActual) {
                    l.setCantidad(0);
                    l.setDisponible(false);
                    System.out.println("Se eliminaron todos los ejemplares.");
                }
                else {
                    System.out.println("\u001B[33mNo hay suficientes libros\u001B[0m");
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\u001B[31mNo se encontro el libro\u001B[0m");
        }
    }

    //Metodo que busca el libro comparando el titulo y el autor
    public Libro buscarLibro(String titulo, String autor) {
        for (Libro l : listaLibros) {
            if (l.getTitulo().trim().equalsIgnoreCase(titulo.trim()) &&
                    l.getAutor().trim().equalsIgnoreCase(autor.trim())) {
                return l;
            }
        }
        return null;
    }

    //Metodo para prestar un libro pidiendo titulo y autor
    public void prestarLibroPorTitulo(String titulo, String autor, Usuario usuarioActual) {
        Libro libro = buscarLibro(titulo, autor);

        if (libro != null) {
            if (libro.getCantidad() > 0) {

                libro.setCantidad(libro.getCantidad() - 1);

                usuarioActual.prestarLibro(libro, 1);

                System.out.println("Préstamo realizado: " + libro.getTitulo() + " | " + libro.getAutor());
            } else {
                System.out.println("\u001B[33mNo hay ejemplares disponibles\u001B[0m");
            }

        } else {
            System.out.println("\u001B[31mLibro no encontrado\u001B[0m");
        }
    }

    // Metodo para devolver un libro
    public void devolverLibro(String titulo, String autor, int cantidad) {
        Libro libro = buscarLibro(titulo, autor);

        if (libro != null) {
            libro.setCantidad(libro.getCantidad() + cantidad);
            System.out.println("Libro devuelto. Nuevo stock: " + libro.getCantidad());
        } else {
            System.out.println("\u001B[31mEl libro no existe en el catalogo\u001B[0m");
        }
    }

    //Metodo que devuelve un valor booleano buscando el noSerie en la lista de libros prestados
    public boolean estaPrestado(String noSerie) {
        for (Usuario u : listaUsuarios) {
            for (LibroPrestado l : u.getLibrosPrestados()) {
                if (l.getNoSerie().equalsIgnoreCase(noSerie)) {
                    return true;
                }
            }
        }
        return false;
    }
}