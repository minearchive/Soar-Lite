package soar.management.setting;

import java.util.*;

import soar.Soar;
import soar.management.mod.Mod;

public final class SettingsManager {

	private ArrayList<Setting> settings;

	public SettingsManager(){
		this.settings = new ArrayList<>();
	}

	public void addSetting(Setting in){
		this.settings.add(in);
	}

	public ArrayList<Setting> getSettingByMod(Mod mod){
		ArrayList<Setting> out = new ArrayList<>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		// I don't want to do null checks
//		if(out.isEmpty()){
//			return null;
//		}
		Collections.sort(out);
		return out;
	}

	public Setting getSettingByNameForClickGUI(String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)){
				return set;
			}
		}
		return null;
	}

	public Setting getSettingByName(Mod mod, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod){
				return set;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Setting getSettingByClass(Class<?> modClass, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod().equals(Soar.INSTANCE.modManager.getModByClass(modClass))){
				return set;
			}
		}
		return null;
	}

	public ArrayList<Setting> getSettings() {
		return settings;
	}
}
