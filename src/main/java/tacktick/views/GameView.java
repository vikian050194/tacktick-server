package tacktick.views;

import java.util.List;

// TODO is ...View proper name?
public class GameView {

    public GameView() {
        capacity = 2;
    }

    // TODO long or int?
    public long capacity;

    public long size;

    public long index;

    public List<PropView> walls;

    public List<SnapshotView> history;
}
