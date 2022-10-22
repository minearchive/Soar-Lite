package soar.management.mod;

import java.util.ArrayList;

import soar.management.mod.hud.CoordsMod;
import soar.management.mod.hud.DayCounterMod;
import soar.management.mod.hud.FPSDisplayMod;
import soar.management.mod.hud.PingDisplayMod;
import soar.management.mod.hud.PotionCounterMod;
import soar.management.mod.hud.ServerIPDisplayMod;
import soar.management.mod.other.ClientSettingsMod;
import soar.management.mod.player.HypixelMod;
import soar.management.mod.player.SprintMod;
import soar.management.mod.render.FullbrightMod;

public final class ModManager {

	private ArrayList<Mod> mods = new ArrayList<Mod>();

	public ModManager() {
		
		//HUD
		mods.add(new CoordsMod());
		mods.add(new DayCounterMod());
		mods.add(new FPSDisplayMod());
		mods.add(new PingDisplayMod());
		mods.add(new PotionCounterMod());
		mods.add(new ServerIPDisplayMod());

		//Performance

		//Player
		mods.add(new HypixelMod());
		mods.add(new SprintMod());

		//Render
		mods.add(new FullbrightMod());
		
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
