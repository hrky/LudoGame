package seng271.group8.ludo.model;

import java.beans.PropertyChangeEvent;
import java.util.Map;

import seng271.group8.ludo.Die;
import seng271.group8.ludo.events.GameEvent;
import seng271.group8.ludo.graphics.AnimationBuilder;
import seng271.group8.ludo.graphics.Animator;
import seng271.group8.ludo.graphics.DieGraphic;
import seng271.group8.ludo.graphics.IAnimatable;

public class DieChangeListener extends AbstractChangeListener {

	public DieChangeListener(Animator animator,
			Map<Class<? extends AnimationBuilder>, AnimationBuilder> builders) {
		super(animator, builders);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		 String propertyName = evt.getPropertyName();
	     IAnimatable ani = null;
	     GameEvent ge = null;
	     Die die = (Die)evt.getSource();
	     
	     if (Die.ROLL.equals(propertyName)) {
	    	 DieGraphic g = (DieGraphic)die.getRendering();
	    	 g.setDirty(true);
	    	 g.setRoll((Integer)evt.getNewValue());
	     }
	       
	}

}
