package feevale.tarefas;

import javax.swing.JProgressBar;

public class MovimentaPgB extends Thread {
	
	private JProgressBar progressBar;
	private EventosPgB pgPrincipal;
	
	private boolean continua;
	
	public MovimentaPgB( EventosPgB pgPrincipal, JProgressBar progressBar ) {
		this.progressBar = progressBar;
		this.pgPrincipal = pgPrincipal;
	}
	
	@Override
	public void run() {

		progressBar.setValue( 0 );
		continua = true;
		
		for( int i = 0; i <= progressBar.getMaximum() && continua; i++ ) {
			progressBar.setValue( i );
			
			try {
				System.out.println( "Vou tirar um cochilo" );
				sleep( 20 );
				System.out.println( "acordei novo" );
			} catch( Exception e ) {
				System.out.println( "fui acordado" );
				continua = false;
				System.out.println( "Mensagem de erro: " + e.getMessage() );
			}
		}	
		
		pgPrincipal.sinalizaFim();
	}
	
	public void finaliza() {
		continua = false;
	}
}





