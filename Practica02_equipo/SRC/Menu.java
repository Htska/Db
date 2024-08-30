import java.util.*;
import java.io.*;

/**
 * <p> Clase del Menú </p>
 * Clase principal que despliega el menú y en la que se encuentra el punto de acceso del sistema
 * @author equipo
 */
public class Menu {

    /**
     * Método principla de la aplicación
    */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int option;
        while(true){
            System.out.println("Opciones: ");
            System.out.println("\t 1. Cargar Archivo.");
            System.out.println("\t 2. Crear Archivo.");
            System.out.println("\t 3. Salir.");
            System.out.println("Seleccione una opción: ");
            try {
                String input = sc.nextLine();
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        cargarArchivo(sc);
                        break;
                    case 2:
                        crearArchivo(sc);
                        break;
                    case 3:
                        sc.close();
                        return;
                    default:
                        System.out.println("Ingrese una opción válida");
                        continue;
                }
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida.");
                continue;
            }
        }
    }

    /**
     * Método que muestra el menu de cargar archivos.
     * @param sc El scanner utilizado
     */
    private static void cargarArchivo(Scanner sc){
        int option;
        while(true){
            System.out.println("Archivos:");
            System.out.println("\t 1. Atletas.csv");
            System.out.println("\t 2. Entrenadores.csv");
            System.out.println("\t 3. Disciplinas.csv");
            System.out.println("\t 4. Regresar");
            System.out.println("Seleccione una opción: ");
            try{
                String input = sc.nextLine();
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        try{
                            HashMap<Long,Atleta> mapA = Lectura.leerAtletas();
                            menuAtletas(mapA,sc);
                        } catch (IOException ioe){
                            System.out.println("Sucedió un error al leer el archivo");
                            return;
                        }
                        break;
                    case 2: 
                        try{
                            HashMap<Long,Entrenador> mapE = Lectura.leerEntrenadores();
                            menuEntrenadores(mapE,sc);
                        } catch (IOException ioe){
                            System.out.println("Sucedió un error al leer el archivo");
                            return;
                        }
                        break;
                    case 3:
                        try{
                            HashMap<Long,Disciplina> mapD = Lectura.leerDisciplinas();
                            menuDisciplinas(mapD,sc);
                        } catch (IOException ioe){
                            System.out.println("Sucedió un error al leer el archivo");
                            return;
                        }
                        break;
                    case 4 :
                        return;
                    default:
                        System.out.println("Ingrese una opción válida");
                        continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método que muestra el menu de crear archivos.
     * @param sc El scanner utilizado
     */
    private static void crearArchivo(Scanner sc){
        int option;
        while(true){
            System.out.println("Crear Archivo:");
            System.out.println("\t 1. Atletas.csv");
            System.out.println("\t 2. Entrenadores.csv");
            System.out.println("\t 3. Disciplinas.csv");
            System.out.println("\t 4. Regresar");
            System.out.println("Seleccione una opción: ");
            try{
                String input = sc.nextLine();
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        HashMap<Long,Atleta> mapA = new HashMap<>();
                        menuAtletas(mapA,sc);
                        break;
                    case 2: 
                        HashMap<Long,Entrenador> mapE = new HashMap<>();
                        menuEntrenadores(mapE,sc);
                        break;
                    case 3:
                        HashMap<Long,Disciplina> mapD = new HashMap<>();
                        menuDisciplinas(mapD,sc);
                        break;
                    case 4 :
                        return;
                    default:
                        System.out.println("Ingrese una opción válida");
                        continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el menu para editar, agregar, eliminar y consultar atletas.
     * @param map El diccionario que contiene a los atletas.
     * @param sc El scanner utilizado.
     */
    private static void menuAtletas(HashMap<Long,Atleta> map, Scanner sc){
        int option;
        while (true){
            System.out.println("Opciones: ");
            System.out.println("\t 1. Consultar Atleta.");
            System.out.println("\t 2. Agregar Atleta.");
            System.out.println("\t 3. Editar Atleta.");
            System.out.println("\t 4. Eliminar Atleta.");
            System.out.println("\t 5. Regresar y guardar.");
            System.out.println("Seleccione una opción: ");
            try{
                String input = sc.nextLine();
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        if (map.size() == 0){
                            System.out.println("No hay atletas que consultar.");
                            continue;
                        }
                        consultarAtleta(map, sc);
                        break;
                    case 2: 
                        agregarAtleta(map,sc);
                        break;
                    case 3: 
                        if (map.size() == 0){
                            System.out.println("No hay atletas que editar.");
                            continue;
                        }
                        editarAtleta(map,sc);
                        break;
                    case 4:
                        if (map.size() == 0){
                            System.out.println("No hay atletas que eliminar.");
                            continue;
                        }
                        eliminarAtleta(map, sc);
                        break;
                    case 5:
                        try{
                            Escritura.escribirAtletas(map);
                        } catch (IOException ioe){
                            System.err.println("Sucedió un error al escribir el archivo");
                            return;
                        }
                        return;
                    default:
                        System.out.println("Ingrese una opción válida.");
                        continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el menu para editar, agregar, eliminar y consultar entrenadores.
     * @param map El diccionario que contiene a los entrenadores.
     * @param sc El scanner utilizado.
     */
    private static void menuEntrenadores(HashMap<Long,Entrenador> map, Scanner sc){
        int option;
        while (true){
            System.out.println("Opciones: ");
            System.out.println("\t 1. Consultar Entrenador.");
            System.out.println("\t 2. Agregar Entrenador.");
            System.out.println("\t 3. Editar Entrenador.");
            System.out.println("\t 4. Eliminar Entrenador.");
            System.out.println("\t 5. Regresar y guardar.");
            System.out.println("Seleccione una opción: ");
            try{
                String input = sc.nextLine();
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        if (map.size() == 0){
                            System.out.println("No hay entrenadores que consultar.");
                            continue;
                        }
                        consultarEntrenador(map, sc);
                        break;
                    case 2: 
                        agregarEntrenador(map,sc);
                        break;
                    case 3: 
                        if (map.size() == 0){
                            System.out.println("No hay entrenadores que editar.");
                            continue;
                        }
                        editarEntrenador(map,sc);
                        break;
                    case 4:
                        if (map.size() == 0){
                            System.out.println("No hay entrenadores que eliminar.");
                            continue;
                        }
                        eliminarEntrenador(map, sc);
                        break;
                    case 5:
                        try{
                            Escritura.escribirEntrenadores(map);
                        } catch (IOException ioe){
                            System.err.println("Sucedió un error al escribir el archivo");
                            return;
                        }
                        return;
                    default:
                        System.out.println("Ingrese una opción válida.");
                        continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el menu para editar, agregar, eliminar y consultar disciplinas.
     * @param map El diccionario que contiene las disciplinas.
     * @param sc El scanner utilizado.
     */
    private static void menuDisciplinas(HashMap<Long,Disciplina> map, Scanner sc){
        int option;
        while (true){
            System.out.println("Opciones: ");
            System.out.println("\t 1. Consultar Disciplina.");
            System.out.println("\t 2. Agregar Disciplina.");
            System.out.println("\t 3. Editar Disciplina.");
            System.out.println("\t 4. Eliminar Disciplina.");
            System.out.println("\t 5. Regresar y guardar.");
            System.out.println("Seleccione una opción: ");
            try{
                String input = sc.nextLine();
                option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        if (map.size() == 0){
                            System.out.println("No hay disciplina que consultar.");
                            continue;
                        }
                        consultarDisciplina(map, sc);
                        break;
                    case 2: 
                        agregarDisciplina(map,sc);
                        break;
                    case 3: 
                        if (map.size() == 0){
                            System.out.println("No hay disciplina que editar.");
                            continue;
                        }
                        editarDisciplina(map,sc);
                        break;
                    case 4:
                        if (map.size() == 0){
                            System.out.println("No hay disciplina que eliminar.");
                            continue;
                        }
                        eliminarDisciplina(map, sc);
                        break;
                    case 5:
                        try{
                            Escritura.escribirDisciplinas(map);
                        } catch (IOException ioe){
                            System.err.println("Sucedió un error al escribir el archivo");
                            return;
                        }
                        return;
                    default:
                        System.out.println("Ingrese una opción válida.");
                        continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el mneú para consultar a los atletas.
     * @param map El diccionario que contiene a los atletas
     * @param sc El scanner utilizado
     */
    private static void consultarAtleta(HashMap<Long,Atleta> map, Scanner sc){
        while (true) {
            System.out.println("Consultar-Ingrese el id del atleta o escriba (r) para terminar: ");
            String input = sc.nextLine();
            if (input.equals("r")){
                return;
            }
            long key;
            try{
                key = Long.parseLong(input);
                Atleta atleta = map.get(key);
                if (atleta == null){
                    System.out.println("No existe ningún atleta con dicho id.");
                    continue;
                }
                System.out.printf("Id: %d \n",atleta.getId());
                System.out.printf("Nombre: %s \n", atleta.getNombre());
                System.out.printf("Primer Apellido: %s \n",atleta.getpApellido());
                if (atleta.getsApellido() != null){
                    System.out.printf("Segundo Apellido: %s \n",atleta.getsApellido());
                }
                System.out.printf("Fecha de Nacimiento: %s \n",atleta.getFecha());
                System.out.printf("Nacionalidad: %s \n", atleta.getNacionalidad());
                System.out.printf("Disciplina: %s \n", atleta.getDisciplina());
                System.out.printf("Género: %s \n",atleta.getGenero());
                for (String telefono : atleta.getTelefonos()){
                    System.out.printf("Teléfono: %s \n", telefono);
                }
                for (String correo : atleta.getCorreos()){
                    System.out.printf("Correo: %s \n",correo);
                }
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el menú para eliminar un atleta
     * @param map el diccionario con los atletas.
     * @param sc el Scanner usado.
     */
    private static void eliminarAtleta(HashMap<Long,Atleta> map, Scanner sc){
        while (true){
            System.out.println("Eliminar-Ingrese el id del atleta o escriba (r) para terminar: ");
            String input = sc.nextLine();
            if (input.equals("r")){
                return;
            }
            long key;
            try{
                key = Long.parseLong(input);
                Atleta atleta = map.get(key);
                if (atleta == null){
                    System.out.println("No existe ningún atleta con dicho id.");
                    continue;
                }
                map.remove(key);
                System.out.println("Atleta eliminado");
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción o id válido");
                continue;
            }
        }
    }

    /**
     * Méetodo auxiliar que despliega el menú para agregar atletas
     * @param map el diccionario con atletas
     * @param sc el scanner utilizado
     */
    private static void agregarAtleta(HashMap<Long,Atleta> map, Scanner sc){
        while (true) {
            System.out.println("Agregar-Ingrese el id del nuevo atleta o presione enter para regresar: ");
            String input = sc.nextLine();
            if (input.equals("")){
                return;
            }
            long id;
            try{
                id = Long.parseLong(input);
                if (map.containsKey(id)){
                    System.out.println("Ese identificador ya está en uso");
                    continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Para el id ingrese únicamente números.");
                continue;
            }
            Atleta atleta = new Atleta();
            atleta.setId(id);
            System.out.println("Ingrese el nombre del atleta: ");
            atleta.setNombre(sc.nextLine());
            System.out.println("Ingrese el primer apellido del atleta: ");
            atleta.setpApellido(sc.nextLine());
            String sApellido;
            System.out.println("Ingrese el segundo apellido del atleta o presione enter para omitir: ");
            sApellido = sc.nextLine();
            if (!sApellido.equals("")){
                atleta.setsApellido(sApellido);
            }
            while (true) {
                System.out.println("Ingrese la fecha de nacimiento del atleta (dd/mm/aaaa): ");
                String fecha = sc.nextLine();
                String[] f = fecha.split("/");
                if (f.length != 3 || f[0].length() != 2 || f[1].length() != 2 || f[2].length() != 4){
                    System.out.println("Ingrese una fecha válida");
                    continue;
                }
                if (!Character.isDigit(f[0].charAt(0)) || !Character.isDigit(f[0].charAt(1)) || !Character.isDigit(f[1].charAt(0)) || !Character.isDigit(f[1].charAt(1)) || !Character.isDigit(f[2].charAt(0)) || !Character.isDigit(f[2].charAt(1)) || !Character.isDigit(f[2].charAt(2)) || !Character.isDigit(f[2].charAt(3))){
                    System.out.println("Ingrese únicamente números");
                    continue;
                }
                atleta.setFecha(fecha);
                break;
            }
            System.out.println("Ingrese la nacionalidad del Atleta: ");
            atleta.setNacionalidad(sc.nextLine());
            System.out.println("Ingrese la disciplina del atleta: ");
            atleta.setDisciplina(sc.nextLine());
            while (true) {
                System.out.println("Ingrese el género del Atleta (M/F/NB): ");
                String genero = sc.nextLine();
                if (genero.equals("M") || genero.equals("F") || genero.equals("NB")){
                    atleta.setGenero(genero);
                    break;
                }
                System.out.println("Ingrese un género válido.");
                continue;
            }
            ArrayList<String> telefonos = new ArrayList<>();
            T: while (true){
                System.out.println("Ingrese el/los teléfonos del atleta o presione enter para terminar: ");
                String telefono = sc.nextLine();
                if (telefono.equals("")){
                    if (telefonos.size() == 0){
                        System.out.println("Debe ingresar al menos un teléfono.");
                        continue;
                    }
                    atleta.setTelefonos(telefonos);
                    break;
                }
                if (telefono.length() != 10){
                    System.out.println("Los teléfonos deben tener una longitud de 10");
                    continue;
                }
                char[] dig = telefono.toCharArray();
                for (char c : dig){
                    if (!Character.isDigit(c)){
                        System.out.println("Los teléfonos incluyen únicamente dígitos");
                        continue T;
                    }
                }
                telefonos.add(telefono);
            }
            ArrayList<String> correos = new ArrayList<>();
            while (true){
                System.out.println("Ingrese el/los correos del atelta o presione enter para terminar: ");
                String correo = sc.nextLine();
                if (correo.equals("")){
                    if (correos.size() == 0){
                        System.out.println("Debe ingresar al menos un correo.");
                        continue;
                    }
                    atleta.setCorreos(correos);
                    break;
                }
                String[] co = correo.split("@");
                if (co.length != 2){
                    System.out.println("Se debe ingresar un correo válido.");
                    continue;
                }
                if (co[0].equals("")){
                    System.out.println("Se debe ingresar un correo válido.");
                    continue;
                }
                correos.add(correo);
            }
            map.put(atleta.getId(), atleta);
        }
    }

    /**
     * Método auxiliar que despliega el menu para editar atletas
     * @param map el diccionario con atletas
     * @param sc el scanner utilizado
     */
    private static void editarAtleta(HashMap<Long,Atleta> map, Scanner sc){
        while (true){
            System.out.println("Editar-Ingrese el id del atleta o presione enter para terminar: ");
            String input = sc.nextLine();
            if (input.equals("")) {
                return;
            }
            long id;
            Atleta atleta = null;
            try {
                id = Long.parseLong(input);
                atleta = map.get(id);
                if (atleta == null){
                    System.out.println("No existe algún atleta con dicho id.");
                    continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una id válido");
            }
            L: while (true){
                System.out.println("Opciones a editar:");
                System.out.println("\t 1. Nombre.");
                System.out.println("\t 2. Primer apellido.");
                System.out.println("\t 3. Segundo apellido.");
                System.out.println("\t 4. Fecha de nacimiento.");
                System.out.println("\t 5. Nacionalidad");
                System.out.println("\t 6. Género.");
                System.out.println("\t 7. Disciplina");
                System.out.println("\t 8. Teléfonos.");
                System.out.println("\t 9. Correos");
                System.out.println("\t 10. Regresar y guardar.");
                System.out.println("Seleccione una opción: ");
                try{
                    int option = Integer.parseInt(sc.nextLine());
                    switch (option) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre del atleta: ");
                            atleta.setNombre(sc.nextLine());
                            break;
                        case 2: 
                            System.out.println("Ingrese el nuevo primer apellido del atleta.");
                            atleta.setpApellido(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("Ingrese el nuevo segundo apellido del atleta.");
                            atleta.setsApellido(sc.nextLine());
                            break;
                        case 4: 
                            while (true) {
                                System.out.println("Ingrese la nueva fecha de nacimiento del atleta (dd/mm/aaaa): ");
                                String fecha = sc.nextLine();
                                String[] f = fecha.split("/");
                                if (f.length != 3 || f[0].length() != 2 || f[1].length() != 2 || f[2].length() != 4){
                                    System.out.println("Ingrese una fecha válida");
                                    continue;
                                }
                                if (!Character.isDigit(f[0].charAt(0)) || !Character.isDigit(f[0].charAt(1)) || !Character.isDigit(f[1].charAt(0)) || !Character.isDigit(f[1].charAt(1)) || !Character.isDigit(f[2].charAt(0)) || !Character.isDigit(f[2].charAt(1)) || !Character.isDigit(f[2].charAt(2)) || !Character.isDigit(f[2].charAt(3))){
                                    System.out.println("Ingrese únicamente números");
                                    continue;
                                }
                                atleta.setFecha(fecha);
                                break;
                            }
                            break;
                        case 5: 
                            System.out.println("Ingrese la nueva nacionalidad del atleta.");
                            atleta.setNacionalidad(sc.nextLine());
                            break;
                        case 6:
                            while (true) {
                                System.out.println("Ingrese el nuevo género del Atleta (M/F/NB): ");
                                String genero = sc.nextLine();
                                if (genero.equals("M") || genero.equals("F") || genero.equals("NB")){
                                    atleta.setGenero(genero);
                                    break;
                                }
                                System.out.println("Ingrese un género válido.");
                                continue;
                            }
                            break;
                        case 7:
                            System.out.println("Ingrese la nueva disciplina del atleta.");
                            atleta.setDisciplina(sc.nextLine());
                            break;
                        case 8:
                            ArrayList<String> telefonos = new ArrayList<>();
                            T: while (true){
                                System.out.println("Ingrese el/los nuevos teléfonos del atleta o presione enter para terminar: ");
                                String telefono = sc.nextLine();
                                if (telefono.equals("")){
                                    if (telefonos.size() == 0){
                                        System.out.println("Debe ingresar al menos un teléfono.");
                                        continue;
                                    }
                                    atleta.setTelefonos(telefonos);
                                    break;
                                }
                                if (telefono.length() != 10){
                                    System.out.println("Los teléfonos deben tener una longitud de 10");
                                    continue;
                                }
                                char[] dig = telefono.toCharArray();
                                for (char c : dig){
                                    if (!Character.isDigit(c)){
                                        System.out.println("Los teléfonos incluyen únicamente dígitos");
                                        continue T;
                                    }
                                }
                                telefonos.add(telefono);
                            }
                            break;
                        case 9:
                            ArrayList<String> correos = new ArrayList<>();
                            while (true){
                                System.out.println("Ingrese el/los nuevos correos del atelta o presione enter para terminar: ");
                                String correo = sc.nextLine();
                                if (correo.equals("")){
                                    if (correos.size() == 0){
                                        System.out.println("Debe ingresar al menos un correo.");
                                        continue;
                                    }
                                    atleta.setCorreos(correos);
                                    break;
                                }
                                String[] co = correo.split("@");
                                if (co.length != 2){
                                    System.out.println("Se debe ingresar un correo válido.");
                                    continue;
                                }
                                if (co[0].equals("")){
                                    System.out.println("Se debe ingresar un correo válido.");
                                    continue;
                                }
                                correos.add(correo);
                            }
                            break;
                        case 10:
                            break L;
                        default:
                            System.out.println("Ingrese una opción válida");
                            continue;
                    }
                } catch (NumberFormatException nfe){
                    System.out.println("Ingrese una opción válida.");
                    continue;
                }
            }
        }
    }

    /**
     * Método auxiliar que despliega el mneú para consultar a los Entrenadores.
     * @param map El diccionario que contiene a los entrenadores
     * @param sc El scanner utilizado
     */
    private static void consultarEntrenador(HashMap<Long,Entrenador> map, Scanner sc){
        while (true) {
            System.out.println("Consultar-Ingrese el id del entrenador o escriba (r) para terminar: ");
            String input = sc.nextLine();
            if (input.equals("r")){
                return;
            }
            long key;
            try{
                key = Long.parseLong(input);
                Entrenador entrenador = map.get(key);
                if (entrenador == null){
                    System.out.println("No existe ningún entrenador con dicho id.");
                    continue;
                }
                System.out.printf("Id: %d \n",entrenador.getId());
                System.out.printf("Nombre: %s \n", entrenador.getNombre());
                System.out.printf("Primer Apellido: %s \n",entrenador.getpApellido());
                if (entrenador.getsApellido() != null){
                    System.out.printf("Segundo Apellido: %s \n",entrenador.getsApellido());
                }
                System.out.printf("Fecha de Nacimiento: %s \n",entrenador.getFecha());
                System.out.printf("Nacionalidad: %s \n", entrenador.getNacionalidad());
                System.out.printf("Disciplina: %s \n", entrenador.getDisciplina());
                for (String telefono : entrenador.getTelefonos()){
                    System.out.printf("Teléfono: %s \n", telefono);
                }
                for (String correo : entrenador.getCorreos()){
                    System.out.printf("Correo: %s \n",correo);
                }
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el menú para eliminar un entrenador
     * @param map el diccionario con los entrenadores.
     * @param sc el Scanner usado.
     */
    private static void eliminarEntrenador(HashMap<Long,Entrenador> map, Scanner sc){
        while (true){
            System.out.println("Eliminar-Ingrese el id del entrenador o escriba (r) para terminar: ");
            String input = sc.nextLine();
            if (input.equals("r")){
                return;
            }
            long key;
            try{
                key = Long.parseLong(input);
                Entrenador entrenador = map.get(key);
                if (entrenador == null){
                    System.out.println("No existe ningún entrenador con dicho id.");
                    continue;
                }
                map.remove(key);
                System.out.println("Entrenador eliminado");
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción o id válido");
                continue;
            }
        }
    }

    /**
     * Méetodo auxiliar que despliega el menú para agregar entrenadores
     * @param map el diccionario con los entrenadores
     * @param sc el scanner utilizado
     */
    private static void agregarEntrenador(HashMap<Long,Entrenador> map, Scanner sc){
        while (true) {
            System.out.println("Agregar-Ingrese el id del nuevo entrenador o presione enter para regresar: ");
            String input = sc.nextLine();
            if (input.equals("")){
                return;
            }
            long id;
            try{
                id = Long.parseLong(input);
                if (map.containsKey(id)){
                    System.out.println("Ese identificador ya está en uso");
                    continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Para el id ingrese únicamente números.");
                continue;
            }
            Entrenador entrenador = new Entrenador();
            entrenador.setId(id);
            System.out.println("Ingrese el nombre del entrenador: ");
            entrenador.setNombre(sc.nextLine());
            System.out.println("Ingrese el primer apellido del entrenador: ");
            entrenador.setpApellido(sc.nextLine());
            String sApellido;
            System.out.println("Ingrese el segundo apellido del entrenador o presione enter para omitir: ");
            sApellido = sc.nextLine();
            if (!sApellido.equals("")){
                entrenador.setsApellido(sApellido);
            }
            while (true) {
                System.out.println("Ingrese la fecha de nacimiento del entrenador (dd/mm/aaaa): ");
                String fecha = sc.nextLine();
                String[] f = fecha.split("/");
                if (f.length != 3 || f[0].length() != 2 || f[1].length() != 2 || f[2].length() != 4){
                    System.out.println("Ingrese una fecha válida");
                    continue;
                }
                if (!Character.isDigit(f[0].charAt(0)) || !Character.isDigit(f[0].charAt(1)) || !Character.isDigit(f[1].charAt(0)) || !Character.isDigit(f[1].charAt(1)) || !Character.isDigit(f[2].charAt(0)) || !Character.isDigit(f[2].charAt(1)) || !Character.isDigit(f[2].charAt(2)) || !Character.isDigit(f[2].charAt(3))){
                    System.out.println("Ingrese únicamente números");
                    continue;
                }
                entrenador.setFecha(fecha);
                break;
            }
            System.out.println("Ingrese la nacionalidad del entrenador: ");
            entrenador.setNacionalidad(sc.nextLine());
            System.out.println("Ingrese la disciplina del entrenador: ");
            entrenador.setDisciplina(sc.nextLine());
            ArrayList<String> telefonos = new ArrayList<>();
            T: while (true){
                System.out.println("Ingrese el/los teléfonos del entrenador o presione enter para terminar: ");
                String telefono = sc.nextLine();
                if (telefono.equals("")){
                    if (telefonos.size() == 0){
                        System.out.println("Debe ingresar al menos un teléfono.");
                        continue;
                    }
                    entrenador.setTelefonos(telefonos);
                    break;
                }
                if (telefono.length() != 10){
                    System.out.println("Los teléfonos deben tener una longitud de 10");
                    continue;
                }
                char[] dig = telefono.toCharArray();
                for (char c : dig){
                    if (!Character.isDigit(c)){
                        System.out.println("Los teléfonos incluyen únicamente dígitos");
                        continue T;
                    }
                }
                telefonos.add(telefono);
            }
            ArrayList<String> correos = new ArrayList<>();
            while (true){
                System.out.println("Ingrese el/los correos del entrenador o presione enter para terminar: ");
                String correo = sc.nextLine();
                if (correo.equals("")){
                    if (correos.size() == 0){
                        System.out.println("Debe ingresar al menos un correo.");
                        continue;
                    }
                    entrenador.setCorreos(correos);
                    break;
                }
                String[] co = correo.split("@");
                if (co.length != 2){
                    System.out.println("Se debe ingresar un correo válido.");
                    continue;
                }
                if (co[0].equals("")){
                    System.out.println("Se debe ingresar un correo válido.");
                    continue;
                }
                correos.add(correo);
            }
            map.put(entrenador.getId(), entrenador);
        }
    }

    /**
     * Método auxiliar que despliega el menu para editar entrenadores
     * @param map el diccionario con los entrenadores
     * @param sc el scanner utilizado
     */
    private static void editarEntrenador(HashMap<Long,Entrenador> map, Scanner sc){
        while (true){
            System.out.println("Editar-Ingrese el id del entrenador o presione enter para terminar: ");
            String input = sc.nextLine();
            if (input.equals("")) {
                return;
            }
            long id;
            Entrenador entrenador = null;
            try {
                id = Long.parseLong(input);
                entrenador = map.get(id);
                if (entrenador == null){
                    System.out.println("No existe algún entrenador con dicho id.");
                    continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una id válido");
            }
            L: while (true){
                System.out.println("Opciones a editar:");
                System.out.println("\t 1. Nombre.");
                System.out.println("\t 2. Primer apellido.");
                System.out.println("\t 3. Segundo apellido.");
                System.out.println("\t 4. Fecha de nacimiento.");
                System.out.println("\t 5. Nacionalidad");
                System.out.println("\t 6. Disciplina");
                System.out.println("\t 7. Teléfonos.");
                System.out.println("\t 8. Correos");
                System.out.println("\t 9. Regresar y guardar.");
                System.out.println("Seleccione una opción: ");
                try{
                    int option = Integer.parseInt(sc.nextLine());
                    switch (option) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre del entrenador: ");
                            entrenador.setNombre(sc.nextLine());
                            break;
                        case 2: 
                            System.out.println("Ingrese el nuevo primer apellido del entrenador.");
                            entrenador.setpApellido(sc.nextLine());
                            break;
                        case 3:
                            System.out.println("Ingrese el nuevo segundo apellido del entrenador.");
                            entrenador.setsApellido(sc.nextLine());
                            break;
                        case 4: 
                            while (true) {
                                System.out.println("Ingrese la nueva fecha de nacimiento del entrenador (dd/mm/aaaa): ");
                                String fecha = sc.nextLine();
                                String[] f = fecha.split("/");
                                if (f.length != 3 || f[0].length() != 2 || f[1].length() != 2 || f[2].length() != 4){
                                    System.out.println("Ingrese una fecha válida");
                                    continue;
                                }
                                if (!Character.isDigit(f[0].charAt(0)) || !Character.isDigit(f[0].charAt(1)) || !Character.isDigit(f[1].charAt(0)) || !Character.isDigit(f[1].charAt(1)) || !Character.isDigit(f[2].charAt(0)) || !Character.isDigit(f[2].charAt(1)) || !Character.isDigit(f[2].charAt(2)) || !Character.isDigit(f[2].charAt(3))){
                                    System.out.println("Ingrese únicamente números");
                                    continue;
                                }
                                entrenador.setFecha(fecha);
                                break;
                            }
                            break;
                        case 5: 
                            System.out.println("Ingrese la nueva nacionalidad del entrenador.");
                            entrenador.setNacionalidad(sc.nextLine());
                            break;
                        case 6:
                            System.out.println("Ingrese la nueva disciplina del entrenador.");
                            entrenador.setDisciplina(sc.nextLine());
                            break;
                        case 7:
                            ArrayList<String> telefonos = new ArrayList<>();
                            T: while (true){
                                System.out.println("Ingrese el/los nuevos teléfonos del entrenador o presione enter para terminar: ");
                                String telefono = sc.nextLine();
                                if (telefono.equals("")){
                                    if (telefonos.size() == 0){
                                        System.out.println("Debe ingresar al menos un teléfono.");
                                        continue;
                                    }
                                    entrenador.setTelefonos(telefonos);
                                    break;
                                }
                                if (telefono.length() != 10){
                                    System.out.println("Los teléfonos deben tener una longitud de 10");
                                    continue;
                                }
                                char[] dig = telefono.toCharArray();
                                for (char c : dig){
                                    if (!Character.isDigit(c)){
                                        System.out.println("Los teléfonos incluyen únicamente dígitos");
                                        continue T;
                                    }
                                }
                                telefonos.add(telefono);
                            }
                            break;
                        case 8:
                            ArrayList<String> correos = new ArrayList<>();
                            while (true){
                                System.out.println("Ingrese el/los nuevos correos del entrenador o presione enter para terminar: ");
                                String correo = sc.nextLine();
                                if (correo.equals("")){
                                    if (correos.size() == 0){
                                        System.out.println("Debe ingresar al menos un correo.");
                                        continue;
                                    }
                                    entrenador.setCorreos(correos);
                                    break;
                                }
                                String[] co = correo.split("@");
                                if (co.length != 2){
                                    System.out.println("Se debe ingresar un correo válido.");
                                    continue;
                                }
                                if (co[0].equals("")){
                                    System.out.println("Se debe ingresar un correo válido.");
                                    continue;
                                }
                                correos.add(correo);
                            }
                            break;
                        case 9:
                            break L;
                        default:
                            System.out.println("Ingrese una opción válida");
                            continue;
                    }
                } catch (NumberFormatException nfe){
                    System.out.println("Ingrese una opción válida.");
                    continue;
                }
            }
        }
    }

    /**
     * Método auxiliar que despliega el mneú para consultar las disciplinas.
     * @param map El diccionario que contiene a las disciplinas
     * @param sc El scanner utilizado
     */
    private static void consultarDisciplina(HashMap<Long,Disciplina> map, Scanner sc){
        while (true) {
            System.out.println("Consultar-Ingrese el id de la disciplina o escriba (r) para terminar: ");
            String input = sc.nextLine();
            if (input.equals("r")){
                return;
            }
            long key;
            try{
                key = Long.parseLong(input);
                Disciplina disciplina = map.get(key);
                if (disciplina == null){
                    System.out.println("No existe ninguna disciplina con dicho id.");
                    continue;
                }
                System.out.printf("Id: %d \n",disciplina.getId());
                System.out.printf("Nombre: %s \n", disciplina.getNombre());
                System.out.printf("Categoría: %s \n", disciplina.getCategoria());
                System.out.printf("Participantes: %d \n", disciplina.getParticipantes());
                for (String patrocinador : disciplina.getPatrocinadores()){
                    System.out.printf("Patrocinador: %s \n", patrocinador);
                }
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción válida");
                continue;
            }
        }
    }

    /**
     * Método auxiliar que despliega el menú para eliminar una disciplina
     * @param map el diccionario con las disciplinas.
     * @param sc el Scanner usado.
     */
    private static void eliminarDisciplina(HashMap<Long,Disciplina> map, Scanner sc){
        while (true){
            System.out.println("Eliminar-Ingrese el id de la disciplina o escriba (r) para terminar: ");
            String input = sc.nextLine();
            if (input.equals("r")){
                return;
            }
            long key;
            try{
                key = Long.parseLong(input);
                Disciplina disciplina = map.get(key);
                if (disciplina == null){
                    System.out.println("No existe ninguna disciplina con dicho id.");
                    continue;
                }
                map.remove(key);
                System.out.println("Disciplina eliminada");
                continue;
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una opción o id válido");
                continue;
            }
        }
    }

    /**
     * Méetodo auxiliar que despliega el menú para agregar disciplinas
     * @param map el diccionario con las disciplinas
     * @param sc el scanner utilizado
     */
    private static void agregarDisciplina(HashMap<Long,Disciplina> map, Scanner sc){
        while (true) {
            System.out.println("Agregar-Ingrese el id de la nueva disciplina o presione enter para regresar: ");
            String input = sc.nextLine();
            if (input.equals("")){
                return;
            }
            long id;
            try{
                id = Long.parseLong(input);
                if (map.containsKey(id)){
                    System.out.println("Ese identificador ya está en uso");
                    continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Para el id ingrese únicamente números.");
                continue;
            }
            Disciplina disciplina = new Disciplina();
            disciplina.setId(id);
            System.out.println("Ingrese el nombre de la disciplina: ");
            disciplina.setNombre(sc.nextLine());
            while (true){
                System.out.println("Ingrese la categoría de la disciplina (individual/equipos): ");
                String categoria = sc.nextLine();
                if (categoria.equals("individual") || categoria.equals("equipos")){
                    disciplina.setCategoria(categoria);
                    break;
                }
                System.out.println("Ingrese una opción válida.");
                continue;
            }
            while (true) {
                System.out.println("Ingrese el número de participantes de la disciplina: ");
                String num = sc.nextLine();
                try{
                    int participantes = Integer.parseInt(num);
                    disciplina.setParticipantes(participantes);
                    break;
                } catch (NumberFormatException nfe){
                    System.out.println("Ingrese un valor númerico.");
                    continue;
                }
            }
            ArrayList<String> patrocinadores = new ArrayList<>();
            while (true){
                System.out.println("Ingrese el/los patrocinadores de la disciplina o presione enter para terminar: ");
                String patrocinador = sc.nextLine();
                if (patrocinador.equals("")){
                    if (patrocinadores.size() == 0){
                        System.out.println("Debe ingresar al menos un patrocinador.");
                        continue;
                    }
                    disciplina.setPatrocinadores(patrocinadores);
                    break;
                }
                patrocinadores.add(patrocinador);
            }
            map.put(disciplina.getId(), disciplina);
        }
    }

    /**
     * Método auxiliar que despliega el menu para editar disciplinas
     * @param map el diccionario con las disciplinas
     * @param sc el scanner utilizado
     */
    private static void editarDisciplina(HashMap<Long,Disciplina> map, Scanner sc){
        while (true){
            System.out.println("Editar-Ingrese el id de la disciplina o presione enter para terminar: ");
            String input = sc.nextLine();
            if (input.equals("")) {
                return;
            }
            long id;
            Disciplina disciplina = null;
            try {
                id = Long.parseLong(input);
                disciplina = map.get(id);
                if (disciplina == null){
                    System.out.println("No existe alguna disciplina con dicho id.");
                    continue;
                }
            } catch (NumberFormatException nfe){
                System.out.println("Ingrese una id válido");
            }
            L: while (true){
                System.out.println("Opciones a editar:");
                System.out.println("\t 1. Nombre.");
                System.out.println("\t 2. Categoría.");
                System.out.println("\t 3. Participantes.");
                System.out.println("\t 4. Patrocinadores.");
                System.out.println("\t 5. Regresar y guardar.");
                System.out.println("Seleccione una opción: ");
                try{
                    int option = Integer.parseInt(sc.nextLine());
                    switch (option) {
                        case 1:
                            System.out.println("Ingrese el nuevo nombre de la disciplina: ");
                            disciplina.setNombre(sc.nextLine());
                            break;
                        case 2: 
                            while (true){
                                System.out.println("Ingrese la nueva categoría de la disciplina (individual/equipos): ");
                                String categoria = sc.nextLine();
                                if (categoria.equals("individual") || categoria.equals("equipos")){
                                    disciplina.setCategoria(categoria);
                                    break;
                                }
                                System.out.println("Ingrese una opción válida.");
                                continue;
                            }
                            break;
                        case 3:
                            while (true) {
                                System.out.println("Ingrese el nuevo número de participantes de la disciplina: ");
                                String num = sc.nextLine();
                                try{
                                    int participantes = Integer.parseInt(num);
                                    disciplina.setParticipantes(participantes);
                                    break;
                                } catch (NumberFormatException nfe){
                                    System.out.println("Ingrese un valor númerico.");
                                    continue;
                                }
                            }
                            break;
                        case 4: 
                            ArrayList<String> patrocinadores = new ArrayList<>();
                            while (true){
                                System.out.println("Ingrese el/los nuevos patrocinadores de la disciplina o presione enter para terminar: ");
                                String patrocinador = sc.nextLine();
                                if (patrocinador.equals("")){
                                    if (patrocinadores.size() == 0){
                                        System.out.println("Debe ingresar al menos un patrocinador.");
                                        continue;
                                    }
                                    disciplina.setPatrocinadores(patrocinadores);
                                    break;
                                }
                                patrocinadores.add(patrocinador);
                            }
                            break;
                        case 5:
                            break L;
                        default:
                            System.out.println("Ingrese una opción válida");
                            continue;
                    }
                } catch (NumberFormatException nfe){
                    System.out.println("Ingrese una opción válida.");
                    continue;
                }
            }
        }
    }
}
