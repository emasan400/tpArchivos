package demo;

public class Campo {
    private int nCampo;
    private String dCampo;
    private String iCampo;

    public Campo(int numeroCampo, String informacionCampo, String valorCampo) {
        this.nCampo = numeroCampo;
        this.dCampo = informacionCampo;
        this.iCampo = valorCampo;
    }

    public int getnCampo() {
        return nCampo;
    }
    public String getdCampo() {
        return dCampo;
    }
    public String getiCampo() {
        return iCampo;
    }

    public void setiCampo(String iCampo) {
        this.iCampo = iCampo;
    }

}
