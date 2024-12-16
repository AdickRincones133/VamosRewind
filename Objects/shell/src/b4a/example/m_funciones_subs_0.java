package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class m_funciones_subs_0 {


public static RemoteObject  _adjusttextsize(RemoteObject _ba,RemoteObject _v,RemoteObject _fscale) throws Exception{
try {
		Debug.PushSubsStack("AdjustTextSize (m_funciones) ","m_funciones",3,_ba,m_funciones.mostCurrent,56);
if (RapidSub.canDelegate("adjusttextsize")) { return b4a.example.m_funciones.remoteMe.runUserSub(false, "m_funciones","adjusttextsize", _ba, _v, _fscale);}
RemoteObject _lbl = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
RemoteObject _btn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
;
Debug.locals.put("v", _v);
Debug.locals.put("fscale", _fscale);
 BA.debugLineNum = 56;BA.debugLine="Private Sub AdjustTextSize(v As View, fscale As Do";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 57;BA.debugLine="If v Is Label Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("i",_v.getObjectOrNull(), RemoteObject.createImmutable("android.widget.TextView"))) { 
 BA.debugLineNum = 58;BA.debugLine="Dim lbl As Label = v";
Debug.ShouldStop(33554432);
_lbl = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
_lbl = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.LabelWrapper"), _v.getObject());Debug.locals.put("lbl", _lbl);Debug.locals.put("lbl", _lbl);
 BA.debugLineNum = 59;BA.debugLine="lbl.TextSize = Max(10, (lbl.TextSize / fscale) -";
Debug.ShouldStop(67108864);
_lbl.runMethod(true,"setTextSize",BA.numberCast(float.class, m_funciones.mostCurrent.__c.runMethod(true,"Max",(Object)(BA.numberCast(double.class, 10)),(Object)(RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_lbl.runMethod(true,"getTextSize"),_fscale}, "/",0, 0)),RemoteObject.createImmutable(2)}, "-",1, 0)))));
 }else 
{ BA.debugLineNum = 60;BA.debugLine="Else If v Is Button Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("i",_v.getObjectOrNull(), RemoteObject.createImmutable("android.widget.Button"))) { 
 BA.debugLineNum = 61;BA.debugLine="Dim btn As Button = v";
Debug.ShouldStop(268435456);
_btn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
_btn = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), _v.getObject());Debug.locals.put("btn", _btn);Debug.locals.put("btn", _btn);
 BA.debugLineNum = 62;BA.debugLine="btn.TextSize = Max(10, btn.TextSize / fscale) '";
Debug.ShouldStop(536870912);
_btn.runMethod(true,"setTextSize",BA.numberCast(float.class, m_funciones.mostCurrent.__c.runMethod(true,"Max",(Object)(BA.numberCast(double.class, 10)),(Object)(RemoteObject.solve(new RemoteObject[] {_btn.runMethod(true,"getTextSize"),_fscale}, "/",0, 0)))));
 }}
;
 BA.debugLineNum = 64;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _autoescalar(RemoteObject _ba,RemoteObject _pantalla) throws Exception{
try {
		Debug.PushSubsStack("AutoEscalar (m_funciones) ","m_funciones",3,_ba,m_funciones.mostCurrent,46);
if (RapidSub.canDelegate("autoescalar")) { return b4a.example.m_funciones.remoteMe.runUserSub(false, "m_funciones","autoescalar", _ba, _pantalla);}
RemoteObject _fscale = RemoteObject.createImmutable(0);
RemoteObject _v = RemoteObject.declareNull("anywheresoftware.b4a.objects.ConcreteViewWrapper");
;
Debug.locals.put("Pantalla", _pantalla);
 BA.debugLineNum = 46;BA.debugLine="Public Sub AutoEscalar(Pantalla As Activity)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 47;BA.debugLine="Dim fscale As Double = access.GetUserFontScale";
Debug.ShouldStop(16384);
_fscale = BA.numberCast(double.class, m_funciones._access.runMethod(true,"GetUserFontScale"));Debug.locals.put("fscale", _fscale);Debug.locals.put("fscale", _fscale);
 BA.debugLineNum = 48;BA.debugLine="If fscale > 1 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(">",_fscale,BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 49;BA.debugLine="For Each v As View In Pantalla.GetAllViewsRecurs";
Debug.ShouldStop(65536);
_v = RemoteObject.createNew ("anywheresoftware.b4a.objects.ConcreteViewWrapper");
{
final RemoteObject group3 = _pantalla.runMethod(false,"GetAllViewsRecursive");
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_v = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), group3.runMethod(false,"Get",index3));Debug.locals.put("v", _v);
Debug.locals.put("v", _v);
 BA.debugLineNum = 50;BA.debugLine="AdjustTextSize(v, fscale)";
Debug.ShouldStop(131072);
_adjusttextsize(_ba,_v,_fscale);
 }
}Debug.locals.put("v", _v);
;
 };
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _fullscreenactivity(RemoteObject _ba,RemoteObject _act) throws Exception{
try {
		Debug.PushSubsStack("FullscreenActivity (m_funciones) ","m_funciones",3,_ba,m_funciones.mostCurrent,9);
if (RapidSub.canDelegate("fullscreenactivity")) { return b4a.example.m_funciones.remoteMe.runUserSub(false, "m_funciones","fullscreenactivity", _ba, _act);}
RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.phone.Phone");
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _window = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _bodyheight = RemoteObject.createImmutable(0);
;
Debug.locals.put("act", _act);
 BA.debugLineNum = 9;BA.debugLine="Sub FullscreenActivity (act As Activity)";
Debug.ShouldStop(256);
 BA.debugLineNum = 10;BA.debugLine="Dim p As Phone";
Debug.ShouldStop(512);
_p = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone");Debug.locals.put("p", _p);
 BA.debugLineNum = 11;BA.debugLine="If p.SdkVersion >= 4.4 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("g",_p.runMethod(true,"getSdkVersion"),BA.numberCast(double.class, 4.4))) { 
 BA.debugLineNum = 12;BA.debugLine="Dim jo As JavaObject";
Debug.ShouldStop(2048);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("jo", _jo);
 BA.debugLineNum = 13;BA.debugLine="Dim window As JavaObject = jo.InitializeContext.";
Debug.ShouldStop(4096);
_window = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_window = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), _jo.runMethod(false,"InitializeContext",BA.rdebugUtils.runMethod(false, "processBAFromBA", _ba)).runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getWindow")),(Object)((m_funciones.mostCurrent.__c.getField(false,"Null")))));Debug.locals.put("window", _window);Debug.locals.put("window", _window);
 BA.debugLineNum = 14;BA.debugLine="window.RunMethod(\"addFlags\", Array(Bit.Or(0x0000";
Debug.ShouldStop(8192);
_window.runVoidMethod ("RunMethod",(Object)(BA.ObjectToString("addFlags")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(m_funciones.mostCurrent.__c.getField(false,"Bit").runMethod(true,"Or",(Object)(BA.numberCast(int.class, ((int)0x00000200))),(Object)(BA.numberCast(int.class, ((int)0x08000000)))))})));
 BA.debugLineNum = 17;BA.debugLine="Dim bodyHeight As Int = GetStatusBarHeight + Get";
Debug.ShouldStop(65536);
_bodyheight = RemoteObject.solve(new RemoteObject[] {_getstatusbarheight(_ba),_getnavigationbarheight(_ba)}, "+",1, 1);Debug.locals.put("bodyHeight", _bodyheight);Debug.locals.put("bodyHeight", _bodyheight);
 BA.debugLineNum = 18;BA.debugLine="act.Height = act.Height + bodyHeight";
Debug.ShouldStop(131072);
_act.runMethod(true,"setHeight",RemoteObject.solve(new RemoteObject[] {_act.runMethod(true,"getHeight"),_bodyheight}, "+",1, 1));
 };
 BA.debugLineNum = 20;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getdimension(RemoteObject _ba,RemoteObject _resourcename) throws Exception{
try {
		Debug.PushSubsStack("GetDimension (m_funciones) ","m_funciones",3,_ba,m_funciones.mostCurrent,33);
if (RapidSub.canDelegate("getdimension")) { return b4a.example.m_funciones.remoteMe.runUserSub(false, "m_funciones","getdimension", _ba, _resourcename);}
RemoteObject _context = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _res = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _resourceid = RemoteObject.createImmutable(0);
;
Debug.locals.put("resourceName", _resourcename);
 BA.debugLineNum = 33;BA.debugLine="Private Sub GetDimension(resourceName As String) A";
Debug.ShouldStop(1);
 BA.debugLineNum = 34;BA.debugLine="Dim context As JavaObject";
Debug.ShouldStop(2);
_context = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("context", _context);
 BA.debugLineNum = 35;BA.debugLine="context.InitializeContext";
Debug.ShouldStop(4);
_context.runVoidMethod ("InitializeContext",BA.rdebugUtils.runMethod(false, "processBAFromBA", _ba));
 BA.debugLineNum = 36;BA.debugLine="Dim res As JavaObject = context.RunMethod(\"getRes";
Debug.ShouldStop(8);
_res = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_res = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), _context.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getResources")),(Object)((m_funciones.mostCurrent.__c.getField(false,"Null")))));Debug.locals.put("res", _res);Debug.locals.put("res", _res);
 BA.debugLineNum = 37;BA.debugLine="Dim resourceId As Int = res.RunMethod(\"getIdentif";
Debug.ShouldStop(16);
_resourceid = BA.numberCast(int.class, _res.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getIdentifier")),(Object)(RemoteObject.createNewArray("Object",new int[] {3},new Object[] {(_resourcename),RemoteObject.createImmutable(("dimen")),(RemoteObject.createImmutable("android"))}))));Debug.locals.put("resourceId", _resourceid);Debug.locals.put("resourceId", _resourceid);
 BA.debugLineNum = 38;BA.debugLine="If resourceId > 0 Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean(">",_resourceid,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 39;BA.debugLine="Return res.RunMethod(\"getDimensionPixelSize\", Ar";
Debug.ShouldStop(64);
if (true) return BA.numberCast(int.class, _res.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getDimensionPixelSize")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(_resourceid)}))));
 }else {
 BA.debugLineNum = 41;BA.debugLine="Return 0";
Debug.ShouldStop(256);
if (true) return BA.numberCast(int.class, 0);
 };
 BA.debugLineNum = 43;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getnavigationbarheight(RemoteObject _ba) throws Exception{
try {
		Debug.PushSubsStack("GetNavigationBarHeight (m_funciones) ","m_funciones",3,_ba,m_funciones.mostCurrent,28);
if (RapidSub.canDelegate("getnavigationbarheight")) { return b4a.example.m_funciones.remoteMe.runUserSub(false, "m_funciones","getnavigationbarheight", _ba);}
;
 BA.debugLineNum = 28;BA.debugLine="Sub GetNavigationBarHeight As Int";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 29;BA.debugLine="Return GetDimension(\"navigation_bar_height\")";
Debug.ShouldStop(268435456);
if (true) return _getdimension(_ba,RemoteObject.createImmutable("navigation_bar_height"));
 BA.debugLineNum = 30;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getstatusbarheight(RemoteObject _ba) throws Exception{
try {
		Debug.PushSubsStack("GetStatusBarHeight (m_funciones) ","m_funciones",3,_ba,m_funciones.mostCurrent,23);
if (RapidSub.canDelegate("getstatusbarheight")) { return b4a.example.m_funciones.remoteMe.runUserSub(false, "m_funciones","getstatusbarheight", _ba);}
;
 BA.debugLineNum = 23;BA.debugLine="Sub GetStatusBarHeight As Int";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 24;BA.debugLine="Return GetDimension(\"status_bar_height\")";
Debug.ShouldStop(8388608);
if (true) return _getdimension(_ba,RemoteObject.createImmutable("status_bar_height"));
 BA.debugLineNum = 25;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable(0);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Private access As Accessiblity";
m_funciones._access = RemoteObject.createNew ("anywheresoftware.b4a.objects.Accessibility.Accessibility2");
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}