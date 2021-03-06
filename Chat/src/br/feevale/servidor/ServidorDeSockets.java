package br.feevale.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.feevale.telas.Servidor;

public class ServidorDeSockets extends Thread {
	
	private ServerSocket serverSoket;
	private boolean continua;
	private EventosDoServidorDeSockets eventos;
	private ConfirmaConexao confirmaConexao;
	private Servidor telaServidor;
	
	public ServidorDeSockets( int nroPorta, EventosDoServidorDeSockets eventos, Servidor teServidor ) throws IOException {
		serverSoket = new ServerSocket( nroPorta );
		this.eventos = eventos;
		this.telaServidor = teServidor;
	}
	
	private Socket getSocket() throws IOException {
		
		Socket socket = serverSoket.accept();
		return socket;
	}
	
	@Override
	public void run() {
		
		System.out.println( "Iniciando serviço de sockets" );
		eventos.aoIniciarServidor();
		
		continua = true;
		while( continua ) {

			try {
				System.out.println( "Servidor de sockets aguardando conexções..." );
				 Socket s = getSocket();
				
				confirmaConexao  = new ConfirmaConexao( s, eventos, telaServidor );
				
				confirmaConexao.start();
				
			} catch (IOException e) {
				if( continua ) {
					eventos.reportDeErro(e);
				}
			}
		}

		System.out.println( "Finalizando serviço de sockets" );
		eventos.aoFinalizarServidor();
	}
	
	public void finaliza() {
		
		continua = false;
		try {
			serverSoket.close();
		} catch (IOException e) {
		}
	}
	
}