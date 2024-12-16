package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class rewind_subs_0 {


public static void  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,26);
if (RapidSub.canDelegate("activity_create")) { b4a.example.rewind.remoteMe.runUserSub(false, "rewind","activity_create", _firsttime); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(b4a.example.rewind parent,RemoteObject _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.rewind parent;
RemoteObject _firsttime;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,26);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 27;BA.debugLine="m_Funciones.FullscreenActivity(Activity)";
Debug.ShouldStop(67108864);
parent.mostCurrent._m_funciones.runVoidMethod ("_fullscreenactivity" /*RemoteObject*/ ,rewind.mostCurrent.activityBA,(Object)(parent.mostCurrent._activity));
 BA.debugLineNum = 28;BA.debugLine="Sleep(1)";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("Sleep",rewind.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "rewind", "activity_create"),BA.numberCast(int.class, 1));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 BA.debugLineNum = 29;BA.debugLine="Activity.LoadLayout(\"Background.bal\")";
Debug.ShouldStop(268435456);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Background.bal")),rewind.mostCurrent.activityBA);
 BA.debugLineNum = 31;BA.debugLine="pnlBackground.Height = Activity.Height + m_Funcio";
Debug.ShouldStop(1073741824);
parent.mostCurrent._pnlbackground.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {parent.mostCurrent._activity.runMethod(true,"getHeight"),parent.mostCurrent._m_funciones.runMethod(true,"_getstatusbarheight" /*RemoteObject*/ ,rewind.mostCurrent.activityBA),parent.mostCurrent._m_funciones.runMethod(true,"_getnavigationbarheight" /*RemoteObject*/ ,rewind.mostCurrent.activityBA)}, "++",2, 1));
 BA.debugLineNum = 32;BA.debugLine="pnlContent.Height = pnlBackground.Height";
Debug.ShouldStop(-2147483648);
parent.mostCurrent._pnlcontent.runMethod(true,"setHeight",parent.mostCurrent._pnlbackground.runMethod(true,"getHeight"));
 BA.debugLineNum = 34;BA.debugLine="changeScreen(currentPanelIndex)";
Debug.ShouldStop(2);
_changescreen(parent._currentpanelindex);
 BA.debugLineNum = 35;BA.debugLine="CreateStatusIndicators(Activity)";
Debug.ShouldStop(4);
_createstatusindicators(parent.mostCurrent._activity);
 BA.debugLineNum = 36;BA.debugLine="m_Funciones.AutoEscalar(Activity)";
Debug.ShouldStop(8);
parent.mostCurrent._m_funciones.runVoidMethod ("_autoescalar" /*RemoteObject*/ ,rewind.mostCurrent.activityBA,(Object)(parent.mostCurrent._activity));
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,43);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 43;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1024);
 BA.debugLineNum = 45;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,39);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","activity_resume");}
 BA.debugLineNum = 39;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(64);
 BA.debugLineNum = 41;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _animateslidein(RemoteObject _view) throws Exception{
try {
		Debug.PushSubsStack("AnimateSlideIn (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,80);
if (RapidSub.canDelegate("animateslidein")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","animateslidein", _view);}
Debug.locals.put("view", _view);
 BA.debugLineNum = 80;BA.debugLine="Sub AnimateSlideIn(view As View)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 81;BA.debugLine="view.Top = Activity.Height + view.Height";
Debug.ShouldStop(65536);
_view.runMethod(true,"setTop",RemoteObject.solve(new RemoteObject[] {rewind.mostCurrent._activity.runMethod(true,"getHeight"),_view.runMethod(true,"getHeight")}, "+",1, 1));
 BA.debugLineNum = 82;BA.debugLine="view.SetLayoutAnimated(800, view.Left, Activity.H";
Debug.ShouldStop(131072);
_view.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 800)),(Object)(_view.runMethod(true,"getLeft")),(Object)(RemoteObject.solve(new RemoteObject[] {rewind.mostCurrent._activity.runMethod(true,"getHeight"),_view.runMethod(true,"getHeight")}, "-",1, 1)),(Object)(_view.runMethod(true,"getWidth")),(Object)(_view.runMethod(true,"getHeight")));
 BA.debugLineNum = 83;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _changescreen(RemoteObject _screen) throws Exception{
try {
		Debug.PushSubsStack("changeScreen (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,49);
if (RapidSub.canDelegate("changescreen")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","changescreen", _screen);}
Debug.locals.put("screen", _screen);
 BA.debugLineNum = 49;BA.debugLine="Sub changeScreen(screen As Int)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 50;BA.debugLine="If screen < 0 Or screen > 4 Then";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean("<",_screen,BA.numberCast(double.class, 0)) || RemoteObject.solveBoolean(">",_screen,BA.numberCast(double.class, 4))) { 
 BA.debugLineNum = 51;BA.debugLine="Return";
Debug.ShouldStop(262144);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 54;BA.debugLine="pnlContent.RemoveAllViews";
Debug.ShouldStop(2097152);
rewind.mostCurrent._pnlcontent.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 56;BA.debugLine="Select screen";
Debug.ShouldStop(8388608);
switch (BA.switchObjectToInt(_screen,BA.numberCast(int.class, 0),BA.numberCast(int.class, 1),BA.numberCast(int.class, 2),BA.numberCast(int.class, 3),BA.numberCast(int.class, 4))) {
case 0: {
 BA.debugLineNum = 58;BA.debugLine="pnlContent.LoadLayout(\"slide1.bal\")";
Debug.ShouldStop(33554432);
rewind.mostCurrent._pnlcontent.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("slide1.bal")),rewind.mostCurrent.activityBA);
 BA.debugLineNum = 59;BA.debugLine="AnimateSlideIn(slide_1)";
Debug.ShouldStop(67108864);
_animateslidein(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), rewind.mostCurrent._slide_1.getObject()));
 break; }
case 1: {
 BA.debugLineNum = 62;BA.debugLine="pnlContent.LoadLayout(\"slide2.bal\")";
Debug.ShouldStop(536870912);
rewind.mostCurrent._pnlcontent.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("slide2.bal")),rewind.mostCurrent.activityBA);
 BA.debugLineNum = 63;BA.debugLine="AnimateSlideIn(slide_1)";
Debug.ShouldStop(1073741824);
_animateslidein(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), rewind.mostCurrent._slide_1.getObject()));
 break; }
case 2: {
 BA.debugLineNum = 66;BA.debugLine="pnlContent.LoadLayout(\"slide3.bal\")";
Debug.ShouldStop(2);
rewind.mostCurrent._pnlcontent.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("slide3.bal")),rewind.mostCurrent.activityBA);
 BA.debugLineNum = 67;BA.debugLine="AnimateSlideIn(slide_1)";
Debug.ShouldStop(4);
_animateslidein(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), rewind.mostCurrent._slide_1.getObject()));
 break; }
case 3: {
 BA.debugLineNum = 70;BA.debugLine="pnlContent.LoadLayout(\"slide4.bal\")";
Debug.ShouldStop(32);
rewind.mostCurrent._pnlcontent.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("slide4.bal")),rewind.mostCurrent.activityBA);
 BA.debugLineNum = 71;BA.debugLine="AnimateSlideIn(slide_1)";
Debug.ShouldStop(64);
_animateslidein(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), rewind.mostCurrent._slide_1.getObject()));
 break; }
case 4: {
 BA.debugLineNum = 74;BA.debugLine="pnlContent.LoadLayout(\"slide5.bal\")";
Debug.ShouldStop(512);
rewind.mostCurrent._pnlcontent.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("slide5.bal")),rewind.mostCurrent.activityBA);
 BA.debugLineNum = 75;BA.debugLine="AnimateSlideIn(slide_1)";
Debug.ShouldStop(1024);
_animateslidein(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), rewind.mostCurrent._slide_1.getObject()));
 break; }
}
;
 BA.debugLineNum = 78;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createstatusindicators(RemoteObject _act) throws Exception{
try {
		Debug.PushSubsStack("CreateStatusIndicators (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,85);
if (RapidSub.canDelegate("createstatusindicators")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","createstatusindicators", _act);}
RemoteObject _numpanels = RemoteObject.createImmutable(0);
RemoteObject _panelheight = RemoteObject.createImmutable(0);
RemoteObject _leftmargin = RemoteObject.createImmutable(0);
RemoteObject _spacebetweenpanels = RemoteObject.createImmutable(0);
RemoteObject _container = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _containerwidth = RemoteObject.createImmutable(0);
RemoteObject _totalspace = RemoteObject.createImmutable(0);
RemoteObject _panelwidth = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _panel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
Debug.locals.put("act", _act);
 BA.debugLineNum = 85;BA.debugLine="Sub CreateStatusIndicators(act As Activity)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 86;BA.debugLine="Dim numPanels As Int = 5";
Debug.ShouldStop(2097152);
_numpanels = BA.numberCast(int.class, 5);Debug.locals.put("numPanels", _numpanels);Debug.locals.put("numPanels", _numpanels);
 BA.debugLineNum = 87;BA.debugLine="Dim panelHeight As Int = 5dip";
Debug.ShouldStop(4194304);
_panelheight = rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)));Debug.locals.put("panelHeight", _panelheight);Debug.locals.put("panelHeight", _panelheight);
 BA.debugLineNum = 88;BA.debugLine="Dim leftMargin As Int = 30dip";
Debug.ShouldStop(8388608);
_leftmargin = rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)));Debug.locals.put("leftMargin", _leftmargin);Debug.locals.put("leftMargin", _leftmargin);
 BA.debugLineNum = 89;BA.debugLine="Dim spaceBetweenPanels As Int = 10dip";
Debug.ShouldStop(16777216);
_spacebetweenpanels = rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)));Debug.locals.put("spaceBetweenPanels", _spacebetweenpanels);Debug.locals.put("spaceBetweenPanels", _spacebetweenpanels);
 BA.debugLineNum = 91;BA.debugLine="Dim container As Panel";
Debug.ShouldStop(67108864);
_container = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("container", _container);
 BA.debugLineNum = 92;BA.debugLine="container.Initialize(\"\")";
Debug.ShouldStop(134217728);
_container.runVoidMethod ("Initialize",rewind.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 93;BA.debugLine="container.Color = Colors.Transparent";
Debug.ShouldStop(268435456);
_container.runVoidMethod ("setColor",rewind.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 94;BA.debugLine="act.AddView(container, leftMargin, m_Funciones.Ge";
Debug.ShouldStop(536870912);
_act.runVoidMethod ("AddView",(Object)((_container.getObject())),(Object)(_leftmargin),(Object)(RemoteObject.solve(new RemoteObject[] {rewind.mostCurrent._m_funciones.runMethod(true,"_getstatusbarheight" /*RemoteObject*/ ,rewind.mostCurrent.activityBA),rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 30)))}, "+",1, 1)),(Object)(RemoteObject.solve(new RemoteObject[] {_act.runMethod(true,"getWidth"),RemoteObject.createImmutable(2),_leftmargin}, "-*",1, 1)),(Object)(_panelheight));
 BA.debugLineNum = 96;BA.debugLine="Dim containerWidth As Int = act.Width - 2 * leftM";
Debug.ShouldStop(-2147483648);
_containerwidth = RemoteObject.solve(new RemoteObject[] {_act.runMethod(true,"getWidth"),RemoteObject.createImmutable(2),_leftmargin}, "-*",1, 1);Debug.locals.put("containerWidth", _containerwidth);Debug.locals.put("containerWidth", _containerwidth);
 BA.debugLineNum = 97;BA.debugLine="Dim totalSpace As Int = (numPanels - 1) * spaceBe";
Debug.ShouldStop(1);
_totalspace = RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_numpanels,RemoteObject.createImmutable(1)}, "-",1, 1)),_spacebetweenpanels}, "*",0, 1);Debug.locals.put("totalSpace", _totalspace);Debug.locals.put("totalSpace", _totalspace);
 BA.debugLineNum = 98;BA.debugLine="Dim panelWidth As Int = (containerWidth - totalSp";
Debug.ShouldStop(2);
_panelwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_containerwidth,_totalspace}, "-",1, 1)),_numpanels}, "/",0, 0));Debug.locals.put("panelWidth", _panelwidth);Debug.locals.put("panelWidth", _panelwidth);
 BA.debugLineNum = 100;BA.debugLine="panels.Initialize";
Debug.ShouldStop(8);
rewind.mostCurrent._panels.runVoidMethod ("Initialize");
 BA.debugLineNum = 102;BA.debugLine="For i = 0 To numPanels - 1";
Debug.ShouldStop(32);
{
final int step13 = 1;
final int limit13 = RemoteObject.solve(new RemoteObject[] {_numpanels,RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step13 > 0 && _i <= limit13) || (step13 < 0 && _i >= limit13) ;_i = ((int)(0 + _i + step13))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 103;BA.debugLine="Dim panel As Panel";
Debug.ShouldStop(64);
_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");Debug.locals.put("panel", _panel);
 BA.debugLineNum = 104;BA.debugLine="panel.Initialize(\"\")";
Debug.ShouldStop(128);
_panel.runVoidMethod ("Initialize",rewind.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 105;BA.debugLine="panel.Color = Colors.ARGB(200, 255, 255, 180)";
Debug.ShouldStop(256);
_panel.runVoidMethod ("setColor",rewind.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 200)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 106;BA.debugLine="container.AddView(panel, (panelWidth + spaceBetw";
Debug.ShouldStop(512);
_container.runVoidMethod ("AddView",(Object)((_panel.getObject())),(Object)(RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_panelwidth,_spacebetweenpanels}, "+",1, 1)),RemoteObject.createImmutable(_i)}, "*",0, 1)),(Object)(BA.numberCast(int.class, 0)),(Object)(_panelwidth),(Object)(_panelheight));
 BA.debugLineNum = 107;BA.debugLine="panels.Add(panel)";
Debug.ShouldStop(1024);
rewind.mostCurrent._panels.runVoidMethod ("Add",(Object)((_panel.getObject())));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 110;BA.debugLine="FillPanelsProgressively";
Debug.ShouldStop(8192);
_fillpanelsprogressively();
 BA.debugLineNum = 111;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _fillcurrentbarandadvance() throws Exception{
try {
		Debug.PushSubsStack("FillCurrentBarAndAdvance (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,177);
if (RapidSub.canDelegate("fillcurrentbarandadvance")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","fillcurrentbarandadvance");}
RemoteObject _panel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _canvas = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
RemoteObject _rect = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");
 BA.debugLineNum = 177;BA.debugLine="Sub FillCurrentBarAndAdvance";
Debug.ShouldStop(65536);
 BA.debugLineNum = 178;BA.debugLine="changeScreen(currentPanelIndex + 1)";
Debug.ShouldStop(131072);
_changescreen(RemoteObject.solve(new RemoteObject[] {rewind._currentpanelindex,RemoteObject.createImmutable(1)}, "+",1, 1));
 BA.debugLineNum = 180;BA.debugLine="If currentPanelIndex >= panels.Size Then Return";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("g",rewind._currentpanelindex,BA.numberCast(double.class, rewind.mostCurrent._panels.runMethod(true,"getSize")))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 182;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
Debug.ShouldStop(2097152);
_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_panel = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), rewind.mostCurrent._panels.runMethod(false,"Get",(Object)(rewind._currentpanelindex)));Debug.locals.put("panel", _panel);Debug.locals.put("panel", _panel);
 BA.debugLineNum = 183;BA.debugLine="Dim canvas As Canvas";
Debug.ShouldStop(4194304);
_canvas = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper");Debug.locals.put("canvas", _canvas);
 BA.debugLineNum = 184;BA.debugLine="canvas.Initialize(panel)";
Debug.ShouldStop(8388608);
_canvas.runVoidMethod ("Initialize",(Object)((_panel.getObject())));
 BA.debugLineNum = 185;BA.debugLine="Dim rect As Rect";
Debug.ShouldStop(16777216);
_rect = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");Debug.locals.put("rect", _rect);
 BA.debugLineNum = 186;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
Debug.ShouldStop(33554432);
_rect.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_panel.runMethod(true,"getWidth")),(Object)(_panel.runMethod(true,"getHeight")));
 BA.debugLineNum = 187;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
Debug.ShouldStop(67108864);
_canvas.runVoidMethod ("DrawRect",(Object)((_rect.getObject())),(Object)(rewind.mostCurrent.__c.getField(false,"Colors").getField(true,"White")),(Object)(rewind.mostCurrent.__c.getField(true,"True")),(Object)(BA.numberCast(float.class, 0)));
 BA.debugLineNum = 189;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, pa";
Debug.ShouldStop(268435456);
rewind._currentpanelindex = BA.numberCast(int.class, rewind.mostCurrent.__c.runMethod(true,"Min",(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {rewind._currentpanelindex,RemoteObject.createImmutable(1)}, "+",1, 1))),(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {rewind.mostCurrent._panels.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1)))));
 BA.debugLineNum = 190;BA.debugLine="progress = 0";
Debug.ShouldStop(536870912);
rewind._progress = BA.numberCast(int.class, 0);
 BA.debugLineNum = 191;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _fillpanelsprogressively() throws Exception{
try {
		Debug.PushSubsStack("FillPanelsProgressively (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,113);
if (RapidSub.canDelegate("fillpanelsprogressively")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","fillpanelsprogressively");}
 BA.debugLineNum = 113;BA.debugLine="Sub FillPanelsProgressively";
Debug.ShouldStop(65536);
 BA.debugLineNum = 114;BA.debugLine="currentPanelIndex = 0";
Debug.ShouldStop(131072);
rewind._currentpanelindex = BA.numberCast(int.class, 0);
 BA.debugLineNum = 115;BA.debugLine="progress = 0";
Debug.ShouldStop(262144);
rewind._progress = BA.numberCast(int.class, 0);
 BA.debugLineNum = 117;BA.debugLine="tmr.Initialize(\"FillTimer\", 500)";
Debug.ShouldStop(1048576);
rewind._tmr.runVoidMethod ("Initialize",rewind.processBA,(Object)(BA.ObjectToString("FillTimer")),(Object)(BA.numberCast(long.class, 500)));
 BA.debugLineNum = 118;BA.debugLine="tmr.Enabled = True";
Debug.ShouldStop(2097152);
rewind._tmr.runMethod(true,"setEnabled",rewind.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 119;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _filltimer_tick() throws Exception{
try {
		Debug.PushSubsStack("FillTimer_Tick (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,121);
if (RapidSub.canDelegate("filltimer_tick")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","filltimer_tick");}
RemoteObject _panel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _panelwidth = RemoteObject.createImmutable(0);
RemoteObject _canvas = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
RemoteObject _rect = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");
 BA.debugLineNum = 121;BA.debugLine="Sub FillTimer_Tick";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 122;BA.debugLine="If currentPanelIndex >= panels.Size Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean("g",rewind._currentpanelindex,BA.numberCast(double.class, rewind.mostCurrent._panels.runMethod(true,"getSize")))) { 
 BA.debugLineNum = 123;BA.debugLine="tmr.Enabled = False";
Debug.ShouldStop(67108864);
rewind._tmr.runMethod(true,"setEnabled",rewind.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 124;BA.debugLine="Return";
Debug.ShouldStop(134217728);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 127;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
Debug.ShouldStop(1073741824);
_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_panel = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), rewind.mostCurrent._panels.runMethod(false,"Get",(Object)(rewind._currentpanelindex)));Debug.locals.put("panel", _panel);Debug.locals.put("panel", _panel);
 BA.debugLineNum = 128;BA.debugLine="Dim panelWidth As Int = panel.Width";
Debug.ShouldStop(-2147483648);
_panelwidth = _panel.runMethod(true,"getWidth");Debug.locals.put("panelWidth", _panelwidth);Debug.locals.put("panelWidth", _panelwidth);
 BA.debugLineNum = 130;BA.debugLine="Dim canvas As Canvas";
Debug.ShouldStop(2);
_canvas = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper");Debug.locals.put("canvas", _canvas);
 BA.debugLineNum = 131;BA.debugLine="canvas.Initialize(panel)";
Debug.ShouldStop(4);
_canvas.runVoidMethod ("Initialize",(Object)((_panel.getObject())));
 BA.debugLineNum = 132;BA.debugLine="Dim rect As Rect";
Debug.ShouldStop(8);
_rect = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");Debug.locals.put("rect", _rect);
 BA.debugLineNum = 133;BA.debugLine="rect.Initialize(0, 0, progress, panel.Height)";
Debug.ShouldStop(16);
_rect.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(rewind._progress),(Object)(_panel.runMethod(true,"getHeight")));
 BA.debugLineNum = 134;BA.debugLine="canvas.DrawRect(rect, Colors.White, True, 0)";
Debug.ShouldStop(32);
_canvas.runVoidMethod ("DrawRect",(Object)((_rect.getObject())),(Object)(rewind.mostCurrent.__c.getField(false,"Colors").getField(true,"White")),(Object)(rewind.mostCurrent.__c.getField(true,"True")),(Object)(BA.numberCast(float.class, 0)));
 BA.debugLineNum = 136;BA.debugLine="progress = progress + 5dip";
Debug.ShouldStop(128);
rewind._progress = RemoteObject.solve(new RemoteObject[] {rewind._progress,rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 5)))}, "+",1, 1);
 BA.debugLineNum = 138;BA.debugLine="If progress >= panelWidth Then";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("g",rewind._progress,BA.numberCast(double.class, _panelwidth))) { 
 BA.debugLineNum = 139;BA.debugLine="currentPanelIndex = Min(currentPanelIndex + 1, p";
Debug.ShouldStop(1024);
rewind._currentpanelindex = BA.numberCast(int.class, rewind.mostCurrent.__c.runMethod(true,"Min",(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {rewind._currentpanelindex,RemoteObject.createImmutable(1)}, "+",1, 1))),(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {rewind.mostCurrent._panels.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1)))));
 BA.debugLineNum = 140;BA.debugLine="changeScreen(currentPanelIndex)";
Debug.ShouldStop(2048);
_changescreen(rewind._currentpanelindex);
 BA.debugLineNum = 141;BA.debugLine="progress = 0";
Debug.ShouldStop(4096);
rewind._progress = BA.numberCast(int.class, 0);
 };
 BA.debugLineNum = 143;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 13;BA.debugLine="Private pnlBackground As Panel";
rewind.mostCurrent._pnlbackground = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private pnlContent As Panel";
rewind.mostCurrent._pnlcontent = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Dim currentPanelIndex As Int = 0";
rewind._currentpanelindex = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 18;BA.debugLine="Dim progress As Int";
rewind._progress = RemoteObject.createImmutable(0);
 //BA.debugLineNum = 19;BA.debugLine="Dim panels As List";
rewind.mostCurrent._panels = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 20;BA.debugLine="Dim startX As Float";
rewind._startx = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 21;BA.debugLine="Dim originalX As Float";
rewind._originalx = RemoteObject.createImmutable(0f);
 //BA.debugLineNum = 23;BA.debugLine="Private slide_1 As ImageView 'IMAGEN PRINCIPAL'";
rewind.mostCurrent._slide_1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _pnlcontent_touch(RemoteObject _action,RemoteObject _x,RemoteObject _y) throws Exception{
try {
		Debug.PushSubsStack("pnlContent_Touch (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,145);
if (RapidSub.canDelegate("pnlcontent_touch")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","pnlcontent_touch", _action, _x, _y);}
RemoteObject _deltax = RemoteObject.createImmutable(0f);
RemoteObject _panelwidth = RemoteObject.createImmutable(0);
Debug.locals.put("Action", _action);
Debug.locals.put("X", _x);
Debug.locals.put("Y", _y);
 BA.debugLineNum = 145;BA.debugLine="Sub pnlContent_Touch(Action As Int, X As Float, Y";
Debug.ShouldStop(65536);
 BA.debugLineNum = 146;BA.debugLine="Select Case Action";
Debug.ShouldStop(131072);
switch (BA.switchObjectToInt(_action,rewind.mostCurrent._activity.getField(true,"ACTION_DOWN"),rewind.mostCurrent._activity.getField(true,"ACTION_MOVE"),rewind.mostCurrent._activity.getField(true,"ACTION_UP"))) {
case 0: {
 BA.debugLineNum = 148;BA.debugLine="startX = X";
Debug.ShouldStop(524288);
rewind._startx = _x;
 BA.debugLineNum = 149;BA.debugLine="originalX = pnlContent.Left";
Debug.ShouldStop(1048576);
rewind._originalx = BA.numberCast(float.class, rewind.mostCurrent._pnlcontent.runMethod(true,"getLeft"));
 break; }
case 1: {
 BA.debugLineNum = 151;BA.debugLine="Dim deltaX As Float = X - startX";
Debug.ShouldStop(4194304);
_deltax = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {_x,rewind._startx}, "-",1, 0));Debug.locals.put("deltaX", _deltax);Debug.locals.put("deltaX", _deltax);
 BA.debugLineNum = 152;BA.debugLine="pnlContent.Left = Max(Min(originalX + deltaX, o";
Debug.ShouldStop(8388608);
rewind.mostCurrent._pnlcontent.runMethod(true,"setLeft",BA.numberCast(int.class, rewind.mostCurrent.__c.runMethod(true,"Max",(Object)(rewind.mostCurrent.__c.runMethod(true,"Min",(Object)(RemoteObject.solve(new RemoteObject[] {rewind._originalx,_deltax}, "+",1, 0)),(Object)(RemoteObject.solve(new RemoteObject[] {rewind._originalx,rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))}, "+",1, 0)))),(Object)(RemoteObject.solve(new RemoteObject[] {rewind._originalx,rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 100)))}, "-",1, 0)))));
 break; }
case 2: {
 BA.debugLineNum = 154;BA.debugLine="Dim deltaX As Float = X - startX";
Debug.ShouldStop(33554432);
_deltax = BA.numberCast(float.class, RemoteObject.solve(new RemoteObject[] {_x,rewind._startx}, "-",1, 0));Debug.locals.put("deltaX", _deltax);Debug.locals.put("deltaX", _deltax);
 BA.debugLineNum = 156;BA.debugLine="If Abs(deltaX) > 50dip Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean(">",rewind.mostCurrent.__c.runMethod(true,"Abs",(Object)(BA.numberCast(double.class, _deltax))),BA.numberCast(double.class, rewind.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))))) { 
 BA.debugLineNum = 157;BA.debugLine="If deltaX < 0 Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("<",_deltax,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 158;BA.debugLine="FillCurrentBarAndAdvance";
Debug.ShouldStop(536870912);
_fillcurrentbarandadvance();
 }else 
{ BA.debugLineNum = 159;BA.debugLine="Else If deltaX > 0 Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(">",_deltax,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 160;BA.debugLine="ResetCurrentBarAndGoBack";
Debug.ShouldStop(-2147483648);
_resetcurrentbarandgoback();
 }}
;
 }else {
 BA.debugLineNum = 163;BA.debugLine="Dim panelWidth As Int = pnlContent.Width / 2";
Debug.ShouldStop(4);
_panelwidth = BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {rewind.mostCurrent._pnlcontent.runMethod(true,"getWidth"),RemoteObject.createImmutable(2)}, "/",0, 0));Debug.locals.put("panelWidth", _panelwidth);Debug.locals.put("panelWidth", _panelwidth);
 BA.debugLineNum = 164;BA.debugLine="If X < panelWidth Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("<",_x,BA.numberCast(double.class, _panelwidth))) { 
 BA.debugLineNum = 165;BA.debugLine="ResetCurrentBarAndGoBack";
Debug.ShouldStop(16);
_resetcurrentbarandgoback();
 }else {
 BA.debugLineNum = 167;BA.debugLine="FillCurrentBarAndAdvance";
Debug.ShouldStop(64);
_fillcurrentbarandadvance();
 };
 };
 BA.debugLineNum = 172;BA.debugLine="pnlContent.SetLayoutAnimated(300, originalX, pn";
Debug.ShouldStop(2048);
rewind.mostCurrent._pnlcontent.runVoidMethod ("SetLayoutAnimated",(Object)(BA.numberCast(int.class, 300)),(Object)(BA.numberCast(int.class, rewind._originalx)),(Object)(rewind.mostCurrent._pnlcontent.runMethod(true,"getTop")),(Object)(rewind.mostCurrent._pnlcontent.runMethod(true,"getWidth")),(Object)(rewind.mostCurrent._pnlcontent.runMethod(true,"getHeight")));
 break; }
}
;
 BA.debugLineNum = 174;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private xui As XUI";
rewind._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 8;BA.debugLine="Private tmr As Timer";
rewind._tmr = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _resetcurrentbarandgoback() throws Exception{
try {
		Debug.PushSubsStack("ResetCurrentBarAndGoBack (rewind) ","rewind",2,rewind.mostCurrent.activityBA,rewind.mostCurrent,193);
if (RapidSub.canDelegate("resetcurrentbarandgoback")) { return b4a.example.rewind.remoteMe.runUserSub(false, "rewind","resetcurrentbarandgoback");}
RemoteObject _panel = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
RemoteObject _canvas = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper");
RemoteObject _rect = RemoteObject.declareNull("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");
 BA.debugLineNum = 193;BA.debugLine="Sub ResetCurrentBarAndGoBack";
Debug.ShouldStop(1);
 BA.debugLineNum = 194;BA.debugLine="changeScreen(currentPanelIndex - 1)";
Debug.ShouldStop(2);
_changescreen(RemoteObject.solve(new RemoteObject[] {rewind._currentpanelindex,RemoteObject.createImmutable(1)}, "-",1, 1));
 BA.debugLineNum = 196;BA.debugLine="If currentPanelIndex <= 0 Then Return";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("k",rewind._currentpanelindex,BA.numberCast(double.class, 0))) { 
if (true) return RemoteObject.createImmutable("");};
 BA.debugLineNum = 198;BA.debugLine="Dim panel As Panel = panels.Get(currentPanelIndex";
Debug.ShouldStop(32);
_panel = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
_panel = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), rewind.mostCurrent._panels.runMethod(false,"Get",(Object)(rewind._currentpanelindex)));Debug.locals.put("panel", _panel);Debug.locals.put("panel", _panel);
 BA.debugLineNum = 199;BA.debugLine="Dim canvas As Canvas";
Debug.ShouldStop(64);
_canvas = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper");Debug.locals.put("canvas", _canvas);
 BA.debugLineNum = 200;BA.debugLine="canvas.Initialize(panel)";
Debug.ShouldStop(128);
_canvas.runVoidMethod ("Initialize",(Object)((_panel.getObject())));
 BA.debugLineNum = 201;BA.debugLine="Dim rect As Rect";
Debug.ShouldStop(256);
_rect = RemoteObject.createNew ("anywheresoftware.b4a.objects.drawable.CanvasWrapper.RectWrapper");Debug.locals.put("rect", _rect);
 BA.debugLineNum = 202;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
Debug.ShouldStop(512);
_rect.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_panel.runMethod(true,"getWidth")),(Object)(_panel.runMethod(true,"getHeight")));
 BA.debugLineNum = 203;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
Debug.ShouldStop(1024);
_canvas.runVoidMethod ("DrawRect",(Object)((_rect.getObject())),(Object)(rewind.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 200)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 180)))),(Object)(rewind.mostCurrent.__c.getField(true,"True")),(Object)(BA.numberCast(float.class, 0)));
 BA.debugLineNum = 205;BA.debugLine="currentPanelIndex = Max(currentPanelIndex - 1, 0)";
Debug.ShouldStop(4096);
rewind._currentpanelindex = BA.numberCast(int.class, rewind.mostCurrent.__c.runMethod(true,"Max",(Object)(BA.numberCast(double.class, RemoteObject.solve(new RemoteObject[] {rewind._currentpanelindex,RemoteObject.createImmutable(1)}, "-",1, 1))),(Object)(BA.numberCast(double.class, 0))));
 BA.debugLineNum = 207;BA.debugLine="panel = panels.Get(currentPanelIndex)";
Debug.ShouldStop(16384);
_panel = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), rewind.mostCurrent._panels.runMethod(false,"Get",(Object)(rewind._currentpanelindex)));Debug.locals.put("panel", _panel);
 BA.debugLineNum = 208;BA.debugLine="canvas.Initialize(panel)";
Debug.ShouldStop(32768);
_canvas.runVoidMethod ("Initialize",(Object)((_panel.getObject())));
 BA.debugLineNum = 209;BA.debugLine="rect.Initialize(0, 0, panel.Width, panel.Height)";
Debug.ShouldStop(65536);
_rect.runVoidMethod ("Initialize",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(_panel.runMethod(true,"getWidth")),(Object)(_panel.runMethod(true,"getHeight")));
 BA.debugLineNum = 210;BA.debugLine="canvas.DrawRect(rect, Colors.ARGB(200, 255, 255,";
Debug.ShouldStop(131072);
_canvas.runVoidMethod ("DrawRect",(Object)((_rect.getObject())),(Object)(rewind.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 200)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 180)))),(Object)(rewind.mostCurrent.__c.getField(true,"True")),(Object)(BA.numberCast(float.class, 0)));
 BA.debugLineNum = 212;BA.debugLine="progress = 0";
Debug.ShouldStop(524288);
rewind._progress = BA.numberCast(int.class, 0);
 BA.debugLineNum = 213;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}