package pedergb.vors;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Globals {

    // ------------ get stored data ------------- \\
    private SharedPreferences sharedPref;
    private static String PREF_NAME = "data";

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static ArrayList<String> getQuestions(Context context) {
        String listAsString = getPrefs(context).getString("questions", "NOT FOUND");
        ArrayList<String> result = new ArrayList<>(Arrays.asList(listAsString.split("§§§")));
        return result;

    }
    public static void setQuestions(Context context, ArrayList<String> questList) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        String listAsString = "";
        for (String quest : questList){
            listAsString += quest + "§§§";
        }
        listAsString= listAsString.substring(0, listAsString.length() - 3);
        editor.putString("questions", listAsString);
        editor.apply();
    }

    /*public static void test(Context ctx, ArrayList<String> questList) {
        Set<String> questSet = new HashSet<>(questList);
        ctx.getSharedPreferences("questions", 0).edit().clear().apply();
        SharedPreferences sharedpreferences = ctx.getSharedPreferences("questions", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putStringSet("set", questSet);
        editor.apply();
    }*/

    // ------------------------------------------- \\

    public static boolean allwaysOnDisplay = false;
    public static int shotSound = R.raw.foghorn;
    public static int QUEST_CLICKED = 1;

    public static List<String> playerNames = new ArrayList<>();

    public static ArrayList<String> localQuestions = new ArrayList<>();

    public static ArrayList<String> defaultQuestions = new ArrayList<>(Arrays.asList(
            "Kjør en runde 'jeg har aldri'",
            "### er tørst og får 5 slurker",
            "Håndbak mellom ### og ###, taperen drikker 5 slurker",
            "Nevn forelesere på Gløs, taperen drikker 3 slurker",
            "Regel: Alle må kun drikke med svak hånd, straff 5 slurker",
            "### må fortelle om sin siste hookup",
            "Nevn utesteder i Trondheim, taperen drikker 5 slurker",
            "### holder på å sovne, folka ved siden må riste h*n våken",
            "Waterfall, personen med mobilen starter",
            "Pekeleken en runde, 5 slurker"
    ));

}
