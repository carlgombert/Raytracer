package main;

import java.util.List;

public class HitableList extends Hitable{
	
	List<Hitable> list;

	public HitableList(List<Hitable> list) {
		this.list = list;
	}
	
	public boolean hit(Ray r, float tMin, float tMax, HitRecord rec) {
		HitRecord tempRec = new HitRecord();
		boolean hitAnything = false;
		float closestSoFar = tMax;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).hit(r, tMin, closestSoFar, tempRec)) {
				hitAnything = true;
				closestSoFar = tempRec.t;
				rec = tempRec;
			}
		}
		
		return hitAnything;
	}

}
