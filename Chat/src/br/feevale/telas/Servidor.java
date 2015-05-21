package br.feevale.telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.feevale.servidor.EventosDoServidorDeSockets;
import br.feevale.servidor.ServidorDeSockets;
import br.feevale.telaComunicacao.TelaChat;

public class Servidor extends JFrame implements ActionListener, EventosDoServidorDeSockets, WindowListener {

	private JButton botao;
	private ServidorDeSockets servidor;
	
	public Servidor() {
		inicializa();
	}
	
	public void inicializa() {
		
		setBounds( 80, 90, 200, 120 );
		setLayout( null );
		
		botao = new JButton( "Iniciar" );
		botao.setBounds( 30, 30, 130, 30 );
		getContentPane().add( botao );
		botao.addActionListener( this );
		
		addWindowListener( this );
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setVisible( true );
	}
	
	public static void main(String[] args) {
		new Servidor();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if( servidor == null ) {
			try {
				servidor = new ServidorDeSockets( 1843, this, this );
				servidor.start();
				botao.setText( "Finalizar" );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			servidor.finaliza();
			botao.setText( "Iniciar" );
			servidor = null;
		}
	}

	@Override
	public void aoIniciarServidor() {}

	@Override
	public void aoFinalizarServidor() {}

	@Override
	public void aoReceberSocket(Socket s, ImageIcon foto, String nome) {
		
		comunicaComEsteSocket( s, foto, nome );
	}

	private void comunicaComEsteSocket(Socket s, ImageIcon foto, String nome ) {
		
		new TelaChat( s, 5, "Servidor", foto, nome );
	}

	@Override
	public void reportDeErro(IOException e) {
		JOptionPane.showMessageDialog( this, "Erro: " + e.getMessage()  );
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {
		
		if( servidor != null ) {
			servidor.finaliza();
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}










