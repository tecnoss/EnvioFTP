package envio;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.ftp.FTPSServerSocketFactory;


public class EnvioFTP_Commonsnet {

	private String Server;
	private int Port;
	private String User;
	private String Pwd;
	private boolean done;
	private String Protocol;
	
	public EnvioFTP_Commonsnet (String Server, Integer Port, String User, String Pwd, String Protocol) {
		
		this.Server = Server;
		this.Port = Port;
		this.User = User;
		this.Pwd = Pwd;
		this.Protocol = Protocol;
	}
	
	
	public boolean Enviar(String Path, String Filename) throws IOException {
		
		try {
			
			System.out.println("INICIO");
			
			FTPSClient f = new FTPSClient();
			
		    //FTPClient f = new FTPClient();
			FTPClientConfig config = new FTPClientConfig();			
			f.configure(config);		    
		    

		    f.connect(Server);
		    f.login(User, Pwd);

		    int reply = f.getReplyCode();
		    
		    f.enterLocalPassiveMode();
			f.setFileType(f.BINARY_FILE_TYPE);
			f.changeWorkingDirectory("ESTAT");		    
		    
		    FTPFile[] files = f.listFiles();
		    files = f.listDirectories();
		    
			for (FTPFile ftpFile : files) {
				System.out.println("Nome: " + ftpFile.getName());
				System.out.println("Tamanho: " + ftpFile.getSize());
				System.out.println("Data: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
						.format(ftpFile.getTimestamp().getTime()));
			}		    
		    
		    System.out.println("FINAL");
			
            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            
	    } finally {
	        
	    }		
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		EnvioFTP_Commonsnet ftp = new EnvioFTP_Commonsnet("ftpprd.crc.com.br", 2122, "Vale", "Tn8#H8VE", "TLS");
		ftp.Enviar("C:\\development\\ws_Relatorio\\GeradorRelatorios\\rels\\", "NOTAFISCALPORDIGITADOR_DETALHADO_20180816.csv");
		
	}
}
