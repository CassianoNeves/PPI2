package exercicios.exercicio5;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sinaleira extends JFrame{

	private int linha = 30;
	private int pulaLinha = 150;
	
	public Sinaleira() {
		
		setBounds(100, 100, 400, 500);
		setLocationRelativeTo( null );
		setLayout( null );
		
		Container container = getContentPane();
		
		JLabel vermelho = new JLabel();
		vermelho.setBounds(150, 30, 100, 100);
		ImageIcon vermelhoClaro = new ImageIcon( getClass().getResource( "/imagens/VermelhoDesLigado.png" ) );
		vermelho.setIcon( vermelhoClaro );
		container.add( vermelho );
		
		JLabel amarelo = new JLabel();
		amarelo.setBounds(150, 150, width, height);
		JLabel verde = new JLabel();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible( true );
		
	} 
	
	public static void main(String[] args) {
		new Sinaleira();
	}
}
