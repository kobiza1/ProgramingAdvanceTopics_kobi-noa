package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{


    public MazeState(int cost, Position position) {
        super(cost, position, null);
    }

    public MazeState(int cost, Position position, MazeState cameFrom) {
        super(cost, position, cameFrom);
    }

    @Override
    public Position getPosition() {
        return (Position)state;
    }

    @Override
    public MazeState getCameFrom() {
        return (MazeState)cameFrom;
    }

    public String toString(){
        return getPosition().toString();
    }

    public void add_to_cost(int cost_to_add){
        this.cost += cost_to_add;
    }

    public void set_cost(int new_cost){
        this.cost = new_cost;
    }


}
