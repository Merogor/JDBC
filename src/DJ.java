public class DJ {

    private int idDJ;
    private String vorname;
    private String nachname;
    private int verfuegbarkeit;
    private int Adresse_idAdresse;
    private int Kontaktdaten_idKontaktdaten;
    private String kuenstlerName;

    public DJ(int idDJ, String vorname, String nachname, int verfuegbarkeit, int adresse_idAdresse, int kontaktdaten_idKontaktdaten, String kuenstlerName) {
        this.idDJ = idDJ;
        this.vorname = vorname;
        this.nachname = nachname;
        this.verfuegbarkeit = verfuegbarkeit;
        Adresse_idAdresse = adresse_idAdresse;
        Kontaktdaten_idKontaktdaten = kontaktdaten_idKontaktdaten;
        this.kuenstlerName = kuenstlerName;
    }

    @Override
    public String toString() {
        return "DJ{ " +
                "idDJ = " + idDJ +
                ", Vorname = '" + vorname + '\'' +
                ", Nachname = '" + nachname + '\'' +
                ", Verfuegbarkeit = " + verfuegbarkeit +
                ", Adresse_idAdresse = " + Adresse_idAdresse +
                ", Kontaktdaten_idKontaktdaten = " + Kontaktdaten_idKontaktdaten +
                ", KuenstlerName = '" + kuenstlerName + '\'' +
                '}';
    }

    public int getIdDJ() {
        return idDJ;
    }

    public void setIdDJ(int idDJ) {
        this.idDJ = idDJ;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public int getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    public void setVerfuegbarkeit(int verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public int getAdresse_idAdresse() {
        return Adresse_idAdresse;
    }

    public void setAdresse_idAdresse(int adresse_idAdresse) {
        Adresse_idAdresse = adresse_idAdresse;
    }

    public int getKontaktdaten_idKontaktdaten() {
        return Kontaktdaten_idKontaktdaten;
    }

    public void setKontaktdaten_idKontaktdaten(int kontaktdaten_idKontaktdaten) {
        Kontaktdaten_idKontaktdaten = kontaktdaten_idKontaktdaten;
    }

    public String getKuenstlerName() {
        return kuenstlerName;
    }

    public void setKuenstlerName(String kuenstlerName) {
        this.kuenstlerName = kuenstlerName;
    }
}
