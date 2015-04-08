package exercicios.exercicio5;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Sinaleira extends JFrame{

	private int linha = 10;
	private int pulaLinha = 20;
	
	public Sinaleira() {
		
		setBounds(100, 100, 190, 400);
		setLocationRelativeTo( null );
		setLayout( null );
		
		Container container = getContentPane();
		
		JLabel vermelho = new JLabel();
		vermelho.setBounds(50, linha, 70, 60);
		ImageIcon vermelhoDesligado = new ImageIcon( getClass().getResource( "/imagens/VermelhoDesLigado.png" ) );
		vermelho.setIcon( vermelhoDesligado );
		container.add( vermelho );
		
		linha  += 60 + pulaLinha; 
		
		JLabel amarelo = new JLabel();
		amarelo.setBounds(50, linha, 70, 60);
		ImageIcon amareloDesligado = new ImageIcon( getClass().getResource( "/imagens/AmareloDesLigado.png" ) );
		amarelo.setIcon( amareloDesligado );
		container.add(amarelo);
		
		linha += 60 + pulaLinha;
		
		JLabel verde = new JLabel();
		verde.setBounds( 50, linha, 70, 60);
		ImageIcon verdeDesligado = new ImageIcon ( getClass().getResource( "/imagens/VerdeDesLigado.png" ) );
		verde.setIcon( verdeDesligado );
		container.add( verde );
		
		JButton start = new JButton( "Star" );
		start.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible( true );
		
	} 
	
	public static void main(String[] args) {
		new Sinaleira();
	}
}
