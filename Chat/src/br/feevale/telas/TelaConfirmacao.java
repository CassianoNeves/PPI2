package br.feevale.telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONException;
import org.json.JSONObject;

import br.feevale.base64.ImagemEncoderHelper;
import br.feevale.servidor.EventosDoServidorDeSockets;

public class TelaConfirmacao extends JFrame{
	
	private JTextField txtNome;
	String nomeDoComunicacodor;
	private Socket s;
	private JFileChooser chooser;
	private JLabel lblfotoEnviada;
	private URL fotoInical = getClass().getResource( "/img/semFoto.jpeg" );
	private String escolhida;
	private EventosDoServidorDeSockets eventos;
	
	public TelaConfirmacao( JSONObject login, Socket s, EventosDoServidorDeSockets eventos ) throws JSONException {
		
		this.eventos = eventos;
		this.s = s;
		this.nomeDoComunicacodor = login.getString( "nome" );
		
		setBounds(50, 50, 510, 470);
		setLayout( null );
		
		Container container = getContentPane();
		
		JLabel lbl1 = new JLabel( nomeDoComunicacodor + " quer conectar com você." );
		lbl1.setBounds( 5, 10, 510, 23);
		lbl1.setHorizontalAlignment( JLabel.CENTER );
		container.add( lbl1 );
		
		JLabel lbl2 = new JLabel( "Deseja conectar com " + nomeDoComunicacodor + "?" );
		lbl2.setBounds( 5, 23, 510, 50);
		lbl2.setHorizontalAlignment( JLabel.CENTER );
		container.add( lbl2 );
		
		String img64 = login.getString( "img" );
		byte[] imgbyte = ImagemEncoderHelper.decodeImage( img64 );
		ImageIcon foto = new ImageIcon(imgbyte);
		
		JLabel lblfoto = new JLabel( foto  );
		lblfoto.setBounds( 205, 70, 100, 100);
		lblfoto.setHorizontalAlignment( JLabel.CENTER );
		lblfoto.setBorder( BorderFactory.createLineBorder( Color.BLACK ));
		container.add( lblfoto );
		
		JButton btAceita = new JButton( "Sim" );
		btAceita.setBounds( 180, 200, 70, 30 );
		btAceita.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					enviarConfirmacao( 0 );
					eventos.aoReceberSocket( s, foto, login.getString( "nome" ) );
					dispose();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		container.add(btAceita);
		
		JButton btRecusa = new JButton( "Não" );
		btRecusa.setBounds( 255, 200, 70, 30 );
		btRecusa.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					enviarConfirmacao( -1 );
					dispose();
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		container.add(btRecusa);
		
		JLabel informacoes = new JLabel( "Para Aceitar é preciso fornecer seu nome e uma foto." );
		informacoes.setBounds(20, 260, 510, 23);
		informacoes.setHorizontalAlignment( JLabel.CENTER );
		container.add( informacoes );
		
		JLabel lblnome = new JLabel( "Nome: " );
		lblnome.setBounds(155, 293, 50, 23);
		container.add( lblnome );
		
		txtNome = new JTextField();
		txtNome.setBounds( 215, 293, 150,23);
		container.add( txtNome );
		
		chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JPEG", "jpeg");
	    chooser.setFileFilter(filter);
		
	    JButton btEscolherFoto = new JButton( "Escolher Foto");
	    btEscolherFoto.setBounds( 135, 400, 130, 30 );
	    btEscolherFoto.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mudarFoto();
			}
		});
	    container.add( btEscolherFoto );
	    
	    lblfotoEnviada = new JLabel();
	    lblfotoEnviada.setBounds( 275, 330, 100, 100 );
	    lblfotoEnviada.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
	    lblfotoEnviada.setIcon( new ImageIcon( redimencionarImagem( lblfotoEnviada, fotoInical ) ) );
		
		getContentPane().add( lblfotoEnviada );
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible( true );
	}
	
	private void mudarFoto() {
		int returnVal = chooser.showOpenDialog(chooser);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	escolhida =  chooser.getSelectedFile().getAbsolutePath();
	    	lblfotoEnviada.setIcon(new ImageIcon( redimencionarImagem( lblfotoEnviada, escolhida ) ) );
	    }
	}
	
	private Image redimencionarImagem(JLabel foto, URL caminho ) {

		Image newImg = new ImageIcon( fotoInical ).getImage().getScaledInstance( foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT );
		return newImg;
	}

	private Image redimencionarImagem(JLabel foto, String caminho ) {

		Image newImg = new ImageIcon( caminho ).getImage().getScaledInstance( foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT );
		return newImg;
	}
	
	private void enviarConfirmacao( int codigo ) throws JSONException {
		
		try {
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream( os );
			
			JSONObject resposta = new JSONObject();
			resposta.put( "cod", codigo );
			resposta.put( "nome", txtNome.getText() );
			
			String img64Enviar = ImagemEncoderHelper.encodeImage( lblfotoEnviada );
			
			resposta.put( "img", img64Enviar );
							
			dos.writeUTF( resposta.toString() );
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog( null, "Não foi possível enviar a confirmação" );
		}
		
	}
}
