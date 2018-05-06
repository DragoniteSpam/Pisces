package stuff;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import exceptions.BufferOutOfBoundsException;
import gamedata.PiscesModel;

public class VRAContainer {
	private String path;
	private HashMap<String, PiscesModel> map;
	
	public VRAContainer(String path) {
		this.path=path;
		this.map=new HashMap<String, PiscesModel>();
		try {
			load();
		} catch (BufferOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	private void load() throws BufferOutOfBoundsException {
		FileHandle fh=Gdx.files.internal(path);
		Buffer buffer=new Buffer(fh.readBytes());
		int n=(int)buffer.getDouble();
		for (int i=0; i<n; i++) {
			String name=buffer.getString();
			if (name.startsWith("C_")) {
				PiscesModel pmodel;
				if (map.containsKey(name.substring(2))) {
					pmodel=map.get(name.substring(2));
				} else {
					pmodel=new PiscesModel(name.substring(2));
					map.put(pmodel.getName(),  pmodel);
				}
				pmodel.setModelCollision(buffer.getModelCollision());
			} else {
				PiscesModel pmodel;
				if (map.containsKey(name)) {
					pmodel=map.get(name);
				} else {
					pmodel=new PiscesModel(name);
					map.put(pmodel.getName(),  pmodel);
				}
				pmodel.setModelVisible(buffer.getModel());
			}
		}
	}
	
	public void dispose() {
		map.clear();
	}
}
