package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class rewind extends Activity implements B4AActivity{
	public static rewind mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.rewind");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (rewind).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.rewind");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.rewind", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (rewind) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (rewind) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return rewind.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (rewind) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (rewind) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            rewind mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (rewind) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static anywheresoftware.b4a.objects.Timer _tmr = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlbackground = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlcontent = null;
public static int _currentpanelindex = 0;
public static int _progress = 0;
public anywheresoftware.b4a.objects.collections.List _panels = null;
public static float _startx = 0f;
public static float _originalx = 0f;
public static boolean _canmove = false;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_7 = null;
public b4a.example.main _main = null;
public b4a.example.m_funciones _m_funciones = null;
public b4a.example.starter _starter = null;
public b4a.example.httputils2service _httputils2service = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.rewind parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
b4a.example.rewind parent;
boolean _firsttime;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 34;BA.debugLine="m_Funciones.FullscreenActivity(Activity)";
parent.mostCurrent._m_funciones._fullscreenactivity /*String*/ (mostCurrent.activityBA,parent.mostCurrent._activity);
 //BA.debugLineNum = 35;BA.debugLine="Sleep(1)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (1));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"Background.bal\")";
parent.mostCurrent._activity.LoadLayout("Background.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 38;BA.debugLine="pnlBackground.Height = Activity.Height + m_Funcio";
parent.mostCurrent._pnlbackground.setHeight((int) (parent.mostCurrent._activity.getHeight()+parent.mostCurrent._m_funciones._getstatusbarheight /*int*/ (mostCurrent.activityBA)+parent.mostCurrent._m_funciones._getnavigationbarheight /*int*/ (mostCurrent.activityBA)));
 //BA.debugLineNum = 39;BA.debugLine="pnlContent.Height = pnlBackground.Height";
parent.mostCurrent._pnlcontent.setHeight(parent.mostCurrent._pnlbackground.getHeight());
 //BA.debugLineNum = 41;BA.debugLine="changeScreen(currentPanelIndex)";
_changescreen(parent._currentpanelindex);
 //BA.debugLineNum = 42;BA.debugLine="CreateStatusIndicators(Activity)";
_createstatusindicators(parent.mostCurrent._activity);
 //BA.debugLineNum = 43;BA.debugLine="m_Funciones.AutoEscalar(Activity)";
parent.mostCurrent._m_funciones._autoescalar /*String*/ (mostCurrent.activityBA,parent.mostCurrent._activity);
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _animatelefttoright(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
 //BA.debugLineNum = 310;BA.debugLine="Sub AnimateLeftToRight(view As View)";
 //BA.debugLineNum = 311;BA.debugLine="view.SetLayoutAnimated(30000, Activity.Width + vi";
_view.SetLayoutAnimated((int) (30000),(int) (mostCurrent._activity.getWidth()+_view.getWidth()),_view.getTop(),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static String  _animaterighttoleft(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Sub AnimateRightToLeft(view As View)";
 //BA.debugLineNum = 315;BA.debugLine="view.SetLayoutAnimated(30000, -view.Width, view.T";
_view.SetLayoutAnimated((int) (30000),(int) (-_view.getWidth()),_view.getTop(),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public static String  _animateslidein(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
 //BA.debugLineNum = 267;BA.debugLine="Sub AnimateSlideIn(view As View)";
 //BA.debugLineNum = 268;BA.debugLine="view.Top = Activity.Height + view.Height";
_view.setTop((int) (mostCurrent._activity.getHeight()+_view.getHeight()));
 //BA.debugLineNum = 269;BA.debugLine="view.SetLayoutAnimated(800, view.Left, Activity.H";
_view.SetLayoutAnimated((int) (800),_view.getLeft(),(int) (mostCurrent._activity.getHeight()-_view.getHeight()),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public static String  _animateslideinfromleft(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
float _originviewx = 0f;
 //BA.debugLineNum = 286;BA.debugLine="Sub AnimateSlideInFromLeft(view As View)";
 //BA.debugLineNum = 287;BA.debugLine="Dim originViewX As Float";
_originviewx = 0f;
 //BA.debugLineNum = 288;BA.debugLine="originViewX = view.Left";
_originviewx = (float) (_view.getLeft());
 //BA.debugLineNum = 289;BA.debugLine="view.Left = -view.Width";
_view.setLeft((int) (-_view.getWidth()));
 //BA.debugLineNum = 290;BA.debugLine="view.SetLayoutAnimated(800, originViewX, view.Top";
_view.SetLayoutAnimated((int) (800),(int) (_originviewx),_view.getTop(),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
return "";
}
public static String  _animateslideinfromright(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
float _originviewx = 0f;
 //BA.debugLineNum = 279;BA.debugLine="Sub AnimateSlideInFromRight(view As View)";
 //BA.debugLineNum = 280;BA.debugLine="Dim originViewX As Float";
_originviewx = 0f;
 //BA.debugLineNum = 281;BA.debugLine="originViewX = view.Left";
_originviewx = (float) (_view.getLeft());
 //BA.debugLineNum = 282;BA.debugLine="view.Left = Activity.Width";
_view.setLeft(mostCurrent._activity.getWidth());
 //BA.debugLineNum = 283;BA.debugLine="view.SetLayoutAnimated(800, originViewX, view.Top";
_view.SetLayoutAnimated((int) (800),(int) (_originviewx),_view.getTop(),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
return "";
}
public static String  _animateslideinfromtop(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
float _originviewy = 0f;
 //BA.debugLineNum = 272;BA.debugLine="Sub AnimateSlideInFromTop(view As View)";
 //BA.debugLineNum = 273;BA.debugLine="Dim originViewY As Float";
_originviewy = 0f;
 //BA.debugLineNum = 274;BA.debugLine="originViewY = view.Top";
_originviewy = (float) (_view.getTop());
 //BA.debugLineNum = 275;BA.debugLine="view.Top = -view.Height";
_view.setTop((int) (-_view.getHeight()));
 //BA.debugLineNum = 276;BA.debugLine="view.SetLayoutAnimated(800, view.Left, originView";
_view.SetLayoutAnimated((int) (800),_view.getLeft(),(int) (_originviewy),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 277;BA.debugLine="End Sub";
return "";
}
public static void  _animateupdown(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
ResumableSub_AnimateUpDown rsub = new ResumableSub_AnimateUpDown(null,_view);
rsub.resume(processBA, null);
}
public static class ResumableSub_AnimateUpDown extends BA.ResumableSub {
public ResumableSub_AnimateUpDown(b4a.example.rewind parent,anywheresoftware.b4a.objects.ConcreteViewWrapper _view) {
this.parent = parent;
this._view = _view;
}
b4a.example.rewind parent;
anywheresoftware.b4a.objects.ConcreteViewWrapper _view;
float _originviewy = 0f;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 294;BA.debugLine="Dim originViewY As Float";
_originviewy = 0f;
 //BA.debugLineNum = 295;BA.debugLine="originViewY = view.Top";
_originviewy = (float) (_view.getTop());
 //BA.debugLineNum = 296;BA.debugLine="Do While canMove = True";
if (true) break;

case 1:
//do while
this.state = 12;
while (parent._canmove==anywheresoftware.b4a.keywords.Common.True) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 297;BA.debugLine="view.SetLayoutAnimated(3000, view.Left, originVi";
_view.SetLayoutAnimated((int) (3000),_view.getLeft(),(int) (_originviewy-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 298;BA.debugLine="Sleep(3000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (3000));
this.state = 13;
return;
case 13:
//C
this.state = 4;
;
 //BA.debugLineNum = 299;BA.debugLine="If canMove = False Then";
if (true) break;

case 4:
//if
this.state = 7;
if (parent._canmove==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 300;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 7:
//C
this.state = 8;
;
 //BA.debugLineNum = 302;BA.debugLine="view.SetLayoutAnimated(3000, view.Left, originVi";
_view.SetLayoutAnimated((int) (3000),_view.getLeft(),(int) (_originviewy+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 303;BA.debugLine="Sleep(3000)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (3000));
this.state = 14;
return;
case 14:
//C
this.state = 8;
;
 //BA.debugLineNum = 304;BA.debugLine="If canMove = False Then";
if (true) break;

case 8:
//if
this.state = 11;
if (parent._canmove==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 10;
}if (true) break;

case 10:
//C
this.state = 11;
 //BA.debugLineNum = 305;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 11:
//C
this.state = 1;
;
 if (true) break;

case 12:
//C
this.state = -1;
;
 //BA.debugLineNum = 308;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _changescreen(int _screen) throws Exception{
ResumableSub_changeScreen rsub = new ResumableSub_changeScreen(null,_screen);
rsub.resume(processBA, null);
}
public static class ResumableSub_changeScreen extends BA.ResumableSub {
public ResumableSub_changeScreen(b4a.example.rewind parent,int _screen) {
this.parent = parent;
this._screen = _screen;
}
b4a.example.rewind parent;
int _screen;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 72;BA.debugLine="If screen < 0 Or screen > 4 Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_screen<0 || _screen>4) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 73;BA.debugLine="Return";
if (true) return ;
 if (true) break;
;
 //BA.debugLineNum = 76;BA.debugLine="If currentPanelIndex = 4 And screen = 4 Then";

case 4:
//if
this.state = 7;
if (parent._currentpanelindex==4 && _screen==4) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 77;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 7:
//C
this.state = 8;
;
 //BA.debugLineNum = 80;BA.debugLine="pnlContent.RemoveAllViews";
parent.mostCurrent._pnlcontent.RemoveAllViews();
 //BA.debugLineNum = 82;BA.debugLine="Select screen";
if (true) break;

case 8:
//select
this.state = 19;
switch (_screen) {
case 0: {
this.state = 10;
if (true) break;
}
case 1: {
this.state = 12;
if (true) break;
}
case 2: {
this.state = 14;
if (true) break;
}
case 3: {
this.state = 16;
if (true) break;
}
case 4: {
this.state = 18;
if (true) break;
}
}
if (true) break;

case 10:
//C
this.state = 19;
 //BA.debugLineNum = 84;BA.debugLine="pnlContent.LoadLayout(\"slide1.bal\")";
parent.mostCurrent._pnlcontent.LoadLayout("slide1.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 85;BA.debugLine="canMove = True";
parent._canmove = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 86;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_1.getObject())));
 //BA.debugLineNum = 87;BA.debugLine="AnimateSlideInFromRight(slide_3)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 //BA.debugLineNum = 88;BA.debugLine="AnimateSlideInFromLeft(slide_2)";
_animateslideinfromleft((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 //BA.debugLineNum = 89;BA.debugLine="AnimateSlideInFromTop(slide_4)";
_animateslideinfromtop((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_4.getObject())));
 //BA.debugLineNum = 90;BA.debugLine="Sleep(800)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (800));
this.state = 20;
return;
case 20:
//C
this.state = 19;
;
 //BA.debugLineNum = 91;BA.debugLine="AnimateUpDown(slide_4)";
_animateupdown((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_4.getObject())));
 //BA.debugLineNum = 92;BA.debugLine="Sleep(800)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (800));
this.state = 21;
return;
case 21:
//C
this.state = 19;
;
 //BA.debugLineNum = 93;BA.debugLine="AnimateUpDown(slide_2)";
_animateupdown((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 //BA.debugLineNum = 94;BA.debugLine="AnimateUpDown(slide_3)";
_animateupdown((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 if (true) break;

case 12:
//C
this.state = 19;
 //BA.debugLineNum = 97;BA.debugLine="pnlContent.LoadLayout(\"slide2.bal\")";
parent.mostCurrent._pnlcontent.LoadLayout("slide2.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 98;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_1.getObject())));
 //BA.debugLineNum = 99;BA.debugLine="AnimateSlideInFromLeft(slide_2)";
_animateslideinfromleft((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 //BA.debugLineNum = 100;BA.debugLine="AnimateSlideInFromRight(slide_3)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 //BA.debugLineNum = 101;BA.debugLine="Sleep(800)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (800));
this.state = 22;
return;
case 22:
//C
this.state = 19;
;
 //BA.debugLineNum = 102;BA.debugLine="AnimateLeftToRight(slide_2)";
_animatelefttoright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 //BA.debugLineNum = 103;BA.debugLine="AnimateRightToLeft(slide_3)";
_animaterighttoleft((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 if (true) break;

case 14:
//C
this.state = 19;
 //BA.debugLineNum = 106;BA.debugLine="pnlContent.LoadLayout(\"slide3.bal\")";
parent.mostCurrent._pnlcontent.LoadLayout("slide3.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 107;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_1.getObject())));
 //BA.debugLineNum = 108;BA.debugLine="AnimateSlideInFromRight(slide_2)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 //BA.debugLineNum = 109;BA.debugLine="AnimateSlideInFromRight(slide_3)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 //BA.debugLineNum = 110;BA.debugLine="AnimateSlideInFromRight(slide_4)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_4.getObject())));
 //BA.debugLineNum = 111;BA.debugLine="AnimateSlideInFromRight(slide_5)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_5.getObject())));
 //BA.debugLineNum = 112;BA.debugLine="AnimateSlideInFromRight(slide_6)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_6.getObject())));
 //BA.debugLineNum = 113;BA.debugLine="AnimateSlideInFromRight(slide_7)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_7.getObject())));
 if (true) break;

case 16:
//C
this.state = 19;
 //BA.debugLineNum = 116;BA.debugLine="pnlContent.LoadLayout(\"slide4.bal\")";
parent.mostCurrent._pnlcontent.LoadLayout("slide4.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 117;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_1.getObject())));
 //BA.debugLineNum = 118;BA.debugLine="AnimateSlideInFromRight(slide_3)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 //BA.debugLineNum = 119;BA.debugLine="AnimateSlideInFromLeft(slide_2)";
_animateslideinfromleft((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 122;BA.debugLine="pnlContent.LoadLayout(\"slide5.bal\")";
parent.mostCurrent._pnlcontent.LoadLayout("slide5.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 123;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_1.getObject())));
 //BA.debugLineNum = 124;BA.debugLine="AnimateSlideInFromLeft(slide_3)";
_animateslideinfromleft((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_3.getObject())));
 //BA.debugLineNum = 125;BA.debugLine="AnimateSlideInFromLeft(slide_2)";
_animateslideinfromleft((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_2.getObject())));
 //BA.debugLineNum = 126;BA.debugLine="AnimateSlideInFromRight(slide_4)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_4.getObject())));
 //BA.debugLineNum = 127;BA.debugLine="AnimateSlideInFromRight(slide_5)";
_animateslideinfromright((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(parent.mostCurrent._slide_5.getObject())));
 if (true) break;

case 19:
//C
this.state = -1;
;
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _createstatusindicators(anywheresoftware.b4a.objects.ActivityWrapper _act) throws Exception{
int _numpanels = 0;
int _panelheight = 0;
int _leftmargin = 0;
int _spacebetweenpanels = 0;
anywheresoftware.b4a.objects.PanelWrapper _container = null;
int _containerwidth = 0;
int _totalspace = 0;
int _panelwidth = 0;
int _i = 0;
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
 //BA.debugLineNum = 132;BA.debugLine="Sub CreateStatusIndicators(act As Activity)";
 //BA.debugLineNum = 133;BA.debugLine="Dim numPanels As Int = 5";
_numpanels = (int) (5);
 //BA.debugLineNum = 134;BA.debugLine="Dim panelHeight As Int = 5dip";
_panelheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5));
 //BA.debugLineNum = 135;BA.debugLine="Dim leftMargin As Int = 30dip";
_leftmargin = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
 //BA.debugLineNum = 136;BA.debugLine="Dim spaceBetweenPanels As Int = 10dip";
_spacebetweenpanels = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 138;BA.debugLine="Dim container As Panel";
_container = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 139;BA.debugLine="container.Initialize(\"\")";
_container.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 140;BA.debugLine="container.Color = Colors.Transparent";
_container.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 141;BA.debugLine="act.AddView(container, leftMargin, m_Funciones.Ge";
_act.AddView((android.view.View)(_container.getObject()),_leftmargin,(int) (mostCurrent._m_funciones._getstatusbarheight /*int*/ (mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),(int) (_act.getWidth()-2*_leftmargin),_panelheight);
 //BA.debugLineNum = 143;BA.debugLine="Dim containerWidth As Int = act.Width - 2 * leftM";
_containerwidth = (int) (_act.getWidth()-2*_leftmargin);
 //BA.debugLineNum = 144;BA.debugLine="Dim totalSpace As Int = (numPanels - 1) * spaceBe";
_totalspace = (int) ((_numpanels-1)*_spacebetweenpanels);
 //BA.debugLineNum = 145;BA.debugLine="Dim panelWidth As Int = (containerWidth - totalSp";
_panelwidth = (int) ((_containerwidth-_totalspace)/(double)_numpanels);
 //BA.debugLineNum = 147;BA.debugLine="panels.Initialize";
mostCurrent._panels.Initialize();
 //BA.debugLineNum = 149;BA.debugLine="For i = 0 To numPanels - 1";
{
final int step13 = 1;
final int limit13 = (int) (_numpanels-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 150;BA.debugLine="Dim panel As Panel";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 151;BA.debugLine="panel.Initialize(\"\")";
_panel.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 152;BA.debugLine="panel.Color = Colors.ARGB(200, 255, 255, 180)";
_panel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)));
 //BA.debugLineNum = 153;BA.debugLine="container.AddView(panel, (panelWidth + spaceBetw";
_container.AddView((android.view.View)(_panel.getObject()),(int) ((_panelwidth+_spacebetweenpanels)*_i),(int) (0),_panelwidth,_panelheight);
 //BA.debugLineNum = 154;BA.debugLine="panels.Add(panel)";
mostCurrent._panels.Add((Object)(_panel.getObject()));
 }
};
 //BA.debugLineNum = 157;BA.debugLine="FillPanelsProgressively";
_fillpanelsprogressively();
 //BA.debugLineNum = 158;BA.debugLine="End Sub";
return "";
}
public static String  _fillcurrentbarandadvance() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 223;BA.debugLine="Sub FillCurrentBarAndAdvance";
 //BA.debugLineNum = 224;BA.debugLine="canMove = False";
_canmove = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 225;BA.debugLine="changeScreen(currentPanelIndex + 1)";
_changescreen((int) (_currentpanelindex+1));
 //BA.debugLineNum = 227;BA.debugLine="If currentPanelIndex >= panels.Size Then Return";
if (_currentpanelindex>=mostCurrent._panels.getSize()) { 
if (true) return "";};
 //BA.debugLineNum = 229;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 230;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 231;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 232;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 233;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 234;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 236;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, pa";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1));
 //BA.debugLineNum = 237;BA.debugLine="progress = 0";
_progress = (int) (0);
 //BA.debugLineNum = 238;BA.debugLine="End Sub";
return "";
}
public static String  _fillpanelsprogressively() throws Exception{
 //BA.debugLineNum = 160;BA.debugLine="Sub FillPanelsProgressively";
 //BA.debugLineNum = 161;BA.debugLine="currentPanelIndex = 0";
_currentpanelindex = (int) (0);
 //BA.debugLineNum = 162;BA.debugLine="progress = 0";
_progress = (int) (0);
 //BA.debugLineNum = 164;BA.debugLine="tmr.Initialize(\"FillTimer\", 500)";
_tmr.Initialize(processBA,"FillTimer",(long) (500));
 //BA.debugLineNum = 165;BA.debugLine="tmr.Enabled = True";
_tmr.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return "";
}
public static String  _filltimer_tick() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
int _panelwidth = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 168;BA.debugLine="Sub FillTimer_Tick";
 //BA.debugLineNum = 169;BA.debugLine="If currentPanelIndex >= panels.Size Then";
if (_currentpanelindex>=mostCurrent._panels.getSize()) { 
 //BA.debugLineNum = 170;BA.debugLine="tmr.Enabled = False";
_tmr.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 171;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 174;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 175;BA.debugLine="Dim panelWidth As Int = panel.Width";
_panelwidth = _panel.getWidth();
 //BA.debugLineNum = 177;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 178;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 179;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 180;BA.debugLine="rect.Initialize(0, 0, progress, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_progress,_panel.getHeight());
 //BA.debugLineNum = 181;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 183;BA.debugLine="progress = progress + 5dip";
_progress = (int) (_progress+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 185;BA.debugLine="If progress >= panelWidth Then";
if (_progress>=_panelwidth) { 
 //BA.debugLineNum = 186;BA.debugLine="changeScreen(Min(currentPanelIndex + 1, panels.S";
_changescreen((int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1)));
 //BA.debugLineNum = 187;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, p";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1));
 //BA.debugLineNum = 188;BA.debugLine="progress = 0";
_progress = (int) (0);
 };
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Private pnlBackground As Panel";
mostCurrent._pnlbackground = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private pnlContent As Panel";
mostCurrent._pnlcontent = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Dim currentPanelIndex As Int = 0";
_currentpanelindex = (int) (0);
 //BA.debugLineNum = 18;BA.debugLine="Dim progress As Int";
_progress = 0;
 //BA.debugLineNum = 19;BA.debugLine="Dim panels As List";
mostCurrent._panels = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 20;BA.debugLine="Dim startX As Float";
_startx = 0f;
 //BA.debugLineNum = 21;BA.debugLine="Dim originalX As Float";
_originalx = 0f;
 //BA.debugLineNum = 22;BA.debugLine="Dim canMove As Boolean = False";
_canmove = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 24;BA.debugLine="Private slide_1 As ImageView 'IMAGEN PRINCIPAL'";
mostCurrent._slide_1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private slide_2 As ImageView";
mostCurrent._slide_2 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private slide_3 As ImageView";
mostCurrent._slide_3 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private slide_4 As ImageView";
mostCurrent._slide_4 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private slide_5 As ImageView";
mostCurrent._slide_5 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private slide_6 As ImageView";
mostCurrent._slide_6 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private slide_7 As ImageView";
mostCurrent._slide_7 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _jobdone(String _respuesta,b4a.example.httpjob _job) throws Exception{
ResumableSub_JobDone rsub = new ResumableSub_JobDone(null,_respuesta,_job);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_JobDone extends BA.ResumableSub {
public ResumableSub_JobDone(b4a.example.rewind parent,String _respuesta,b4a.example.httpjob _job) {
this.parent = parent;
this._respuesta = _respuesta;
this._job = _job;
}
b4a.example.rewind parent;
String _respuesta;
b4a.example.httpjob _job;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 56;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 14;
this.catchState = 13;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 13;
 //BA.debugLineNum = 57;BA.debugLine="If job.Success Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_job._success /*boolean*/ ) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 58;BA.debugLine="Select job.JobName";
if (true) break;

case 7:
//select
this.state = 10;
switch (BA.switchObjectToInt(_job._jobname /*String*/ )) {
}
if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 0;
 //BA.debugLineNum = 64;BA.debugLine="Log(LastException.Message)";
anywheresoftware.b4a.keywords.Common.LogImpl("56946825",anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA).getMessage(),0);
 if (true) break;
if (true) break;

case 14:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 66;BA.debugLine="Return Null";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,anywheresoftware.b4a.keywords.Common.Null);return;};
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _pnlcontent_touch(int _action,float _x,float _y) throws Exception{
float _deltax = 0f;
int _panelwidth = 0;
 //BA.debugLineNum = 192;BA.debugLine="Sub pnlContent_Touch(Action As Int, X As Float, Y";
 //BA.debugLineNum = 193;BA.debugLine="Select Case Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE,mostCurrent._activity.ACTION_UP)) {
case 0: {
 //BA.debugLineNum = 195;BA.debugLine="startX = X";
_startx = _x;
 //BA.debugLineNum = 196;BA.debugLine="originalX = pnlContent.Left";
_originalx = (float) (mostCurrent._pnlcontent.getLeft());
 break; }
case 1: {
 //BA.debugLineNum = 198;BA.debugLine="Dim deltaX As Float = X - startX";
_deltax = (float) (_x-_startx);
 //BA.debugLineNum = 199;BA.debugLine="pnlContent.Left = Max(Min(originalX + deltaX, o";
mostCurrent._pnlcontent.setLeft((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.Min(_originalx+_deltax,_originalx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))),_originalx-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)))));
 break; }
case 2: {
 //BA.debugLineNum = 201;BA.debugLine="Dim deltaX As Float = X - startX";
_deltax = (float) (_x-_startx);
 //BA.debugLineNum = 203;BA.debugLine="If Abs(deltaX) > 50dip Then";
if (anywheresoftware.b4a.keywords.Common.Abs(_deltax)>anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))) { 
 //BA.debugLineNum = 204;BA.debugLine="If deltaX < 0 Then";
if (_deltax<0) { 
 //BA.debugLineNum = 205;BA.debugLine="FillCurrentBarAndAdvance";
_fillcurrentbarandadvance();
 }else if(_deltax>0) { 
 //BA.debugLineNum = 207;BA.debugLine="ResetCurrentBarAndGoBack";
_resetcurrentbarandgoback();
 };
 }else {
 //BA.debugLineNum = 210;BA.debugLine="Dim panelWidth As Int = pnlContent.Width / 2";
_panelwidth = (int) (mostCurrent._pnlcontent.getWidth()/(double)2);
 //BA.debugLineNum = 211;BA.debugLine="If X < panelWidth Then";
if (_x<_panelwidth) { 
 //BA.debugLineNum = 212;BA.debugLine="ResetCurrentBarAndGoBack";
_resetcurrentbarandgoback();
 }else {
 //BA.debugLineNum = 214;BA.debugLine="FillCurrentBarAndAdvance";
_fillcurrentbarandadvance();
 };
 };
 //BA.debugLineNum = 219;BA.debugLine="pnlContent.SetLayoutAnimated(300, originalX, pn";
mostCurrent._pnlcontent.SetLayoutAnimated((int) (300),(int) (_originalx),mostCurrent._pnlcontent.getTop(),mostCurrent._pnlcontent.getWidth(),mostCurrent._pnlcontent.getHeight());
 break; }
}
;
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 8;BA.debugLine="Private tmr As Timer";
_tmr = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _resetcurrentbarandgoback() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 240;BA.debugLine="Sub ResetCurrentBarAndGoBack";
 //BA.debugLineNum = 241;BA.debugLine="canMove = False";
_canmove = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 242;BA.debugLine="changeScreen(currentPanelIndex - 1)";
_changescreen((int) (_currentpanelindex-1));
 //BA.debugLineNum = 244;BA.debugLine="If currentPanelIndex <= 0 Then Return";
if (_currentpanelindex<=0) { 
if (true) return "";};
 //BA.debugLineNum = 246;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 247;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 248;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 249;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 250;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 251;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)),anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 253;BA.debugLine="currentPanelIndex = Max(currentPanelIndex - 1, 0)";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Max(_currentpanelindex-1,0));
 //BA.debugLineNum = 255;BA.debugLine="panel = panels.Get(currentPanelIndex)";
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 256;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 257;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 258;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)),anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 260;BA.debugLine="progress = 0";
_progress = (int) (0);
 //BA.debugLineNum = 261;BA.debugLine="End Sub";
return "";
}
}
