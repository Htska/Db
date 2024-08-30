import java.util.*;
/**
 * <p>Clase para Atletas.</p>
 * @author equipo.
 */
public class Atleta{
    /**
     * Id único del atleta
     */
    private long id;
    
    /**
     * El nombre del Atleta
     */
    private String nombre;

    /**
     * Primer apellido.
     */
    private String pApellido;

    /**
     * Segundo apellido
     */
    private String sApellido;

    /**
     * Nacionalidad del Atleta
     */
    private String nacionalidad;

    /**
     * Fecha de naciomiento del Atleta
     */
    private String fecha;

    /**
     * Disciplina que practica
     */
    private String disciplina;

    /**
     * Género del atleta
     */
    private String genero;

    /**
     * Teléfonos del Atleta
     */
    private ArrayList<String> telefonos;

    /**
     * Correos del Atleta
     */
    private ArrayList<String> correos;

    /**
     *  Constructor sin parámetros del Atleta, se inicializan sus listas.
     */
    public Atleta(){
        this.telefonos = new ArrayList<>();
        this.correos = new ArrayList<>();
    }
    
    /** 
     * Regresa el identificador del atleta
     * @return long El id del atleta
     * 
     */
    public long getId() {
        return id;
    }

    /** 
     * Asigna el identificador a un atleta
     * @param id el id que se le asigna al atleta
     */
    public void setId(long id) {
        this.id = id;
    }

    
    /** 
     * Regresa el nombre del atleta
     * @return String el nombre del atleta.
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * Asigna su nombre al atleta
     * @param nombre el nombre del atleta
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** 
     * Regresa el primer Apellido del Atleta
     * @return String el primer apellido del Atleta
     */
    public String getpApellido() {
        return pApellido;
    }

    /**
     * Asigna el Apellido al Atleta
     * @param pApellido el primer apellido del atleta
     */
    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    /**
     * Regresa el segundo apellido del atleta
     * @return String el segundo apellido del atleta
     */
    public String getsApellido() {
        return sApellido;
    }

    /**
     * Asigna un segundo apellido al atleta
     * @param sApellido el segundo apellido a asignar
     */
    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    /**
     * Regresa la nacionalidad del atleta
     * @return String la nacionalidad del atleta
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Asigna la nacionalidad al atleta
     * @param nacionalidad la nacionalidad a ser asignada
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Regresa la fecha de nacimiento del atleta
     * @return String la fecha de naciomiento del atleta.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Asigna la fecha de nacimiento del atleta
     * @param fecha la fecha de nacimiento
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Regresa la disciplina en que partcipa el atleta
     * @return String la disciplina
     */
    public String getDisciplina() {
        return disciplina;
    }

    /**
     * Asigna la disciplina a un atleta
     * @param disciplina la disciplina
     */
    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Regresa el género del atleta
     * @return String el género.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Asigna el género al atleta
     * @param genero el género del atleta
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Regresa los telefonos del atleta en forma de lista
     * @return ArrayList<String> los telefonos
     */
    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    /**
     * Asigna una lista de telefonos al atleta
     * @param telefonos la lista de telefonos
     */
    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * Regresa una lista con los correos del atleta
     * @return ArrayList<String> los correos
     */
    public ArrayList<String> getCorreos() {
        return correos;
    }

    /**
     * Asigna una lista de correos al atleta
     * @param correos los correos del atleta
     */
    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
    }

    /**
     * Método estático el cual regresa un atleta después de recibir sus datos en una cadéna con formacto .csv
     * @param atleta la cadena que representa al atleta
     * @return Atleta el atleta ya construido.
     */
    public static Atleta deseria(String atleta){
        String[] inf = atleta.split(",");
        Atleta nuevoAtleta = new Atleta();
        int index = 0;
        nuevoAtleta.setId(Long.parseLong(inf[index++]));
        nuevoAtleta.setNombre(inf[index++]);
        nuevoAtleta.setpApellido(inf[index++]);
        if (Character.isDigit(inf[index].charAt(0))){
            nuevoAtleta.setFecha(inf[index++]);
        } else {
            nuevoAtleta.setsApellido(inf[index++]);
            nuevoAtleta.setFecha(inf[index++]);
        }
        nuevoAtleta.setNacionalidad(inf[index++]);
        nuevoAtleta.setDisciplina(inf[index++]);
        nuevoAtleta.setGenero(inf[index++]);
        ArrayList<String> telefonos = new ArrayList<>();
        while (index < inf.length -1){
            if (!inf[index].contains("@")){
                telefonos.add(inf[index++]);
                continue;
            } else{
                nuevoAtleta.setTelefonos(telefonos);
                break;
            }
        }
        ArrayList<String> correos = new ArrayList<>();
        while (index < inf.length){
            correos.add(inf[index++]);
        }
        nuevoAtleta.setCorreos(correos);
        return nuevoAtleta;
    }

    
    /** 
     * Método el cual transforma un atelta en su representación en formato .csv
     * @return String los datos del atleta
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
        sb.append(this.genero).append(",");
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