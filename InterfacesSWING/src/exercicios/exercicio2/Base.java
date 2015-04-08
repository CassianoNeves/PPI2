package exercicios.exercicio2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Base extends JFrame{
	
	protected int linha = 0;
	protected int coluna = 0;
	protected int espaçamento = 40;
	protected Container container;
	private int setColuna = 0;
	
	
	public Base( String titulo){
		
		container = getContentPane();
		
		JLabel lbltitulo = new JLabel( titulo );
		lbltitulo.setBounds(0, 0, 700, 100);
		lbltitulo.setHorizontalAlignment( JLabel.CENTER );
		lbltitulo.setBackground( Color.green );
		lbltitulo.setOpaque( true );
		
		Font fonte = new Font( "Arial", Font.PLAIN , 30);
		lbltitulo.setFont( fonte );
		
		container.add(lbltitulo);
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		
	}
	
	public int getLinha() {
		return linha;
	}

	public void setLinha() {
		this.linha = linha + espaçamento;
	}
	
	public void setLinha( int linha) {
		this.linha = this.linha + linha + espaçamento;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna() {
		this.coluna = 30;
	}
	
	public void setColuna( int coluna ) {
		this.coluna = coluna;
	}
	
	public Container getCampoTexto( int coluna, int linha, int txtLargura, int lblLargura, String nome) {
	
		this.linha = linha;
		this.coluna = coluna;
		
		Container container = new Container();
		container.setBounds( coluna, linha, txtLargura+lblLargura, 100 );
		
		JLabel lbl = new JLabel( nome );
		lbl.setBounds( 0, 0, lblLargura, 23 );
		
		JTextField txt = new JTextField();
		txt.setBounds( lblLargura, 0, txtLargura, 23 );
		
		container.add(txt);
		container.add(lbl);
		
		this.coluna = this.coluna + txtLargura+lblLargura+10;
		
		return container;
	}
	
	public Container getCampoTexto( int txtLargura, int lblLargura, String nome) {
		
		return ( getCampoTexto(coluna, linha, txtLargura, lblLargura, nome) );
		
	}
	
	public Container getComboBox( int coluna, int linha, int cbLargura, int lblLargura, String nome, ArrayList<String> estados ){
		
		this.linha = linha;
		this.coluna = coluna;
		
		Container container = new Container();
		container.setBounds( coluna, linha, cbLargura+lblLargura, 100 );
		
		JLabel lbl = new JLabel( nome );
		lbl.setBounds( 0, 0, lblLargura, 23 );
		
		JComboBox cb = new JComboBox();
		cb.setBounds( lblLargura, 0, cbLargura, 23 );
		
		for( String estado : estados){
			cb.addItem( estado );
		}
		
		container.add( cb );
		container.add( lbl );
		
		this.coluna = this.coluna + cbLargura+lblLargura+10;
		
		return container;
	}
	
	public Container getComboBox( int cbLargura, int lblLargura, String nome, ArrayList<String> estados ) {
		
		return ( getComboBox( coluna, linha, cbLargura, lblLargura, nome, estados ) );
		
	}
	
	public JRadioButton getRadioButton( int coluna, int linha, int rbLagura, String nome){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JRadioButton rb = new JRadioButton( nome );
		rb.setBounds( coluna, linha, rbLagura, 23 );
		
		this.coluna = this.coluna + rbLagura + 10 ;
		
		return rb;
	}
	
	public JRadioButton getRadioButton( int rbLagura, String nome ) {
		
		return ( getRadioButton( coluna, linha, rbLagura, nome ) );
		
	}
	
	public JLabel getJLabel( int coluna, int linha, int lblLargura, String nome){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JLabel lbl = new JLabel( nome );
		lbl.setBounds( coluna, linha, lblLargura, 23 );
		
		this.coluna = this.coluna + lblLargura + 10;
		
		return lbl;
	}
	
	public JLabel getJLabel( int lblLargura, String nome ) {
		
		return ( getJLabel( coluna, linha, lblLargura, nome ) );
		
	}
	
	public JCheckBox getJCheckBox( int coluna, int linha){
		
		this.linha = linha;
		this.coluna = coluna;
		
		JCheckBox cb = new JCheckBox();
		cb.setBounds( coluna, linha, 23, 23 );
		
		this.coluna = this.coluna + 23 + 10;
		
		return cb;
	}
	
	public JCheckBox getJCheckBox() {
		
		return ( getJCheckBox( coluna, linha ) );
		
	}
	
	public JTabbedPane getJTabbedPane( int coluna, int linha, int largura, int altura ) {
		
		this.linha = linha;
		this.coluna = coluna;
		
		JTabbedPane tp = new JTabbedPane();
		tp.setBounds( coluna, linha, largura, altura );
		
		return tp;
	}
	
	public JPanel getPainel( int coluna, int linha, int largura, int altura ){
		
		JPanel pnl = new JPanel();
		pnl.setBounds( coluna, linha, largura, altura );
		
		return pnl;
	}
	

	
	

}
