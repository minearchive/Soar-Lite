package soar.management.mod;

import java.util.ArrayList;

import soar.management.mod.hud.DayCounterMod;
import soar.management.mod.hud.FPSDisplayMod;
import soar.management.mod.other.ClientSettingsMod;
import soar.management.mod.player.SprintMod;

public class ModManager {

	private ArrayList<Mod> mods = new ArrayList<Mod>();
	
	public ModManager() {
		
		//HUD
		mods.add(new DayCounterMod());
		mods.add(new FPSDisplayMod());
		
		//Performance
		
		//Player
		mods.add(new SprintMod());
		
		//Render
		
		//Other
		mods.add(new ClientSettingsMod());
	}

	public ArrayList<Mod> getMods() {
		return mods;
	}
	
	public Mod getModByName(String name) {
		return mods.stream().filter(mod -> mod.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	public Mod getModByClass(Class<?> modClass) {
		return mods.stream().filter(mod -> mod.getClass().equals(modClass)).findFirst().orElse(null);
	}
	
	public ArrayList<Mod> getModByCategory(ModCategory c) {
		ArrayList<Mod> mods = new ArrayList<Mod>();
		for (Mod m : this.mods) {
			if (m.getCategory() == c) {
				mods.add(m);
			}
		}
		return mods;
	}
}
