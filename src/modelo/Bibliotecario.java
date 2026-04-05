package modelo;
import sisbiblio.Biblioteca;
import sisbiblio.LibroPrestado;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Bibliotecario extends Usuario {
    private String idEmpleado;
    String puesto;

    //Constructor
    public Bibliotecario(String nombre, String apellidoPaterno, String apellidoMaterno,String nombreUsuario,String contrasenia,
                         String idEmpleado, String puesto) {
        super(nombre, apellidoPaterno, apellidoMaterno, nombreUsuario, contrasenia);
        this.idEmpleado = idEmpleado;
        this.puesto = puesto;
    }

    //Getters
    public String getIdEmpleado(){
        return idEmpleado;
    }

    //Setters
    public void setIdEmpleado(String idEmpleado){
        this.idEmpleado= idEmpleado;
    }

    //Metodos
    @Override
    public boolean iniciarSesion(String user, String pass) {
        return this.getNombreUsuario().equals(user) && this.getContrasenia().equals(pass);
    }

    Scanner sc= new Scanner(System.in);

    //METODO PARA AGREGAR NUEVOS LIBROS EN BIBLIOTECA
    public void añadirLibro(Biblioteca biblio) {
        String titulo,autor,categoria,noSerie;
        int cantidad;

        do{
            System.out.print("Titulo:");
            titulo = sc.nextLine().trim();
            if (titulo.isEmpty()){
                System.out.println("Ingrese un titulo valido");
            }
        }while(titulo.isEmpty());

        do{
            System.out.print("Autor:");
            autor = sc.nextLine().trim();
            if (autor.isEmpty()){
                System.out.println("Ingrese un autor valido");
            }
        }while(autor.isEmpty());

        do{
            System.out.print("Categoria:");
            categoria = sc.nextLine().trim();
            if (categoria.isEmpty()){
                System.out.println("Ingrese una categoria valido");
            }
        }while(categoria.isEmpty());

        while(true){
            System.out.print("Cantidad:");
            try{
                cantidad=sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("\u001B[31mDato no valido\u001B[0m");
                sc.nextLine();
                continue;
            }
            if(cantidad>0){
             break;
            }else{
                System.out.println("\u001B[31mCantidad no valida\u001B[0m");
            }
        }
        sc.nextLine();

        do{
            System.out.print("No. Serie: ");
            noSerie = sc.nextLine().trim();
            boolean existe = false;
            if(noSerie.isEmpty()){
                System.out.println("El número de serie no puede estar vacío");
            } else {
                for(Libro l : biblio.getListaLibros()){
                    if(l.getNoSerie().equalsIgnoreCase(noSerie)){
                        existe = true;
                        break;
                    }
                }
                if(existe){
                    System.out.println("Ese número de serie ya existe");
                    noSerie = ""; //repite el ciclo
                }
            }
        }while(noSerie.isEmpty());

        boolean disponible = cantidad > 0;

        Libro nuevoLibro = new Libro(titulo, autor, categoria, cantidad, noSerie, disponible);

        biblio.agregarLibro(nuevoLibro, true);
    }

    //METODO PARA ELIMINAR LIBROS
    public void eliminarLibro(Biblioteca biblio){
        System.out.print("Ingrese el numero de serie: ");
        String noSerie = sc.nextLine();

        int cantidad;
        while(true){
            System.out.print("Cantidad a eliminar: ");
            try{
                cantidad=sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("\u001B[31mDato no valido\u001B[0m");
                sc.nextLine();
                continue;
            }
            if(cantidad>0){
                break;
            }else{
                System.out.println("\u001B[31mCantidad no valida\u001B[0m");
            }
        }
        sc.nextLine();

        biblio.eliminarLibro(noSerie, cantidad);
    }

    //VER PRESTAMOS DE USUARIOS (YA CON CANTIDAD)
    public void verPrestamos(ArrayList<Usuario> listaUsuarios) {
        if(listaUsuarios.isEmpty()){
            System.out.println("\u001B[33mNo hay usuarios\u001B[0m");
            return;
        }
        for (Usuario u : listaUsuarios) {
            System.out.println("\nUsuario: " + u.getNombreUsuario());

            if (u.getLibrosPrestados().isEmpty()) {
                System.out.println("\u001B[33mNo tiene libros prestados\u001B[0m");
            } else {
                for (LibroPrestado l : u.getLibrosPrestados()) {
                    System.out.println("  - " + l.getTitulo() + " | " + l.getAutor() + " | Cantidad: " + l.getCantidad());
                }
            }
        }
    }

    public void editarLibro(Biblioteca biblio) {
        int cantidad = 0;

        System.out.print("Ingrese el numero de serie del libro a editar: ");
        String noSerie = sc.nextLine();

        Libro libro = null;

        // Buscar libro por numero de serie
        for (Libro l : biblio.getListaLibros()) {
            if (l.getNoSerie().equalsIgnoreCase(noSerie)) {
                libro = l;
                break;
            }
        }

        if (libro == null) {
            System.out.println("\u001B[33mLibro no encontrado\u001B[0m");
            return;
        }

        if (biblio.estaPrestado(noSerie)) {
            System.out.println("\u001B[31mNo se puede editar el libro porque un usuario tiene una copia está prestada\u001B[0m");
            return;
        }

        // Mostrar datos actuales
        System.out.println("\nDatos actuales:");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor());
        System.out.println("Categoria: " + libro.getCategoria());
        System.out.println("Cantidad: " + libro.getCantidad());

        // Editar datos del libro
        System.out.print("\nNuevo titulo (enter para no cambiar): ");
        String titulo = sc.nextLine();
        if (!titulo.isEmpty()) {
            libro.setTitulo(titulo);
        }

        System.out.print("Nuevo autor (enter para no cambiar): ");
        String autor = sc.nextLine();
        if (!autor.isEmpty()) {
            libro.setAutor(autor);
        }

        System.out.print("Nueva categoria (enter para no cambiar): ");
        String categoria = sc.nextLine();
        if (!categoria.isEmpty()) {
            libro.setCategoria(categoria);
        }

        while(true){
            System.out.print("Nueva cantidad (-1 para no cambiar): ");
            try{
                cantidad = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("\u001B[31mDato no valido,ingrese un numero\u001B[0m");
                sc.nextLine();
            }
            if (cantidad >= 0) {
                libro.setCantidad(cantidad);
                libro.setDisponible(libro.getCantidad() > 0);
                break;
            }else if(cantidad < -1){
                System.out.println("\u001B[31mCantidad no valida\u001B[0m");
            }else{
                break;
            }
        }
        System.out.println("\u001B[32mLibro actualizado correctamente\u001B[0m");
    }

}