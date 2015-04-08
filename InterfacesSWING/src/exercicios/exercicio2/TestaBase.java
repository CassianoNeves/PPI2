package exercicios.exercicio2;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import exercicios.exercicio2.Base;

public class TestaBase extends Base{
	
	JRadioButton rb1;
	JRadioButton rb2;
	JTabbedPane tp1;
	JPanel painel1;
	JPanel painel2;
	
	public TestaBase(){
		super( "Exercicio 2 de Java" );
		
		setBounds(0, 0, 700, 600 );
		setLocationRelativeTo(null);
		setLayout(null);
		
		container.add( getCampoTexto( 30, 130, 60, 60, "Código:" ));
		container.add( getCampoTexto( 230, 50, "Nome:" ));
		container.add( getCampoTexto( 100, 90, "Logradouro:" ));
		setLinha();
		setColuna();
		container.add( getCampoTexto( 100, 40, "CEP:" ));
		
		ArrayList<String> estados = new ArrayList<String>();
		estados.add( "RJ" );
		estados.add( "SP" );
		estados.add( "CR" );
		estados.add( "SC" );
		estados.add( "RS" );
		
		container.add( getComboBox( 100, 60, "Estado:", estados ));
		container.add( getJLabel(40, "Sexo:"));
		
		container.add( rb1 = getRadioButton(100, "Masculino"));
		container.add( rb2 =  getRadioButton(100, "Feminino"));
		
		ButtonGroup gp = new ButtonGroup();
		gp.add( rb1 );
		gp.add( rb2 );
		
		setColuna();
		setLinha();
		
		container.add( getJLabel(90, "Fornecedor:" ));
		container.add( getJCheckBox());
		
		setLinha();
		setColuna( 95 );
		container.add( tp1 = getJTabbedPane( getColuna(), getLinha(), 500, 200 ) );
		
		tp1.add( painel1 = getPainel(getColuna(), getLinha(), 500, 200), "Painel 1" );
		tp1.add( painel2 = getPainel(getColuna(), getLinha(), 500, 200), "Painel 2" );
		
		setLinha(200);
		
		JLabel lbl = new JLabel( "testes" );
		lbl.setPreferredSize( new Dimension( 60, 30 ));
		
		painel1.add( lbl );
		
		//Rever este treicho do codigo
		
		JTextArea txtArea = new JTextArea();
		txtArea.setBorder( BorderFactory.createTitledBorder( "Titulo ") );
		txtArea.setLineWrap( true );
		
		JScrollPane scroll = new JScrollPane( txtArea );
		scroll.setBounds(50, 50, 500, 200);
		
		Container containerTexto = new Container();
		containerTexto.setBounds(50, 50, 500, 200);
		containerTexto.setLayout( null );
		containerTexto.add( scroll );
		
		painel2.add( containerTexto );
		
		JButton btSalvar = new JButton( "Salvar" );
		btSalvar.setBounds( 420, getLinha(), 100, 30);
		
		JButton btCancelar = new JButton( "Cancelar" );
		btCancelar.setBounds( 530, getLinha(), 100, 30);
		
		container.add( btSalvar );
		container.add( btCancelar );
		
		setVisible(true);
		
	}
	
	

	public static void main(String[] args) {
		new TestaBase();
	}

	

}
