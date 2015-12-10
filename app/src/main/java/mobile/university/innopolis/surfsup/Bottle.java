package mobile.university.innopolis.surfsup;

/**
 * Created by Kirill
 */
public class Bottle {

    public String raspId;
    public String value;

    //returns ID of string by ID of rasp to display message
    public int getID() {

    /*    switch (raspId){
            case "0": return R.string.campus_one_1st_floor;

            case "1": return R.string.campus_one_2nd_floor;

            case "2": return R.string.campus_one_3rd_floor;

            case "3": return R.string.campus_one_4th_floor;

            case "4": return R.string.campus_two_1st_floor;

            case "5": return R.string.campus_two_2nd_floor;

            case "6": return R.string.campus_two_3rd_floor;

            case "7": return R.string.campus_two_4th_floor;

            default: return 0;

        }*/
        return R.string.campus_two_4th_floor;
    }

    //returns value of water in bottle
    public int getLevel() {
        switch (value){
            case "0": return R.drawable.empty;

            case "SDDSD": return R.drawable.low;

            case "14": return R.drawable.half;

            case "3": return R.drawable.high;

            case "4": return R.drawable.full;

            default: return R.drawable.empty;
        }

    }

    public Bottle(String raspId, String value) {
        this.raspId = raspId;
        this.value = value;
    }
}
