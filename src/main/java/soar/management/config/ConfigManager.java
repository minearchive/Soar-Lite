package soar.management.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import soar.Soar;
import soar.management.mod.Mod;
import soar.management.setting.Setting;

public class ConfigManager {

	private File configFile;
	
	public ConfigManager() {
		
		configFile = new File(Soar.instance.getSoarDir(), "Config.txt");
		
		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.load();
	}
	
	/*
	 * Load Soar Lite Config
	 */
	public void load() {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.configFile));
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String s : lines) {
			
			String[] args = s.split(":");
			
			if (s.toLowerCase().startsWith("mod:")) {
				Mod m = Soar.instance.modManager.getModByName(args[1]);
				if (m != null) {
					m.setToggled(Boolean.parseBoolean(args[2]));
				}
			}
			
			if (s.toLowerCase().startsWith("set:")) {
				Mod m = Soar.instance.modManager.getModByName(args[2]);
				if (m != null) {
					Setting set = Soar.instance.settingsManager.getSettingByName(m, args[1]);
					if (set != null) {
						if (set.isCheck()) {
							set.setValBoolean(Boolean.parseBoolean(args[3]));
						}
						if (set.isCombo()) {
							set.setValString(args[3]);
						}
						if (set.isSlider()) {
							set.setValDouble(Double.parseDouble(args[3]));
						}
					}
				}
			}
			
			if (s.toLowerCase().startsWith("pos:")) {
				Mod m = Soar.instance.modManager.getModByName(args[1]);
				if (m != null) {
					m.setX(Integer.parseInt(args[2]));
					m.setY(Integer.parseInt(args[3]));
					m.setWidth(Integer.parseInt(args[4]));
					m.setHeight(Integer.parseInt(args[5]));
				}
			}
		}
	}
	
	/*
	 * Save Soar Lite Config
	 */
	public void save() {
		
		ArrayList<String> toSave = new ArrayList<String>();
		
		for(Mod m : Soar.instance.modManager.getMods()) {
			if(!m.isHide()) {
				toSave.add("MOD:" + m.getName() + ":" + m.isToggled());
				toSave.add("POS:" + m.getName() + ":" + m.getX() + ":" + m.getY() + ":" + m.getWidth() + ":" + m.getHeight());
			}
		}
		
		for(Setting set : Soar.instance.settingsManager.getSettings()) {
			
			if (set.isCheck()) {
				toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValBoolean());
			}
			if (set.isCombo()) {
				toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
			}
			if (set.isSlider()) {
				toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
			}
		}
		
		try {
			PrintWriter pw = new PrintWriter(this.configFile);
			for (String str : toSave) {
				pw.println(str);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
