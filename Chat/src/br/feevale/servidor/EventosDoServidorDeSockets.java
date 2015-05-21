package br.feevale.servidor;

import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;

public interface EventosDoServidorDeSockets {

	public void aoIniciarServidor();
	public void aoFinalizarServidor();
	public void aoReceberSocket( Socket s, ImageIcon foto, String nome );
	public void reportDeErro( IOException e );
}