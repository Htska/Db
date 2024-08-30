import java.util.*;
import java.io.*;
/**
 * <p> Clase para Lectura </p>
 * Contiene métodos estáticos para la lectura de los tres posibles archivos csv.
 * @author equipo
 */
public class Lectura {
    /**
     * El buffer de lectura de archivos
     */
    private static BufferedReader in;

    /**
     * Método que abre y lee el archivo de los atletas y regresa un diccionario que contine a los atletas y como llave tiene su id
     * @return HashMap<Long,Atleta> El diccionario que contiene a los atletas
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static HashMap<Long,Atleta> leerAtletas() throws IOException {
        in = new BufferedReader(new FileReader("Atletas.csv"));
        HashMap<Long,Atleta> map = new HashMap<>();
        String l = null;
        while ( (l = in.readLine()) != null){
            Atleta atleta = Atleta.deseria(l);
            map.put(atleta.getId(), atleta);
        }
        in.close();
        return map;
    }

    /**
     * Método que abre y lee el archivo de los entrenadores y regresa un diccionario que contine a los entrenadores y como llave tiene su id
     * @return HashMap<Long,Entrenador> El diccionario que contiene a los entrenadores
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static HashMap<Long,Entrenador> leerEntrenadores() throws IOException {
        in = new BufferedReader(new FileReader("Entrenadores.csv"));
        HashMap<Long,Entrenador> map = new HashMap<>();
        String l = null;
        while ( (l = in.readLine()) != null){
            Entrenador entrenador = Entrenador.deseria(l);
            map.put(entrenador.getId(), entrenador);
        }
        in.close();
        return map;
    }

    /**
     * Método que abre y lee el archivo de las disciplinas y regresa un diccionario que contine a las disciplinas y como llave tiene su id
     * @return HashMap<Long,Disciplina> El diccionario que contiene a las disciplinas
     * @throws IOException si ocurre un error al leer el archivo
     */
    public static HashMap<Long,Disciplina> leerDisciplinas() throws IOException {
        in = new BufferedReader(new FileReader("Disciplinas.csv"));
        HashMap<Long,Disciplina> map = new HashMap<>();
        String l = null;
        while ( (l = in.readLine()) != null){
            Disciplina disciplina = Disciplina.deseria(l);
            map.put(disciplina.getId(), disciplina);
        }
        in.close();
        return map;
    }
}
