package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by chetan on 11/8/2016.
 */
public class PreferenceHelper {
    private static Context mContext;
    public PreferenceHelper(Context context) {
        mContext = context;
    }

    private static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    private static SharedPreferences.Editor getPrefEditor() {
        return getSharedPreferences().edit();
    }

    public void setPrefrance(String key, String value) {
        SharedPreferences.Editor editor = getPrefEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPrefrance(String key) {
        return getSharedPreferences().getString(key, null);
    }


}
