package max.waitzman.oopshelpme.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;

import max.waitzman.oopshelpme.ApplicationMy;
import max.waitzman.oopshelpme.R;


/*
 * Copyright 2015 Dor Sakal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class LogUtil {

	//TODO: use your own TAG here
	private static final String TAG = ApplicationMy.getAppContext().getString(R.string.app_name);

	/*********************************
	 * public static methods
	 *********************************/

	public static void v(String text) {
		doLog(Log.VERBOSE, text);
	}

	public static void d(String text) {
		doLog(Log.DEBUG, text);
	}

	public static void i(String text) {
		doLog(Log.INFO, text);
	}

	public static void w(String text) {
		doLog(Log.WARN, text);
	}

	public static void e(String text) {
		doLog(Log.ERROR, text);
	}

	public static void printSHA(){
		try {
			PackageInfo info = ApplicationMy.getAppContext().getPackageManager().getPackageInfo("max.waitzman.oopshelpme", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}
	}

	/*********************************
	 * private static methods
	 *********************************/

	private static void doLog(int logLevel, String logText) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

		//take stackTrace element at index 4 because:
		//0: VMStack.getThreadStackTrace(Native Method)
		//1: java.lang.Thread.getStackTrace
		//2: LogUtil -> doLog method (this method)
		//3: LogUtil -> log method
		//4: this is the calling method!
		if (stackTrace != null && stackTrace.length > 4) {

			StackTraceElement element = stackTrace[4];

			String fullClassName = element.getClassName();
			String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

			//add class and method data to logText
			logText = MessageFormat.format("T:{0} | {1} , {2}() | {3}", Thread.currentThread().getId(), simpleClassName, element.getMethodName(), logText);
		}

		Log.println(logLevel, TAG, logText);
	}

}