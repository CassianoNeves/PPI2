package exercicios.componentes;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class SeletorDeEstados extends JComboBox<String> {
	ArrayList<String> estados = aplicaEstados();
	ArrayList<String> siglas = aplicaSiglas();
	
	
	public SeletorDeEstados(){
		
		
		for( String estado : estados ){
			addItem( estado );
		}
	}
	
	public String getCdEstado(){

		int item = getSelectedIndex();	
		String sigla = siglas.get(item);
		
		JOptionPane.showMessageDialog(null, "A sigla estado selecionado é: " + sigla);
		
		return sigla;		
	}
	
	public String getNomeEstado(){

		int item = getSelectedIndex();	
		String estado = estados.get(item);
		
		JOptionPane.showMessageDialog(null, "O nome estado selecionado é: " + estado);
		
		return estado;		
	}
	
	public void setCdEstado( String cdEstado ){
		int item = 21;
		
		for( int i = 0; i < siglas.size(); i++ ){
			if( cdEstado == siglas.get( i ) ){
				item = i;
			}
		}
	
		setSelectedIndex( item );
	}

	private ArrayList<String> aplicaEstados() {
		ArrayList<String> estados = new ArrayList<String>();
		estados.add( "--Selecione--" );
		estados.add( "Acre" );
		estados.add( "Alagoas" );
		estados.add( "Amapá" );
		estados.add( "Amazonas" );
		estados.add( "Bahia" );
		estados.add( "Ceará" );
		estados.add( "Distrito Federal" );
		estados.add( "Espírito Santo" );
		estados.add( "Goiás" );
		estados.add( "Maranhão" );
		estados.add( "Mato Grosso" );
		estados.add( "Mato Grosso do Sul" );
		estados.add( "Minas Gerais" );
		estados.add( "Pará" );
		estados.add( "Paraíba" );
		estados.add( "Paraná" );
		estados.add( "Pernambuco" );
		estados.add( "Piauí" );
		estados.add( "Rio de Janeiro" );
		estados.add( "Rio Grande do Norte" );
		estados.add( "Rio Grande do Sul" );
		estados.add( "Rondônia" );
		estados.add( "Roraima" );
		estados.add( "Santa Catarina" );
		estados.add( "São Paulo" );
		estados.add( "Sergipe" );
		estados.add( "Tocantins" );
		
		return estados;
	}
	
	private ArrayList<String> aplicaSiglas() {
		ArrayList<String> siglas = new ArrayList<String>();
		siglas.add( "--Selecione--" );
		siglas.add( "AC" );
		siglas.add( "AL" );
		siglas.add( "AP" );
		siglas.add( "AM" );
		siglas.add( "BA" );
		siglas.add( "CE" );
		siglas.add( "DF" );
		siglas.add( "ES" );
		siglas.add( "GO" );
		siglas.add( "MA" );
		siglas.add( "MT" );
		siglas.add( "MS" );
		siglas.add( "MG" );
		siglas.add( "PA" );
		siglas.add( "PB" );
		siglas.add( "PR" );
		siglas.add( "PE" );
		siglas.add( "PI" );
		siglas.add( "RJ" );
		siglas.add( "RN" );
		siglas.add( "RS" );
		siglas.add( "RO" );
		siglas.add( "RR" );
		siglas.add( "SC" );
		siglas.add( "SP" );
		siglas.add( "SE" );
		siglas.add( "TO" );
		
		return siglas;
	}
}
