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
	
	private JLabel vermelho;
	private JLabel amarelo;
	private JLabel verde;
	private JButton start;
	private boolean ligada = false;
	BackendDaSinaleira acao;
	
	ImageIcon amareloDesligado = new ImageIcon( getClass().getResource( "/imagens/AmareloDesLigado.png" ) );
	ImageIcon vermelhoDesligado = new ImageIcon( getClass().getResource( "/imagens/VermelhoDesLigado.png" ) );
	ImageIcon verdeDesligado = new ImageIcon ( getClass().getResource( "/imagens/VerdeDesLigado.png" ) );
	
	public JLabel getVermelho() {
		return vermelho;
	}

	public void setVermelho( ImageIcon icone ) {
		this.vermelho.setIcon(icone);
	}

	public JLabel getAmarelo() {
		return amarelo;
	}

	public void setAmarelo( ImageIcon icone ) {
		this.amarelo.setIcon(icone);
	}

	public JLabel getVerde() {
		return verde;
	}

	public void setVerde( ImageIcon icone ) {
		this.verde.setIcon(icone);
	}
	
	public void setTextoBotao( String texto ){
		this.start.setText( texto );
	}
	
	public Boolean getLigada(){
		return this.ligada;
	}
	
	public void setLigada( Boolean ligada ){
		this.ligada = ligada;
	}

	public Sinaleira() {
		
		setBounds(100, 100, 187, 350);
		setLocationRelativeTo( null );
		setLayout( null );
		
		Container container = getContentPane();
		
		vermelho = new JLabel();
		vermelho.setBounds(50, linha, 70, 60);
		
		vermelho.setIcon( vermelhoDesligado );
		container.add( vermelho );
		
		linha  += 60 + pulaLinha; 
		
		amarelo = new JLabel();
		amarelo.setBounds(50, linha, 70, 60);
		
		amarelo.setIcon( amareloDesligado );
		container.add(amarelo);
		
		linha += 60 + pulaLinha;
		
		verde = new JLabel();
		verde.setBounds( 50, linha, 70, 60);
		
		verde.setIcon( verdeDesligado );
		container.add( verde );
		
		start = new JButton( "LIGAR" );
		start.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
					if( ligada == false ){
						acao = new BackendDaSinaleira(Sinaleira.this);
						acao.start();
						ligada = true;
						start.setText( "PARAR" );
						
					}
					else{
						acao.interrupt();
						acao.stop();
						ligada = false;
						start.setText( "LIGAR" );
						verde.setIcon( verdeDesligado );
						vermelho.setIcon( vermelhoDesligado );
						amarelo.setIcon( amareloDesligado );
					}
				
				
			}
		});
		
		linha += 60 + pulaLinha;
		
		start.setBounds( 35, linha, 100, 30);
		container.add(start);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible( true );
		
	} 
	
	public static void main(String[] args) {
		new Sinaleira();
	}
}
