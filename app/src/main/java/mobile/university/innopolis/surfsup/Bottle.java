package mobile.university.innopolis.surfsup;

/**
 * Created by Kirill on 10.12.2015.
 */
public class Bottle {

    private int ID;
    private int level;

    public int getID() {
        switch (ID){
            case 0: return R.string.campus_one_1st_floor;

            case 1: return R.string.campus_one_2nd_floor;

            case 2: return R.string.campus_one_3rd_floor;

            case 3: return R.string.campus_one_4th_floor;

            case 4: return R.string.campus_two_1st_floor;

            case 5: return R.string.campus_two_2nd_floor;

            case 6: return R.string.campus_two_3rd_floor;

            case 7: return R.string.campus_two_4th_floor;

            default: return 0;

        }
    }

    public int getLevel() {
        switch (level){
            case 0: return R.drawable.empty;

            case 1: return R.drawable.low;

            case 14: return R.drawable.half;

            case 3: return R.drawable.high;

            case 4: return R.drawable.full;

            default: return 0;
        }

    }

    public Bottle(int ID, int level) {
        this.ID = ID;
        this.level = level;
    }
}
