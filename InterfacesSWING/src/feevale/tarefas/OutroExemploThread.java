package feevale.tarefas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class OutroExemploThread extends JFrame implements EventosPgB {
	
	private JProgressBar progressBar1;
	private MovimentaPgB mov;
	
	public OutroExemploThread() {
		
		setBounds( 300, 400, 500, 200 );
		setLayout( null );
		 	
		getContentPane().add( progressBar1 = criaProgressBar( 90, 100000000 ) );
		
		JButton btIniciar = new JButton( "Iniciar" );
		btIniciar.setBounds( 200, 40, 100, 23 );
		getContentPane().add( btIniciar );
		getContentPane().setBackground( Color.BLUE );
		btIniciar.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movimenta();
			}
		});
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setVisible( true );
		
	}
	
	private JProgressBar criaProgressBar( int linha, int vlrMaximo ) {
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds( 50, linha, 400, 20 );
		
		progressBar.setMaximum( vlrMaximo );		
		progressBar.setValue( 0 );

		return progressBar;
	}

	protected void movimenta() {

		if( mov == null ) {
			mov = new MovimentaPgB( this, progressBar1 );
			mov.start();
		}

	}

	public void sinalizaFim() {
		mov = null;
		JOptionPane.showMessageDialog( this, "Terminei processo OutroExemplo" );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new OutroExemploThread();
	}
	
}