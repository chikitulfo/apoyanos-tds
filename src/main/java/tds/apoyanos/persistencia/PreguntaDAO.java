package tds.apoyanos.persistencia;


import tds.apoyanos.modelo.Pregunta;

public interface PreguntaDAO {
    public void registrar(Pregunta pregunta);
    public boolean borrar(Pregunta pregunta);
    public void actualizarPregunta(Pregunta pregunta);
    public Pregunta recuperar(int id);
}
