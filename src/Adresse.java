public class Adresse {

    private int idAdresse;
    private String strasse;
    private int hausnummer;
    private int postleitzahl;
    private String stadt;
    private String land;

    public Adresse(int idAdresse, String strasse, int hausnummer, int postleitzahl, String stadt, String land) {
        this.idAdresse = idAdresse;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.postleitzahl = postleitzahl;
        this.stadt = stadt;
        this.land = land;
    }

    @Override
    public String toString() {
        return "Adresse{ " +
                "idAdresse = " + idAdresse +
                ", Strasse = '" + strasse + '\'' +
                ", Hausnummer = " + hausnummer +
                ", Postleitzahl = " + postleitzahl +
                ", Stadt = '" + stadt + '\'' +
                ", Land = '" + land + '\'' +
                '}';
    }

    public int getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(int idAdresse) {
        this.idAdresse = idAdresse;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }
}
