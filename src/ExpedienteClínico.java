public class ExpedienteClínico {
    private int folio;
    private String tratamiento, aparatologia, duracion, pronostico, observaciones;
    private int id_paciente;

    public ExpedienteClínico() {
    }

    public ExpedienteClínico(int folio, String tratamiento, String aparatologia, String duracion, String pronostico, String observaciones, int id_paciente) {
        this.folio = folio;
        this.tratamiento = tratamiento;
        this.aparatologia = aparatologia;
        this.duracion = duracion;
        this.pronostico = pronostico;
        this.observaciones = observaciones;
        this.id_paciente = id_paciente;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getAparatologia() {
        return aparatologia;
    }

    public void setAparatologia(String aparatologia) {
        this.aparatologia = aparatologia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }    
}
