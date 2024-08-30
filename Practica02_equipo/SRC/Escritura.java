import java.util.*;
import java.io.*;

/**
 * <p> Clase para Escritura de archivos </p>
 * Contiene métodos estáticos para escibir el contenido en los diversos archivos
 */
public class Escritura {
    /**
     * El buffer de escritura de los archivos
     */
    private static BufferedWriter out;

    /**
     * Método estático que se encarga de escribir los atletas en su archivo correspondiente
     * @param map El diccionario que contiene a los atletas
     * @throws IOException Si ocurre un error al excribir el archivo
     */
    public static void escribirAtletas(HashMap<Long,Atleta> map) throws IOException {
        out = new BufferedWriter(new FileWriter("Atletas.csv"));
        for (Atleta atleta : map.values()){
            out.write(atleta.seria());
        }
        out.close();
        return;
    }

    /**
     * Método estático que se encarga de escribir los entrenadores en su archivo correspondiente
     * @param map El diccionario que contiene a los entrenadores
     * @throws IOException Si ocurre un error al excribir el archivo
     */
    public static void escribirEntrenadores(HashMap<Long,Entrenador> map) throws IOException {
        out = new BufferedWriter(new FileWriter("Entrenadores.csv"));
        for (Entrenador entrenador : map.values()){
            out.write(entrenador.seria());
        }
        out.close();
        return;
    }

    /**
     * Método estático que se encarga de escribir las disciplinas en su archivo correspondiente
     * @param map El diccionario que contiene a las disciplinas
     * @throws IOException Si ocurre un error al excribir el archivo
     */
    public static void escribirDisciplinas(HashMap<Long,Disciplina> map) throws IOException {
        out = new BufferedWriter(new FileWriter("Disciplinas.csv"));
        for (Disciplina disciplina : map.values()){
            out.write(disciplina.seria());
        }
        out.close();
        return;
    }
}
