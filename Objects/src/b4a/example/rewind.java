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
public static anywheresoftware.b4a.objects.Accessibility.Accessibility2 _access = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlbackground = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlcontent = null;
public static int _currentpanelindex = 0;
public static int _progress = 0;
public anywheresoftware.b4a.objects.collections.List _panels = null;
public anywheresoftware.b4a.objects.Timer _tmr = null;
public static float _startx = 0f;
public static float _originalx = 0f;
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_1 = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 29;BA.debugLine="FullscreenActivity(Activity)";
_fullscreenactivity(mostCurrent._activity);
 //BA.debugLineNum = 30;BA.debugLine="Activity.LoadLayout(\"Background.bal\")";
mostCurrent._activity.LoadLayout("Background.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 32;BA.debugLine="pnlBackground.Height = Activity.Height + GetStatu";
mostCurrent._pnlbackground.setHeight((int) (mostCurrent._activity.getHeight()+_getstatusbarheight()+_getnavigationbarheight()));
 //BA.debugLineNum = 33;BA.debugLine="pnlContent.Height = pnlBackground.Height";
mostCurrent._pnlcontent.setHeight(mostCurrent._pnlbackground.getHeight());
 //BA.debugLineNum = 35;BA.debugLine="changeScreen(currentPanelIndex)";
_changescreen(_currentpanelindex);
 //BA.debugLineNum = 36;BA.debugLine="CreateStatusIndicators(Activity)";
_createstatusindicators(mostCurrent._activity);
 //BA.debugLineNum = 37;BA.debugLine="AutoEscalar(Activity)";
_autoescalar(mostCurrent._activity);
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 40;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 42;BA.debugLine="End Sub";
return "";
}
public static String  _animateslidein(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub AnimateSlideIn(view As View)";
 //BA.debugLineNum = 82;BA.debugLine="view.Top = Activity.Height + view.Height";
_view.setTop((int) (mostCurrent._activity.getHeight()+_view.getHeight()));
 //BA.debugLineNum = 83;BA.debugLine="view.SetLayoutAnimated(800, view.Left, Activity.H";
_view.SetLayoutAnimated((int) (800),_view.getLeft(),(int) (mostCurrent._activity.getHeight()-_view.getHeight()),_view.getWidth(),_view.getHeight());
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return "";
}
public static String  _autoescalar(anywheresoftware.b4a.objects.ActivityWrapper _pantalla) throws Exception{
double _fscale = 0;
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl = null;
anywheresoftware.b4a.objects.ButtonWrapper _s = null;
 //BA.debugLineNum = 254;BA.debugLine="Public Sub AutoEscalar(Pantalla As Activity)";
 //BA.debugLineNum = 255;BA.debugLine="Dim fscale As Double";
_fscale = 0;
 //BA.debugLineNum = 257;BA.debugLine="fscale = access.GetUserFontScale";
_fscale = _access.GetUserFontScale();
 //BA.debugLineNum = 258;BA.debugLine="If fscale > 1 Then";
if (_fscale>1) { 
 //BA.debugLineNum = 259;BA.debugLine="For Each v As View In Pantalla.GetAllViewsRecurs";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group4 = _pantalla.GetAllViewsRecursive();
final int groupLen4 = group4.getSize()
;int index4 = 0;
;
for (; index4 < groupLen4;index4++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group4.Get(index4)));
 //BA.debugLineNum = 260;BA.debugLine="If v Is Label Then";
if (_v.getObjectOrNull() instanceof android.widget.TextView) { 
 //BA.debugLineNum = 261;BA.debugLine="Dim lbl As Label = v";
_lbl = new anywheresoftware.b4a.objects.LabelWrapper();
_lbl = (anywheresoftware.b4a.objects.LabelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.LabelWrapper(), (android.widget.TextView)(_v.getObject()));
 //BA.debugLineNum = 262;BA.debugLine="lbl.TextSize = NumberFormat2((lbl.TextSize / f";
_lbl.setTextSize((float)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.NumberFormat2((_lbl.getTextSize()/(double)_fscale)-2,(int) (1),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.False))));
 //BA.debugLineNum = 263;BA.debugLine="Log((lbl.TextSize / fscale) - 1)";
anywheresoftware.b4a.keywords.Common.LogImpl("31310729",BA.NumberToString((_lbl.getTextSize()/(double)_fscale)-1),0);
 }else if(_v.getObjectOrNull() instanceof android.widget.Button) { 
 //BA.debugLineNum = 265;BA.debugLine="Dim s As Button = v";
_s = new anywheresoftware.b4a.objects.ButtonWrapper();
_s = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(_v.getObject()));
 //BA.debugLineNum = 266;BA.debugLine="s.TextSize = NumberFormat2(s.TextSize / fscale";
_s.setTextSize((float)(Double.parseDouble(anywheresoftware.b4a.keywords.Common.NumberFormat2(_s.getTextSize()/(double)_fscale,(int) (1),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.False))));
 };
 }
};
 };
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
public static String  _changescreen(int _screen) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub changeScreen(screen As Int)";
 //BA.debugLineNum = 51;BA.debugLine="If screen < 0 Or screen > 4 Then";
if (_screen<0 || _screen>4) { 
 //BA.debugLineNum = 52;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 55;BA.debugLine="pnlContent.RemoveAllViews";
mostCurrent._pnlcontent.RemoveAllViews();
 //BA.debugLineNum = 57;BA.debugLine="Select screen";
switch (_screen) {
case 0: {
 //BA.debugLineNum = 59;BA.debugLine="pnlContent.LoadLayout(\"slide1.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide1.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 60;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 1: {
 //BA.debugLineNum = 63;BA.debugLine="pnlContent.LoadLayout(\"slide2.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide2.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 64;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 2: {
 //BA.debugLineNum = 67;BA.debugLine="pnlContent.LoadLayout(\"slide3.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide3.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 68;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 3: {
 //BA.debugLineNum = 71;BA.debugLine="pnlContent.LoadLayout(\"slide4.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide4.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 72;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 4: {
 //BA.debugLineNum = 75;BA.debugLine="pnlContent.LoadLayout(\"slide5.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide5.bal",mostCurrent.activityBA);
 //BA.debugLineNum = 76;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
}
;
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
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
 //BA.debugLineNum = 86;BA.debugLine="Sub CreateStatusIndicators(act As Activity)";
 //BA.debugLineNum = 87;BA.debugLine="Dim numPanels As Int = 5";
_numpanels = (int) (5);
 //BA.debugLineNum = 88;BA.debugLine="Dim panelHeight As Int = 5dip";
_panelheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5));
 //BA.debugLineNum = 89;BA.debugLine="Dim leftMargin As Int = 30dip";
_leftmargin = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
 //BA.debugLineNum = 90;BA.debugLine="Dim spaceBetweenPanels As Int = 10dip";
_spacebetweenpanels = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 92;BA.debugLine="Dim container As Panel";
_container = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 93;BA.debugLine="container.Initialize(\"\")";
_container.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 94;BA.debugLine="container.Color = Colors.Transparent";
_container.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 95;BA.debugLine="act.AddView(container, leftMargin, GetStatusBarHe";
_act.AddView((android.view.View)(_container.getObject()),_leftmargin,(int) (_getstatusbarheight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),(int) (_act.getWidth()-2*_leftmargin),_panelheight);
 //BA.debugLineNum = 97;BA.debugLine="Dim containerWidth As Int = act.Width - 2 * leftM";
_containerwidth = (int) (_act.getWidth()-2*_leftmargin);
 //BA.debugLineNum = 98;BA.debugLine="Dim totalSpace As Int = (numPanels - 1) * spaceBe";
_totalspace = (int) ((_numpanels-1)*_spacebetweenpanels);
 //BA.debugLineNum = 99;BA.debugLine="Dim panelWidth As Int = (containerWidth - totalSp";
_panelwidth = (int) ((_containerwidth-_totalspace)/(double)_numpanels);
 //BA.debugLineNum = 101;BA.debugLine="panels.Initialize";
mostCurrent._panels.Initialize();
 //BA.debugLineNum = 103;BA.debugLine="For i = 0 To numPanels - 1";
{
final int step13 = 1;
final int limit13 = (int) (_numpanels-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
 //BA.debugLineNum = 104;BA.debugLine="Dim panel As Panel";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 105;BA.debugLine="panel.Initialize(\"\")";
_panel.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 106;BA.debugLine="panel.Color = Colors.ARGB(200, 255, 255, 180)";
_panel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)));
 //BA.debugLineNum = 107;BA.debugLine="container.AddView(panel, (panelWidth + spaceBetw";
_container.AddView((android.view.View)(_panel.getObject()),(int) ((_panelwidth+_spacebetweenpanels)*_i),(int) (0),_panelwidth,_panelheight);
 //BA.debugLineNum = 108;BA.debugLine="panels.Add(panel)";
mostCurrent._panels.Add((Object)(_panel.getObject()));
 }
};
 //BA.debugLineNum = 111;BA.debugLine="FillPanelsProgressively";
_fillpanelsprogressively();
 //BA.debugLineNum = 112;BA.debugLine="End Sub";
return "";
}
public static String  _fillcurrentbarandadvance() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 178;BA.debugLine="Sub FillCurrentBarAndAdvance";
 //BA.debugLineNum = 179;BA.debugLine="changeScreen(currentPanelIndex + 1)";
_changescreen((int) (_currentpanelindex+1));
 //BA.debugLineNum = 181;BA.debugLine="If currentPanelIndex >= panels.Size Then Return";
if (_currentpanelindex>=mostCurrent._panels.getSize()) { 
if (true) return "";};
 //BA.debugLineNum = 183;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 184;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 185;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 186;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 187;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 188;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 190;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, pa";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1));
 //BA.debugLineNum = 191;BA.debugLine="progress = 0";
_progress = (int) (0);
 //BA.debugLineNum = 192;BA.debugLine="End Sub";
return "";
}
public static String  _fillpanelsprogressively() throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Sub FillPanelsProgressively";
 //BA.debugLineNum = 115;BA.debugLine="currentPanelIndex = 0";
_currentpanelindex = (int) (0);
 //BA.debugLineNum = 116;BA.debugLine="progress = 0";
_progress = (int) (0);
 //BA.debugLineNum = 118;BA.debugLine="tmr.Initialize(\"FillTimer\", 500)";
mostCurrent._tmr.Initialize(processBA,"FillTimer",(long) (500));
 //BA.debugLineNum = 119;BA.debugLine="tmr.Enabled = True";
mostCurrent._tmr.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _filltimer_tick() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
int _panelwidth = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 122;BA.debugLine="Sub FillTimer_Tick";
 //BA.debugLineNum = 123;BA.debugLine="If currentPanelIndex >= panels.Size Then";
if (_currentpanelindex>=mostCurrent._panels.getSize()) { 
 //BA.debugLineNum = 124;BA.debugLine="tmr.Enabled = False";
mostCurrent._tmr.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 125;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 128;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 129;BA.debugLine="Dim panelWidth As Int = panel.Width";
_panelwidth = _panel.getWidth();
 //BA.debugLineNum = 131;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 132;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 134;BA.debugLine="rect.Initialize(0, 0, progress, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_progress,_panel.getHeight());
 //BA.debugLineNum = 135;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 137;BA.debugLine="progress = progress + 5dip";
_progress = (int) (_progress+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 139;BA.debugLine="If progress >= panelWidth Then";
if (_progress>=_panelwidth) { 
 //BA.debugLineNum = 140;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, p";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1));
 //BA.debugLineNum = 141;BA.debugLine="changeScreen(currentPanelIndex)";
_changescreen(_currentpanelindex);
 //BA.debugLineNum = 142;BA.debugLine="progress = 0";
_progress = (int) (0);
 };
 //BA.debugLineNum = 144;BA.debugLine="End Sub";
return "";
}
public static String  _fullscreenactivity(anywheresoftware.b4a.objects.ActivityWrapper _act) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
int _bodyheight = 0;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _window = null;
 //BA.debugLineNum = 220;BA.debugLine="Sub FullscreenActivity (act As Activity)";
 //BA.debugLineNum = 221;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 222;BA.debugLine="Dim bodyHeight As Int = GetStatusBarHeight + GetN";
_bodyheight = (int) (_getstatusbarheight()+_getnavigationbarheight());
 //BA.debugLineNum = 223;BA.debugLine="If p.SdkVersion >= 4.4 Then";
if (_p.getSdkVersion()>=4.4) { 
 //BA.debugLineNum = 224;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 225;BA.debugLine="Dim window As JavaObject = jo.InitializeContext.";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_jo.InitializeContext(processBA).RunMethod("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 226;BA.debugLine="window.RunMethod(\"addFlags\", Array(Bit.Or(0x0000";
_window.RunMethod("addFlags",new Object[]{(Object)(anywheresoftware.b4a.keywords.Common.Bit.Or(((int)0x00000200),((int)0x08000000)))});
 //BA.debugLineNum = 227;BA.debugLine="act.Height = act.Height + bodyHeight";
_act.setHeight((int) (_act.getHeight()+_bodyheight));
 };
 //BA.debugLineNum = 229;BA.debugLine="End Sub";
return "";
}
public static int  _getnavigationbarheight() throws Exception{
anywheresoftware.b4j.object.JavaObject _context = null;
anywheresoftware.b4j.object.JavaObject _res = null;
int _resourceid = 0;
 //BA.debugLineNum = 243;BA.debugLine="Sub GetNavigationBarHeight As Int";
 //BA.debugLineNum = 244;BA.debugLine="Dim context As JavaObject";
_context = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 245;BA.debugLine="context.InitializeContext";
_context.InitializeContext(processBA);
 //BA.debugLineNum = 246;BA.debugLine="Dim res As JavaObject = context.RunMethod(\"getRes";
_res = new anywheresoftware.b4j.object.JavaObject();
_res = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_context.RunMethod("getResources",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 247;BA.debugLine="Dim resourceId As Int = res.RunMethod(\"getIdentif";
_resourceid = (int)(BA.ObjectToNumber(_res.RunMethod("getIdentifier",new Object[]{(Object)("navigation_bar_height"),(Object)("dimen"),(Object)("android")})));
 //BA.debugLineNum = 248;BA.debugLine="If resourceId > 0 Then";
if (_resourceid>0) { 
 //BA.debugLineNum = 249;BA.debugLine="Return res.RunMethod(\"getDimensionPixelSize\", Ar";
if (true) return (int)(BA.ObjectToNumber(_res.RunMethod("getDimensionPixelSize",new Object[]{(Object)(_resourceid)})));
 }else {
 //BA.debugLineNum = 251;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 253;BA.debugLine="End Sub";
return 0;
}
public static int  _getstatusbarheight() throws Exception{
anywheresoftware.b4j.object.JavaObject _context = null;
anywheresoftware.b4j.object.JavaObject _res = null;
int _resourceid = 0;
 //BA.debugLineNum = 231;BA.debugLine="Sub GetStatusBarHeight As Int";
 //BA.debugLineNum = 232;BA.debugLine="Dim context As JavaObject";
_context = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 233;BA.debugLine="context.InitializeContext";
_context.InitializeContext(processBA);
 //BA.debugLineNum = 234;BA.debugLine="Dim res As JavaObject = context.RunMethod(\"getRes";
_res = new anywheresoftware.b4j.object.JavaObject();
_res = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_context.RunMethod("getResources",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 235;BA.debugLine="Dim resourceId As Int = res.RunMethod(\"getIdentif";
_resourceid = (int)(BA.ObjectToNumber(_res.RunMethod("getIdentifier",new Object[]{(Object)("status_bar_height"),(Object)("dimen"),(Object)("android")})));
 //BA.debugLineNum = 236;BA.debugLine="If resourceId > 0 Then";
if (_resourceid>0) { 
 //BA.debugLineNum = 237;BA.debugLine="Return res.RunMethod(\"getDimensionPixelSize\", Ar";
if (true) return (int)(BA.ObjectToNumber(_res.RunMethod("getDimensionPixelSize",new Object[]{(Object)(_resourceid)})));
 }else {
 //BA.debugLineNum = 239;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Private pnlBackground As Panel";
mostCurrent._pnlbackground = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private pnlContent As Panel";
mostCurrent._pnlcontent = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim currentPanelIndex As Int = 0";
_currentpanelindex = (int) (0);
 //BA.debugLineNum = 19;BA.debugLine="Dim progress As Int";
_progress = 0;
 //BA.debugLineNum = 20;BA.debugLine="Dim panels As List";
mostCurrent._panels = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 21;BA.debugLine="Dim tmr As Timer";
mostCurrent._tmr = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 22;BA.debugLine="Dim startX As Float";
_startx = 0f;
 //BA.debugLineNum = 23;BA.debugLine="Dim originalX As Float";
_originalx = 0f;
 //BA.debugLineNum = 25;BA.debugLine="Private slide_1 As ImageView 'IMAGEN PRINCIPAL'";
mostCurrent._slide_1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _pnlcontent_touch(int _action,float _x,float _y) throws Exception{
float _deltax = 0f;
int _panelwidth = 0;
 //BA.debugLineNum = 146;BA.debugLine="Sub pnlContent_Touch(Action As Int, X As Float, Y";
 //BA.debugLineNum = 147;BA.debugLine="Select Case Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE,mostCurrent._activity.ACTION_UP)) {
case 0: {
 //BA.debugLineNum = 149;BA.debugLine="startX = X";
_startx = _x;
 //BA.debugLineNum = 150;BA.debugLine="originalX = pnlContent.Left";
_originalx = (float) (mostCurrent._pnlcontent.getLeft());
 break; }
case 1: {
 //BA.debugLineNum = 152;BA.debugLine="Dim deltaX As Float = X - startX";
_deltax = (float) (_x-_startx);
 //BA.debugLineNum = 153;BA.debugLine="pnlContent.Left = Max(Min(originalX + deltaX, o";
mostCurrent._pnlcontent.setLeft((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.Min(_originalx+_deltax,_originalx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))),_originalx-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)))));
 break; }
case 2: {
 //BA.debugLineNum = 155;BA.debugLine="Dim deltaX As Float = X - startX";
_deltax = (float) (_x-_startx);
 //BA.debugLineNum = 157;BA.debugLine="If Abs(deltaX) > 50dip Then";
if (anywheresoftware.b4a.keywords.Common.Abs(_deltax)>anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))) { 
 //BA.debugLineNum = 158;BA.debugLine="If deltaX < 0 Then";
if (_deltax<0) { 
 //BA.debugLineNum = 159;BA.debugLine="FillCurrentBarAndAdvance";
_fillcurrentbarandadvance();
 }else if(_deltax>0) { 
 //BA.debugLineNum = 161;BA.debugLine="ResetCurrentBarAndGoBack";
_resetcurrentbarandgoback();
 };
 }else {
 //BA.debugLineNum = 164;BA.debugLine="Dim panelWidth As Int = pnlContent.Width / 2";
_panelwidth = (int) (mostCurrent._pnlcontent.getWidth()/(double)2);
 //BA.debugLineNum = 165;BA.debugLine="If X < panelWidth Then";
if (_x<_panelwidth) { 
 //BA.debugLineNum = 166;BA.debugLine="ResetCurrentBarAndGoBack";
_resetcurrentbarandgoback();
 }else {
 //BA.debugLineNum = 168;BA.debugLine="FillCurrentBarAndAdvance";
_fillcurrentbarandadvance();
 };
 };
 //BA.debugLineNum = 173;BA.debugLine="pnlContent.SetLayoutAnimated(300, originalX, pn";
mostCurrent._pnlcontent.SetLayoutAnimated((int) (300),(int) (_originalx),mostCurrent._pnlcontent.getTop(),mostCurrent._pnlcontent.getWidth(),mostCurrent._pnlcontent.getHeight());
 break; }
}
;
 //BA.debugLineNum = 175;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 8;BA.debugLine="Private access As Accessiblity";
_access = new anywheresoftware.b4a.objects.Accessibility.Accessibility2();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _resetcurrentbarandgoback() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
 //BA.debugLineNum = 194;BA.debugLine="Sub ResetCurrentBarAndGoBack";
 //BA.debugLineNum = 195;BA.debugLine="changeScreen(currentPanelIndex - 1)";
_changescreen((int) (_currentpanelindex-1));
 //BA.debugLineNum = 197;BA.debugLine="If currentPanelIndex <= 0 Then Return";
if (_currentpanelindex<=0) { 
if (true) return "";};
 //BA.debugLineNum = 199;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 200;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 201;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 202;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
 //BA.debugLineNum = 203;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 204;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)),anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 206;BA.debugLine="currentPanelIndex = Max(currentPanelIndex - 1, 0)";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Max(_currentpanelindex-1,0));
 //BA.debugLineNum = 208;BA.debugLine="panel = panels.Get(currentPanelIndex)";
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
 //BA.debugLineNum = 209;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
 //BA.debugLineNum = 210;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
 //BA.debugLineNum = 211;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)),anywheresoftware.b4a.keywords.Common.True,(float) (0));
 //BA.debugLineNum = 213;BA.debugLine="progress = 0";
_progress = (int) (0);
 //BA.debugLineNum = 214;BA.debugLine="End Sub";
return "";
}
}
