package m3.uf5.ticketing.model;

import java.util.Date;

/**
 * Qualsevol de les operacions de seguiment del tiquet realitzada per un usuari en una data
 *
 * Durant la creació de l'acció: - Si la data és nul·la indicar la data actual - Podem suposar que
 * l'usuari no serà nul - Podem suposar que el tiquet no serà nul
 *
 * No es pot actualitzar cap atribut a un valor incorrecte
 *
 * @author alex
 *
 */
public abstract class Accio {
    protected Usuari usuari;
    protected Date data;
    protected Tiquet tiquet;

    public Accio(Usuari usuari, Tiquet tiquet, Date data) throws Exception {
        if (usuari == null) throw new Exception("L'usuari de l'acció no pot ser nul");
        if (tiquet == null) throw new Exception("El tiquet de l'acció no pot ser nul");
        this.usuari = usuari;
        this.tiquet = tiquet;
        this.data = data == null ? new Date() : data;
    }

    public Accio() {
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public void setUsuari(Usuari usuari) throws Exception {
        throw new Exception("No es pot modificar l'usuari d'aquesta acció");
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) throws Exception {
        throw new Exception("No es pot modificar la data d'aquesta acció");
    }

    public Tiquet getTiquet() {
        return tiquet;
    }

    public void setTiquet(Tiquet tiquet) throws Exception {
        throw new Exception("No es pot modificar el tiquet d'aquesta acció");
    }

    public boolean esObertura() {
        return false;
    }

    public boolean esAssignacio() {
        return false;
    }

    public boolean esIntervencio() {
        return false;
    }

    public boolean esTancament() {
        return false;
    }

    public int getPrioritat() {
        return 0;
    }

    public abstract String resum();
}
