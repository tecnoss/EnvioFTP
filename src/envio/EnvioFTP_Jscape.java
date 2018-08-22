package envio;

import java.io.IOException;
import com.jscape.inet.sftp.*;
import com.jscape.inet.sftp.events.*;
import com.jscape.inet.ssh.util.SshParameters;
import java.io.*;
import java.util.Enumeration;

public class EnvioFTP_Jscape extends SftpAdapter {

	private String Server;
	private int Port;
	private String User;
	private String Pwd;
	private boolean done;
	private String Protocol;
	
	public EnvioFTP_Jscape (String Server, Integer Port, String User, String Pwd, String Protocol) {
		
		this.Server = Server;
		this.Port = Port;
		this.User = User;
		this.Pwd = Pwd;
		this.Protocol = Protocol;
	}
		
	
	public boolean Enviar(String Path, String Filename) throws SftpException {
		
		
			System.out.println("INICIO");
			
			// Create an SschParameters object to give to the sftp constructor.
			// For this example, we also set up th filter as a String.
			String hostname = "ftpprd.crc.com.br";
			String username = "Vale";
			String password = "Tn8#H8VE";
			String filter = ".*\\.png";
			SshParameters params = new SshParameters(hostname,username,password);
			//params.setPort(2122);
			    	
			// create new Sftp object
			Sftp ftp = new Sftp(params);
	        
	        // establish connection, download the PNG files and "hang up".
	        ftp.connect();        
	        ftp.mdownload(filter);
	        ftp.disconnect();
	        
		    System.out.println("FINAL");			
            
	
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		try {
		
			EnvioFTP_Jscape ftp = new EnvioFTP_Jscape("ftpprd.crc.com.br", 2122, "Vale", "Tn8#H8VE", "TLS");
			ftp.Enviar("C:\\development\\ws_Relatorio\\GeradorRelatorios\\rels\\", "NOTAFISCALPORDIGITADOR_DETALHADO_20180816.csv");
		
	    } catch (IOException ex) {
	        System.out.println("Error: " + ex.getMessage());
	        ex.printStackTrace();
	        
	    }				
	}	
}
