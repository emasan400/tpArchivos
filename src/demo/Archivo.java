package demo;

import java.time.LocalDate;
import java.util.*;

public class Archivo
{
	//Atributos clase Archivo
	private int numeroSerie;
	private String nombreArchivo;
	private LocalDate fechaModif;
	private int camposConfigurados = 0;
    private int cantRegistros = 0;

    //Optimizo esto usando una nueva estructura
    private List<Campo> campos = new ArrayList<>();

    public void agregarCampo(Campo campo){
        campos.add(campo);
        camposConfigurados++;
    }
	
	//Getter - Setter nombreArchivo
	public String getNombreArchivo()
	{
        return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo)
	{
        this.nombreArchivo = nombreArchivo;
	}
	
	//Getter - Setter numeroSerie
	public int getNumeroSerie()
	{
		return numeroSerie;
	}
	public void setNumeroSerie(Integer numeroSerie)
	{
        this.numeroSerie = numeroSerie;
	}

    //Getter - Setter campo
    public Campo getCampo(int i){
        return  campos.get(i);
    }
    public List<Campo> getListCampo(){
        return campos;
    }
    public void setCampo(int n, String d, String i)
    {
        agregarCampo(new Campo(n,d,i));
    }

	//Getter - Setter fechaModif
	public LocalDate getFechaModif()
	{
		return fechaModif;
	}
	public void setFechaModif(LocalDate fechaModif)
	{
		this.fechaModif = fechaModif;
	}
	
	//Getter - Setter camposConfigurados
	public int getCamposConfigurados()
	{
		return camposConfigurados;
	}
	public void setCamposConfigurados(int camposConfigurados)
	{
		this.camposConfigurados = camposConfigurados;
	}
	
	//Getter - Setter CantRegistros
	public int getCantRegistros()
	{
		return cantRegistros;
	}
	
	public void setCantRegistros(int cantRegistros)
	{
		this.cantRegistros = cantRegistros;
	}
	
}
