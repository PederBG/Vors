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
    public static String[] QUESTION_TEMPLATES = {"questionsNOR", "questionsENG", "questionsNTNU", "questionCUST"};
    public static int activeQuest = 0;

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static ArrayList<String> getQuestions(Context context) {
        String listAsString = getPrefs(context).getString(QUESTION_TEMPLATES[activeQuest], "NOT FOUND");
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
        editor.putString(QUESTION_TEMPLATES[activeQuest], listAsString);
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


    public static ArrayList<String> localQuestionsNOR = new ArrayList<>();
    public static ArrayList<String> localQuestionsENG = new ArrayList<>();
    public static ArrayList<String> localQuestionsNTNU = new ArrayList<>();
    public static ArrayList<String> localQuestionsCUST = new ArrayList<>();

    public static ArrayList[] LOCAL_TEMPLATES = {localQuestionsNOR, localQuestionsENG, localQuestionsNTNU, localQuestionsCUST};

    //public static ArrayList<String> localQuestionsCUSTOM = new ArrayList<>();


    public static ArrayList<String> defaultQuestionsNOR = new ArrayList<>(Arrays.asList(
            "Kjør en runde 'jeg har aldri'",
            "### er tørst og får 5 slurker",
            "Håndbak mellom ### og ###, taperen drikker 5 slurker",
            "Regel: Alle må kun drikke med svak hånd, straff 5 slurker",
            "### må fortelle om sin siste hookup",
            "Nevn utesteder i Trondheim, taperen drikker 5 slurker",
            "### holder på å sovne, folka ved siden må riste h*n våken",
            "Waterfall, personen med mobilen starter",
            "Pekeleken en runde, 5 slurker"
    ));

    public static ArrayList<String> defaultQuestionsENG = new ArrayList<>(Arrays.asList(
            "Dring 5 sips"
    ));

    public static ArrayList<String> defaultQuestionsNTNU = new ArrayList<>(Arrays.asList(
            "Nevn forelesere på Gløs, taperen drikker 3 slurker"
    ));

    public static ArrayList[] DEFAULT_TEMPLATES = {defaultQuestionsNOR, defaultQuestionsENG, defaultQuestionsNTNU};


}
