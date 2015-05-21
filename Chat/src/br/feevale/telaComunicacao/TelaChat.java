package br.feevale.telaComunicacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import br.feevale.fileDownload.FileReceiver;
import br.feevale.fileDownload.FileSender;
import br.feevale.fileDownload.IFileDownloadHandler;
import feevale.baseInterface.BaseInterface;

public class TelaChat extends BaseInterface implements WindowListener, IFileDownloadHandler {
	
	private Socket socket;
	private Recebedor recebedor;
	
	private File arquivoEnviar;
	
	private JTextArea areaChat;
	private JTextField texto;
	private JButton btEnviar;
	JButton btArquivo;
	private String nome;
	
	private List<Integer> portasUsadasArquivo;
	
	public TelaChat( Socket socket, int coluna, String titulo, ImageIcon foto, String nome ) {
		
		this.nome = nome;
		this.socket = socket;
		
		portasUsadasArquivo = new ArrayList<>();
		
		setTitle( titulo );
		setBounds( coluna, 200, 500, 600 );
		setLayout( null );
		
		areaChat = new JTextArea();
		areaChat.setEditable( false );
		JScrollPane sp = new JScrollPane( areaChat );
		sp.setBounds( 5, 10, 470, 300 );
		
		getContentPane().add( sp );
		
		texto = new JTextField();
		texto.setBounds( 5, 320, 380, 23 );
		getContentPane().add( texto );
		
		
		JButton bt = new JButton( "Enviar" );
		bt.setBounds( 390, 320, 80, 23 );
		getContentPane().add( bt );
		btEnviar = bt;
		
		JLabel lblfoto = new JLabel( foto );
		lblfoto.setBounds(5, 350, 100, 100);
		getContentPane().add( lblfoto );
		
		bt.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				enviaTexto();
			}
		});
		
		btArquivo = new JButton( "Arquivo" );
		btArquivo.setBounds(390, 350, 80, 23);
		getContentPane().add( btArquivo );
		
		btArquivo.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File[] arquivos  = null;  
			    JFileChooser fc = new JFileChooser();  
			    fc.setDialogTitle("Escolha o arquivo...");  
			    fc.setDialogType(JFileChooser.OPEN_DIALOG);  
			    fc.setApproveButtonText("OK");  
			    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			    fc.setMultiSelectionEnabled(true);  
			    int resultado = fc.showOpenDialog(fc);  
			    if (resultado != JFileChooser.CANCEL_OPTION){
			    	arquivos = fc.getSelectedFiles();
			    	solicitaEnvioArquivo(arquivos);
			    }
			}
		});

		setVisible( true );
		addWindowListener( this );
		
		recebedor = new Recebedor();
		recebedor.start();
	}
	
	private void enviaTexto() {
		
		String txt = texto.getText();
		
		if( txt.length() > 0 ) {
			
			areaChat.setText( areaChat.getText() + "\n Enviando: " + txt );
			texto.setText( "" );
			texto.requestFocusInWindow();
			
			enviaPeloSocket( txt );
		}
	}
	
	private void solicitaEnvioArquivo(File[] arquivos) {
		
		if( arquivos.length > 0 ) {
			File fl = arquivos[0];
			areaChat.setText( areaChat.getText() + "\n Solicitação de arquivo: " + fl.getName() );
			//texto.setText( "" );
			texto.requestFocusInWindow();
			
			arquivoEnviar = fl;
			
			enviaPeloSocket( fl );
		}
	}
	
	private void enviaPeloSocket( String txt ) {
		
		try {
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream( os );

			JSONObject transacao = new JSONObject();
			transacao.put( "cod", 2 );
			transacao.put( "mensagem", txt );
			
			dos.writeUTF( transacao.toString() );
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Não foi possível enviar sua mensagem: " + e.getMessage() );
		}
	}
	
	private void enviaPeloSocket(File fl){
		try {
			
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream( os );
			
			JSONObject transacao = new JSONObject();
			transacao.put( "cod", 4 );
			transacao.put( "nomeArquivo", fl.getName() );
			transacao.put( "tamanho", fl.length() );
			
			dos.writeUTF( transacao.toString() );
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Não foi possível enviar sua mensagem: " + e.getMessage() );
		}
	}
	
	private void iniciaThreadEnvioArquivo(int porta){
		try {
			new FileSender(socket.getInetAddress().getHostAddress(), porta, arquivoEnviar.getAbsolutePath(), this).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void respostaSolicitacaoArquivo(int resposta, int tamanhoArquivo, String nomeArquivo, String caminhoSalvar ){
		try {
			ServerSocket server = null;
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream( os );

			JSONObject transacao = new JSONObject();
			transacao.put( "cod", resposta );
			
			if( resposta == 5){
				int porta = geraPortaEnvioArquivo();
				transacao.put( "porta", porta );
				server = new ServerSocket(porta);
			}
			
			dos.writeUTF( transacao.toString() );
			
			if( resposta == 5 ){
				Socket s = server.accept();
				new FileReceiver(s, tamanhoArquivo, caminhoSalvar  + "/" + nomeArquivo, this).start();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Não foi possível enviar sua mensagem: " + e.getMessage() );
		}
	}
	
	private class Recebedor extends Thread {

		@Override
		public void run() {
			
			try {
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream( is );
				System.out.println("Recebendo");

				while( isVisible() ) {
					
					String msg = dis.readUTF();
					if( msg != null ) {
						
						JSONObject rec = new JSONObject( msg );

						switch( rec.getInt( "cod" ) ) {
							case 2:  areaChat.setText( areaChat.getText() + "\n " + nome +": " + rec.getString( "mensagem" ) );
								     break;
							case 3:  FileInputStream fl = new FileInputStream(rec.getString( "mensagem" ));
								     break;
							case 11: areaChat.setText( areaChat.getText() + "\n ATENÇÃO: o usuário remoto desconectou!" );
									 texto.setEnabled( false );
									 btEnviar.setEnabled( false );
									 btArquivo.setEnabled( false );
									 
									 break;
							case 4:  DecimalFormat df = new DecimalFormat("#,###.00");
								     int x = JOptionPane.showConfirmDialog(null, "Solicitação de arquivo: \n"
									                                            + "Arquivo: " + rec.getString("nomeArquivo") + "\n"
									                                            + "Tamanho: " + df.format( rec.getDouble("tamanho")/1024 ) + "kb", "Arquivo", JOptionPane.YES_NO_OPTION); 
									 if (x == JOptionPane.OK_OPTION){
										 escolherCaminhoParaSalvar( rec );
									 }else{
										respostaSolicitacaoArquivo(6, 0, "", "");
										System.out.println("Recusou o arquivo");
									 }
									 break;
							case 5:  iniciaThreadEnvioArquivo( rec.getInt("porta") );
									 break;
							case 6:  areaChat.setText( areaChat.getText() + "\n " + nome + " recusou o arquivo." );
									 break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void escolherCaminhoParaSalvar( JSONObject rec ) throws JSONException {
			
			
			JFileChooser chooser = new JFileChooser();
			
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int returnVal = chooser.showOpenDialog(chooser);
    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
    	    	String caminhoSalvar = chooser.getSelectedFile().getAbsolutePath();
    	    	respostaSolicitacaoArquivo(5, rec.getInt("tamanho"), rec.getString("nomeArquivo"), caminhoSalvar);
    	    } else{
    	    	respostaSolicitacaoArquivo(6, 0, "", "");
    	    }
			System.out.println("Aceitou o arquivo");
		}
	}
	
	private int geraPortaEnvioArquivo(){
		int porta = 40000;
		Random gerador = new Random();
			
		while( portasUsadasArquivo.contains(porta) ){
			porta = 40000 + gerador.nextInt(60000-40000);
		}
		
		portasUsadasArquivo.add(porta);
		
		return porta;
	}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {

		try {
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream( os );

			JSONObject transacao = new JSONObject();
			transacao.put( "cod", 11 );
			
			
			dos.writeUTF( transacao.toString() );
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, "Não foi possível enviar sua mensagem: " + e.getMessage() );
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

	@Override
	public void onFinishSendFile(String fileName) {
		areaChat.setText( areaChat.getText() + "\n Arquivo Enviado: " + fileName );
	}

	@Override
	public void onFinishReceiveFile(String fileName) {
		areaChat.setText( areaChat.getText() + "\n Arquivo Recebido: " + fileName );
	}

	@Override
	public void onErrorSendFile(Exception e) {
		areaChat.setText( areaChat.getText() + "\n Erro ao enviar arquivo: " + e.getMessage() );
	}

	@Override
	public void onErrorReceiveFile(Exception e) {
		areaChat.setText( areaChat.getText() + "\n Erro ao receber arquivo: " + e.getMessage() );
	}
}













