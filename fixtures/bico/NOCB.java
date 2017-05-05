package bico;

public class NOCB {
	public void a() {
		try {
			try {
				
			} catch(Exception a) {}
			
		} catch(IOException e) {}
		catch(FileExistsException d) {}
	}
	
	public void b() {
		try {
			
		} catch(IOException | Exception | FileExistsException d) {}
	}
}