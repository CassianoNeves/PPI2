package exercicios.exercicio1;

import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class exercicio1 extends JFrame {
	
	private int linha;
	private int espaçoLinha = 40;
	
	public exercicio1(){
		
		setBounds(50, 50, 400, 650);
		setLocationRelativeTo(null);
		setLayout(null);
		Container container = getContentPane();
		
		JLabel lblTitulo = new JLabel( "Cadastro" );
		lblTitulo.setBounds(0, 0, 400, 50);
		container.add(lblTitulo);
		
		Font fonte = new Font( "Arial",Font.PLAIN , 30);
		lblTitulo.setFont( fonte );
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);

		container.add( getJLabel( 70, "Código:"));
		container.add( getJTextField( 50 ) );
		container.add( getJLabel("Nome:"));
		container.add( getJTextField( 120 ) );
		container.add( getJLabel("Telefone:"));
		container.add( getJTextField( 120 ) );
		container.add( getJLabel("Endereço:"));
		container.add( getJTextField( 120 ) );
		container.add( getJLabel("cidade:"));
		container.add( getJTextField( 120 ) );
		container.add( getJLabel( "Estado:" ));
		
		JComboBox cbEstado = new JComboBox();
		cbEstado.setBounds(130, linha, 130, 30);
		cbEstado.addItem( " - Selecione - " );
		cbEstado.addItem( "RS" );
		cbEstado.addItem( "SC" );
		cbEstado.addItem( "PR" );
		cbEstado.addItem( "SP" );
		
		container.add(cbEstado);
		
		container.add( getJLabel("Sexo:"));
		
		JRadioButton rb1 = new JRadioButton( "Masculino" );
		JRadioButton rb2 = new JRadioButton( "Feminino" );
		
		rb1.setBounds(120, linha, 100, 23);
		rb2.setBounds(220, linha, 100, 23);
		rb1.setOpaque(false);
		rb2.setOpaque(false);
		
		container.add(rb1);
		container.add(rb2);
		
		container.add( getJLabel( "É admin: " ));
		
		JCheckBox ck = new JCheckBox();
		ck.setBounds( 130, linha, 23, 23);
		container.add(ck);
		
		ButtonGroup gp = new ButtonGroup();
		gp.add(rb1);
		gp.add(rb2);
		
		linha = linha+espaçoLinha;
		
		JTextArea txtArea = new JTextArea();
		
		txtArea.setBorder( BorderFactory.createTitledBorder( "Titulo" ) );
		txtArea.setLineWrap( true );
		
		JScrollPane sp = new JScrollPane( txtArea );
		sp.setBounds(50, linha, 300, 100);
		
		container.add(sp);
		
		linha = linha+espaçoLinha+100;
		
		JButton btCadastro = new JButton();
		btCadastro.setBounds( 240, linha, 110, 40);
		btCadastro.setText( "Cadastrar" );
		container.add(btCadastro);
		
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private JTextField getJTextField(int largura) {
		JTextField tf = new JTextField();
		tf.setBounds(130, linha, largura, 30);
		return tf;
	}

	private JLabel getJLabel(String nome) {
		this.linha = this.linha + this.espaçoLinha;
		return getJLabel(linha, nome);
	}
	
	private JLabel getJLabel(int linha, String nome) {
		this.linha = linha;
		JLabel lbl = new JLabel( nome );
		lbl.setBounds( 50, linha, 100, 30 );
		//System.out.println("k");
		return lbl;
	}
	
}
