package myserver;

public class Application {

	public static void main(String[] args) {
		int port = 9999;
		String rootPath = "samplewebroot";
		int workerThread = 10;
		
		
		System.out.println("Usage: java -cp MultiThreadedWebServer-0.0.1-SNAPSHOT.jar myserver.Application  <root path> <port> <threads limit>\n");
		
		if(args.length==1){
			rootPath=args[0];
		}
		else if(args.length==2){
			rootPath=args[0];
			port = Integer.parseInt(args[1]);
		}
		else if(args.length==3){
			rootPath = args[0];
			port = Integer.parseInt(args[1]);
			workerThread = Integer.parseInt(args[2]);
		}
		
		new Thread(new Server(port, rootPath, workerThread)).start();
	}
}
