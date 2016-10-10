package model.animals;

public class IceBlock extends PushableBlock {


	public IceBlock(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		updateAdd();
	}
	
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}


	public void slide(String dir){
		updateRemove();
		if(dir.equals("north")){
			while(!grid.isBlocked(x,y-1) && inBounds(x,y-1)){
				y--;
			}
		}
		else if(dir.equals("south")){
			while(!grid.isBlocked(x,y+1)&& inBounds(x,y+1)){
				y++;
			}
		}
		else if(dir.equals("east")){
			while((!grid.isBlocked(x +1,y) && inBounds(x+1,y))){
				x++;
			}
		}
		else if (dir.equals("west")){
			while((!grid.isBlocked(x -1,y) && inBounds(x-1,y))){
				x--;
			}
		}
		updateAdd();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}




}
