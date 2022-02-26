package TCPConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class Server {
	
	public static void main(String[] args) {
		
		ServerSocket server;
		try {
			server = new ServerSocket(6000);
			while(true) {
				System.out.println("Esperando...");
				Socket socket  = server.accept();
				System.out.println("Cliente conectado");
				
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
				
				Scanner scanner = new Scanner(System.in);
				
				
				while(true) {
					String msg = "";
					System.out.println("Esperando mensaje...");
					String line = reader.readLine();
					System.out.println(line);
					
					InetAddress myAddress = InetAddress.getLocalHost();
					if(line=="remoteIpconfig") { // -------------------IP-------------------
						
						bw.write(myAddress.getHostName()+"\n");
						bw.flush();
						//System.out.println(myAddress.getHostName());
						//System.out.println(myAddress.getHostAddress());
						break;
					}else if(line.equals("interface")) { // --------------INTERFACES --------------
						
						String intt = "";
						
						Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

						while(interfaces.hasMoreElements()){			
						
							NetworkInterface netN = interfaces.nextElement();


							if(netN.isUp()){
								
								intt += netN.getName()+"\n";
								//bw.write(netN.getName()+"\n");
								//bw.flush();
								//System.out.println(netN.getName());
								
								if(netN.getHardwareAddress()!= null){	
									
									//Interfaces
									String mac = new BigInteger(1, netN.getHardwareAddress()).toString(16);
									//intt += netN.getHardwareAddress()+"\n";
									//bw.write(netN.getHardwareAddress()+"\n");
									//bw.flush();
									
									//intt += mac+"\n";
									//bw.write(mac+"\n");
									//bw.flush();
									//System.out.println(netN.getHardwareAddress());
									//System.out.println("<<<"+mac);

									//Direcciones IP
									List<InterfaceAddress> list = netN.getInterfaceAddresses();

									for(int i=0;i<list.size();i++){
										//intt += list.get(i)+"\n";
										//bw.write(String.valueOf(list.get(i)));
										//bw.flush();
										
										//System.out.println(">>>"+list.get(i));
									}
								}
							}
						}
						//PREFIJO DE SUBRED
						NetworkInterface net = NetworkInterface.getByInetAddress(myAddress);
						//intt += "Network prefix: "+ net.getInterfaceAddresses().get(1).getNetworkPrefixLength()+"\n";
						//bw.write("Network prefix: "+ net.getInterfaceAddresses().get(1).getNetworkPrefixLength()+"\n");
						System.out.println(intt+"\n");
						bw.write(intt+"\n");
						bw.flush();
						//System.out.println("Network prefix: "+ net.getInterfaceAddresses().get(1).getNetworkPrefixLength());
						break;
					}else if(line.equals("whatTimeIsIt")) {
						LocalDate date = LocalDate.now();
						bw.write(String.valueOf(date)+"\n");
						bw.flush();
						break;
					}else if(line.equals("RTT")) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						msg = reader.readLine();
						System.out.println(msg);
						
						bw.write(msg+"\n");
						bw.flush();
						
						break;
					}else if(line.equals("speed")) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						msg = reader.readLine();
						System.out.println(msg);
						
						bw.write(msg+"\n");
						bw.flush();
						break;
					}else {
						break;
					}
				}	
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
		
