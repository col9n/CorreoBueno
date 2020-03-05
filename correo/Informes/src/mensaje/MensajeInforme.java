package mensaje;

import java.util.Date;

public class MensajeInforme {
    private String asunto;
    private String remitente;
    private Date date;
    private String contenido;

    public MensajeInforme(String asunto, String remitente, Date date, String contenido) {
        this.asunto = asunto;
        this.remitente = remitente;
        this.date = date;
        this.contenido = contenido;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
