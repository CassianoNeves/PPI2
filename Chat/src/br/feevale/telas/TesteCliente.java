package br.feevale.telas;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TesteCliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
//		System.out.println( "Solicitando conexao..." );
		Socket s = new Socket( "localhost", 1843 );
//		System.out.println( "Ok, conectei");
	}
}