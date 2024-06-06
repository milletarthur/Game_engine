package Model_Snake;

public class Queue extends Entity {

	private int vie;

	public int vie() {
		return this.vie;
	}

	public Queue(int x, int y, int vie) {
		this.vie = vie;
		this.x = x;
		this.y = y;
	}

	@Override
	void egg(int x, int y) {
		new Queue(x, y, this.vie);
	}

}
