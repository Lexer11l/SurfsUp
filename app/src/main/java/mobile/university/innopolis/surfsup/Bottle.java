package mobile.university.innopolis.surfsup;

/**
 * Created by Kirill
 */
public class Bottle {

    public String raspId;
    public String value;

    //returns ID of string by ID of rasp to display message
    public int getID() {

        switch (raspId.toLowerCase()){
            case "c1f1": return R.string.campus_one_1st_floor;

            case "c1f2": return R.string.campus_one_2nd_floor;

            case "c1f3": return R.string.campus_one_3rd_floor;

            case "c1f4": return R.string.campus_one_4th_floor;

            case "c2f1": return R.string.campus_two_1st_floor;

            case "c2f2": return R.string.campus_two_2nd_floor;

            case "c2f3": return R.string.campus_two_3rd_floor;

            case "c2f4": return R.string.campus_two_4th_floor;

            default: return R.string.no_such_id;

        }
    }

    //returns value of water in bottle
    public int getLevel() {
        switch (value.toLowerCase()){
            case "empty": return R.drawable.empty;

            case "low": return R.drawable.low;

            case "half": return R.drawable.half;

            case "high": return R.drawable.high;

            case "full": return R.drawable.full;

            default: return R.drawable.empty;
        }

    }

    public Bottle(String raspId, String value) {
        this.raspId = raspId;
        this.value = value;
    }
}
