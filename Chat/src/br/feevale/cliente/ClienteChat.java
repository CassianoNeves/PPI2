package br.feevale.cliente;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONException;
import org.json.JSONObject;

import br.feevale.base64.ImagemEncoderHelper;
import br.feevale.telaComunicacao.TelaChat;
import feevale.baseInterface.BaseInterface;

public class ClienteChat extends BaseInterface {
	
	private JTextField nome;
	private JTextField endereco;
	private JTextField nrPorta;
	private Recebedor recebedor;
	JFileChooser chooser;
	private JLabel foto;
	private URL fotoInical = getClass().getResource( "/img/semFoto.jpeg" );
	private String escolhida;
	
	public ClienteChat() {
		
		setBounds( 400, 100, 320, 310 );
		setLayout( null );
		
		getContentPane().add( getJLabel( 20, "Nome" ) );
		getContentPane().add( nome = getJTextField( 150 ) );
		nome.setText( "Cassiano" );
		
		saltaLinha();
		
		foto = new JLabel();
		foto.setBounds( 170, linha, 100, 100 );
		foto.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		
		foto.setIcon( new ImageIcon( redimencionarImagem( foto, fotoInical ) ) );
		
		getContentPane().add( foto );
		
		chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "JPEG", "jpeg");
	    chooser.setFileFilter(filter);
	    
		JButton escolher = new JButton( "Escolher Foto" );
	    escolher.setBounds(30, linha +70, 130, 30);
	    escolher.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				mudarFoto();
			}
		});
	    getContentPane().add( escolher );

		saltaLinha();
		saltaLinha();
		saltaLinha();
		
		getContentPane().add( getJLabel( "Endereço" ) );
		getContentPane().add( endereco = getJTextField( 150 ) );
		endereco.setText( "localhost" );
		
		getContentPane().add( getJLabel( "Porta" ) );
		getContentPane().add( nrPorta = getJTextField( 80 ) );
		nrPorta.setText( "1843" );
		
		saltaLinha();
		saltaLinha();

		JButton bt = new JButton( "Conectar" );
		bt.setBounds( 120, linha, 100, 23 );
		getContentPane().add( bt );
		
		bt.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		
		setVisible( true );
	}
	
	private void mudarFoto() {
	
	    int returnVal = chooser.showOpenDialog(chooser);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	escolhida =  chooser.getSelectedFile().getAbsolutePath();
	    	foto.setIcon(new ImageIcon( redimencionarImagem( foto, escolhida ) ) );
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
	
	protected void conectar() {

		String usr = nome.getText().trim();
		String end = endereco.getText().trim();
		String prt = nrPorta.getText().trim();
		
		if( end.length() == 0 ) {
			JOptionPane.showMessageDialog( this, "Defina o endereço para conexão" );
			endereco.requestFocusInWindow();
			return;
		}
		
		try {
			int nrPrt = Integer.parseInt( prt );

			try {
				Socket s = new Socket( end, nrPrt );
				comunicaComEsteSocket( s );
			} catch( Exception e ) {
				JOptionPane.showMessageDialog( this, "Erro: " + e.getMessage()  );
			}
		} catch( Exception e ) {
			JOptionPane.showMessageDialog( this, "Defina o número da porta para conexão" );
			nrPorta.requestFocusInWindow();
			return;
		}
	}

	private void comunicaComEsteSocket(Socket s) throws JSONException {
		
		try {
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = 	new DataOutputStream( os );
			
			JSONObject login = new JSONObject();
			login.put( "cod", 2 );
			login.put( "nome", nome.getText() );

			String img64 = ImagemEncoderHelper.encodeImage( foto );
			
			login.put( "img", img64 );
			
			dos.writeUTF( login.toString() );
			
			recebedor = new Recebedor( s );
			recebedor.start();
		} catch ( IOException e ) {
			e.printStackTrace();
			//JOptionPane.showMessageDialog( this, "Não foi possível enviar sua mensagem: " + e.getMessage() );
		}
		
		//
	}
	

	public static void main(String[] args) {
		new ClienteChat();
	}
	
	private class Recebedor extends Thread {

		private Socket s;
		
		public Recebedor( Socket s ){
			this.s = s;
		}
		
		@Override
		public void run() {
			
			try {
				InputStream is = s.getInputStream();
				DataInputStream dis = new DataInputStream( is );
				
				boolean receber = true;
				
				while( receber ) {
					
					String msg = dis.readUTF();
					
					if( msg != null ) {
						
						JSONObject recebido = new JSONObject( msg );
						
						switch ( recebido.getInt( "cod" ) ) {
						case 0:
							
							String img64 = recebido.getString( "img" );
							byte[] imgbyte = ImagemEncoderHelper.decodeImage( img64 );
							
							ImageIcon newIcon = new ImageIcon(imgbyte);
							
							new TelaChat( s, 510, "Cliente", newIcon, recebido.getString( "nome" ) );
							receber = false;
							break;

						case -1:
							JOptionPane.showMessageDialog(ClienteChat.this, "Sua Solicitação foi negada!");
							break;
							
						default:
							JOptionPane.showMessageDialog(ClienteChat.this, "Ocorreu um erro na confirmação!");
							break;
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}