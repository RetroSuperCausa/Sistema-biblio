package main;
import modelo.Admin;
import modelo.Bibliotecario;
import modelo.Libro;
import modelo.Usuario;
import sisbiblio.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        ArrayList<Admin> listaAdmin = new ArrayList<>();
        ArrayList <Bibliotecario> listaEmp = new ArrayList<>();

        Biblioteca biblio = new Biblioteca();

        listaAdmin.add(new Admin("Saul","Goodman","Better",
                "saul89", "7412"));

        listaEmp.add(new Bibliotecario("Walter", "Hartwell", "White",
                "mrwhite", "ww74","581724","Auxiliar"));

        listaUsuarios.add(new Usuario("Mauro","Insta","gram",
                "mauro123","1234"));
        listaUsuarios.add(new Usuario("Ana","Lopez","Diaz",
                "ana456","abcd"));
        listaUsuarios.add(new Usuario("Luis","Perez","Torres",
                "luis789","pass"));

        biblio.setListaUsuarios(listaUsuarios);

        biblio.agregarLibro(new Libro("Algebra Lineal y sus aplicaciones", "David C. Lay", "Matematicas",
                30, "001",true),false);
        biblio.agregarLibro(new Libro("El principito", "Antoine de Saint-Exupery",
                "Ficcion",7,"002",true),false);
        biblio.agregarLibro(new Libro("Dune", "Frank Herbert",
                "Ciencia Ficcion",3,"003",true),false);
        biblio.agregarLibro(new Libro("Gruñon", "Suzanne Lang",
                "ilustracion",15,"004",true),false);


        System.out.println("\n-------------------------------------------");
        System.out.println("\u001B[92mBienvenido a la biblioteca\u001B[0m");
        System.out.println("-------------------------------------------\n");

        boolean acceso=false;
        boolean cancelar;
        int numeroSalir1=4;
        int numero = 0;
        int opcion;
        int opcionS = 0;
        Usuario usuarioActual = null;
        while(numero!=numeroSalir1){
            acceso=false;
            cancelar=false;
            System.out.println("\nSeleccione una opcion: ");
            System.out.println("1.-Iniciar sesion como Usuario");
            System.out.println("2.-Iniciar sesion como Bibliotecario");
            System.out.println("3.-Iniciar sesion como Admin");
            System.out.println("4.-Salir");

            try{
                numero = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("\u001B[31mDebes ingresar un numero valido...\u001B[0m");
                sc.nextLine();
                continue;
            }
            sc.nextLine();

            switch(numero){
                //Inicio caso 1,Usuario
                case 1:
                    opcionS=0;
                    while(opcionS!=3){
                        System.out.println("1.-Iniciar Sesion");
                        System.out.println("2.-Registro de usuario");
                        System.out.println("3.-Regresar al menu");
                        try{
                            opcionS=sc.nextInt();
                        }catch(InputMismatchException e){
                            System.out.println("\u001B[31mDebes ingresar un numero valido...\u001B[0m");
                            sc.nextLine();
                            continue;
                        }
                        sc.nextLine();
                        switch(opcionS){
                            case 1: //Menu inicio
                                acceso=false;
                                cancelar=false;
                                opcion=0;
                                if (listaUsuarios.isEmpty()){
                                    System.out.println("\u001B[33mNO HAY USUARIOS REGISTRADOS\u001B[0m");
                                    break;
                                }
                                System.out.println("\n-------------------------------------------");
                                System.out.println("\u001B[92mInicio de sesion como usuario\u001B[0m");
                                System.out.println("-------------------------------------------\n");
                                while(!acceso && !cancelar) {
                                    System.out.println("\u001B[36mEscribe 'Salir' para regresar al menú principal\u001B[0m");
                                    System.out.print("Ingrese su Usuario: ");
                                    String userIngresado = sc.nextLine();
                                    if(userIngresado.equalsIgnoreCase("Salir")){
                                        cancelar=true;
                                        continue;
                                    }
                                    System.out.print("Ingrese su Contraseña: ");
                                    String passIngresada = sc.nextLine();
                                    for (Usuario u : listaUsuarios) {
                                        if (u.iniciarSesion(userIngresado, passIngresada)) {
                                            acceso = true;
                                            usuarioActual = u;
                                            break;
                                        }
                                    }

                                    if (!acceso) {
                                        System.out.println("\u001B[31mDatos incorrectos\u001B[0m");
                                    }
                                }
                                if(cancelar){
                                    System.out.println("\u001B[32mRegresando al menu principal...\u001B[0m");
                                    break;
                                }
                                int numeroSalir2=7;
                                while (opcion!=numeroSalir2){

                                    System.out.println("\n1.Ver el Catalogo\n2.Seleccionar un libro\n3.Pedir un libro \n4.Devolver libro \n5.Ver mis libros \n6.Mostrar perfil \n7.Salir");

                                    try{
                                        opcion = sc.nextInt();
                                    }catch(InputMismatchException e){
                                        System.out.println("\u001B[31mNumero invalido\u001B[0m");
                                        sc.nextLine();
                                        continue;
                                    }
                                    sc.nextLine();

                                    switch(opcion){

                                        case 1:
                                            biblio.mostrarCatalogo();
                                            break;
                                        case 2:
                                            usuarioActual.seleccionarLibro(biblio);
                                            break;
                                        case 3:
                                            usuarioActual.buscarLibro(biblio,usuarioActual);
                                            break;
                                        case 4:
                                            usuarioActual.devolucionLibro(biblio,usuarioActual);
                                            break;
                                        case 5:
                                            usuarioActual.mostrarListaPrestamos(usuarioActual);
                                            break;
                                        case 6:
                                            usuarioActual.mostrarDatos();
                                            break;
                                        case 7:
                                            System.out.println("\u001B[32mCerrando sesion...\u001B[0m");
                                            break;
                                    }

                                }
                                break;// fin menu Sesion
                            case 2:
                                listaAdmin.get(0).añadirUsuario(listaUsuarios);
                                break;
                            case 3:
                                System.out.println("Regresando al menu...");
                                break;
                            default:
                                System.out.println("Opcion no existente ");
                                break;
                        }

                    }

                    break; //Cierre caso 1,usuario

                //Caso 2,Bibliotecario
                case 2:
                    int opc=0;
                    acceso=false;
                    System.out.println("\n-------------------------------------------");
                    System.out.println("\u001B[92mInicio de sesion como Bibliotecario\u001B[0m");
                    System.out.println("-------------------------------------------\n");
                    while(!acceso && !cancelar){
                        System.out.println("\u001B[36mEscribe 'Salir' para regresar al menú principal\u001B[0m");
                        System.out.print("Ingrese su usuario: ");
                        String user = sc.nextLine();
                        if(user.equalsIgnoreCase("Salir")){
                            cancelar=true;
                            continue;
                        }
                        System.out.print("Ingrese su contraseña: ");
                        String pass = sc.nextLine();

                        for(Bibliotecario b : listaEmp){
                            if(b.iniciarSesion(user, pass)){
                                acceso = true;
                                break;
                            }
                        }

                        if(!acceso){
                            System.out.println("\u001B[31mDatos incorrectos\u001B[0m");
                        }
                    }
                    if(cancelar){
                        System.out.println("\u001B[32mRegresando al menu principal...\u001B[0m");
                        break;
                    }

                    while(opc!=6){
                        System.out.println("1.Mostrar Catalogo\n2.Añadir libro\n3.Editar Libro\n4.Eliminar libro\n5.Ver prestamos de cada usuario\n6.Salir");
                        try{
                            opc = sc.nextInt();
                        }catch(InputMismatchException e){
                            System.out.println("\u001B[31mDato no valido\u001B[0m");
                            sc.nextLine();
                            continue;
                        }

                        switch(opc){
                            case 1:
                                biblio.mostrarLibrosEmp();
                                break;

                            case 2:
                                listaEmp.get(0).añadirLibro(biblio);
                                break;

                            case 3:
                                listaEmp.get(0).editarLibro(biblio);
                                break;

                            case 4:
                                listaEmp.get(0).eliminarLibro(biblio);
                                break;

                            case 5:
                                listaEmp.get(0).verPrestamos(listaUsuarios);
                                break;

                            case 6:
                                System.out.println("\u001B[32mSaliendo...\u001B[0m");
                                break;
                        }
                    }
                    break; //Cierre del caso 2,bibliotecario

                case 3: //Inicio caso 3,Admin
                    int opc2=0;
                    acceso=false;
                    System.out.println("\n-------------------------------------------");
                    System.out.println("\u001B[92mInicio de sesion como Admin\u001B[0m");
                    System.out.println("-------------------------------------------\n");
                    while(!acceso && !cancelar) {
                        System.out.println("\u001B[36mEscribe 'Salir' para regresar al menú principal\u001B[0m");
                        System.out.print("Ingrese su Usuario: ");
                        String userIngresado = sc.nextLine();
                        if(userIngresado.equalsIgnoreCase("Salir")){
                            cancelar=true;
                            continue;
                        }
                        System.out.print("Ingrese su Contraseña: ");
                        String passIngresada = sc.nextLine();

                        for (Admin a : listaAdmin) {
                            if (a.iniciarSesion(userIngresado, passIngresada)) {
                                acceso = true;
                                break;
                            }
                        }

                        if (!acceso) {
                            System.out.println("\u001B[31mDatos incorrectos\u001B[0m");
                        }
                    }
                    if(cancelar){
                        System.out.println("\u001B[32mRegresando al menu principal...\u001B[0m");
                        break;
                    }

                    while(opc2!=4){
                        System.out.println("1.Añadir usuario\n2.Eliminar usuario\n3.Ver lista de usuarios\n4.Salir");

                        try{
                            opc2 = sc.nextInt();
                        }catch(InputMismatchException e){
                            System.out.println("\u001B[31mDato no valido\u001B[0m");
                            sc.nextLine();
                            continue;
                        }

                        switch(opc2){
                            case 1:
                                listaAdmin.get(0).añadirUsuario(listaUsuarios);
                                break;
                            case 2:
                                listaAdmin.get(0).mostrarUsuarios(listaUsuarios);
                                listaAdmin.get(0).eliminarUsuario(listaUsuarios);
                                break;
                            case 3:
                                listaAdmin.get(0).mostrarUsuarios(listaUsuarios);
                                break;
                            case 4:
                                System.out.println("\u001B[32mSaliendo...\u001B[0m");
                                break;
                            default:
                                System.out.println("\u001B[31mOpcion no valida\u001B[0m");
                                break;
                        }
                    }
                    break; //Cierre caso 3,Admin

                case 4:
                    System.out.println("\u001B[32mSaliendo de la app...\u001B[0m");
                    break;

                case 67:
                    System.out.println("SIX SEVEEEENNNN!!!");
                    break;
                default:
                    System.out.println("\u001B[31mOpcion no valida\u001B[0m");
                    break;
            }
        }
        sc.close();
    }
}