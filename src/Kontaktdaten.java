public class Kontaktdaten {

    private int idKontaktdaten;
    private String festnetznummer;
    private String mobilnummer;
    private String emailadresse;

    public Kontaktdaten(int idKontaktdaten, int festnetznummer, int mobilnummer, String emailadresse) {
        this.idKontaktdaten = idKontaktdaten;
        this.festnetznummer = festnetznummer;
        this.mobilnummer = mobilnummer;
        this.emailadresse = emailadresse;
    }

    @Override
    public String toString() {
        return "Kontaktdaten{ " +
                "idKontaktdaten = " + idKontaktdaten +
                ", Festnetznummer = " + festnetznummer +
                ", Mobilnummer = " + mobilnummer +
                ", Emailadresse = '" + emailadresse + '\'' +
                '}';
    }

    public int getIdKontaktdaten() {
        return idKontaktdaten;
    }

    public void setIdKontaktdaten(int idKontaktdaten) {
        this.idKontaktdaten = idKontaktdaten;
    }

    public String getFestnetznummer() {
        return festnetznummer;
    }

    public void setFestnetznummer(int festnetznummer) {
        this.festnetznummer = festnetznummer;
    }

    public String getMobilnummer() {
        return mobilnummer;
    }

    public void setMobilnummer(int mobilnummer) {
        this.mobilnummer = mobilnummer;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }
}
