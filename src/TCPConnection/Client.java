package TCPConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			

			
			
			Scanner scanner = new Scanner(System.in);
			
			
			
			while(true) {
				String msg = "";
				double timeStart = 0;
				double timeFinal = 0;
				
				System.out.println("DESCONECTADO");
				String line = scanner.nextLine();
				
				
				Socket socket = new Socket("172.30.165.69", 6000);
				System.out.println("CONECTADO");
				
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				if(line.equals("remoteIpconfig")) {
					bw.write(line+"\n");
					bw.flush();
				}else if(line.equals("interface")) {
					bw.write(line+"\n");
					bw.flush();
					
					

						String ans = reader.readLine();
						
							System.out.println(ans);
							
					
				}else if(line.equals("whatTimeIsIt")) {
					bw.write(line+"\n");
					bw.flush();
					
					String ans = reader.readLine();
					System.out.println(ans);
				}else if(line=="RTT") {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					msg = scanner.nextLine();
					
					timeStart = System.currentTimeMillis();
					bw.write(msg+"\n");
					bw.flush();
					
					System.out.println("Esperando mensaje...");
					String answer = reader.readLine();
					
					timeFinal = System.currentTimeMillis();
					
					System.out.println(answer+" TIME: "+ (timeFinal-timeStart));
					
				}else if(line=="speed"){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					msg = scanner.nextLine();
					timeStart = System.currentTimeMillis();
					bw.write(msg+"\n");
					bw.flush();
					
					System.out.println("Esperando mensaje...");
					String answer = reader.readLine();
					
					timeFinal = System.currentTimeMillis();
					
					System.out.println(answer+" TIME: "+ (timeFinal-timeStart));
					
				}else {
					System.out.println("Entrada erronea");
				}
				
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
