package teste;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.spi.ImageInputStreamSpi;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.codec.binary.Base64;

/**
 * @desc Image manipulation - Conversion
 * 
 * @filename ImageManipulation.java
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @copyright myjeeva.com
 */
public class ImageManipulation {

//	public static void main(String[] args) {
//
//		File file = new File("/home/cassiano/Imagens/perfil.jpg");
//
//		try {
//			// Reading a Image file from file system
//			FileInputStream imageInFile = new FileInputStream(file);
//			byte imageData[] = new byte[(int) file.length()];
//			imageInFile.read(imageData);
//
//			// Converting Image byte array into Base64 String
////			String imageDataString = encodeImage(imageData);
//
//			// Converting a Base64 String into Image byte array
//			byte[] imageByteArray = decodeImage(imageDataString);
//
//			// Write a image byte array into file system
//			FileOutputStream imageOutFile = new FileOutputStream(
//					"/home/cassiano/Imagens/perfilll.jpg");
//
//			imageOutFile.write(imageByteArray);
//
//			imageInFile.close();
//			imageOutFile.close();
//
//			System.out.println("Image Successfully Manipulated!");
//		} catch (FileNotFoundException e) {
//			System.out.println("Image not found" + e);
//		} catch (IOException ioe) {
//			System.out.println("Exception while reading the Image " + ioe);
//		}
//	}
	
	

	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 * @throws IOException 
	 */
	public static String encodeImage( JLabel label, String caminho ) throws IOException {
		
		File file = new File( caminho );
		
		// Reading a Image file from file system
		FileInputStream imageInFile = new FileInputStream(file);
		byte imageData[] = new byte[(int) file.length()];
		imageInFile.read(imageData);

		imageInFile.close();
		
		return Base64.encodeBase64URLSafeString(imageData);
	}

	/**
	 * Decodes the base64 string into byte array
	 *
	 * @param imageDataString - a {@link java.lang.String}
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {
		return Base64.decodeBase64(imageDataString);
	}
	
}