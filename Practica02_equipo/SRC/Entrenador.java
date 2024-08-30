import java.util.*;

/**
 * <p>Clase para Entrenadores.</p>
 * @author equipo.
 */
public class Entrenador {
    /**
     * El id único del entrenador
     */
    private long id;

    /**
     * El nombre del entrenador
     */
    private String nombre;

    /**
     * El primer apellido del entrenador
     */
    private String pApellido;

    /**
     * El segundo apellido del entrenador
     */
    private String sApellido;

    /**
     * Fecha de nacimiento del entrenador
     */
    private String fecha;

    /**
     * La nacionalidad del entrenador 
     */
    private String nacionalidad;
    
    /**
     * Disciplina en la que es entrenador
     */
    private String disciplina;

    /**
     * Los teléfonos del entrenador
     */
    private ArrayList<String> telefonos;

    /**
     * Los correos del entrenador
     */
    private ArrayList<String> correos;

    /**
     * Constructor sin parámetros de Entrenador, inicializa sus listas
     */
    public Entrenador(){
        telefonos = new ArrayList<>();
        correos = new ArrayList<>();
    }

    /**
     * Regresa el identificador único del entrenador
     * @return long el Id del entrenador
     */
    public long getId() {
        return id;
    }

    /**
     * Define el identificador para el entrenador
     * @param id el identificador a asignar
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Regresa el nombre del entrenador
     * @return String el nombre del entrenador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del entrenador
     * @param nombre el nombre del entrenador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el primer apellido del entrenador
     * @return String el primer apellido del entrenador
     */
    public String getpApellido() {
        return pApellido;
    }

    /**
     * define el primer apellido del entrenador
     * @param pApellido el apellido
     */
    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    /**
     * Regresa el segundo apellido del entrenador
     * @return String el segundo apellido
     */
    public String getsApellido() {
        return sApellido;
    }

    /**
     * Define el segundo apellido del entrenador.
     * @param sApellido el apellido
     */
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    /**
     * Regesa la fecha de nacimiento del entrenador
     * @return String la fecha de nacimiento del entrenador
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Define la fecha del entrenador
     * @param fecha la fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Regresa la nacionalidad del entrenador
     * @return String la nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define la nacionalidad del entrenador
     * @param nacionalidad la nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Regresa la disciplina la cual enseña el entrenador
     * @return String la disciplina
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * Define qué disciplina enseña el entrenador
     * @param disciplina la disciplina
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Regresa la lista de telefonos que tiene el entrenador
     * @return ArrayList<String> la lista de telefonos
     */
    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    /**
     * define la lista de telefonos del entrenador
     * @param telefonos los telefonos
     */
    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * Regresa la lista de correos que tiene el entrenador
     * @return ArrayList<String> los correos
     */
    public ArrayList<String> getCorreos() {
        return correos;
    }

    /**
     * Define la lista de correos que tiene un entrenador
     * @param correos la lista
     */
    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
    }

    /**
     * Método estático el cual regresa un entrenador después de recibir sus datos en una cadéna con formacto .csv
     * @param entrenador la cadena que representa al entrenador
     * @return Entrenador el entrenaador ya construido.
     */
    public static Entrenador deseria(String entrenador){
        String[] inf = entrenador.split(",");
        Entrenador nuevoEntrenador = new Entrenador();
        int index = 0;
        nuevoEntrenador.setId(Long.parseLong(inf[index++]));
        nuevoEntrenador.setNombre(inf[index++]);
        nuevoEntrenador.setpApellido(inf[index++]);
        if (Character.isDigit(inf[index].charAt(0))){
            nuevoEntrenador.setFecha(inf[index++]);
        } else {
            nuevoEntrenador.setsApellido(inf[index++]);
            nuevoEntrenador.setFecha(inf[index++]);
        }
        nuevoEntrenador.setNacionalidad(inf[index++]);
        nuevoEntrenador.setDisciplina(inf[index++]);
        ArrayList<String> telefonos = new ArrayList<>();
        while (index < inf.length -1){
            if (!inf[index].contains("@")){
                telefonos.add(inf[index++]);
                continue;
            } else{
                nuevoEntrenador.setTelefonos(telefonos);
                break;
            }
        }
        ArrayList<String> correos = new ArrayList<>();
        while (index < inf.length){
            correos.add(inf[index++]);
        }
        nuevoEntrenador.setCorreos(correos);
        return nuevoEntrenador;
    }

    
    /** 
     * Método el cual transforma un atelta en su representación en formato .csv
     * @return String los datos devoEnuevoEntrenador
     */
    public String seria(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(",");
        sb.append(this.nombre).append(",");
        sb.append(this.pApellido).append(",");
        if (this.sApellido != null)
            sb.append(this.sApellido).append(",");
        sb.append(this.fecha).append(",");
        sb.append(this.nacionalidad).append(",");
        sb.append(this.disciplina).append(",");
        for (String tel : this.telefonos){
            sb.append(tel).append(",");
        }
        for (String cor: this.correos){
            sb.append(cor).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }
}
