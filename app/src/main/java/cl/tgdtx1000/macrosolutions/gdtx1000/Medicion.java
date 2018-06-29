package cl.tgdtx1000.macrosolutions.gdtx1000;

/**
 * Created by jorgejaramillo on 6/29/18.
 */

public class Medicion {
    private int Id_Serie;
    private String Fecha;
    private int N_Particulas;

    public Medicion() {
    }

    public Medicion(int id_serie, String fecha, int n_particulas) {
        this.Id_Serie = id_serie;
        this.Fecha = fecha;
        this.N_Particulas = n_particulas;
    }

    public int getId_Serie() {
        return Id_Serie;
    }

    public void setId_Serie(int id_serie) {
        this.Id_Serie = id_serie;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public int getN_Particulas() {
        return N_Particulas;
    }

    public void setN_Particulas(int n_particulas) {
        this.N_Particulas = n_particulas;
    }

    @Override
    public String toString() {
        return "Medicion{" +
                "Id_Serie=" + Id_Serie +
                ", Fecha='" + Fecha + '\'' +
                ", N_Particulas='" + N_Particulas + '\'' +
                '}';
    }
}
