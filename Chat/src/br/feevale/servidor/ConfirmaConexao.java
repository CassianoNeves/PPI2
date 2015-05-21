package br.feevale.servidor;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.JSONException;
import org.json.JSONObject;

import br.feevale.base64.ImagemEncoderHelper;
import br.feevale.telas.Servidor;
import br.feevale.telas.TelaConfirmacao;

public class ConfirmaConexao extends Thread{

	private Socket s;
	private EventosDoServidorDeSockets eventos;
	private Servidor telaServidor;
	private JFileChooser chooser;
	private String escolhida;
	
	public ConfirmaConexao( Socket s, EventosDoServidorDeSockets eventos, Servidor telaServidor ){
		this.s = s;
		this.eventos = eventos;
		this.telaServidor = telaServidor;
	}
	
		public void run() {
			
			try {
				InputStream is = s.getInputStream();
				DataInputStream dis = new DataInputStream( is );
				
				boolean receber = true;
				
				while( receber  ) {

						String msg = dis.readUTF();
						
						if( msg != null ){
							
							JSONObject recebido = new JSONObject( msg );
							
							if( recebido.getInt( "cod" ) == 2 ){
								
								new TelaConfirmacao( recebido, s, eventos );
								receber = false;
								
//								String img64 = recebido.getString( "img" );
//								String nome = recebido.getString( "nome" );
//								byte[] imgbyte = ImagemEncoderHelper.decodeImage( img64 );
//								
//								ImageIcon newIcon = new ImageIcon(imgbyte);
//								
//								int dialogButton = JOptionPane.YES_NO_OPTION;
//				                int dialogResult = JOptionPane.showConfirmDialog(telaServidor, 
//				                		recebido.getString( "nome" ) + " quer conectar com você.\nDeseja conectar com " + nome + "?" +
//				                		"\nOBS: Para aceitar você deve escolher um foto a ser enviada.",
//				                		"Solicitação", dialogButton, 1, newIcon);
//				                
//				                if(dialogResult == JOptionPane.YES_OPTION){
//				                	
//				                	int codigo = 0;
//				                	
//				                	chooser = new JFileChooser();
//				            	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
//				            	        "JPEG", "jpeg");
//				            	    chooser.setFileFilter(filter);
//				            	    
//				            	    boolean escolheu = true;
//				            	    
//				            	    while( escolheu ){
//				            	    
//					            	    int returnVal = chooser.showOpenDialog(telaServidor);
//					            	    if(returnVal == JFileChooser.APPROVE_OPTION) {
//					            	    	escolhida =  chooser.getSelectedFile().getAbsolutePath();
//					            	    	
//					            	    	Image newImg = new ImageIcon( escolhida ).getImage().getScaledInstance( 100, 100, Image.SCALE_DEFAULT );
//					            	    	JLabel foto = new JLabel( new ImageIcon( newImg ));
//					            	    	String img64Enviar = ImagemEncoderHelper.encodeImage( foto );
//					            	    	
//					            	    	enviarConfirmacao( codigo, , img64Enviar );
//					            	    	
//					            	    	
//					            	    	
//						                	eventos.aoReceberSocket( s, newIcon,  );
//						                	receber = false;
//						                	escolheu = false;
//					            	    } else{
//					            	    	int confimacao = JOptionPane.YES_NO_OPTION;
//					            	    	confimacao = JOptionPane.showConfirmDialog(telaServidor, "Você não escolheu uma foto valida,\ndeseja escolher novamente ?", "Atenção", confimacao,1 );
//					            	    	if( confimacao == JOptionPane.NO_OPTION ){
//					            	    		System.out.println("aqui");
//					            	    		escolheu = false;
//					            	    		
//					            	    		codigo = -1;
//							                	
//							                	enviarConfirmacao( codigo, , "" );
//					            	    	}
//					            	    }
//				            	    }
//				                }
//				                else if(dialogResult == JOptionPane.NO_OPTION){
//				                	
//				                	int codigo = -1;
//				                	
//				                	enviarConfirmacao( codigo, ,"" );
//				                	receber = false;
//				                }
							}
						
						
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

//		private void enviarConfirmacao( int codigo, String nome, String img64 ) throws JSONException {
//			
//			try {
//				OutputStream os = s.getOutputStream();
//				DataOutputStream dos = new DataOutputStream( os );
//				
//				JSONObject resposta = new JSONObject();
//				resposta.put( "cod", codigo );
//				resposta.put( "nome", nome );
//				resposta.put( "img", img64 );
//								
//				dos.writeUTF( resposta.toString() );
//				
//			} catch (IOException e) {
//				JOptionPane.showMessageDialog( null, "Não foi possível enviar a confirmação" );
//			}
//			
//		}
	}

