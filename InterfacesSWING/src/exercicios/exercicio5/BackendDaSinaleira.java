package exercicios.exercicio5;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BackendDaSinaleira extends Thread{
	
	private Sinaleira sinaleira;
	
	ImageIcon VerdeDesligado = new ImageIcon ( getClass().getResource( "/imagens/VerdeDesLigado.png" ) );
	ImageIcon VerdeLigado = new ImageIcon ( getClass().getResource( "/imagens/VerdeLigado.png" ) );
	ImageIcon AmareloDesligado = new ImageIcon ( getClass().getResource( "/imagens/AmareloDesLigado.png" ) );
	ImageIcon AmareloLigado = new ImageIcon ( getClass().getResource( "/imagens/AmareloLigado.png" ) );
	ImageIcon VermelhoDesligado = new ImageIcon ( getClass().getResource( "/imagens/VermelhoDesLigado.png" ) );
	ImageIcon vermelhoLigado = new ImageIcon ( getClass().getResource( "/imagens/VermelhoLigado.png" ) );
	
	public BackendDaSinaleira( Sinaleira sinaleira ) {
		this.sinaleira = sinaleira;
	}
	
	@Override
	public void run() {
		
		while( sinaleira.getLigada() ){
		
		sinaleira.setVerde( VerdeLigado );
		
		try {
			sleep( 1000 );
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog( null, "A Sinaleira parou!" );
			e.printStackTrace();
		}
		
		sinaleira.setVerde( VerdeDesligado );
		sinaleira.setAmarelo( AmareloLigado );
		
		try {
			sleep( 1000 );
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog( null, "A sinaleira parou!" );
			e.printStackTrace();
		}
		
		sinaleira.setAmarelo( AmareloDesligado );
		sinaleira.setVermelho( vermelhoLigado );
		
		try {
			sleep( 1000 );
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog( null, "A sinaleira parou!" );
			e.printStackTrace();
		}
		
		sinaleira.setVermelho( VermelhoDesligado );
		
		}
	}
}
