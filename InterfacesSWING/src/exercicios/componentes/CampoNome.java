package exercicios.componentes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CampoNome extends CampoTexto implements FocusListener{

	public CampoNome() {
		addFocusListener( this );
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		String texto = getText();
		String saida = "";
		String letra = "";
		
		for( int i = 0; i < texto.length(); i++ ){
			if( i == 0){
				letra = letra + texto.charAt( i );
				letra  = letra.toUpperCase();
				saida += letra;
			} else{
				letra = "";
				letra = letra + texto.charAt( i );
				letra  = letra.toLowerCase();
				saida += letra;
			}
		}
		
		setText( saida );
	}
	
	

}
