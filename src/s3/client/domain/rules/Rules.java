package s3.client.domain.rules;

import java.util.LinkedList;
import java.util.List;

import s3.client.domain.GameState;

public class Rules implements Rule{
	private List<Rule> rules;
	
	public static Rule standard() {
		List<Rule> list = new LinkedList<Rule>();		
		list.add(new EatOrMove());
		list.add(new GameOver());
		list.add(new ApplesCreation());
		
		return new Rules(list);
	}
	
	@Override
	public void evaluate(GameState state) {
		for (Rule r : rules) {
			r.evaluate(state);
		}
	}

	private Rules(List<Rule> rules) {
		super();
		this.rules = rules;
	}
}
