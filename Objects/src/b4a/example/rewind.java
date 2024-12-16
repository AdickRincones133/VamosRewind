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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.rewind");
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



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
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
public anywheresoftware.b4a.objects.ImageViewWrapper _slide_1 = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.m_funciones _m_funciones = null;
public static void  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}); return;}
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
RDebugUtils.currentModule="rewind";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="m_Funciones.FullscreenActivity(Activity)";
parent.mostCurrent._m_funciones._fullscreenactivity /*String*/ (mostCurrent.activityBA,parent.mostCurrent._activity);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="Sleep(1)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "rewind", "activity_create"),(int) (1));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="Activity.LoadLayout(\"Background.bal\")";
parent.mostCurrent._activity.LoadLayout("Background.bal",mostCurrent.activityBA);
RDebugUtils.currentLine=851973;
 //BA.debugLineNum = 851973;BA.debugLine="pnlBackground.Height = Activity.Height + m_Funcio";
parent.mostCurrent._pnlbackground.setHeight((int) (parent.mostCurrent._activity.getHeight()+parent.mostCurrent._m_funciones._getstatusbarheight /*int*/ (mostCurrent.activityBA)+parent.mostCurrent._m_funciones._getnavigationbarheight /*int*/ (mostCurrent.activityBA)));
RDebugUtils.currentLine=851974;
 //BA.debugLineNum = 851974;BA.debugLine="pnlContent.Height = pnlBackground.Height";
parent.mostCurrent._pnlcontent.setHeight(parent.mostCurrent._pnlbackground.getHeight());
RDebugUtils.currentLine=851976;
 //BA.debugLineNum = 851976;BA.debugLine="changeScreen(currentPanelIndex)";
_changescreen(parent._currentpanelindex);
RDebugUtils.currentLine=851977;
 //BA.debugLineNum = 851977;BA.debugLine="CreateStatusIndicators(Activity)";
_createstatusindicators(parent.mostCurrent._activity);
RDebugUtils.currentLine=851978;
 //BA.debugLineNum = 851978;BA.debugLine="m_Funciones.AutoEscalar(Activity)";
parent.mostCurrent._m_funciones._autoescalar /*String*/ (mostCurrent.activityBA,parent.mostCurrent._activity);
RDebugUtils.currentLine=851979;
 //BA.debugLineNum = 851979;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _changescreen(int _screen) throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "changescreen", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "changescreen", new Object[] {_screen}));}
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub changeScreen(screen As Int)";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="If screen < 0 Or screen > 4 Then";
if (_screen<0 || _screen>4) { 
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="pnlContent.RemoveAllViews";
mostCurrent._pnlcontent.RemoveAllViews();
RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="Select screen";
switch (_screen) {
case 0: {
RDebugUtils.currentLine=1048585;
 //BA.debugLineNum = 1048585;BA.debugLine="pnlContent.LoadLayout(\"slide1.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide1.bal",mostCurrent.activityBA);
RDebugUtils.currentLine=1048586;
 //BA.debugLineNum = 1048586;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 1: {
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="pnlContent.LoadLayout(\"slide2.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide2.bal",mostCurrent.activityBA);
RDebugUtils.currentLine=1048590;
 //BA.debugLineNum = 1048590;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 2: {
RDebugUtils.currentLine=1048593;
 //BA.debugLineNum = 1048593;BA.debugLine="pnlContent.LoadLayout(\"slide3.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide3.bal",mostCurrent.activityBA);
RDebugUtils.currentLine=1048594;
 //BA.debugLineNum = 1048594;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 3: {
RDebugUtils.currentLine=1048597;
 //BA.debugLineNum = 1048597;BA.debugLine="pnlContent.LoadLayout(\"slide4.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide4.bal",mostCurrent.activityBA);
RDebugUtils.currentLine=1048598;
 //BA.debugLineNum = 1048598;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
case 4: {
RDebugUtils.currentLine=1048601;
 //BA.debugLineNum = 1048601;BA.debugLine="pnlContent.LoadLayout(\"slide5.bal\")";
mostCurrent._pnlcontent.LoadLayout("slide5.bal",mostCurrent.activityBA);
RDebugUtils.currentLine=1048602;
 //BA.debugLineNum = 1048602;BA.debugLine="AnimateSlideIn(slide_1)";
_animateslidein((anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(mostCurrent._slide_1.getObject())));
 break; }
}
;
RDebugUtils.currentLine=1048605;
 //BA.debugLineNum = 1048605;BA.debugLine="End Sub";
return "";
}
public static String  _createstatusindicators(anywheresoftware.b4a.objects.ActivityWrapper _act) throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "createstatusindicators", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "createstatusindicators", new Object[] {_act}));}
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
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub CreateStatusIndicators(act As Activity)";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Dim numPanels As Int = 5";
_numpanels = (int) (5);
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="Dim panelHeight As Int = 5dip";
_panelheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5));
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="Dim leftMargin As Int = 30dip";
_leftmargin = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30));
RDebugUtils.currentLine=1179652;
 //BA.debugLineNum = 1179652;BA.debugLine="Dim spaceBetweenPanels As Int = 10dip";
_spacebetweenpanels = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="Dim container As Panel";
_container = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="container.Initialize(\"\")";
_container.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="container.Color = Colors.Transparent";
_container.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
RDebugUtils.currentLine=1179657;
 //BA.debugLineNum = 1179657;BA.debugLine="act.AddView(container, leftMargin, m_Funciones.Ge";
_act.AddView((android.view.View)(_container.getObject()),_leftmargin,(int) (mostCurrent._m_funciones._getstatusbarheight /*int*/ (mostCurrent.activityBA)+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),(int) (_act.getWidth()-2*_leftmargin),_panelheight);
RDebugUtils.currentLine=1179659;
 //BA.debugLineNum = 1179659;BA.debugLine="Dim containerWidth As Int = act.Width - 2 * leftM";
_containerwidth = (int) (_act.getWidth()-2*_leftmargin);
RDebugUtils.currentLine=1179660;
 //BA.debugLineNum = 1179660;BA.debugLine="Dim totalSpace As Int = (numPanels - 1) * spaceBe";
_totalspace = (int) ((_numpanels-1)*_spacebetweenpanels);
RDebugUtils.currentLine=1179661;
 //BA.debugLineNum = 1179661;BA.debugLine="Dim panelWidth As Int = (containerWidth - totalSp";
_panelwidth = (int) ((_containerwidth-_totalspace)/(double)_numpanels);
RDebugUtils.currentLine=1179663;
 //BA.debugLineNum = 1179663;BA.debugLine="panels.Initialize";
mostCurrent._panels.Initialize();
RDebugUtils.currentLine=1179665;
 //BA.debugLineNum = 1179665;BA.debugLine="For i = 0 To numPanels - 1";
{
final int step13 = 1;
final int limit13 = (int) (_numpanels-1);
_i = (int) (0) ;
for (;_i <= limit13 ;_i = _i + step13 ) {
RDebugUtils.currentLine=1179666;
 //BA.debugLineNum = 1179666;BA.debugLine="Dim panel As Panel";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
RDebugUtils.currentLine=1179667;
 //BA.debugLineNum = 1179667;BA.debugLine="panel.Initialize(\"\")";
_panel.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1179668;
 //BA.debugLineNum = 1179668;BA.debugLine="panel.Color = Colors.ARGB(200, 255, 255, 180)";
_panel.setColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)));
RDebugUtils.currentLine=1179669;
 //BA.debugLineNum = 1179669;BA.debugLine="container.AddView(panel, (panelWidth + spaceBetw";
_container.AddView((android.view.View)(_panel.getObject()),(int) ((_panelwidth+_spacebetweenpanels)*_i),(int) (0),_panelwidth,_panelheight);
RDebugUtils.currentLine=1179670;
 //BA.debugLineNum = 1179670;BA.debugLine="panels.Add(panel)";
mostCurrent._panels.Add((Object)(_panel.getObject()));
 }
};
RDebugUtils.currentLine=1179673;
 //BA.debugLineNum = 1179673;BA.debugLine="FillPanelsProgressively";
_fillpanelsprogressively();
RDebugUtils.currentLine=1179674;
 //BA.debugLineNum = 1179674;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="rewind";
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="End Sub";
return "";
}
public static String  _animateslidein(anywheresoftware.b4a.objects.ConcreteViewWrapper _view) throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "animateslidein", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "animateslidein", new Object[] {_view}));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Sub AnimateSlideIn(view As View)";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="view.Top = Activity.Height + view.Height";
_view.setTop((int) (mostCurrent._activity.getHeight()+_view.getHeight()));
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="view.SetLayoutAnimated(800, view.Left, Activity.H";
_view.SetLayoutAnimated((int) (800),_view.getLeft(),(int) (mostCurrent._activity.getHeight()-_view.getHeight()),_view.getWidth(),_view.getHeight());
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="End Sub";
return "";
}
public static String  _fillpanelsprogressively() throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "fillpanelsprogressively", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "fillpanelsprogressively", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub FillPanelsProgressively";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="currentPanelIndex = 0";
_currentpanelindex = (int) (0);
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="progress = 0";
_progress = (int) (0);
RDebugUtils.currentLine=1245188;
 //BA.debugLineNum = 1245188;BA.debugLine="tmr.Initialize(\"FillTimer\", 500)";
_tmr.Initialize(processBA,"FillTimer",(long) (500));
RDebugUtils.currentLine=1245189;
 //BA.debugLineNum = 1245189;BA.debugLine="tmr.Enabled = True";
_tmr.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245190;
 //BA.debugLineNum = 1245190;BA.debugLine="End Sub";
return "";
}
public static String  _fillcurrentbarandadvance() throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "fillcurrentbarandadvance", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "fillcurrentbarandadvance", null));}
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Sub FillCurrentBarAndAdvance";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="changeScreen(currentPanelIndex + 1)";
_changescreen((int) (_currentpanelindex+1));
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="If currentPanelIndex >= panels.Size Then Return";
if (_currentpanelindex>=mostCurrent._panels.getSize()) { 
if (true) return "";};
RDebugUtils.currentLine=1441797;
 //BA.debugLineNum = 1441797;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
RDebugUtils.currentLine=1441798;
 //BA.debugLineNum = 1441798;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
RDebugUtils.currentLine=1441799;
 //BA.debugLineNum = 1441799;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
RDebugUtils.currentLine=1441800;
 //BA.debugLineNum = 1441800;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
RDebugUtils.currentLine=1441801;
 //BA.debugLineNum = 1441801;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
RDebugUtils.currentLine=1441802;
 //BA.debugLineNum = 1441802;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.True,(float) (0));
RDebugUtils.currentLine=1441804;
 //BA.debugLineNum = 1441804;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, pa";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1));
RDebugUtils.currentLine=1441805;
 //BA.debugLineNum = 1441805;BA.debugLine="progress = 0";
_progress = (int) (0);
RDebugUtils.currentLine=1441806;
 //BA.debugLineNum = 1441806;BA.debugLine="End Sub";
return "";
}
public static String  _filltimer_tick() throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "filltimer_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "filltimer_tick", null));}
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
int _panelwidth = 0;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Sub FillTimer_Tick";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="If currentPanelIndex >= panels.Size Then";
if (_currentpanelindex>=mostCurrent._panels.getSize()) { 
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="tmr.Enabled = False";
_tmr.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1310726;
 //BA.debugLineNum = 1310726;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
RDebugUtils.currentLine=1310727;
 //BA.debugLineNum = 1310727;BA.debugLine="Dim panelWidth As Int = panel.Width";
_panelwidth = _panel.getWidth();
RDebugUtils.currentLine=1310729;
 //BA.debugLineNum = 1310729;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
RDebugUtils.currentLine=1310730;
 //BA.debugLineNum = 1310730;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
RDebugUtils.currentLine=1310731;
 //BA.debugLineNum = 1310731;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
RDebugUtils.currentLine=1310732;
 //BA.debugLineNum = 1310732;BA.debugLine="rect.Initialize(0, 0, progress, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_progress,_panel.getHeight());
RDebugUtils.currentLine=1310733;
 //BA.debugLineNum = 1310733;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.True,(float) (0));
RDebugUtils.currentLine=1310735;
 //BA.debugLineNum = 1310735;BA.debugLine="progress = progress + 5dip";
_progress = (int) (_progress+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
RDebugUtils.currentLine=1310737;
 //BA.debugLineNum = 1310737;BA.debugLine="If progress >= panelWidth Then";
if (_progress>=_panelwidth) { 
RDebugUtils.currentLine=1310738;
 //BA.debugLineNum = 1310738;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, p";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Min(_currentpanelindex+1,mostCurrent._panels.getSize()-1));
RDebugUtils.currentLine=1310739;
 //BA.debugLineNum = 1310739;BA.debugLine="changeScreen(currentPanelIndex)";
_changescreen(_currentpanelindex);
RDebugUtils.currentLine=1310740;
 //BA.debugLineNum = 1310740;BA.debugLine="progress = 0";
_progress = (int) (0);
 };
RDebugUtils.currentLine=1310742;
 //BA.debugLineNum = 1310742;BA.debugLine="End Sub";
return "";
}
public static String  _pnlcontent_touch(int _action,float _x,float _y) throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "pnlcontent_touch", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "pnlcontent_touch", new Object[] {_action,_x,_y}));}
float _deltax = 0f;
int _panelwidth = 0;
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Sub pnlContent_Touch(Action As Int, X As Float, Y";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="Select Case Action";
switch (BA.switchObjectToInt(_action,mostCurrent._activity.ACTION_DOWN,mostCurrent._activity.ACTION_MOVE,mostCurrent._activity.ACTION_UP)) {
case 0: {
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="startX = X";
_startx = _x;
RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="originalX = pnlContent.Left";
_originalx = (float) (mostCurrent._pnlcontent.getLeft());
 break; }
case 1: {
RDebugUtils.currentLine=1376262;
 //BA.debugLineNum = 1376262;BA.debugLine="Dim deltaX As Float = X - startX";
_deltax = (float) (_x-_startx);
RDebugUtils.currentLine=1376263;
 //BA.debugLineNum = 1376263;BA.debugLine="pnlContent.Left = Max(Min(originalX + deltaX, o";
mostCurrent._pnlcontent.setLeft((int) (anywheresoftware.b4a.keywords.Common.Max(anywheresoftware.b4a.keywords.Common.Min(_originalx+_deltax,_originalx+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))),_originalx-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)))));
 break; }
case 2: {
RDebugUtils.currentLine=1376265;
 //BA.debugLineNum = 1376265;BA.debugLine="Dim deltaX As Float = X - startX";
_deltax = (float) (_x-_startx);
RDebugUtils.currentLine=1376267;
 //BA.debugLineNum = 1376267;BA.debugLine="If Abs(deltaX) > 50dip Then";
if (anywheresoftware.b4a.keywords.Common.Abs(_deltax)>anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))) { 
RDebugUtils.currentLine=1376268;
 //BA.debugLineNum = 1376268;BA.debugLine="If deltaX < 0 Then";
if (_deltax<0) { 
RDebugUtils.currentLine=1376269;
 //BA.debugLineNum = 1376269;BA.debugLine="FillCurrentBarAndAdvance";
_fillcurrentbarandadvance();
 }else 
{RDebugUtils.currentLine=1376270;
 //BA.debugLineNum = 1376270;BA.debugLine="Else If deltaX > 0 Then";
if (_deltax>0) { 
RDebugUtils.currentLine=1376271;
 //BA.debugLineNum = 1376271;BA.debugLine="ResetCurrentBarAndGoBack";
_resetcurrentbarandgoback();
 }}
;
 }else {
RDebugUtils.currentLine=1376274;
 //BA.debugLineNum = 1376274;BA.debugLine="Dim panelWidth As Int = pnlContent.Width / 2";
_panelwidth = (int) (mostCurrent._pnlcontent.getWidth()/(double)2);
RDebugUtils.currentLine=1376275;
 //BA.debugLineNum = 1376275;BA.debugLine="If X < panelWidth Then";
if (_x<_panelwidth) { 
RDebugUtils.currentLine=1376276;
 //BA.debugLineNum = 1376276;BA.debugLine="ResetCurrentBarAndGoBack";
_resetcurrentbarandgoback();
 }else {
RDebugUtils.currentLine=1376278;
 //BA.debugLineNum = 1376278;BA.debugLine="FillCurrentBarAndAdvance";
_fillcurrentbarandadvance();
 };
 };
RDebugUtils.currentLine=1376283;
 //BA.debugLineNum = 1376283;BA.debugLine="pnlContent.SetLayoutAnimated(300, originalX, pn";
mostCurrent._pnlcontent.SetLayoutAnimated((int) (300),(int) (_originalx),mostCurrent._pnlcontent.getTop(),mostCurrent._pnlcontent.getWidth(),mostCurrent._pnlcontent.getHeight());
 break; }
}
;
RDebugUtils.currentLine=1376285;
 //BA.debugLineNum = 1376285;BA.debugLine="End Sub";
return "";
}
public static String  _resetcurrentbarandgoback() throws Exception{
RDebugUtils.currentModule="rewind";
if (Debug.shouldDelegate(mostCurrent.activityBA, "resetcurrentbarandgoback", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "resetcurrentbarandgoback", null));}
anywheresoftware.b4a.objects.PanelWrapper _panel = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _canvas = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper _rect = null;
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub ResetCurrentBarAndGoBack";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="changeScreen(currentPanelIndex - 1)";
_changescreen((int) (_currentpanelindex-1));
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="If currentPanelIndex <= 0 Then Return";
if (_currentpanelindex<=0) { 
if (true) return "";};
RDebugUtils.currentLine=1507333;
 //BA.debugLineNum = 1507333;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
_panel = new anywheresoftware.b4a.objects.PanelWrapper();
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="Dim canvas As Canvas";
_canvas = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
RDebugUtils.currentLine=1507336;
 //BA.debugLineNum = 1507336;BA.debugLine="Dim rect As Rect";
_rect = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper();
RDebugUtils.currentLine=1507337;
 //BA.debugLineNum = 1507337;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
RDebugUtils.currentLine=1507338;
 //BA.debugLineNum = 1507338;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)),anywheresoftware.b4a.keywords.Common.True,(float) (0));
RDebugUtils.currentLine=1507340;
 //BA.debugLineNum = 1507340;BA.debugLine="currentPanelIndex = Max(currentPanelIndex - 1, 0)";
_currentpanelindex = (int) (anywheresoftware.b4a.keywords.Common.Max(_currentpanelindex-1,0));
RDebugUtils.currentLine=1507342;
 //BA.debugLineNum = 1507342;BA.debugLine="panel = panels.Get(currentPanelIndex)";
_panel = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._panels.Get(_currentpanelindex)));
RDebugUtils.currentLine=1507343;
 //BA.debugLineNum = 1507343;BA.debugLine="canvas.Initialize(panel)";
_canvas.Initialize((android.view.View)(_panel.getObject()));
RDebugUtils.currentLine=1507344;
 //BA.debugLineNum = 1507344;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
_rect.Initialize((int) (0),(int) (0),_panel.getWidth(),_panel.getHeight());
RDebugUtils.currentLine=1507345;
 //BA.debugLineNum = 1507345;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
_canvas.DrawRect((android.graphics.Rect)(_rect.getObject()),anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (200),(int) (255),(int) (255),(int) (180)),anywheresoftware.b4a.keywords.Common.True,(float) (0));
RDebugUtils.currentLine=1507347;
 //BA.debugLineNum = 1507347;BA.debugLine="progress = 0";
_progress = (int) (0);
RDebugUtils.currentLine=1507348;
 //BA.debugLineNum = 1507348;BA.debugLine="End Sub";
return "";
}
}