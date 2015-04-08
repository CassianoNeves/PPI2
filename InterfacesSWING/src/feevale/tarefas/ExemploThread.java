package feevale.tarefas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class ExemploThread extends JFrame implements EventosPgB {
	
	private JProgressBar progressBar1;
	private MovimentaPgB mov;
	private JButton botao;
	
	public ExemploThread() {
		
		setBounds( 100, 80, 500, 200 );
		setLayout( null );
		 	
		getContentPane().add( progressBar1 = criaProgressBar( 90, 100000000 ) );
		
		JButton btIniciar = new JButton( "Iniciar" );
		btIniciar.setBounds( 200, 40, 100, 23 );
		getContentPane().add( btIniciar );
		botao = btIniciar;
		
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
			botao.setText( "Parar" );
		} else {
			mov.finaliza();
		}

	}

	@Override
	public void sinalizaFim() {

		mov = null;
		botao.setText( "Iniciar" );
		JOptionPane.showMessageDialog( null, "Terminei processo 1111" );
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new ExemploThread();
		
	}
	
	
	
	
	
	
	
	
	

	
}