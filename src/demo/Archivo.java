package demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Archivo
{
	//Atributos clase Archivo
	private Integer numeroSerie;
	private String nombreArchivo;
	private LocalDate fechaModif;
	private Integer camposConfigurados = 0;
	private Map<Integer,String> descripcionCampo = new LinkedHashMap<Integer,String>();
	private Integer cantRegistros = 0;
	private Map<Integer,String> infoCampo = new LinkedHashMap<Integer,String>();
	
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
	public Integer getNumeroSerie()
	{
		return numeroSerie;
	}
	public void setNumeroSerie(Integer numeroSerie)
	{
		this.numeroSerie = numeroSerie;
	}
	
	//Getter - Setter descripcionCampo
	public Map<Integer,String> getDescripcionCampo()
	{
		return descripcionCampo;
	}
	
	//IMPORTANTE, getter para sacar info del campo particular
	public String getDescripcionCampo(int i)
	{
		return descripcionCampo.get(i);
	}
	
	public void setDescripcionCampo(Integer codAtributo, String descripcionCampo)
	{
		this.descripcionCampo.put(codAtributo,descripcionCampo);
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
	public Integer getCamposConfigurados()
	{
		return camposConfigurados;
	}
	public void setCamposConfigurados(Integer camposConfigurados)
	{
		this.camposConfigurados = camposConfigurados;
	}
	
	//Getter - Setter CantRegistros
	public Integer getCantRegistros()
	{
		return cantRegistros;
	}
	
	public void setCantRegistros(Integer cantRegistros)
	{
		this.cantRegistros = cantRegistros;
	}
	
	public Map<Integer,String> getInfoCampo()
	{
		return infoCampo;
	}
	
	public void setInfoCampo(Map<Integer,String> infoCampo)
	{
		this.infoCampo = infoCampo;
	}
	
	public void setInfoCampo(Integer key, String infoCampo)
	{
		this.infoCampo.put(key,infoCampo);
	}
	
}
