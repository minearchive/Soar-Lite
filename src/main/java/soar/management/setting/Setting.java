package soar.management.setting;

import java.awt.*;
import java.util.ArrayList;

import soar.management.mod.Mod;

public class Setting {
	
	private final String name;
	private final Mod parent;
	private final String mode;
	
	private String sval;
	private ArrayList<String> options;
	
	private boolean bval;
	
	private double dval;
	private double min;
	private double max;
	private boolean onlyint = false;

	// For color setting
	private Color cval;
	

	public Setting(String name, Mod parent, String sval, ArrayList<String> options){
		this.name = name;
		this.parent = parent;
		this.sval = sval;
		this.options = options;
		this.mode = "Combo";
	}
	
	public Setting(String name, Mod parent, boolean bval){
		this.name = name;
		this.parent = parent;
		this.bval = bval;
		this.mode = "Check";
	}
	public Setting(String name, Mod parent, Color cval){
		this.name = name;
		this.parent = parent;
		this.cval = cval;
		this.mode = "Color";
	}
	
	public Setting(String name, Mod parent, double dval, double min, double max, boolean onlyint){
		this.name = name;
		this.parent = parent;
		this.dval = dval;
		this.min = min;
		this.max = max;
		this.onlyint = onlyint;
		this.mode = "Slider";
	}
	
	public String getName(){
		return name;
	}
	
	public Mod getParentMod(){
		return parent;
	}
	
	public String getValString(){
		return this.sval;
	}
	
	public void setValString(String in){
		this.sval = in;
	}
	
	public ArrayList<String> getOptions(){
		return this.options;
	}
	
	public boolean getValBoolean(){
		return this.bval;
	}
	
	public void setValBoolean(boolean in){
		this.bval = in;
	}
	
	public double getValDouble(){
		if(this.onlyint){
			this.dval = (int)dval;
		}
		return this.dval;
	}
	
	public float getValFloat(){
		if(this.onlyint){
			this.dval = (int)dval;
		}
		return (float) this.dval;
	}
	
	public int getValInt(){
		if(this.onlyint){
			this.dval = (int)dval;
		}
		return (int) this.dval;
	}

	public void setValDouble(double in){
		this.dval = in;
	}
	
	public double getMin(){
		return this.min;
	}
	
	public double getMax(){
		return this.max;
	}
	
	public boolean isCombo(){
		return this.mode.equalsIgnoreCase("Combo");
	}
	
	public boolean isCheck(){
		return this.mode.equalsIgnoreCase("Check");
	}
	
	public boolean isSlider(){
		return this.mode.equalsIgnoreCase("Slider");
	}
	public boolean isColor(){
		return this.mode.equalsIgnoreCase("Color");
	}
	
	public boolean onlyInt(){
		return this.onlyint;
	}

	public String getMode() {
        return mode;
    }
}