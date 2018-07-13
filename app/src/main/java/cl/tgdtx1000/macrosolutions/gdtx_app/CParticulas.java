package cl.tgdtx1000.macrosolutions.gdtx_app;

/**
 * Created by jorgejaramillo on 12-07-18.
 */

public class CParticulas {

    private Integer Id;
    private Integer N_Particulas;
    private String Fecha;

    public CParticulas(){

    }

    public CParticulas(Integer Id, Integer N_Particulas, String Fecha){
        this.Id = Id;
        this.N_Particulas = N_Particulas;
        this.Fecha = Fecha;
    }

    @Override public String toString(){
        return "Particulas: " + String.valueOf(N_Particulas) + " Fecha: " + Fecha;
    }

    public Integer getId(){
        return Id;
    }

    public Integer getN_Particulas(){
        return N_Particulas;
    }

    public String getFecha(){
        return Fecha;
    }

}
