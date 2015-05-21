package br.feevale.base64;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class TestImageIO01 {

    public static void main(String[] args) {
        try {

            Image in = new ImageIcon( "/home/cassiano/Imagens/perfil.jpg" ).getImage().getScaledInstance( 100, 100, Image.SCALE_DEFAULT );
            
            JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(in)), "Yeah", JOptionPane.INFORMATION_MESSAGE);

            BufferedImage out = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = out.createGraphics();
            g2d.drawImage(in, 0, 0, null);
            g2d.dispose();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	
    	    ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
            

            ImageIO.write(out, "jpg", ios);
            
            byte[] bytes = baos.toByteArray();
            
            System.out.println(bytes);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}