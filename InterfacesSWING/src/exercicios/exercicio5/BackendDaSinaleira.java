package exercicios.exercicio5;

public class BackendDaSinaleira extends Thread{
	
	private Sinaleira sinaleira;
	
	public BackendDaSinaleira( Sinaleira sinaleira ) {
		this.sinaleira = sinaleira;
	}
	
	@Override
	public void run() {
		if(sinaleira.getLigada()){
			sinaleira.setLigada( false );
			
			sinaleira.setTextoBotao( "LIGAR" );
		} else{
			sinaleira.setLigada( true  );
			
			sinaleira.setTextoBotao( "PARAR" );
		}
	}
}
