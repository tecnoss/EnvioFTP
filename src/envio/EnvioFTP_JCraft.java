package envio;

import java.io.IOException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class EnvioFTP_JCraft {

	private String Server;
	private int Port;
	private String User;
	private String Pwd;
	private boolean done;
	private String Protocol;
	
	public EnvioFTP_JCraft (String Server, Integer Port, String User, String Pwd, String Protocol) {
		
		this.Server = Server;
		this.Port = Port;
		this.User = User;
		this.Pwd = Pwd;
		this.Protocol = Protocol;
	}
		
	
	public boolean Enviar(String Origem, String Destino) throws IOException {
		

			
			System.out.println("INICIO");

			JSch jsch = new JSch();
	        Session session = null;
	        try {
	           session = jsch.getSession(User, Server, Port);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword(Pwd);
	            session.connect();
	            
	            Channel channel = session.openChannel(Protocol);
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel;
	            sftpChannel.put(Origem, Destino);  
	            sftpChannel.exit();
	            session.disconnect();
	        } catch (JSchException e) {
	            e.printStackTrace();  
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	 
	   			
			
		    System.out.println("FINAL");
			
            
		
		    return true;
	}
				
	
	
	public static void main(String[] args) throws IOException {
		
		try {
		
			EnvioFTP_JCraft ftp = new EnvioFTP_JCraft("ftpprd.crc.com.br", 2122, "Vale", "Tn8#H8VE", "sftp");
			ftp.Enviar("C:/development/ws_Relatorio/GeradorRelatorios/rels/NOTAFISCALPORDIGITADOR_DETALHADO_20180816.csv", "/ESTAT/NOTAFISCALPORDIGITADOR_DETALHADO_20180816.csv");
		
	    } catch (IOException ex) {
	        System.out.println("Error: " + ex.getMessage());
	        ex.printStackTrace();
	        
	    }				
	}		
}
