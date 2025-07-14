import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.UUID;


public class Atest {

	public static void main(String[] args) {
		try { 
			UUID uuid = null;
			ByteArrayOutputStream bao = new ByteArrayOutputStream();
			ObjectOutputStream oos =  new ObjectOutputStream(bao);
			oos.writeObject("i".toCharArray());
			oos.flush();
			uuid = UUID.nameUUIDFromBytes(bao.toByteArray());
			oos.close();
			System.out.println(uuid);
		}catch (Exception ex) { 
			ex.printStackTrace();
		}
	}

}
