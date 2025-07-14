import java.util.UUID;

import org.checkerframework.checker.units.qual.Area;
import org.nanotek.Base;
import org.nanotek.beans.entity.Artist;
import org.nanotek.beans.entity.Gender;

public class AreaArtistUUID {

	public static void main(String[] args) {
		UUID u1 = Base.withUUID(Area.class);
		System.out.println("UUID for Area class: " + u1);
		UUID u2 = Base.withUUID(Artist.class);
		System.out.println("UUID for Artist class: " + u2);
		UUID u3 = Base.withUUID(Gender.class);
		System.out.println("UUID for Gender class: " + u3);

	}

}
