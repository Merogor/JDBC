public class test {
    public static void main(String[] args){
        String s = "Lasse` tretr";

        if(s.matches("^[a-zA-Z ]*$") == false){
            System.out.println("Ungültige Eingabe");
        } else {
            System.out.println("what");
        }
    }
}
