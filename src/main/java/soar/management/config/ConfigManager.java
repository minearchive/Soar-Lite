package soar.management.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import soar.Soar;
import soar.management.mod.Mod;
import soar.management.setting.Setting;

public final class ConfigManager {

	private final File configFile;

	public ConfigManager() {

		configFile = new File(Soar.INSTANCE.getSoarDir(), "Config.txt");

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
		try (BufferedReader reader = new BufferedReader(new FileReader(this.configFile))) {
			String s;
			while((s = reader.readLine()) != null) {
				String[] args = s.split(":");

				if (s.toLowerCase().startsWith("mod:")) {
					Mod m = Soar.INSTANCE.modManager.getModByName(args[1]);
					if (m != null) {
						m.setToggled(Boolean.parseBoolean(args[2]));
					}
				}

				if (s.toLowerCase().startsWith("set:")) {
					Mod m = Soar.INSTANCE.modManager.getModByName(args[2]);
					if (m != null) {
						Setting set = Soar.INSTANCE.settingsManager.getSettingByName(m, args[1]);
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
					Mod m = Soar.INSTANCE.modManager.getModByName(args[1]);
					if (m != null) {
						m.setX(Integer.parseInt(args[2]));
						m.setY(Integer.parseInt(args[3]));
						m.setWidth(Integer.parseInt(args[4]));
						m.setHeight(Integer.parseInt(args[5]));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Save Soar Lite Config
	 */
	public void save() {
		try(PrintWriter writer = new PrintWriter(this.configFile)) {
			for(Mod m : Soar.INSTANCE.modManager.getMods()) {
				if(!m.isHide()) {
					writer.println("MOD:" + m.getName() + ":" + m.isToggled());
					writer.println("POS:" + m.getName() + ":" + m.getX() + ":" + m.getY() + ":" + m.getWidth() + ":" + m.getHeight());
				}
			}

			for(Setting set : Soar.INSTANCE.settingsManager.getSettings()) {
				if (set.isCheck()) {
					writer.println("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValBoolean());
				}
				if (set.isCombo()) {
					writer.println("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
				}
				if (set.isSlider()) {
					writer.println("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
