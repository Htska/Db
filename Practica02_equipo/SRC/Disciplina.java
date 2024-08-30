import java.util.*;

/**
 * <p>Clase para Disciplinas.</p>
 * @author equipo.
 */
public class Disciplina {
    
    /**
     * id único de la disciplina
     */
    private long id;
    
    /**
     * Nombre de la disciplina
     */
    private String nombre;

    /**
     * Categoría de la disciplina (individual y equipos)
     */
    private String categoria;

    /**
     * Total de participantes
     */
    private int participantes;

    /**
     * El patrocinadores de la categoría
     */
    private ArrayList<String> patrocinadores;

    /**
     * Constructor sin parámetros de una disciplina, inicializa su lista de patrocinadores
     */
    public Disciplina() {
        patrocinadores = new ArrayList<>();
    }

    /**
     * Regresa el identificador único de la disciplina
     * @return long el id
     */
    public long getId() {
        return id;
    }

    /**
     * Asigna un id a una disciplina
     * @param id el id de la disciplina
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Regresa el nombre de la disciplina
     * @return String el nombre de la disciplina
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define un nombre a una disciplina
     * @param nombre el nombre de la disciplina
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Regresa el tipo de categoría, es decir individual o por equipos
     * @return String el tipo de categoría de la disciplina
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define una categoría para la disciplina
     * @param categoria la categoría
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Regresa el número de participantes
     * @return int el número de participantes
     */
    public int getParticipantes() {
        return participantes;
    }

    /**
     * Define un número de participantes para una disciplina
     * @param participantes el número
     */
    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    /**
     * Regresa la lista de patrocinadores que tiene una disciplina
     * @return ArrayList<String> la lista de patrocinadores
     */
    public ArrayList<String> getPatrocinadores() {
        return patrocinadores;
    }

    /**
     * Define una lista de patrocinadores para la disciplina
     * @param patrocinadores la lista
     */
    public void setPatrocinadores(ArrayList<String> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    /**
     * Método estático el cual regresa una disciplina después de recibir sus datos en una cadéna con formacto .csv
     * @param disciplina la cadena que representa a la disciplina
     * @return Disciplina la disciplina ya construida.
     */
    public static Disciplina deseria(String disciplina){
        String[] inf = disciplina.split(",");
        Disciplina nuevaDisciplina = new Disciplina();
        int index = 0;
        nuevaDisciplina.setId(Long.parseLong(inf[index++]));
        nuevaDisciplina.setNombre(inf[index++]);
        nuevaDisciplina.setCategoria(inf[index++]);
        nuevaDisciplina.setParticipantes(Integer.parseInt(inf[index++]));
        ArrayList<String> patrocinadores = new ArrayList<>();
        while (index < inf.length){
            patrocinadores.add(inf[index++]);
        }
        nuevaDisciplina.setPatrocinadores(patrocinadores);
        return nuevaDisciplina;
    }

    
    /** 
     * Método el cual transforma un atelta en su representación en formato .csv
     * @return String los datos devoEnuevaDisciplina
     */
    public String seria(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(",");
        sb.append(this.nombre).append(",");
        sb.append(this.categoria).append(",");
        sb.append(this.participantes).append(",");
        for (String pat: this.patrocinadores){
            sb.append(pat).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        return sb.toString();
    }
}
